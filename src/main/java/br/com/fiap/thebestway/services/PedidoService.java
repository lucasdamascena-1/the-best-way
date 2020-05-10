package br.com.fiap.thebestway.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.thebestway.domain.CarroPedido;
//import br.com.fiap.thebestway.domain.Destino;
import br.com.fiap.thebestway.domain.PagamentoComBoleto;
import br.com.fiap.thebestway.domain.Pedido;
import br.com.fiap.thebestway.domain.enums.EstadoPagamento;
import br.com.fiap.thebestway.repositories.CarroPedidoRepository;
import br.com.fiap.thebestway.repositories.PagamentoRepository;
import br.com.fiap.thebestway.repositories.PedidoRepository;
import br.com.fiap.thebestway.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private CarroPedidoRepository carroPedidoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private CarroService carroService;

	@Autowired
	private DestinoService destinoService;

	@Autowired
	private UsuarioService usuarioService;

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	public Pedido find(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setUsuario(usuarioService.find(obj.getUsuario().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);

		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}

		for (Integer id : obj.getDestinos()) {
			destinoService.find(id);
		}

		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());

		for (CarroPedido ip : obj.getCorrida()) {
			ip.setDesconto(0.0);
			ip.setCustoFixo(0.75);
			ip.getExtras();
			ip.getSubTotal();
			ip.getPreco();

			ip.setPedido(obj);
			ip.setCarro(carroService.find(ip.getCarro().getId()));
		}

		carroPedidoRepository.saveAll(obj.getCorrida());

		return obj;
	}
}
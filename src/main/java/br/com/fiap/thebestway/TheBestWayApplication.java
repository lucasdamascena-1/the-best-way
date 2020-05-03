package br.com.fiap.thebestway;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.fiap.thebestway.domain.Carro;
import br.com.fiap.thebestway.domain.CarroPedido;
import br.com.fiap.thebestway.domain.Cidade;
import br.com.fiap.thebestway.domain.Destino;
import br.com.fiap.thebestway.domain.Estado;
import br.com.fiap.thebestway.domain.Pagamento;
import br.com.fiap.thebestway.domain.PagamentoComBoleto;
import br.com.fiap.thebestway.domain.PagamentoComCartao;
import br.com.fiap.thebestway.domain.Pedido;
import br.com.fiap.thebestway.domain.Usuario;
import br.com.fiap.thebestway.domain.enums.EstadoPagamento;
import br.com.fiap.thebestway.domain.enums.TipoCartao;
import br.com.fiap.thebestway.repositories.CarroPedidoRepository;
import br.com.fiap.thebestway.repositories.CarroRepository;
import br.com.fiap.thebestway.repositories.CidadeRepository;
import br.com.fiap.thebestway.repositories.DestinoRepository;
import br.com.fiap.thebestway.repositories.EstadoRepository;
import br.com.fiap.thebestway.repositories.PagamentoRepository;
import br.com.fiap.thebestway.repositories.PedidoRepository;
import br.com.fiap.thebestway.repositories.UsuarioRepository;

@SpringBootApplication
public class TheBestWayApplication implements CommandLineRunner {

	@Autowired
	private CarroRepository carroRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private DestinoRepository destinoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private CarroPedidoRepository carroPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TheBestWayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/** Tabela Carro **/
		Carro carro1 = new Carro(null, "Acura", "Integra GS 1.8", "ABC-012", 8.5, 20, 1);
		Carro carro2 = new Carro(null, "Cross Lander", "CL-330 2.8 132cv 4x4", "DEF-345", 6.5, 53, 0);
		Carro carro3 = new Carro(null, "Honda", "Accord Coupe EX", "GHI-678", 9.5, 75, 1);

		carroRepository.saveAll(Arrays.asList(carro1, carro2, carro3));

		/** Relacionamento Estado - Cidade **/
		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);

		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		/** Tabela Destino **/
		
		Destino destino1 = new Destino(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cidade1);
		Destino destino2 = new Destino(null, "Avenida Matos", "105", "Apto 101", "Centro", "38777012",
				cidade2);

		
		Destino destino3 = new Destino(null, "Rua Flores 2", "300", "Apto 303", "Jardim", "38220834", cidade1);
		Destino destino4 = new Destino(null, "Avenida Matos 2", "105", "Apto 101", "Centro", "38777012",
				cidade2);

		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		destinoRepository.saveAll(Arrays.asList(destino1, destino2, destino3, destino4));

		/** Tabela Usuario **/
		Usuario usuario = new Usuario(null, "Apollo Creed", "apollocreed@gmail.com", "33925814803", "123", "997364786", 1);
		usuarioRepository.saveAll(Arrays.asList(usuario));
	

		/** Pedidos e suas Associacoes - Pagamento **/
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		// Viagem 1
		Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), usuario);
		pedido1.getDestinos().addAll(Arrays.asList(destino1.getId(), destino2.getId()));

		// Viagem 2
		Pedido pedido2 = new Pedido(null, sdf.parse("01/11/2017 08:47"), usuario);
		pedido2.getDestinos().addAll(Arrays.asList(destino2.getId(), destino1.getId()));
		
		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, "1234567890", 1,
				sdf.parse("30/09/2017 10:32"), TipoCartao.CREDITO);
		pedido1.setPagamento(pagamento1);

		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, "1234567890",
				sdf.parse("30/10/2018 10:32"), sdf.parse("01/11/2018 23:59"), null);
		pedido2.setPagamento(pagamento2);

		usuario.getPedidos().addAll(Arrays.asList(pedido1, pedido2));

		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));

		CarroPedido carroPedido1 = new CarroPedido(pedido1, carro1, 0.75, 0.0, 11.50, 0.0);
		CarroPedido carroPedido2 = new CarroPedido(pedido2, carro2, 0.75, 0.0, 20.50, 0.0);

		pedido1.getCorrida().addAll(Arrays.asList(carroPedido1));
		pedido2.getCorrida().addAll(Arrays.asList(carroPedido2));

		carro1.getCorrida().addAll(Arrays.asList(carroPedido1));
		carro2.getCorrida().addAll(Arrays.asList(carroPedido2));

		carroPedidoRepository.saveAll(Arrays.asList(carroPedido1, carroPedido2));
	}
}

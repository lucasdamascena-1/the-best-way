package br.com.fiap.thebestway;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.fiap.thebestway.domain.Carro;
import br.com.fiap.thebestway.domain.Cidade;
import br.com.fiap.thebestway.domain.Cliente;
import br.com.fiap.thebestway.domain.Corrida;
import br.com.fiap.thebestway.domain.CorridaPedido;
import br.com.fiap.thebestway.domain.Endereco;
import br.com.fiap.thebestway.domain.Estado;
import br.com.fiap.thebestway.domain.Pagamento;
import br.com.fiap.thebestway.domain.PagamentoComBoleto;
import br.com.fiap.thebestway.domain.PagamentoComCartao;
import br.com.fiap.thebestway.domain.Pedido;
import br.com.fiap.thebestway.domain.enums.EstadoPagamento;
import br.com.fiap.thebestway.repositories.CarroRepository;
import br.com.fiap.thebestway.repositories.CidadeRepository;
import br.com.fiap.thebestway.repositories.ClienteRepository;
import br.com.fiap.thebestway.repositories.CorridaPedidoRepository;
import br.com.fiap.thebestway.repositories.CorridaRepository;
import br.com.fiap.thebestway.repositories.EnderecoRepository;
import br.com.fiap.thebestway.repositories.EstadoRepository;
import br.com.fiap.thebestway.repositories.PagamentoRepository;
import br.com.fiap.thebestway.repositories.PedidoRepository;

@SpringBootApplication
public class TheBestWayApplication implements CommandLineRunner {

	@Autowired
	private CarroRepository carroRepository;

	@Autowired
	private CorridaRepository corridaRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private CorridaPedidoRepository corridaPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TheBestWayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Carro carro1 = new Carro(null, "Acura", "Integra GS 1.8", "1992 Gasolina", "ABC-012");
		Carro carro2 = new Carro(null, "Cross Lander", "CL-330 2.8 132cv 4x4", "2006 Diesel", "DEF-345");
		Carro carro3 = new Carro(null, "Honda", "Accord Coupe EX", "1993 Gasolina", "GHI-678");

		Corrida corrida1 = new Corrida(null, 1, 10.0, 0);
		Corrida corrida2 = new Corrida(null, 2, 20.00, 1);
		Corrida corrida3 = new Corrida(null, 3, 0.00, 1);
		Corrida corrida4 = new Corrida(null, 1, 45.00, 0);

		carro1.getCorridas().addAll(Arrays.asList(corrida1, corrida2));
		carro2.getCorridas().addAll(Arrays.asList(corrida3));
		carro3.getCorridas().addAll(Arrays.asList(corrida4));

		corrida1.getCarros().addAll(Arrays.asList(carro1));
		corrida2.getCarros().addAll(Arrays.asList(carro1));
		corrida3.getCarros().addAll(Arrays.asList(carro2));
		corrida4.getCarros().addAll(Arrays.asList(carro3));

		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);

		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

		Cliente cliente1 = new Cliente(null, "Apollo Creed", "apollocreed@gmail.com", "33925814803");
		cliente1.getTelefones().addAll(Arrays.asList("36887711", "997364786"));

		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cliente1,
				cidade1);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Apto 101", "Centro", "38777012", cliente1,
				cidade2);

		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));

		carroRepository.saveAll(Arrays.asList(carro1, carro2, carro3));
		corridaRepository.saveAll(Arrays.asList(corrida1, corrida2, corrida3, corrida4));

		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, sdf.parse("01/11/2017 08:47"), cliente1, endereco2);

		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);

		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2,
				sdf.parse("01/11/2017 08:47"), null);
		pedido2.setPagamento(pagamento2);

		cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));

		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));

		CorridaPedido corridaPedido1 = new CorridaPedido(pedido1, corrida1, 0.0, 1, 10.50);
		CorridaPedido corridaPedido2 = new CorridaPedido(pedido2, corrida2, 2.0, 1, 08.50);

		pedido1.getCorridas().addAll(Arrays.asList(corridaPedido1));
		pedido2.getCorridas().addAll(Arrays.asList(corridaPedido2));

		corrida1.getCorridas().addAll(Arrays.asList(corridaPedido1));
		corrida2.getCorridas().addAll(Arrays.asList(corridaPedido2));

		corridaPedidoRepository.saveAll(Arrays.asList(corridaPedido1, corridaPedido2));
	}
}

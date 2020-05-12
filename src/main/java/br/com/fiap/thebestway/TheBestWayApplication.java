package br.com.fiap.thebestway;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.com.fiap.thebestway.domain.Carro;
import br.com.fiap.thebestway.domain.CarroPedido;
import br.com.fiap.thebestway.domain.Pagamento;
import br.com.fiap.thebestway.domain.PagamentoComBoleto;
import br.com.fiap.thebestway.domain.PagamentoComCartao;
import br.com.fiap.thebestway.domain.Pedido;
import br.com.fiap.thebestway.domain.Usuario;
import br.com.fiap.thebestway.domain.enums.EstadoPagamento;
import br.com.fiap.thebestway.domain.enums.TipoCartao;
import br.com.fiap.thebestway.repositories.CarroPedidoRepository;
import br.com.fiap.thebestway.repositories.CarroRepository;
import br.com.fiap.thebestway.repositories.PagamentoRepository;
import br.com.fiap.thebestway.repositories.PedidoRepository;
import br.com.fiap.thebestway.repositories.UsuarioRepository;

@SpringBootApplication
public class TheBestWayApplication implements CommandLineRunner {

	@Autowired
	private CarroRepository carroRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private CarroPedidoRepository carroPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TheBestWayApplication.class, args);
	}

	@Configuration
	@EnableWebMvc
	public class WebConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE",
					"CONNECT");
		}
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
			registry.addResourceHandler("/webjars/").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
	}

	@Override
	public void run(String... args) throws Exception {

		/** Tabela Carro **/
		Carro carro1 = new Carro(null, "Acura", "Integra GS 1.8", "ABC-012", 8.5, 20, 1);
		Carro carro2 = new Carro(null, "Cross Lander", "CL-330 2.8 132cv 4x4", "DEF-345", 6.5, 53, 0);
		Carro carro3 = new Carro(null, "Honda", "Accord Coupe EX", "GHI-678", 9.5, 75, 1);

		carroRepository.saveAll(Arrays.asList(carro1, carro2, carro3));

		/** Tabela Usuario **/
		Usuario usuario = new Usuario(null, "Apollo Creed", "apollocreed@gmail.com", "33925814803", "123", "997364786",
				1);
		usuarioRepository.saveAll(Arrays.asList(usuario));

		/** Pedidos e suas Associacoes - Pagamento **/
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		// Viagem 1
		Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), usuario, "Endereco 1", "Endereco 2");

		// Viagem 2
		Pedido pedido2 = new Pedido(null, sdf.parse("01/11/2017 08:47"), usuario, "Endereco 2", "Endereco 1");

		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, "1234567890", 1,
				TipoCartao.CREDITO);
		pedido1.setPagamento(pagamento1);

		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2,
				sdf.parse("01/11/2018 23:59"), null);
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

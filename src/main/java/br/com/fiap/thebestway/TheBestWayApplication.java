package br.com.fiap.thebestway;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.fiap.thebestway.domain.Carro;
import br.com.fiap.thebestway.domain.Cidade;
import br.com.fiap.thebestway.domain.Corrida;
import br.com.fiap.thebestway.domain.Estado;
import br.com.fiap.thebestway.repositories.CarroRepository;
import br.com.fiap.thebestway.repositories.CidadeRepository;
import br.com.fiap.thebestway.repositories.CorridaRepository;
import br.com.fiap.thebestway.repositories.EstadoRepository;

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

		carroRepository.saveAll(Arrays.asList(carro1, carro2, carro3));
		corridaRepository.saveAll(Arrays.asList(corrida1, corrida2, corrida3, corrida4));
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
	}
}

package br.com.fiap.thebestway;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.fiap.thebestway.domain.Categoria;
import br.com.fiap.thebestway.repositories.CategoriaRepository;

@SpringBootApplication
public class TheBestWayApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(TheBestWayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria categoria1 = new Categoria(null, "X");
		Categoria categoria2 = new Categoria(null, "VIP");
		Categoria categoria3 = new Categoria(null, "COMFORT");
		Categoria categoria4 = new Categoria(null, "BAG");

		repository.saveAll(Arrays.asList(categoria1, categoria2, categoria3, categoria4));

	}
}

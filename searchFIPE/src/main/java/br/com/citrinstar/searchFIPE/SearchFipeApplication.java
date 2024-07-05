package br.com.citrinstar.searchFIPE;

import br.com.citrinstar.searchFIPE.center.Center;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SearchFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Center.realizarChamadas();
	}
}

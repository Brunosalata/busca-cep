package one.digitalinnovation.ProjetoJavaPadroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


/*
 * Projeto Spring Boot gerado via Spring Initializr
 * Os eguintes módulos foram selecionados:
 * - Spring Data JPA
 * - Spring Web
 * - H2 Database
 * - OpenFeign
 *  * 
 */

@EnableFeignClients
@SpringBootApplication
public class ProjetoJavaPadroesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoJavaPadroesApplication.class, args);
	}

}

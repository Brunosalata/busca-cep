package one.digitalinnovation.ProjetoJavaPadroes.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import one.digitalinnovation.ProjetoJavaPadroes.model.Endereco;

/*
 * Client HTTP, criado via <b>OpenFeign</b>, para o consumo da API do <b>ViaCEP</b>
 * 
 * @see <a href="httpsd://spring.io/project/spring-cloud-openfeign">Spring Cloud OpenFeign</a>
 * @see <a href="httpsd://viacep.com.br">ViaCEP</a>
 */

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {
	
	@GetMapping("/{cep}/json/")
	Endereco consultarCep(@PathVariable("cep") String cep);

}

package one.digitalinnovation.ProjetoJavaPadroes.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.ProjetoJavaPadroes.model.Cliente;
import one.digitalinnovation.ProjetoJavaPadroes.model.ClienteRepository;
import one.digitalinnovation.ProjetoJavaPadroes.model.Endereco;
import one.digitalinnovation.ProjetoJavaPadroes.model.EnderecoRepository;
import one.digitalinnovation.ProjetoJavaPadroes.service.ClienteService;
import one.digitalinnovation.ProjetoJavaPadroes.service.ViaCepService;

/*
 * Implementação de <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe
 * é um {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 */

@Service
public class ClienteServiceImpl implements ClienteService {

	// Singleton: Injetar os componentes do Spring com @Autowired
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;
	// Strategy: Implementar os métodos definidos na interface
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples
	
	@Override
	public Iterable<Cliente> buscaTodos() {
		return clienteRepository.findAll();
	}
	
	@Override
	public Cliente buscaPorId(Long id) {
		// FIXME Buscar todos os Clientes
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
	}
		
	@Override
	public void inserir(Cliente cliente) {
		salvarClienteComCep(cliente);
	}

	private void salvarClienteComCep(Cliente cliente) {
		// FIXME Verificar se o Endereco do Cliente jpa existe (pelo CEP)
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
		// FIXME Caso não exista, integrar com o ViaCEP e persistir o retorno
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		cliente.setEndereco(endereco);
		// FIXME Inserir Cliente, vinculando o Endereco (novo ou existente)
		clienteRepository.save(cliente);
	}
	
	@Override
	public void atualizar(Long id, Cliente cliente) {
		// FIXME Buscar Cliente por ID, caso exista:
		Optional<Cliente> clienteBD = clienteRepository.findById(id);
		if (clienteBD.isPresent()) {
			// FIXME Verificar se o Endereco do Cliente jpa existe (pelo CEP)
			// FIXME Caso não exista, integrar com o ViaCEP e persistir o retorno
			// FIXME Alterar Cliente, vinculando o Endereco (novo ou existente)	
			salvarClienteComCep(cliente); 		//método extraido do processo private acima
		}
		
	}
	
	@Override
	public void deletar(Long id) {
		// FIXME Deletar Cliente por ID
		clienteRepository.deleteById(id);;
	}
}

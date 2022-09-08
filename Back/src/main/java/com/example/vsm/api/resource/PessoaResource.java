package com.example.vsm.api.resource;


import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.vsm.api.model.Pessoa;
import com.example.vsm.api.repository.filter.PessoaFilter;
import com.example.vsm.api.service.PessoaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//utilizado para transforma a classe em controlador rest
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pessoas")
public class PessoaResource {

	//injeção de dependencias
	@Autowired
	private PessoaService pessoaService;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		return pessoaService.criar(pessoa, response);
	}
	
	@GetMapping("codigo/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and hasAuthority('SCOPE_read')")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
		return pessoaService.buscarPeloCodigo(codigo);
	}
	
	@GetMapping("/{cpfcnpj}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and hasAuthority('SCOPE_read')")
	public ResponseEntity<Pessoa> buscarPeloCPFCNPJ(@PathVariable String cpfcnpj) {
		return pessoaService.buscarPeloCPFCNPJ(cpfcnpj);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and hasAuthority('SCOPE_write')")
	public void remover(@PathVariable Long codigo) {
		pessoaService.remover(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_PESSOA') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
		return ResponseEntity.ok(pessoaSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_PESSOA') and hasAuthority('SCOPE_write')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
	}
		
    @ApiOperation(value = "Retorna uma lista de pessoas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de pessoa"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and hasAuthority('SCOPE_read')")
	public Page<Pessoa> pesquisar(PessoaFilter pessoaFilter, Pageable pageable){
    	return pessoaService.pesquisar(pessoaFilter, pageable);  
	}
    
    /*
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA')")
	public Page<Pessoa> pesquisar(@RequestParam(required = false, defaultValue = "") String nome, Pageable pageable) {
		return pessoaRepository.findByNomeContaining(nome, pageable);
	}*/
}

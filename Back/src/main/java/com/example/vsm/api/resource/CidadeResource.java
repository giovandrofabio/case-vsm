package com.example.vsm.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.vsm.api.model.Cidade;
import com.example.vsm.api.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeService cidadeService;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CIDADE') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Cidade> criar(@Valid @RequestBody Cidade cidade, HttpServletResponse response) {
		return cidadeService.criar(cidade, response);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CIDADE') and hasAuthority('SCOPE_write')")
	public void remover(@PathVariable Long codigo) {
		this.cidadeService.remover(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_CIDADE') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Cidade> atualizar(@PathVariable Long codigo, @Valid @RequestBody Cidade cidade) {
		return cidadeService.atualiza(codigo, cidade);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CIDADE') and hasAuthority('SCOPE_read')")
	public Page<Cidade> pesquisar(@RequestParam(required = false, defaultValue = "") String nome, Pageable pageable) {
		return cidadeService.pesquisar(nome, pageable);
	}
	
	@GetMapping("/listar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CIDADE') and hasAuthority('SCOPE_read')" )
	public List<Cidade> listar(){
		return cidadeService.listar();
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CIDADE') and hasAuthority('SCOPE_read')")
	public ResponseEntity<Cidade> buscarPeloCodigo(@PathVariable Long codigo) {
		return cidadeService.buscarPeloCodigo(codigo);
	}
	
}

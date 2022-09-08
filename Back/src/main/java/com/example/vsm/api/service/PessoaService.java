package com.example.vsm.api.service;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.vsm.api.event.RecursoCriadoEvent;
import com.example.vsm.api.model.Pessoa;
import com.example.vsm.api.repository.PessoaRepository;
import com.example.vsm.api.repository.filter.PessoaFilter;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    
	@Autowired
	private ApplicationEventPublisher publisher;
    
    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoaSalva = consultarPorId(codigo);
        
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return pessoaRepository.save(pessoaSalva);
    }
    
    public Pessoa consultarPorId(Long codigo) {
        Pessoa pessoaSalva = pessoaRepository.findByCodigo(codigo);
        if (pessoaSalva == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return pessoaSalva;
    }

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = consultarPorId(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}    
	
	public Page<Pessoa> pesquisar(PessoaFilter pessoaFilter, Pageable pageable){
		return pessoaRepository.filtrar(pessoaFilter, pageable);
	}
	
	public void remover(Long codigo) {
		this.pessoaRepository.deleteById(codigo);
	}
	
	public ResponseEntity<Pessoa> buscarPeloCPFCNPJ(String cpfcnpj) {
		Pessoa pessoa = pessoaRepository.findByCpfcnpj(cpfcnpj);
		if(pessoa != null) {
			return ResponseEntity.ok().body(pessoa);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Pessoa> buscarPeloCodigo(Long codigo) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(codigo);	
		return pessoa.isPresent() ? ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<Pessoa> criar(Pessoa pessoa, HttpServletResponse response) {
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//pessoa.setSenha(encoder.encode(pessoa.getSenha())); 
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);		
		
		//Foi criado um evento para adicionar um header location
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
}

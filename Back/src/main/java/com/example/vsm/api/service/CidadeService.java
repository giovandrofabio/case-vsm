package com.example.vsm.api.service;


import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.vsm.api.event.RecursoCriadoEvent;
import com.example.vsm.api.model.Cidade;
import com.example.vsm.api.repository.CidadeRepository;

@Service
public class CidadeService {
	
    @Autowired
    private CidadeRepository cidadeRepository;
    
	@Autowired
	private ApplicationEventPublisher publisher;
    
   
    public Cidade atualizar(Long codigo, Cidade cidade) {
        Cidade cidadeSalva = buscarCidadeExistente(codigo);
        
        if(!cidade.getCodigo().equals(cidadeSalva.getCodigo())) {
        	validarCidade(cidade);
        }
        
        BeanUtils.copyProperties(cidade, cidadeSalva, "codigo");
        return cidadeRepository.save(cidadeSalva);
    }
    
	private void validarCidade(Cidade cidade) {
		Optional<Cidade> cidadeValidada = null;
		if (cidade.getCodigo() != null) {
			cidadeValidada = cidadeRepository.findById(cidade.getCodigo());
		}
	}	
   
    public Cidade consultarPorId(Long codigo) {
        Cidade cidadeSalva = cidadeRepository.findByCodigo(codigo);
        if (cidadeSalva == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return cidadeSalva;
    } 
    
	private Cidade buscarCidadeExistente(Long codigo) {
		return cidadeRepository.findById(codigo).orElseThrow(() -> new IllegalArgumentException());
	}
	
	public ResponseEntity<Cidade> buscarPeloCodigo(Long codigo) {
		Optional<Cidade> cidade = cidadeRepository.findById(codigo); 			
		return cidade.isPresent() ? ResponseEntity.ok(cidade.get()) : ResponseEntity.notFound().build(); 
	}
	
	public List<Cidade> listar(){
		return cidadeRepository.findAll();
	}
	
	public Page<Cidade> pesquisar(String nome, Pageable pageable) {
		return cidadeRepository.findByNomeContaining(nome, pageable);
	}
	
	public ResponseEntity<Cidade> atualiza(Long codigo,Cidade cidade) {
		try {
			Cidade cidadeSalva = atualizar(codigo, cidade);
			return ResponseEntity.ok(cidadeSalva);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public void remover(Long codigo) {
		this.cidadeRepository.deleteById(codigo);
	}
	
	public ResponseEntity<Cidade> criar(Cidade cidade, HttpServletResponse response) {
		Cidade cidadeSalva = cidadeRepository.save(cidade);		
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, cidadeSalva.getCodigo()));
				
		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
	}
}

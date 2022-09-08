package com.example.vsm.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vsm.api.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	public Cidade findByCodigo(Long codigo);
	public Page<Cidade> findByNomeContaining(String nome, Pageable pageable);
}

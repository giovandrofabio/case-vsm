package com.example.vsm.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vsm.api.model.Pessoa;
import com.example.vsm.api.repository.pessoa.PessoaRepositoryQuery;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery {
	
	public Pessoa findByCodigo(Long codigo);
	public Page<Pessoa> findByNomeContaining(String nome, Pageable pageable);
	public Pessoa findByCpfcnpj(String cpfcnpj);
}

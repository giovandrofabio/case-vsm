package com.example.vsm.api.repository.pessoa;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.vsm.api.model.Pessoa;
import com.example.vsm.api.repository.filter.PessoaFilter;

public interface PessoaRepositoryQuery {

	public Page<Pessoa> filtrar(PessoaFilter pessoaFilter, Pageable pageable);
}

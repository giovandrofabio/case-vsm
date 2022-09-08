package com.example.vsm.api.repository.filter;

import com.example.vsm.api.model.TipoPessoa;

public class PessoaFilter {

	private String nome;
	private TipoPessoa tipo;
	private String cpfcnpj;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public TipoPessoa getTipo() {
		return tipo;
	}
	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}
	public String getCpfcnpj() {
		return cpfcnpj;
	}
	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}
	
	
}

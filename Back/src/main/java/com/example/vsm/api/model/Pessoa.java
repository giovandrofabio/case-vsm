package com.example.vsm.api.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.example.vsm.api.validation.ClienteGroupSequenceProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="pessoa")
@GroupSequenceProvider(value = ClienteGroupSequenceProvider.class)
public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Código da pessoa")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long codigo;
	
	@ApiModelProperty(value = "Nome da pessoa")
	@NotNull
	private String nome;
	
	@NotNull
    @Enumerated(EnumType.STRING)
	private TipoPessoa tipo;
	
	@NotNull
    @CPF(groups = PessoaFisica.class)
    @CNPJ(groups = PessoaJuridica.class)
	private String cpfcnpj;
	
	@NotNull
	private String senha;
	
	@Embedded
	private Endereco endereco;
	
	private String telefone;
	
	private String celular;
	
	private String email;
	
	@NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_cidade")
	private Cidade cidade;
	
	@NotNull
	private Boolean ativo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}
	
	@JsonIgnore
	@Transient
	public boolean isInativo() {
		return !this.ativo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(codigo, other.codigo);
	}


}

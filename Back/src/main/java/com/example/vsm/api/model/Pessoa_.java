package com.example.vsm.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ {

	public static volatile SingularAttribute<Pessoa, String> senha;
	public static volatile SingularAttribute<Pessoa, Long> codigo;
	public static volatile SingularAttribute<Pessoa, TipoPessoa> tipo;
	public static volatile SingularAttribute<Pessoa, String> telefone;
	public static volatile SingularAttribute<Pessoa, Cidade> cidade;
	public static volatile SingularAttribute<Pessoa, Boolean> ativo;
	public static volatile SingularAttribute<Pessoa, Endereco> endereco;
	public static volatile SingularAttribute<Pessoa, String> celular;
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, String> cpfcnpj;
	public static volatile SingularAttribute<Pessoa, String> email;

	public static final String SENHA = "senha";
	public static final String CODIGO = "codigo";
	public static final String TIPO = "tipo";
	public static final String TELEFONE = "telefone";
	public static final String CIDADE = "cidade";
	public static final String ATIVO = "ativo";
	public static final String ENDERECO = "endereco";
	public static final String CELULAR = "celular";
	public static final String NOME = "nome";
	public static final String CPFCNPJ = "cpfcnpj";
	public static final String EMAIL = "email";

}


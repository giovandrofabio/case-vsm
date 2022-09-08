package com.example.vsm.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cidade.class)
public abstract class Cidade_ {

	public static volatile SingularAttribute<Cidade, String> uf;
	public static volatile SingularAttribute<Cidade, Long> codigo;
	public static volatile SingularAttribute<Cidade, String> nome;

	public static final String UF = "uf";
	public static final String CODIGO = "codigo";
	public static final String NOME = "nome";

}


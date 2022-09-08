package com.example.vsm.api.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.example.vsm.api.model.Pessoa;
import com.example.vsm.api.model.PessoaFisica;
import com.example.vsm.api.model.PessoaJuridica;
import com.example.vsm.api.model.TipoPessoa;

public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<Pessoa> {

	  public List<Class<?>> getValidationGroups(Pessoa entity) {
		    List<Class<?>> groups = new ArrayList<>();
		    
		    /*
		     * Informamos ao HibernateValidator para usar as validações default
		     * definidas na classe Cliente.
		     */
		    groups.add(Pessoa.class);
		    
		    if (entity != null) {
		      /*
		       * Aqui nós implementamos a lógica que determina o grupo de
		       * validação a ser aplicado ao bean.
		       */
		      if (TipoPessoa.PF.equals(entity.getTipo())) {
		        groups.add(PessoaFisica.class);
		      } else if (TipoPessoa.PJ.equals(entity.getTipo())) {
		        groups.add(PessoaJuridica.class);
		      }
		    }
		    
		    return groups;
		  }
}

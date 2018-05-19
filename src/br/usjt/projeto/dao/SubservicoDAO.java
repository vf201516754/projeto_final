package br.usjt.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import br.usjt.projeto.entity.Subservico;
@Repository
public class SubservicoDAO {

	@PersistenceContext
	EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Subservico> carregaSubservico() {
		return manager.createQuery("select s from Subservico s").getResultList();
	}
	
}

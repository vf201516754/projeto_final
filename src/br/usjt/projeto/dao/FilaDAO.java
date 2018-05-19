package br.usjt.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.projeto.entity.Fila;

@Repository
public class FilaDAO {

	@PersistenceContext
	EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Fila> carregarFilas() {
		return manager.createQuery("select f from Fila f").getResultList();
	}

	public Fila carregar(String fila) {
		Query query = manager.createQuery("select f from Fila f where f.nome = :fila or f.sigla = :fila");
		query.setParameter("fila", fila);
		return (Fila) query.getSingleResult();
	}

}

package br.usjt.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.projeto.entity.Servico;

@Repository
public class ServicoDAO {
	
	@PersistenceContext
	EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Servico> carregaServicos() {
		return manager.createQuery("select s from Servico s").getResultList();
	}
	
	public Servico carregar(String servico) {
		Query query = manager.createQuery("select s from Servico s where s.nome = :servico or s.sigla = :servico");
		query.setParameter("servico", servico);
		return (Servico) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Servico> carregarListaServicos(){
		Query query = manager.createQuery("select * from Servico");
		query.setMaxResults(1);
		return query.getResultList();
	}
	

}

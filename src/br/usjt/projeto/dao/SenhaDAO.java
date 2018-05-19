package br.usjt.projeto.dao;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.projeto.entity.Fila;
import br.usjt.projeto.entity.Senha;
import br.usjt.projeto.entity.Servico;

@Repository
public class SenhaDAO {
	
	@PersistenceContext
	EntityManager manager;
	private Connection conn;
	
	@Autowired
	public SenhaDAO(DataSource dataSource) throws IOException {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	public Senha carregar(Fila fila) {
		Query query =  manager.createQuery("select s from Senha s where id = :fila");
		query.setParameter("fila", fila.getId());
		return (Senha) query.getSingleResult();
	}
	
	public Senha carregarServico(Senha senha) {
		Query query =  manager.createQuery("select s from Senha s where id = :servico");
		query.setParameter("servico", senha.getId());
		return (Senha) query.getSingleResult();
	}
	
	public Senha carregarSenhasDoServico(Servico servico) {
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Senha> getLastSenha(Fila fila) {
		Query query =  manager.createQuery("select s from Senha s inner join s.fila where s.fila.sigla = :sigla");
		query.setParameter("sigla", fila.getSigla());
		return query.getResultList();
	}

	public Senha gerarSenha(Senha novaSenha) {
		manager.persist(novaSenha);
		return novaSenha;
	}
	
	@SuppressWarnings("unchecked")
	public List<Senha> carregarSenhasInativas(int senha) {
		Query query = manager.createQuery("from Senha where id_servico = :senha and isActive = 0");
		query.setParameter("senha", senha);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Senha> carregarUltimaSenha(){
		Query query = manager.createQuery("select s from Senha s ORDER BY id DESC");
		query.setMaxResults(1);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Senha> carregarTodasSenha(){
		Query query = manager.createQuery("select s from Senha s ORDER BY id DESC");
		return query.getResultList();
	}

	public Senha carregarSenha(int senha) {
		Query query = manager.createQuery("select s from Senha s where s.id = :senha");
		query.setParameter("senha",senha);
		return (Senha) query.getSingleResult();
	}

	public void mudaStatusSenha(int senha) {
		System.out.println(senha);
		Query query = manager.createQuery("update Senha set isActive = 1 " + 
					"where id = :senha");
		query.setParameter("senha", senha);
		query.executeUpdate();
	}
	
	public void inserirTempoDeAtendimento(int tempo) {
		Query query = manager.createQuery("update Senha set tempo_atendimento = :tempo");
		query.setParameter("tempo_atendimento", tempo);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Senha> carregarSenhasSubservico(int subservico) {
		Query query = manager.createQuery("select a from Atendimento a inner join a.senha where a.senha.isActive = 1 and id_subservico = :subservico");
		query.setParameter("subservico", subservico);
		return query.getResultList();
	}
	
}

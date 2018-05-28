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

import br.usjt.projeto.entity.Atendimento;
import br.usjt.projeto.entity.Senha;

@Repository
public class AtendimentoDAO {
	
	@PersistenceContext
	EntityManager manager;
	private Connection conn;
	
	@Autowired
	public AtendimentoDAO(DataSource dataSource) throws IOException {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
	
	public Atendimento gerarAtendimento(Atendimento novoAtendimento) {
		manager.persist(novoAtendimento);
		return novoAtendimento;
	}

	@SuppressWarnings("unchecked")
	public List<Atendimento> carregarSenhaPainelAtendimento() {
		Query query = manager.createQuery("select a from Atendimento a inner join a.senha where a.senha.isActive = 1 and a.isActive = 1");
		query.setMaxResults(1);
		return query.getResultList();
	}

	public void finalizaAtendimento(int senha) {
		Query queryFinalizaAtendimento = manager.createQuery("update Atendimento set isActive = 0 where id_senha = :senha ");
		queryFinalizaAtendimento.setParameter("senha", senha);
		queryFinalizaAtendimento.executeUpdate();
		
		Query queryFinalizaSenha = manager.createQuery("update Senha set isActive = 3 where id = :senha");
		queryFinalizaSenha.setParameter("senha", senha);
		queryFinalizaSenha.executeUpdate();
	}
	

	public void encaminharAtendimento(int subservico, int senha) {
		Query query = manager.createQuery("update Senha set id_subservico = :subservico where id = :senha");
		query.setParameter("subservico", subservico);
		query.setParameter("senha", senha);
		query.executeUpdate();
		
		Query query1 = manager.createQuery("update Atendimento set isActive = 3 where id = :senha");
		query1.setParameter("senha", senha);
		query1.executeUpdate();
	}

	public void gerarAtendimentoSubservico(int senha) {
		Query query = manager.createQuery("update Atendimento set isActive = 4 where id_senha = :senha ");
		query.setParameter("senha", senha);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Atendimento> carregarSenhaPainelAutenticacao() {
		Query query = manager.createQuery("select a from Atendimento a inner join a.senha where a.senha.isActive = 1 and a.isActive = 4 and id_subservico = 1");
		query.setMaxResults(1);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Atendimento> carregarSenhaPainelCaixa() {
		Query query = manager.createQuery("select a from Atendimento a inner join a.senha where a.senha.isActive = 1 and id_subservico = 2");
		query.setMaxResults(1);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Atendimento> buscarUltimasSenhas() {
		Query query = manager.createQuery("select a from Atendimento a inner join a.senha where a.senha.isActive = 1");
		query.setMaxResults(6);
		return query.getResultList();
	}
	
}

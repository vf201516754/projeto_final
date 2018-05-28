package br.usjt.projeto.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.projeto.dao.AtendimentoDAO;
import br.usjt.projeto.dao.SenhaDAO;
import br.usjt.projeto.entity.Atendimento;
import br.usjt.projeto.entity.Senha;

@Service
public class AtendimentoService {
	
	@Autowired
	private AtendimentoDAO dao;
	
	@Autowired
	private SenhaDAO senhaDao;
	
	@Autowired
	private SenhaService senhaService;
	
	private Senha senha;

	public Atendimento geraAtendimento(int id) {
		Senha senha = senhaService.carregarSenha(id);
		Atendimento novoAtendimento = new Atendimento();
		novoAtendimento.setSenha(senha);
		novoAtendimento.setIsActive(1);

		return dao.gerarAtendimento(novoAtendimento);
	}

	public List<Atendimento> buscaProximaSenhaAtendimento() {
		return dao.carregarSenhaPainelAtendimento();
	}

	public void finalizaAtendimento(int senha) {
		dao.finalizaAtendimento(senha);
	}
	

	public void encaminharAtendimento(int subservico, int senha) {
		dao.encaminharAtendimento(subservico, senha);
	}

	public void geraAtendimentoSubservico(int senha) {
		dao.gerarAtendimentoSubservico(senha);
	}

	public List<Atendimento> buscaProximaSenhaAutenticacao() {
		return dao.carregarSenhaPainelAutenticacao();
	}

	public List<Atendimento> buscaProximaSenhaCaixa() {
		return dao.carregarSenhaPainelCaixa();
	}

	public List<Atendimento> buscarUltimasSenhas() {
		return dao.buscarUltimasSenhas();
	}
	

	
}

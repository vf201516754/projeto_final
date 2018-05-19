package br.usjt.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.projeto.dao.ServicoDAO;
import br.usjt.projeto.entity.Servico;

@Service
public class ServicoService {
	
	@Autowired
	private ServicoDAO dao;
	
	public List<Servico> carregaServicos(){
		return dao.carregaServicos();
	}

	public Servico carregar(String servico) {
		return dao.carregar(servico);
	}

}

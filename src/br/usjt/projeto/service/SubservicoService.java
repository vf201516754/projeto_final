package br.usjt.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.usjt.projeto.dao.SubservicoDAO;
import br.usjt.projeto.entity.Subservico;

@Service
public class SubservicoService {
	
	@Autowired
	private SubservicoDAO dao;
	
	public List<Subservico> carregaSubservico(){
		return dao.carregaSubservico();
	}

}

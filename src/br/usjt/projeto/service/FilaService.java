package br.usjt.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.projeto.dao.FilaDAO;
import br.usjt.projeto.entity.Fila;

@Service
public class FilaService {
	
	@Autowired
	private FilaDAO dao;
	
	public List<Fila> carregarFilas(){
		return dao.carregarFilas();
	}

	public Fila carregar(String fila) {
		return dao.carregar(fila);
	}

}

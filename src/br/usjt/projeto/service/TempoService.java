package br.usjt.projeto.service;

	

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.projeto.dao.SenhaDAO;
import br.usjt.projeto.entity.Fila;
import br.usjt.projeto.entity.Senha;

@Service
public class TempoService {
	
	@Autowired
	private SenhaDAO dao;
	
	public String getHoraChegada() {
		LocalTime horarioDeChegada = LocalTime.now();
		String horCheg = String.valueOf(horarioDeChegada); 
		System.out.println(horCheg);
		return horCheg;
	}
	
	public void getTempoDeAtendimento(String horaChegada) {
		LocalTime inicio = LocalTime.parse(horaChegada);
		LocalTime termino = LocalTime.now();
		int diferencaEmMinutos = (int) ChronoUnit.MINUTES.between(inicio, termino);
		System.out.println(diferencaEmMinutos);
		dao.inserirTempoDeAtendimento(diferencaEmMinutos);
	}
	
	public List<Senha> carregarSenhas(){
		List<Senha> senhas = dao.carregarTodasSenha();
		return senhas;
	}
	
	public String getMediaTempoAtendimento(ArrayList<Senha> senhas) {
		String mediaTempoAtend = null;
		int acumulador = 0;
		int tempoAtendimento = 0;
		int tamanhoDaFila = 0;
		
		for(int i = 0; i < senhas.size(); i++) {
			tempoAtendimento = senhas.get(i).getTempoAtendimento();
			acumulador = acumulador + tempoAtendimento;
		}
		tamanhoDaFila = senhas.size()-1;
		int media = acumulador / tamanhoDaFila;
		
		return mediaTempoAtend;
	}
	
	public String previsaoInicioAtendimento(int tamanhoFila, String mediaAtendimento) {
		int previsao = 0;
		int medAtendimento = Integer.parseInt(mediaAtendimento);
		previsao = (tamanhoFila - 1) * medAtendimento;
		String prevIniAtend = Integer.toString(previsao);
		return prevIniAtend;
	}

    public static void main(String[] args) {
    	
    }
}

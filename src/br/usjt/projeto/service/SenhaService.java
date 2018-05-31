package br.usjt.projeto.service;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.projeto.dao.SenhaDAO;
import br.usjt.projeto.entity.Atendimento;
import br.usjt.projeto.entity.Fila;
import br.usjt.projeto.entity.Senha;
import br.usjt.projeto.entity.Servico;

@Service
public class SenhaService {

	@Autowired
	private SenhaDAO dao;

	@Autowired
	private FilaService filaService;
	
	@Autowired
	private ServicoService servicoService;

	
	//SENHA
	public Senha gerarSenha(String siglaFila, String siglaServico) {
		
		Fila fila = filaService.carregar(siglaFila);
		Servico servico = servicoService.carregar(siglaServico);
		Senha novaSenha = new Senha();
		
		novaSenha.setDataAbertura(new Date());
		novaSenha.setServico(servico);
		novaSenha.setFila(fila);
		Senha ultimaSenha = getLastSenha(fila);
		if (isEmpty(ultimaSenha)) {
			return geraPrimeiraSenhaDia(novaSenha, fila, servico);
		}
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime dataUltimaSenha = LocalDateTime.ofInstant(ultimaSenha.getDataAbertura().toInstant(),
				ZoneId.systemDefault());
		if (!(now.getMonth().equals(dataUltimaSenha.getMonth())
				&& now.getDayOfMonth() == dataUltimaSenha.getDayOfMonth())) {
			return geraPrimeiraSenhaDia(novaSenha, fila, servico);
		}
		
		novaSenha.setNumero(ultimaSenha.getNumero() + 1);
		novaSenha.setCodigo(createCodigo(fila, servico, novaSenha.getNumero()));
		novaSenha.setTempoAtendimento("0");
		novaSenha.setPrevInicioAtendimento(prevInicioAtendimento(siglaFila));
		novaSenha.setPrevTerminoAtendimento(prevTerminoAtendimento(siglaFila));
		novaSenha.setIsActive(0);
		
		return dao.gerarSenha(novaSenha);
	}
	
	//CARREGA TIPOS DE FILA NO SPINNER 
	public Senha carregar(Fila fila) {
		return dao.carregar(fila);
	}
	
	//CARREGA SERVICOS NO SPINNER
	public Senha carregarServico(Senha senha) {
		return dao.carregarServico(senha);
	}
	
	public Senha getLastSenha(Fila fila) {
		List<Senha> senhasFila = dao.getLastSenha(fila);
		if (senhasFila.isEmpty()) {
			return null;
		}
		return senhasFila.get(senhasFila.size() - 1);
	}
	
	private Senha geraPrimeiraSenhaDia(Senha primeiraSenha, Fila fila, Servico servico) {
		primeiraSenha.setNumero(1);
		primeiraSenha.setCodigo(createCodigo(fila, servico, 1));
		return dao.gerarSenha(primeiraSenha);
	}
	
	private String createCodigo(Fila fila, Servico servico, Integer numero) {
		return fila.getSigla() + servico.getSigla() + numero;
	}
	
	//MUDA STATUS DE ATENDIMENTO DA SENHA
	public void mudaStatusSenha(int senha) {
		dao.mudaStatusSenha(senha);
	}
	
	public Senha carregarSenha(int senha) {
		mudaStatusSenha(senha);
		return dao.carregarSenha(senha);
	}

	//PEGA QUANDO INICIA O ATENDIMENTO
	public void inicioAtendimento(int senha) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(new Locale("pt", "br"));
		String dataAtual = String.valueOf(now.format(formatador));
		dao.inserirInicioAtendimento(senha, dataAtual);
	}
	
	public void tempoNaFila(Senha senha) {
		String inicioAtendimento = String.valueOf(senha.getInicioAtendimento());
		System.out.println("aqui:" + inicioAtendimento);
        int horaInicioAtendimento = Integer.parseInt(inicioAtendimento.substring(11,13));
        int minutoInicioAtendimento = Integer.parseInt(inicioAtendimento.substring(14,16));
        int segundosInicioAtendimento = Integer.parseInt(inicioAtendimento.substring(17,19));
        
		String dataAtendimento = String.valueOf(senha.getDataAbertura());
		System.out.println(dataAtendimento);
        int horaChegada = Integer.parseInt(dataAtendimento.substring(11,13));
        int minutoChegada = Integer.parseInt(dataAtendimento.substring(14,16));
        int segundosChegada = Integer.parseInt(dataAtendimento.substring(17,19));
        
		LocalTime horaCheg = LocalTime.of(horaChegada, minutoChegada, segundosChegada);
		LocalTime horaIni = LocalTime.of(horaInicioAtendimento, minutoInicioAtendimento, segundosInicioAtendimento);
		Duration duracao = Duration.between(horaCheg, horaIni);
		long duracaoEmMinutos = duracao.toMinutes();
		String tempo = String.valueOf(duracaoEmMinutos);
		System.out.println("tempo de duração" + duracaoEmMinutos);
		dao.inserirTempoNaFila(senha.getId(), tempo);
	}
	
	//PEGA QUANDO TERMINA O ATENDIMENTO
	public void terminoAtendimento(int senha) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(new Locale("pt", "br"));
		String dataAtual = String.valueOf(now.format(formatador));
		dao.inserirTerminoAtendimento(senha, dataAtual);
	}
	
	public void tempoAtendimento(Senha senha) {
		String inicioAtendimento = String.valueOf(senha.getInicioAtendimento());
        int horaIncioAtendimento = Integer.parseInt(inicioAtendimento.substring(11,13));
        int minutoIncioAtendimento = Integer.parseInt(inicioAtendimento.substring(14,16));
        int segundosIncioAtendimento = Integer.parseInt(inicioAtendimento.substring(17,19));
        
		String terminoAtendimento = String.valueOf(senha.getTempoAtendimento());
		System.out.println("aqui" + terminoAtendimento);
        int horaTerminoAtendimento = Integer.parseInt(terminoAtendimento.substring(11,13));
        int minutoTerminoAtendimento = Integer.parseInt(terminoAtendimento.substring(14,16));
        System.out.println("aqui minuto" + minutoTerminoAtendimento);
        int segundosTerminoAtendimento = Integer.parseInt(terminoAtendimento.substring(17,19));
        
		LocalTime horaCheg = LocalTime.of(horaIncioAtendimento , minutoIncioAtendimento , segundosIncioAtendimento );
		LocalTime horaAten = LocalTime.of(horaTerminoAtendimento, minutoTerminoAtendimento, segundosTerminoAtendimento);
		Duration duracao = Duration.between(horaCheg, horaAten);
		long duracaoEmMinutos = duracao.toMinutes();
		String tempo = String.valueOf(duracaoEmMinutos);
		System.out.println("tempo de duração" + duracaoEmMinutos);
		dao.inserirTempoDeAtendimento(senha.getId(), tempo);
	}		
	
	//PREVISAO DE INICIO DE ATENDIMENTO//
	public String prevInicioAtendimento(String siglaFila) {
		return previsaoInicioAtendimento(dao.carregarTodasSenha(), siglaFila);
	}
	
	public void pegaTempoFila(int senha) {
		tempoNaFila(dao.carregarSenha(senha));
	}
	
	
	public String previsaoInicioAtendimento(List<Senha> senhas, String siglaFila) {
		String prevIniAten = null;
		int acumulador = 0;
		int tempoFila = 0;
		int mediaTempoFila = 0;
		int contPref = 0;
		int contNorm = 0;
		
		for (Iterator<Senha> iterator = senhas.iterator(); iterator.hasNext(); ) {  
			try {
				Senha s = (Senha) iterator.next();
				if(s.getFila().getId() == 1) {
					contPref++;
				}
				else {
					contNorm++;
				}
				tempoFila = Integer.parseInt(s.getTempoFila());
				acumulador += tempoFila;
				System.out.println (acumulador);  
			} catch (Exception e) {
				
			}
		}
		System.out.println("aqui" + contNorm);
		System.out.println("aqui" + contPref);
		if(siglaFila.equals("P")) {
			mediaTempoFila = acumulador / (contNorm + contPref);
			mediaTempoFila *= contPref;
			if(mediaTempoFila == 0) {
				mediaTempoFila += 5;
			}
			prevIniAten = Integer.toString(mediaTempoFila);
			return prevIniAten;
		} else {
			mediaTempoFila = acumulador / (contNorm + contPref);
			mediaTempoFila *= (contNorm + contPref);
			if(mediaTempoFila == 0) {
				mediaTempoFila += 5;
			}
			prevIniAten = Integer.toString(mediaTempoFila);
			return prevIniAten;
		}
		
	}
	
	
	//PREVISÃO DE TERMINO DE ATENDIMENTO//
	public String prevTerminoAtendimento(String siglaFila) {
		return previsaoTerminoAtendimento(dao.carregarTodasSenha(), siglaFila);
	}
	
	public void pegaTempoAtendimento(int senha) {
		tempoAtendimento(dao.carregarSenha(senha));
	}
	
	public String previsaoTerminoAtendimento(List<Senha> senhas, String siglaFila) {
		String prevTerAten = null;
		int acumulador = 0;
		int tempoFila = 0;
		int mediaTempoFila = 0;
		int contPref = 0;
		int contNorm = 0;
		
		for (Iterator<Senha> iterator = senhas.iterator(); iterator.hasNext(); ) {  
			try {
				Senha s = (Senha) iterator.next();
				if(s.getFila().getId() == 1) {
					contPref++;
				}
				else {
					contNorm++;
				}
				tempoFila = Integer.parseInt(s.getTempoFila());
				acumulador += tempoFila;
				System.out.println (acumulador);  
			} catch (Exception e) {
				
			}
		}
		System.out.println("aqui" + contNorm);
		System.out.println("aqui" + contPref);
		mediaTempoFila = acumulador / (contNorm + contPref);
		prevTerAten = Integer.toString(mediaTempoFila);
		if(mediaTempoFila == 0) {
			mediaTempoFila += 5;
		}
		return prevTerAten;
		
	}
	
}

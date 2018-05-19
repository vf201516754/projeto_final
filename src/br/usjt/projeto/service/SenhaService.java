package br.usjt.projeto.service;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.projeto.dao.SenhaDAO;
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
		novaSenha.setIsActive(0);
		return dao.gerarSenha(novaSenha);
	}

	public Senha carregar(Fila fila) {
		return dao.carregar(fila);
	}
	
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
	
	public Senha carregarSenha(int senha) {
		mudaStatusSenha(senha);
		return dao.carregarSenha(senha);
	}
	
	public void mudaStatusSenha(int senha) {
		dao.mudaStatusSenha(senha);
	}
	
}

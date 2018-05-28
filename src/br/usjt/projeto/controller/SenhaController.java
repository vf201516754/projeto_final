package br.usjt.projeto.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.usjt.projeto.dao.SenhaDAO;
import br.usjt.projeto.entity.Senha;
import br.usjt.projeto.service.FilaService;
import br.usjt.projeto.service.SenhaService;
import br.usjt.projeto.service.ServicoService;
import br.usjt.projeto.entity.Servico;
import br.usjt.projeto.entity.Fila;

@Controller
@Transactional
public class SenhaController {

	@Autowired
	private SenhaService service;

	@Autowired
	private ServicoService servicoService;

	@Autowired
	private FilaService filaService;
	
	@Autowired
	private SenhaDAO senhaDAO;
	
	@RequestMapping("index")
	public String inicio() {
		return "index";
	}

	@RequestMapping("/form_senha")
	public String formSenha(Model model) {
		model.addAttribute("servicos", servicoService.carregaServicos());
		model.addAttribute("filas", filaService.carregarFilas());
		return "GeraSenha";
	}
	
	@RequestMapping("/gera_senha")
	public String geraSenha(String fila, String servico, Model model) {
		try {
			Senha novaSenha = service.gerarSenha(fila, servico);
			model.addAttribute("senha", novaSenha);
			return "SenhaGerada";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/busca_ultima_senha")
    public List<Senha> listar() {
        List<Senha> senhas = senhaDAO.carregarUltimaSenha();
        return senhas;
    }
	
	@ResponseBody
	@RequestMapping(value = "/busca_todas_senha")
    public List<Senha> listarTodasSenhas() {
        List<Senha> senhas = senhaDAO.carregarTodasSenha();
        return senhas;
    }
	
	@ResponseBody
	@RequestMapping(value = "/lista_servicos", method = RequestMethod.GET)
    public List<Servico> listarServicos() {
        List<Servico> servicos = servicoService.carregaServicos();
        return servicos;
    }
	
	@ResponseBody
	@RequestMapping(value = "/lista_filas", method = RequestMethod.GET)
    public List<Fila> listarFilas() {
        List<Fila> filas = filaService.carregarFilas();
        return filas;
    }
}

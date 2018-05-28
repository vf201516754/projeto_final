package br.usjt.projeto.controller;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import br.usjt.projeto.dao.SenhaDAO;
import br.usjt.projeto.entity.Atendimento;
import br.usjt.projeto.entity.Senha;
import br.usjt.projeto.service.AtendimentoService;
import br.usjt.projeto.service.SenhaService;
import br.usjt.projeto.service.ServicoService;
import br.usjt.projeto.service.SubservicoService;

@Controller
@Transactional
public class AtendimentoController {

	@Autowired
	private SenhaDAO senhaDAO;

	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private SubservicoService subService;
	
	@Autowired
	private AtendimentoService atendimentoService;
	
	@Autowired
	private AtendimentoService tempoService;
	
	@Autowired
	private SenhaService senhaService;
	
	/* PAIN�IS */

	@RequestMapping("/painel_senhas")
	public String painelDeSenhas() {
		return "atendimento";
	}
	
	@RequestMapping("/painel_servico")
	public String painelServico(Model model) {
		model.addAttribute("servicos", servicoService.carregaServicos());
		model.addAttribute("subservico", subService.carregaSubservico());
		return "servico";
	}
	
	@RequestMapping("/painel_subservico")
	public String painelSubservico(Model model) {
		model.addAttribute("subservico", subService.carregaSubservico());
		return "subservico";
	}
	
	/* CONTROLES SERVI�O */
	
	@ResponseBody
	@RequestMapping(value = "/buscar_senha", method = RequestMethod.POST)
    public List<Senha> listarSenhaServico(@RequestParam int senha) {
        System.out.println(senha);
        List<Senha> senhas = senhaDAO.carregarSenhasInativas(senha);
        return senhas;
    }
	
	@ResponseBody
	@RequestMapping(value = "/cadastrar_atendimento", method = RequestMethod.POST)
	public int cadastrarAtendimento(@RequestParam int senha) {
		try {
			atendimentoService.geraAtendimento(senha);
			senhaService.inicioAtendimento(senha);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return senha;
	}
	
	@ResponseBody
	@RequestMapping(value = "/finalizar_atendimento", method = RequestMethod.POST)
	public int finalizarAtendimento(@RequestParam int senha) {
		try {
			atendimentoService.finalizaAtendimento(senha);
			senhaService.terminoAtendimento(senha);
			senhaService.pegaTempoFila(senha);
			//senhaService.pegaTempoAtendimento(senha);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return senha;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/encaminhar_atendimento", method = RequestMethod.POST)
	public int encaminharAtendimento(@RequestParam int subservico, int senha) {
		try {
			atendimentoService.encaminharAtendimento(subservico, senha);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return subservico;
	}
	
	
	/* CONTROLES SUBSERVI�O */
	
	@ResponseBody
	@RequestMapping(value = "/buscar_senha_subservico", method = RequestMethod.POST)
    public List<Senha> listarSenhaSubservico(@RequestParam int subservico) {
        System.out.println(subservico);
        List<Senha> senhas = senhaDAO.carregarSenhasSubservico(subservico);
        return senhas;
    }
	
	@ResponseBody
	@RequestMapping(value = "/cadastrar_atendimento_subservico", method = RequestMethod.POST)
	public int cadastrarAtendimentoSubservico(@RequestParam int senha) {
		try {
			atendimentoService.geraAtendimentoSubservico(senha);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return senha;
	}
	
	/* CONTROLES PAINEL DE SENHAS */
	
	@ResponseBody
	@RequestMapping(value = "/verifica_senha_atendimento")
	public List<Atendimento> chamarProximaSenhaAtendimento() {
		List<Atendimento> atendimento = atendimentoService.buscaProximaSenhaAtendimento();
		return atendimento;
	}
	
	@ResponseBody
	@RequestMapping(value = "/verifica_senha_autenticacao")
	public List<Atendimento> chamarProximaSenhaAutenticacao() {
		List<Atendimento> atendimento = atendimentoService.buscaProximaSenhaAutenticacao();
		return atendimento;
	}
	
	@ResponseBody
	@RequestMapping(value = "/verifica_senha_caixa")
	public List<Atendimento> chamarProximaSenhaCaixa() {
		List<Atendimento> atendimento = atendimentoService.buscaProximaSenhaCaixa();
		return atendimento;
	}
	
	@ResponseBody
	@RequestMapping(value = "/verifica_ultimas_senhas")
	public List<Atendimento> buscarUltimasSenhas() {
		List<Atendimento> atendimento = atendimentoService.buscarUltimasSenhas();
		return atendimento;
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

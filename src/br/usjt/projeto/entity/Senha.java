package br.usjt.projeto.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Senha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Campo numero nao pode ser nulo.")
	private int numero;

	@NotNull(message = "Campo codigo nao pode ser nulo.")
	private String codigo;
	
	@ManyToOne
	@JoinColumn(name = "id_fila")
	private Fila fila;
	
	@ManyToOne
	@JoinColumn(name = "id_servico")
	private Servico servico;
	
	@ManyToOne
	@JoinColumn(name = "id_subservico")
	private Subservico subservico;
	
	@Column(name = "data_abertura")
	private Date dataAbertura;
	
	@Column(name = "inicio_atendimento")
	private String inicioAtendimento;
	
	@Column(name = "termino_atendimento")
	private String terminoAtendimento;
	
	@Column(name = "tempo_fila")
	private String tempoFila;
	
	@Column(name = "tempo_atendimento")
	private String tempoAtendimento;
	
	@Column(name = "prev_inicio_atendimento")
	private String prevInicioAtendimento;
	
	@Column(name = "prev_termino_atendimento")
	private String prevTerminoAtendimento;
	
	@NotNull(message = "Campo isActive nao pode ser nulo.")
	private int isActive;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getTempoFila() {
		return tempoFila;
	}

	public void setTempoFila(String tempoFila) {
		this.tempoFila = tempoFila;
	}
	
	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Subservico getSubservico() {
		return subservico;
	}

	public void setSubservico(Subservico subservico) {
		this.subservico = subservico;
	}

	public Fila getFila() {
		return fila;
	}

	public void setFila(Fila fila) {
		this.fila = fila;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
	public String getTempoAtendimento() {
		return tempoAtendimento;
	}
	
	public void setTempoAtendimento(String tempoAtendimento) {
		this.tempoAtendimento = tempoAtendimento;
	}
	
	public String getInicioAtendimento() {
		return inicioAtendimento;
	}
	
	public void setInicioAtendimento(String inicioAtendimento) {
		this.inicioAtendimento = inicioAtendimento;
	}
	
	public String getTerminoAtendimento() {
		return terminoAtendimento;
	}
	
	public void setTerminoAtendimento(String terminoAtendimento) {
		this.terminoAtendimento = terminoAtendimento;
	}
	
	public String getPrevInicioAtendimento() {
		return prevInicioAtendimento;
	}
	
	public void setPrevInicioAtendimento(String prevInicioAtendimento) {
		this.prevInicioAtendimento = prevInicioAtendimento;
	}
	
	public String getPrevTerminoAtendimento() {
		return prevTerminoAtendimento;
	}
	
	public void setPrevTerminoAtendimento(String prevTerminoAtendimento) {
		this.prevTerminoAtendimento = prevTerminoAtendimento;
	}
	
	

	@Override
	public String toString() {
		return "Senha [id=" + id + ", numero=" + numero + ", codigo=" + codigo + ", dataAbertura=" + dataAbertura
				+ ", tempoFila=" + tempoFila + ", fila=" + fila + "]";
	}


}
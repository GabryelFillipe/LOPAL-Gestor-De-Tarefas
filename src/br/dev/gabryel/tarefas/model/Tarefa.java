package br.dev.gabryel.tarefas.model;

import java.time.LocalDate;

import br.dev.gabryel.tarefas.utils.Utils;

public class Tarefa {

	private String nome;
	private String descricao;
	private Funcionario responsavel;
	private LocalDate dataInicio;
	private int prazo;
	private LocalDate dataEntrega;
	private Status status;
	private String id;

	public Tarefa(Funcionario responsavel) {
		setResponsavel(responsavel);
		setID(Utils.gerarUUID8());

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Funcionario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Funcionario responsavel) {
		this.responsavel = responsavel;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	public LocalDate getDataPrevistaEntrega() {
		return dataInicio.plusDays(prazo);

	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	
	

	public String getId() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public Status getStatus() {
		LocalDate hoje = LocalDate.now();
		if (hoje.isBefore(dataInicio)) {
			status = status.NAO_INICIADO;
		} else if (hoje.isAfter(dataEntrega)) {
			status = status.EM_ATRASO;
		} else if (hoje.isAfter(dataInicio)) {
			status = status.EM_ANDAMENTO;
		} else if (hoje.isEqual(dataEntrega)) {
			status = status.CONCLUIDO;
		}
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	public String toString() {
		return nome + "," + descricao + "," + responsavel + "," + dataInicio + "," + prazo + "," + dataEntrega + "," +  status + "\n";  
	}
}

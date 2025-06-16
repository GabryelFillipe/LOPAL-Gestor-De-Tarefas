package br.dev.gabryel.tarefas.model;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

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
	private String txtDataInicio;

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

	public void setDataEntrega(String dataTxt) {
		if (dataTxt.equals("null")) {

		} else {
			System.out.println(dataTxt);
			this.dataEntrega = LocalDate.parse(dataTxt, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
	}

	public void setDataInicio(String txtData) {
		this.dataInicio = LocalDate.parse(txtData, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		txtDataInicio = dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public LocalDate getDataPrazo() {
		LocalDate dataPrazo = dataInicio.plusDays(prazo);
		return dataPrazo;
	}

	public String getId() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public Status getStatus() {
		if (status == null) {
			classificarStatus();
		}
		return status;
	}

	private void classificarStatus() {

		LocalDate dataPrazo = getDataPrazo();
		LocalDate hoje = LocalDate.now();

		if (hoje.isBefore(dataInicio)) {
			status = status.NAO_INICIADO;

		}
		if (hoje.isAfter(dataPrazo)) {
			status = status.EM_ATRASO;

		} else if (hoje.isAfter(dataPrazo)) {
			status = status.EM_ATRASO;
		}

	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setStatus(String status) {
		if (status.equals("CONCLUIDO")) {
			this.status = Status.CONCLUIDO;
		} else {
			classificarStatus();
		}

	}

	public String toString() {
		return id+ "," + nome + "," + descricao + "," + responsavel.getMatricula() + "," + dataInicio + "," + prazo + "," + dataEntrega + ","
				+ status + "\n";
	}
}

package br.dev.gabryel.tarefas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import br.dev.gabryel.tarefas.factory.ArquivoTarefasFactory;
import br.dev.gabryel.tarefas.model.Funcionario;
import br.dev.gabryel.tarefas.model.Status;
import br.dev.gabryel.tarefas.model.Tarefa;

public class TarefasDAO {

	private Tarefa tarefa;
	ArquivoTarefasFactory aff = new ArquivoTarefasFactory(); // Injeção de dependencia

	public TarefasDAO(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public boolean gravar() {

		try {
			BufferedWriter bw = aff.getBw();
			bw.write(tarefa.toString());
			bw.flush();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public List<Tarefa> getTarefas() {

		List<Tarefa> Tarefas = new ArrayList<Tarefa>();

		try {
			BufferedReader br = aff.getBr();

			String linha = "";

			while (linha != null) {
				linha = br.readLine();
				if (linha != null) {
					String[] tarefasVetor = linha.split(",");
					Tarefa tarefa = new Tarefa(null);
					FuncionarioDAO dao = new FuncionarioDAO(null);

					tarefa.setID(tarefasVetor[0]);
					tarefa.setNome(tarefasVetor[1]);
					tarefa.setDescricao(tarefasVetor[2]);
					tarefa.setResponsavel(dao.getFuncionario(tarefasVetor[3]));
					tarefa.setDataInicio(tarefasVetor[5]);
					tarefa.setPrazo(Integer.parseInt(tarefasVetor[6]));
					tarefa.setStatus(tarefasVetor[7]);
					tarefa.setDataEntrega(tarefasVetor[8]);
					Tarefas.add(tarefa);
				}
			}

			return Tarefas;

		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}
	}

	public Tarefa getTarefa(String Id) {

		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		tarefas = getTarefas();

		String id;
		Tarefa tarefa = null;

		for (int i = 0; i < tarefas.size(); i++) {

			tarefa = tarefas.get(i);
			id = tarefa.getId();
			if (id.equals(Id)) {
				return tarefa;
			}
		}
		return tarefa;
	}

	public void concluirTarefa(String tarefaId) {
		Tarefa tarefaConcluida = getTarefa(tarefaId);
		tarefaConcluida.setStatus(Status.CONCLUIDO);
		tarefaConcluida.setDataEntrega(LocalDate.now());
		inserirTarefa(tarefaConcluida);
	}

	private void inserirTarefa(Tarefa tarefa) {

		String idTarefa = tarefa.getId();
		try {
			BufferedReader br = aff.getBr();

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Tarefa> tarefas = getTarefas();
		String[] atualizar = new String[tarefas.size()];

		for (int i = 0; i < tarefas.size(); i++) {
			Tarefa tarefaTemp = tarefas.get(i);
			if (tarefaTemp.getId().equals(idTarefa)) {
				atualizar[i] = tarefa.toString();
			} else {
				atualizar[i] = tarefaTemp.toString();
			}

		}

		try {

			BufferedWriter bw = aff.getDbBW();
			bw.write(simplificar(atualizar));
			bw.flush();

		} catch (Exception e) {

		}

	}
	
	private String simplificar(String[] atualizar) {
		String simplificado = "";
		
		for(int i = 0; i < atualizar.length; i++) {
			simplificado+=atualizar[i];
		}
		return simplificado;
	}

}

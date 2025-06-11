package br.dev.gabryel.tarefas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import br.dev.gabryel.tarefas.factory.ArquivoTarefasFactory;
import br.dev.gabryel.tarefas.model.Funcionario;
import br.dev.gabryel.tarefas.model.Tarefa;

public class TarefasDAO {

	private Tarefa tarefa;
	private ArquivoTarefasFactory aff = new ArquivoTarefasFactory(); // Injeção de dependencia

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
					tarefa.setNome(tarefasVetor[0]);
					tarefa.setDescricao(tarefasVetor[1]);
					//tarefa.setResponsavel();
					//tarefa.setDataInicio(tarefasVetor[3]);
//						tarefa.setPrazo(Double.parseDouble(tarefasVetor[4]));
//						tarefa.setDataEntrega(null);
//						tarefa.setStatus(.toString() (tarefasVetor[7]));
					Tarefas.add(tarefa);
				}
			}

			return Tarefas;

		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}
	}
}

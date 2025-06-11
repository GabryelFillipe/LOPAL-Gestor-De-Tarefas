package br.dev.gabryel.tarefas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import br.dev.gabryel.tarefas.dao.FuncionarioDAO;
import br.dev.gabryel.tarefas.model.Funcionario;
import br.dev.gabryel.tarefas.model.Status;
import br.dev.gabryel.tarefas.model.Tarefa;
import br.dev.gabryel.tarefas.ui.FuncionarioFrame;
import br.dev.gabryel.tarefas.ui.FuncionarioListaFrame;
import br.dev.gabryel.tarefas.ui.GerenciadorFrame;
import br.dev.gabryel.tarefas.ui.TarefasFrame;
import br.dev.gabryel.tarefas.utils.Utils;

public class Main {

	public static void main(String[] args) {

		// new FuncionarioFrame();
		// new FuncionarioListaFrame();
		new GerenciadorFrame();

//		FuncionarioDAO dao = new FuncionarioDAO(null);
//		dao.getFuncionarios();

		Tarefa t = new Tarefa(null);
		

	}

	private static void testarLeituraEscritaArquivo() {
		String so = System.getProperty("os.name");
		System.out.println(so);

		String caminho = "/Users/25132404/projetoTarefas/tarefas";

		FileReader fr = null;
		BufferedReader br = null;

		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fr = new FileReader(caminho);
			br = new BufferedReader(fr);

			fw = new FileWriter(caminho, true);
			bw = new BufferedWriter(fw);

			bw.append("Celso PQ?\n");
			bw.flush();

			String linha = br.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = br.readLine();
			}

		} catch (FileNotFoundException erro) {
			System.out.println("Arquivo não encontrado");
		} catch (IOException erro) {
			System.out.println("ACESSO NEGADO!");
		} catch (Exception erro) {
			System.out.println(erro.getMessage());
		}
	}

}

package br.dev.gabryel.tarefas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.dev.gabryel.tarefas.factory.ArquivoFuncionarioFactory;
import br.dev.gabryel.tarefas.model.Funcionario;

public class FuncionarioDAO {

	private Funcionario funcionario;
	private ArquivoFuncionarioFactory aff = new ArquivoFuncionarioFactory(); // Injeção de dependencia

	public FuncionarioDAO(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public boolean gravar() {

		try {
			BufferedWriter bw = aff.getBw();
			bw.write(funcionario.toString());
			bw.flush();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public List<Funcionario> getFuncionarios() {

		List<Funcionario> funcionarios = new ArrayList<Funcionario>();

		try {
			BufferedReader br = aff.getBr();

			String linha = "";

			while (linha != null) {
				linha = br.readLine();
				if (linha != null) {
					String[] funcionarioVetor = linha.split(",");
					Funcionario funcionario = new Funcionario(null);
					funcionario.setMatricula(funcionarioVetor[0]);
					funcionario.setNome(funcionarioVetor[1]);
					funcionario.setCargo(funcionarioVetor[2]);
					funcionario.setSetor(funcionarioVetor[3]);
					funcionario.setSalario(Double.parseDouble(funcionarioVetor[4]));
					funcionarios.add(funcionario);
				}
			}
			
			return funcionarios;

		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}
	}
	
	
	public Funcionario getFuncionario(String matricula) {
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = getFuncionarios();
		
		String matriculaTemp;
		Funcionario funcionario = null;
		
		for (int i = 0 ; i < funcionarios.size(); i++) {
			funcionario = funcionarios.get(i);
			matriculaTemp = funcionario.getMatricula();
			if (matriculaTemp.equals(matricula)) {
				return funcionario;
			}
		}
		return funcionario;
	}
	
	public String getMatricula(int indexResponsavel) {
		
		Object[][] dados = listaFuncionario();
		String matricula = (String) dados[indexResponsavel][0];
		return matricula;
	}
	
	private Object[][] listaFuncionario(){
		
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		//Puxando os dados e criando a lista
		FuncionarioDAO dao = new FuncionarioDAO(null);
		funcionarios = dao.getFuncionarios();
		
		Object[][] dados = new Object[funcionarios.size()][2];
		
		int i = 0;
		for (Funcionario f : funcionarios) {
			dados[i][0] = f.getMatricula();
			dados[i][1] = f.getNome();
			i++;
		}
		return dados;
		
	}
	
	public String[] getFuncionariosNomes(){
		
		Object[][] dados = listaFuncionario();
		String[] funcionariosNomes = new String[dados.length];
		
		for(int i = 0; i < funcionariosNomes.length; i++) {
			funcionariosNomes[i] = (String) dados[i][1];
		}
		
		return funcionariosNomes;
		
	}
	
	
}

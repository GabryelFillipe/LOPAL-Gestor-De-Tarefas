package br.dev.gabryel.tarefas.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.dev.gabryel.tarefas.model.Funcionario;

public class FuncionarioListaFrame {

	private JLabel labelTitulo;
	private  JButton btnNovo;
	
	private DefaultTableModel model; //Dados da tabela
	private JTable tabelaFuncionarios; //Tabela visualmente
	private JScrollPane ScrollFuncionarios; //Container da Tabela
	
	String[] colunas = {"CÓDIGO", "NOME DO FUNCIONÁRIO", "CARGO"};
	
	public FuncionarioListaFrame() {
		criarTela();
	}
	
	private void criarTela() {
		JFrame telaFuncionarioLista = new JFrame("Lista de funcionarios");
		telaFuncionarioLista.setSize(700, 500);
		telaFuncionarioLista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaFuncionarioLista.setLayout(null);
		telaFuncionarioLista.setLocationRelativeTo(null);
		telaFuncionarioLista.setResizable(false);
		
		Container painel = telaFuncionarioLista.getContentPane();
		
		labelTitulo = new JLabel("Cadastro de funcionários");
		labelTitulo.setBounds(10, 10, 500, 40);
		labelTitulo.setFont(new Font("Ärial", Font.BOLD, 32));
		labelTitulo.setForeground(Color.RED);
		
		//Criar a Tabela
	
		model = new DefaultTableModel(colunas, 10);
		tabelaFuncionarios = new JTable(model);
		ScrollFuncionarios = new JScrollPane(tabelaFuncionarios);
		ScrollFuncionarios.setBounds(10, 70, 680, 300);
		carregarDadosTabela();
		
		painel.add(labelTitulo);
		painel.add(ScrollFuncionarios);
		
		telaFuncionarioLista.setVisible(true);
	}
	
	private void carregarDadosTabela() {
		
		List<Funcionario> funcionarios = new ArrayList<>();
		
		int i = 0;
		
		Object[] [] dados = new Object[funcionarios.size()][3];
		
		for(Funcionario f : funcionarios) {
			dados[i][0] = f.getMatricula();
			dados[i][1] = f.getNome();
			dados[i][2] = f.getCargo();
			i++;
		}
		
		model.setDataVector(dados, colunas);
		
	
	}
	
}

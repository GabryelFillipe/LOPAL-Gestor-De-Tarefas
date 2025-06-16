package br.dev.gabryel.tarefas.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.dev.gabryel.tarefas.dao.FuncionarioDAO;
import br.dev.gabryel.tarefas.dao.TarefasDAO;
import br.dev.gabryel.tarefas.model.Funcionario;
import br.dev.gabryel.tarefas.model.Tarefa;

public class TarefasListaFrame {

	private JLabel labelTitulo;
	private  JButton btnNovo;
	private JButton btnSair;
	
	private DefaultTableModel model; //Dados da tabela
	private JTable tabelaTarefas; //Tabela visualmente
	private JScrollPane scrollTarefas; //Container da Tabela
	
	String[] colunas = {"CÓDIGO", "NOME DO FUNCIONÁRIO", "CARGO"};
	
	public TarefasListaFrame(JFrame pai) {
		criarTela(pai);
	}
	
	private void criarTela(JFrame pai) {
		JDialog telaTarefaLista = new JDialog(pai , "Lista de Tarefas" , true);
		telaTarefaLista.setSize(700, 500);
		telaTarefaLista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaTarefaLista.setLayout(null);
		telaTarefaLista.setLocationRelativeTo(null);
		telaTarefaLista.setResizable(false);
		
		Container painel = telaTarefaLista.getContentPane();
		
		labelTitulo = new JLabel("Cadastro de Tarefas");
		labelTitulo.setBounds(10, 10, 500, 40);
		labelTitulo.setFont(new Font("Ärial", Font.BOLD, 32));
		labelTitulo.setForeground(Color.RED);
		
		//Criar a Tabela
	
		model = new DefaultTableModel(colunas, 100);
		tabelaTarefas = new JTable(model);
		scrollTarefas = new JScrollPane(tabelaTarefas);
		scrollTarefas.setBounds(10, 70, 680, 300);
		
		carregarDadosTabela();
		
		btnNovo = new JButton("Cadastrar nova Tarefa");
		btnNovo.setBounds(10, 400, 250, 50);
		
		btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new TarefasFrame(pai);
				carregarDadosTabela();
				
			}
		});
		
		btnSair = new JButton("Sair");
		btnSair.setBounds(430, 400, 250, 50);
		
		btnSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int resposta =	JOptionPane.showConfirmDialog(telaTarefaLista, "Sair do sistema?", "Atenção", JOptionPane.YES_NO_OPTION );
			
				if(resposta == 0) {
					telaTarefaLista.dispose();
				}
				
				
			}
		});
		
		painel.add(labelTitulo);
		painel.add(scrollTarefas);
		painel.add(btnNovo);
		painel.add(btnSair);
		
		telaTarefaLista.setVisible(true);
	}
	
	private void carregarDadosTabela() {
		
		List<Tarefa> tarefas = new ArrayList<>();
		
		TarefasDAO dao = new TarefasDAO(null);
		tarefas = dao.getTarefas();
		
		int i = 0;
		
		Object[] [] dados = new Object[tarefas.size()][3];
		
		for(Tarefa f : tarefas) {
			dados[i][0] = f.getNome();
			dados[i][1] = f.getDescricao();
			dados[i][2] = f.getResponsavel();
			i++;
		}
		
		model.setDataVector(dados, colunas);
		
	
	}
}

package br.dev.gabryel.tarefas.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.dev.gabryel.tarefas.dao.FuncionarioDAO;
import br.dev.gabryel.tarefas.model.Funcionario;
import br.dev.gabryel.tarefas.utils.Utils;

public class TarefasFrame {

	private JLabel labelNome;
	private JLabel labelDescricao;
	private JLabel labelFuncionario;
	private JLabel labelDataInicio;
	private JLabel labelPrazo;
	private JLabel labelDataEntrega;
	private JLabel labelStatus;
	private JTextField txtNome;
	private JTextField txtDescricao;
	private JTextField txtDataInicio;
	private JTextField txtPrazo;
	private JTextField txtDataEntrega;
	private JComboBox<String> comboStatus;
	private JComboBox<String> comboFuncionario;
	private JButton btnSalvar;
	private JButton btnSair;

	public TarefasFrame(JFrame pai) {
		criarTela(pai);
	}

	private void criarTela(JFrame pai) {
		JDialog telaTarefas = new JDialog(pai, "Cadastro de Tarefas", true);
		telaTarefas.setSize(500, 700);
		telaTarefas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaTarefas.setLayout(null);
		telaTarefas.setResizable(false);
		telaTarefas.setLocationRelativeTo(null);

		Container painel = telaTarefas.getContentPane();

		labelNome = new JLabel("Tarefa:");
		labelNome.setBounds(10, 20, 150, 30);
		txtNome = new JTextField();
		txtNome.setBounds(10, 50, 350, 30);

		labelDescricao = new JLabel("Funcionario responsável");
		labelDescricao.setBounds(10, 85, 200, 30);
		txtDescricao = new JTextField();
		txtDescricao.setBounds(10, 115, 350, 30);

		labelFuncionario = new JLabel("Funcionário");
		labelFuncionario.setBounds(10, 150, 150, 30);
		comboFuncionario = new JComboBox<>();
		comboFuncionario.setBounds(10, 180, 350, 30);
		comboFuncionario.addItem("teste2");

		labelDataInicio = new JLabel("Data De Inicio");
		labelDataInicio.setBounds(10, 212, 150, 30);
		txtDataInicio = new JTextField();
		txtDataInicio.setBounds(10, 235, 150, 30);

		labelPrazo = new JLabel("Prazo");
		labelPrazo.setBounds(10, 270, 150, 30);
		txtPrazo = new JTextField();
		txtPrazo.setBounds(10, 300, 150, 30);

		labelDataEntrega = new JLabel("Data de Entrega (Dias)");
		labelDataEntrega.setBounds(10, 335, 150, 30);
		txtDataEntrega = new JTextField();
		txtDataEntrega.setBounds(10, 365, 150, 30);

		labelStatus = new JLabel("Status");
		labelStatus.setBounds(10, 400, 150, 30);
		comboStatus = new JComboBox<>();
		comboStatus.setBounds(10, 430, 150, 30);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(10, 485, 200, 50);

		btnSair = new JButton("Sair");
		btnSair.setBounds(260, 485, 200, 50);

		btnSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(telaTarefas, "Sair do sistema?", "Atenção",
						JOptionPane.YES_NO_OPTION);

				if (resposta == 0) {
					telaTarefas.dispose();
				}

			}
		});

		painel.add(labelNome);
		painel.add(txtNome);
		painel.add(labelDescricao);
		painel.add(txtDescricao);
		painel.add(labelFuncionario);
		painel.add(comboFuncionario);
		painel.add(labelDataInicio);
		painel.add(txtDataInicio);
		painel.add(labelPrazo);
		painel.add(txtPrazo);
		painel.add(labelDataEntrega);
		painel.add(txtDataEntrega);
		painel.add(labelStatus);
		painel.add(comboStatus);
		painel.add(btnSalvar);
		painel.add(btnSair);

		telaTarefas.setVisible(true);
	}

}

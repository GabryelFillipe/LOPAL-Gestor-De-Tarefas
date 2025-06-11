package br.dev.gabryel.tarefas.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GerenciadorFrame {

	private JButton btnFuncionarios;
	private JButton btnTarefas;

	public GerenciadorFrame() {
		criarTela();
	}

	private void criarTela() {

		JFrame telaGerenciador = new JFrame("Gerenciador");
		telaGerenciador.setSize(500, 300);
		telaGerenciador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaGerenciador.setLayout(null);
		telaGerenciador.setLocationRelativeTo(null);
		telaGerenciador.setResizable(false);

		Container painel = telaGerenciador.getContentPane();

		btnFuncionarios = new JButton("Funcionários");
		btnFuncionarios.setBounds(30, 100, 200, 50);

		btnFuncionarios.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new FuncionarioListaFrame(telaGerenciador);

			}
		});

		btnTarefas = new JButton("Tarefas");
		btnTarefas.setBounds(255, 100, 200, 50);

		btnTarefas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TarefasListaFrame(telaGerenciador);

			}
		});

		painel.add(btnFuncionarios);
		painel.add(btnTarefas);

		telaGerenciador.setVisible(true);
	}

}

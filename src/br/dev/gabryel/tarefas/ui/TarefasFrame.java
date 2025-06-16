package br.dev.gabryel.tarefas.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.dev.gabryel.tarefas.dao.FuncionarioDAO;
import br.dev.gabryel.tarefas.dao.TarefasDAO;
import br.dev.gabryel.tarefas.model.Funcionario;
import br.dev.gabryel.tarefas.model.Tarefa;
import br.dev.gabryel.tarefas.utils.Utils;

public class TarefasFrame {
	private FuncionarioDAO daoFuncionario = new FuncionarioDAO(null);

	private JLabel labelNome;
	private JLabel labelId;
	private JLabel labelDescricao;
	private JLabel labelFuncionario;
	private JLabel labelDataInicio;
	private JLabel labelPrazo;
	private JLabel labelDataPrazo;
	private JLabel labelStatus;
	private JTextField txtNome;
	private JTextField txtDescricao;
	private JTextField txtDataInicio;
	private JTextField txtPrazo;
	private JTextField txtDataPrazo;
	private JTextField txtId;
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

		labelId = new JLabel("ID da Tarefa");
		labelId.setBounds(10, 85, 150, 30);
		txtId = new JTextField();
		txtId.setBounds(10, 115, 200, 30);
		txtId.setText(Utils.gerarUUID8());
		txtId.setEnabled(false);

		labelDescricao = new JLabel("Descrição da tarefa");
		labelDescricao.setBounds(10, 150, 200, 30);
		txtDescricao = new JTextField();
		txtDescricao.setBounds(10, 180, 350, 30);

		labelFuncionario = new JLabel("Funcionário");
		labelFuncionario.setBounds(10, 215, 150, 30);
		comboFuncionario = new JComboBox<>(daoFuncionario.getFuncionariosNomes());
		comboFuncionario.setBounds(10, 245, 350, 30);

		labelDataInicio = new JLabel("Data De Inicio");
		labelDataInicio.setBounds(10, 280, 150, 30);
		txtDataInicio = new JTextField();
		txtDataInicio.setBounds(10, 310, 150, 30);

		labelPrazo = new JLabel("Prazo");
		labelPrazo.setBounds(10, 345, 150, 30);
		txtPrazo = new JTextField();
		txtPrazo.setBounds(10, 375, 150, 30);

		labelDataPrazo = new JLabel("Data de Entrega (Dias)");
		labelDataPrazo.setBounds(10, 410, 150, 30);
		txtDataPrazo = new JTextField();
		txtDataPrazo.setBounds(10, 440, 150, 30);
		txtDataPrazo.setEditable(false);

		DocumentListener listener = new DocumentListener() {// Calculo de previsão de entrega

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				atualizarDataPrevista();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				atualizarDataPrevista();
			}

			private void atualizarDataPrevista() {
				String dataTxt = txtDataInicio.getText();
				String prazoTxt = txtPrazo.getText();
				try {
					LocalDate data = LocalDate.parse(dataTxt, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					// TODO: esse formato funciona, mas acaba barrando input de "d/M/yyyy" que
					// também deveria funcionar, ajustar depois
					int prazo = Integer.parseInt(prazoTxt);
					LocalDate dataPrevista = (LocalDate) data.plusDays(prazo);

					txtDataPrazo.setText(dataPrevista.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				} catch (Exception DateTimeParseException) {
				}
			}
		};

		txtDataInicio.getDocument().addDocumentListener(listener);
		txtPrazo.getDocument().addDocumentListener(listener);


		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(10, 545, 200, 50);

		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int funcionarioIndex = comboFuncionario.getSelectedIndex();
				String funcionarioMatricula = daoFuncionario.getMatricula(funcionarioIndex);
				Funcionario funcionario = daoFuncionario.getFuncionario(funcionarioMatricula);

				Tarefa tarefa = new Tarefa(null);

				try {

					tarefa.setID(txtId.getText());
					tarefa.setNome(txtNome.getText());
					tarefa.setDescricao(txtDescricao.getText());
					tarefa.setDataInicio(txtDataInicio.getText());
					tarefa.setPrazo(Integer.parseInt(txtPrazo.getText()));
					tarefa.setDataEntrega(txtDataPrazo.getText());

				} catch (Exception e2) {

				}
				if (temComma()) {
					JOptionPane.showMessageDialog(telaTarefas, "Não inserir vírgulas");
				} else {
					TarefasDAO daoTarefa = new TarefasDAO(tarefa);
					boolean success = daoTarefa.gravar();

					// Feedback para o usario
					if (success) {
						JOptionPane.showMessageDialog(telaTarefas, "Tarefa atribuída com sucesso!");
						limparFormulario();
					} else {
						JOptionPane.showMessageDialog(telaTarefas,
								"Ocorreu um erro na gravação\nTente novamente.\nSe o problema persistir, entre em contato com o suporte.");
					}

				}

			}
		});

		btnSair = new JButton("Sair");
		btnSair.setBounds(260, 545, 200, 50);

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
		painel.add(labelId);
		painel.add(txtId);
		painel.add(labelDescricao);
		painel.add(txtDescricao);
		painel.add(labelFuncionario);
		painel.add(comboFuncionario);
		painel.add(labelDataInicio);
		painel.add(txtDataInicio);
		painel.add(labelPrazo);
		painel.add(txtPrazo);
		painel.add(labelDataPrazo);
		painel.add(txtDataPrazo);
		painel.add(btnSalvar);
		painel.add(btnSair);

//

		telaTarefas.setVisible(true);
	}

	private void limparFormulario() {
		txtId.setText(Utils.gerarUUID8());
		txtNome.setText(null);
		txtDescricao.setText(null);
		txtDataInicio.setText(null);
		txtPrazo.setText(null);
		txtNome.requestFocus();
	}

	private boolean temComma() {
		boolean commaNome = txtId.getText().contains(",");
		boolean commaDescricao = txtDescricao.getText().contains(",");
		boolean commaPrazo = txtPrazo.getText().contains(",");
		boolean commaDataInicio = txtDataInicio.getText().contains(",");

		return (commaNome || commaDescricao || commaPrazo || commaDataInicio);
	}

}

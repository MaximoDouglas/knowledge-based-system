package br.ufal.ic.ia.skynet.view;

import java.awt.FlowLayout;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.ufal.ic.ia.skynet.exceptions.InvalidArgs;
import br.ufal.ic.ia.skynet.motor_inferencia.controller.InferenceController;
import javafx.util.Pair;

@SuppressWarnings({ "serial", "restriction" })
public class ExemploPanel extends JFrame {

	private JButton executar, voltar, selecionarGoal;
	private JRadioButton forward, forwardExplicacao, backward, consulta;
	private List<JRadioButton> listaVariaveisRadio;
	private JFrame backMenu, telaQuestionario;
	private InferenceController infController;
	private JPanel painelPrincipal;
	private InferenceMotorHandler handler;
	private String goal;

	public ExemploPanel(JFrame backMenu) {

		super("Exemplo - Funcionamento do motor");

		this.backMenu = backMenu;
		this.painelPrincipal = new JPanel();
		this.listaVariaveisRadio = new ArrayList<>();
		selecionarGoal = new JButton("Selecionar");
		this.goal = "";

		this.handler = new InferenceMotorHandler();

		this.forward = new JRadioButton("Forward");
		forward.addActionListener(handler);

		this.forwardExplicacao = new JRadioButton("Forward com explicação");
		forwardExplicacao.addActionListener(handler);

		this.backward = new JRadioButton("Backward");
		backward.addActionListener(handler);

		this.consulta = new JRadioButton("Consulta");
		consulta.addActionListener(handler);

		JPanel painelOpcoes = new JPanel();
		painelOpcoes.add(forward);
		painelOpcoes.add(forwardExplicacao);
		painelOpcoes.add(backward);
		painelOpcoes.add(consulta);

		this.executar = new JButton("Executar");
		executar.addActionListener(handler);
		this.voltar = new JButton("Voltar");
		voltar.addActionListener(handler);

		JPanel painelBotoes = new JPanel();
		painelBotoes.add(executar);
		painelBotoes.add(voltar);

		painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
		painelPrincipal.add(painelOpcoes);
		painelPrincipal.add(painelBotoes);

		add(painelPrincipal);
		setLayout(new FlowLayout());
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 200);
		setVisible(true);

	}

	private void CreateAndShowFactsTable(List<String> linhas, String sequence) {
		JFrame telaTabelaFatos = new JFrame("Fatos");

		String[] colunas = { "Base de fatos - " + sequence };

		JTable tabela;
		String[][] linhasStr = new String[linhas.size()][1];

		int i = 0;

		for (String string : linhas) {
			String[] str = { string };
			linhasStr[i] = str;
			i++;
		}

		tabela = new JTable(linhasStr, colunas);
		tabela.setEnabled(false);
		JScrollPane barraRolagem = new JScrollPane(tabela);

		JPanel tabelaPanel = new JPanel();
		tabelaPanel.add(barraRolagem);

		telaTabelaFatos.add(tabelaPanel);
		telaTabelaFatos.setLayout(new FlowLayout());
		telaTabelaFatos.setResizable(true);
		telaTabelaFatos.setLocationRelativeTo(null);
		telaTabelaFatos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaTabelaFatos.setSize(500, 550);
		telaTabelaFatos.setVisible(true);
	}

	private void CreateAndShowRulesTable(List<Pair<String, String>> linhas) {
		JFrame telaTabelaFatos = new JFrame("Regras");

		String[] colunas = { "Antecedente", "Consequente" };

		JTable tabela;
		String[][] linhasStr = new String[linhas.size()][2];

		int i = 0;

		for (Pair<String, String> regra : linhas) {
			String[] str = { regra.getKey(), regra.getValue() };
			linhasStr[i] = str;
			i++;
		}

		tabela = new JTable(linhasStr, colunas);
		tabela.setEnabled(false);
		JScrollPane barraRolagem = new JScrollPane(tabela);

		JPanel tabelaPanel = new JPanel();
		tabelaPanel.add(barraRolagem);

		telaTabelaFatos.add(tabelaPanel);
		telaTabelaFatos.setLayout(new FlowLayout());
		telaTabelaFatos.setResizable(true);
		telaTabelaFatos.setLocationRelativeTo(null);
		telaTabelaFatos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaTabelaFatos.setSize(500, 550);
		telaTabelaFatos.setVisible(true);
	}

	private void CreateAndShowRulesTableExplained(List<Pair<String, String>> linhas) {
		JFrame telaTabelaFatos = new JFrame("Regras");

		String[] colunas = { "     Regra     ", "     Ação     " };

		JTable tabela;
		String[][] linhasStr = new String[linhas.size()][2];

		int i = 0;

		for (Pair<String, String> regra : linhas) {
			String[] str = { regra.getKey(), regra.getValue() };
			linhasStr[i] = str;
			i++;
		}

		tabela = new JTable(linhasStr, colunas);
		tabela.doLayout();
		tabela.setEnabled(false);
		JScrollPane barraRolagem = new JScrollPane(tabela);

		JPanel tabelaPanel = new JPanel();
		tabelaPanel.add(barraRolagem);

		telaTabelaFatos.add(tabelaPanel);
		telaTabelaFatos.setLayout(new FlowLayout());
		telaTabelaFatos.setResizable(true);
		telaTabelaFatos.setLocationRelativeTo(null);
		telaTabelaFatos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaTabelaFatos.setSize(500, 550);
		telaTabelaFatos.setVisible(true);
	}

	private void CreateAndShowQuestionario() {

		telaQuestionario = new JFrame("Questionário");

		List<String> variaveis = infController.getVariaveis();

		JPanel telaJRadio = new JPanel();
		telaJRadio.setLayout(new BoxLayout(telaJRadio, BoxLayout.Y_AXIS));

		for (String string : variaveis) {
			listaVariaveisRadio.add(new JRadioButton(string));
		}

		for (JRadioButton radio : listaVariaveisRadio) {
			radio.addActionListener(handler);
			telaJRadio.add(radio);
		}

		JScrollPane barraRolagem = new JScrollPane(telaJRadio);

		JPanel telaBotaoSelecionar = new JPanel();
		telaBotaoSelecionar.add(selecionarGoal);
		selecionarGoal.addActionListener(handler);

		JPanel telaGeral = new JPanel();
		telaGeral.setLayout(new BoxLayout(telaGeral, BoxLayout.Y_AXIS));

		telaGeral.add(barraRolagem);
		telaGeral.add(telaBotaoSelecionar);

		telaQuestionario.add(telaGeral);
		telaQuestionario.setLayout(new FlowLayout());
		telaQuestionario.setResizable(true);
		telaQuestionario.setLocationRelativeTo(null);
		telaQuestionario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaQuestionario.setSize(500, 550);
		telaQuestionario.setVisible(true);

	}

	private class InferenceMotorHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == executar) {

				try {
					infController = new InferenceController();
				} catch (InvalidArgs e1) {
					JOptionPane.showMessageDialog(null, "Algum erro ocorreu.");
					dispose();
					backMenu.setVisible(true);
				}

				if (forward.isSelected()) {
					CreateAndShowFactsTable(infController.getFacts(), "Antes");
					CreateAndShowRulesTable(infController.getRulePairs());

					List<String> lista = infController.forward();

					CreateAndShowFactsTable(lista, "Depois");
					infController.wipeData();
				} else if (forwardExplicacao.isSelected()) {
					CreateAndShowFactsTable(infController.getFacts(), "Antes");
					CreateAndShowRulesTable(infController.getRulePairs());

					List<String> lista = infController.forwardExplained();

					CreateAndShowFactsTable(lista, "Depois");
					CreateAndShowRulesTableExplained(infController.getExplicacoes());

					infController.wipeData();
				} else if (backward.isSelected()) {
					CreateAndShowQuestionario();
				} else if (consulta.isSelected()) {
					CreateAndShowFactsTable(infController.getFacts(), "Atual");
					CreateAndShowRulesTable(infController.getRulePairs());
					infController.wipeData();
				} else {
					JOptionPane.showMessageDialog(null, "Selecione ao menos uma opção.");
				}
			}

			if (e.getSource() == forward) {
				if (forward.isSelected()) {
					forwardExplicacao.setEnabled(false);
					consulta.setEnabled(false);
					backward.setEnabled(false);
				} else {
					forwardExplicacao.setEnabled(true);
					consulta.setEnabled(true);
					backward.setEnabled(true);
				}
			}
			if (e.getSource() == forwardExplicacao) {
				if (forwardExplicacao.isSelected()) {
					forward.setEnabled(false);
					consulta.setEnabled(false);
					backward.setEnabled(false);
				} else {
					forward.setEnabled(true);
					consulta.setEnabled(true);
					backward.setEnabled(true);
				}
			}
			if (e.getSource() == consulta) {
				if (consulta.isSelected()) {
					forwardExplicacao.setEnabled(false);
					forward.setEnabled(false);
					backward.setEnabled(false);
				} else {
					forwardExplicacao.setEnabled(true);
					forward.setEnabled(true);
					backward.setEnabled(true);
				}
			}
			if (e.getSource() == backward) {
				if (backward.isSelected()) {
					forwardExplicacao.setEnabled(false);
					consulta.setEnabled(false);
					forward.setEnabled(false);
				} else {
					forwardExplicacao.setEnabled(true);
					consulta.setEnabled(true);
					forward.setEnabled(true);
				}
			}
			if (e.getSource() == voltar) {
				dispose();
				backMenu.setVisible(true);
			}

			boolean flag = false;

			if (e.getSource() == selecionarGoal) {
				for (JRadioButton jRadioButton : listaVariaveisRadio) {
					if (jRadioButton.isSelected()) {
						goal = jRadioButton.getText();
						flag = true;

					}
				}

				if (!flag) {
					JOptionPane.showMessageDialog(null, "Por favor, selecione ao menos uma variável",
							"Erro - seleção de variável", MessageType.ERROR.ordinal());
				} else {
					String result = infController.backward(goal);
					JOptionPane.showMessageDialog(null, "O objetivo '"+goal+"' é '"+result.toUpperCase()+"'.", "Resultado - Backward", MessageType.WARNING.ordinal());
					telaQuestionario.dispose();
					listaVariaveisRadio = new ArrayList<>();
					infController.wipeData();
					flag = false;
				}
			}

			for (JRadioButton jRadioButton1 : listaVariaveisRadio) {
				if (e.getSource() == jRadioButton1) {
					if (jRadioButton1.isSelected()) {
						for (JRadioButton jRadioButton2 : listaVariaveisRadio) {
							if (jRadioButton1 != jRadioButton2) {
								jRadioButton2.setEnabled(false);
							}
						}
					} else {
						for (JRadioButton jRadioButton2 : listaVariaveisRadio) {
							jRadioButton2.setEnabled(true);
						}
					}
				}
			}

		}
	}
}
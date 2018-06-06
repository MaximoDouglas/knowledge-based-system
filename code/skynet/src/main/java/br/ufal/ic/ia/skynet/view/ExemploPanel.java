package br.ufal.ic.ia.skynet.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

@SuppressWarnings("serial")
public class ExemploPanel extends JFrame {

	private JButton executar, voltar;
	private JRadioButton forward, forwardExplicacao, backward, consulta;
	private JFrame backMenu;
	private InferenceController infController;
	private JPanel painelPrincipal;

	public ExemploPanel(JFrame backMenu) {

		super("Exemplo - Funcionamento do motor");

		this.backMenu = backMenu;
		this.painelPrincipal = new JPanel();

		InferenceMotorHandler handler = new InferenceMotorHandler();

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
		
		String[] colunas = { "Base de fatos - " + sequence};

		JTable tabela;
		String[][] linhasStr = new String[linhas.size()][1];
		
		int i = 0;
		
		for (String string : linhas) {
			String[] str = {string};
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
		
		String[] colunas = { "Antecedente", "Consequente"};

		JTable tabela;
		String[][] linhasStr = new String[linhas.size()][2];
		
		int i = 0;
		
		for (Pair<String, String> regra : linhas) {
			String[] str = {regra.getKey(), regra.getValue()};
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
					infController.wipeData();
				} else if (backward.isSelected()) {
					
				} else if (consulta.isSelected()) {
					
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
		}

	}
}
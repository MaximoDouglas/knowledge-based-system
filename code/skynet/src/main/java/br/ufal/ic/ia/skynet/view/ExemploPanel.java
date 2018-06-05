package br.ufal.ic.ia.skynet.view;

import java.awt.GridBagLayout;
import java.awt.TrayIcon.MessageType;
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

@SuppressWarnings("serial")
public class ExemploPanel extends JFrame {

	private JButton executar, voltar;
	private JRadioButton forward, forwardExplicacao, backward, consulta;
	private JFrame backMenu, instance;
	private InferenceController infController;

	public ExemploPanel(JFrame backMenu) {

		super("Exemplo - Funcionamento do motor");

		this.backMenu = backMenu;
		this.instance = this;
		try {
			this.infController = new InferenceController();
		} catch (InvalidArgs e) {
			JOptionPane.showMessageDialog(null, "Algum erro ocorreu.");
			dispose();
			backMenu.setVisible(true);
		}

		InferenceMotorHandler handler = new InferenceMotorHandler();

		forward = new JRadioButton("Forward");
		forward.addActionListener(handler);

		forwardExplicacao = new JRadioButton("Forward com explicação");
		forwardExplicacao.addActionListener(handler);

		backward = new JRadioButton("Backward");
		backward.addActionListener(handler);

		consulta = new JRadioButton("Consulta");
		consulta.addActionListener(handler);

		JPanel painelOpcoes = new JPanel();
		painelOpcoes.add(forward);
		painelOpcoes.add(forwardExplicacao);
		painelOpcoes.add(backward);
		painelOpcoes.add(consulta);

		executar = new JButton("Executar");
		executar.addActionListener(handler);
		voltar = new JButton("Voltar");
		voltar.addActionListener(handler);

		JPanel painelBotoes = new JPanel();
		painelBotoes.add(executar);
		painelBotoes.add(voltar);

		JPanel painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
		painelPrincipal.add(painelOpcoes);
		painelPrincipal.add(painelBotoes);

		add(painelPrincipal);
		setLayout(new GridBagLayout());
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 200);
		setVisible(true);

	}

	private void CreateAndShowTable(List<String> linhas) {
		String[] colunas = { "Fatos" };

		JTable tabela;
		String[][] linhasStr = new String[linhas.size()][1];
		
		int i = 0;
		
		for (String string : linhas) {
			String[] str = {string};
			linhasStr[i] = str;
			i++;
		}
		
		tabela = new JTable(linhasStr, colunas);
		
		JScrollPane barraRolagem = new JScrollPane(tabela);

		add(barraRolagem);
		barraRolagem.setVisible(true);

	}

	private class InferenceMotorHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == executar) {
				if (forward.isSelected()) {

					List<String> lista = infController.forward();

//					String msg = "";
//
//					for (String string : lista) {
//						msg += string + "\n";
//					}
//					JOptionPane.showMessageDialog(null, msg, "Resultado", MessageType.WARNING.ordinal());

					CreateAndShowTable(lista);
					
				} else if (forwardExplicacao.isSelected()) {
					new EdicaoPanel(instance);
				} else if (backward.isSelected()) {
					new ConsultasPanel(instance);
				} else if (consulta.isSelected()) {
					new ExemploPanel(instance);
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
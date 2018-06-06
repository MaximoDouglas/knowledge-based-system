package br.ufal.ic.ia.skynet.view;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
@SuppressWarnings("serial")
public class InferenceMotorPanel extends JFrame {
	
	private JButton executar, voltar;
	private JRadioButton recomendacao, consultarRegras, exemploInferencia;
	private JFrame mainMenu, instance;

	public InferenceMotorPanel(JFrame mainMenu) {
		super("Motor de inferência");

		this.mainMenu = mainMenu;
		this.instance = this;
		InferenceMotorHandler handler = new InferenceMotorHandler();

		recomendacao = new JRadioButton("Recomendação");
		recomendacao.addActionListener(handler);
		
		consultarRegras = new JRadioButton("Consultar regras");
		consultarRegras.addActionListener(handler);
		
		exemploInferencia = new JRadioButton("Exemplos");
		exemploInferencia.addActionListener(handler);
		
		JPanel painelOpcoes = new JPanel();
		painelOpcoes.add(recomendacao);
		painelOpcoes.add(consultarRegras);
		painelOpcoes.add(exemploInferencia);
		
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
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,200);
		setVisible(true);

	}

	private class InferenceMotorHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == executar) {
				if (recomendacao.isSelected()) {
					setVisible(false);
					new RecomendacaoPanel(instance);
				} else if (consultarRegras.isSelected()) {
					setVisible(false);
					new consultarRegrasPanel(instance);
				} else if (exemploInferencia.isSelected()) {
					setVisible(false);
					new ExemploPanel(instance);
				} else {
					JOptionPane.showMessageDialog(null, "Selecione ao menos uma opção.");
				}
			}
			
			if (e.getSource() == recomendacao) {
				if (recomendacao.isSelected()) {
					consultarRegras.setEnabled(false);
					exemploInferencia.setEnabled(false);
				} else {
					consultarRegras.setEnabled(true);
					exemploInferencia.setEnabled(true);
				}
			}
			if (e.getSource() == consultarRegras) {
				if (consultarRegras.isSelected()) {
					recomendacao.setEnabled(false);
					exemploInferencia.setEnabled(false);
				} else {
					recomendacao.setEnabled(true);
					exemploInferencia.setEnabled(true);
				}
			}
			if (e.getSource() == exemploInferencia) {
				if (exemploInferencia.isSelected()) {
					consultarRegras.setEnabled(false);
					recomendacao.setEnabled(false);
				} else {
					consultarRegras.setEnabled(true);
					recomendacao.setEnabled(true);
				}
			}
			if (e.getSource() == voltar) {
				dispose();
				mainMenu.setVisible(true);
			}
		}

	}
}
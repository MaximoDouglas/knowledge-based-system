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
	private JRadioButton recomendacao, edicao;
	private JFrame mainMenu;

	public InferenceMotorPanel(JFrame mainMenu) {
		super("Motor de inferência");

		this.mainMenu = mainMenu;
		InferenceMotorHandler handler = new InferenceMotorHandler();

		recomendacao = new JRadioButton("Recomendação");
		recomendacao.addActionListener(handler);
		
		edicao = new JRadioButton("Editar dados ou regras");
		edicao.addActionListener(handler);
		
		JPanel painelOpcoes = new JPanel();
		painelOpcoes.add(recomendacao);
		painelOpcoes.add(edicao);
		
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
		setSize(500,200);
		setVisible(true);

	}

	public class InferenceMotorHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == executar) {
				if (recomendacao.isSelected()) {

				} else if (edicao.isSelected()) {

				} else {
					JOptionPane.showMessageDialog(null, "Selecione ao menos uma opção.");
				}
			}
			if (e.getSource() == recomendacao) {
				if (recomendacao.isSelected()) {
					edicao.setEnabled(false);
				} else {
					edicao.setEnabled(true);
				}
			}
			if (e.getSource() == edicao) {
				if (edicao.isSelected()) {
					recomendacao.setEnabled(false);
				} else {
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
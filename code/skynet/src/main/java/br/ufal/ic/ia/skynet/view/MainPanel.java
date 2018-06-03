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
public class MainPanel extends JFrame {

	private JButton executar, sair;
	private JRadioButton eightPuzzle, inferenceMotor;
	private String[] args;
	private MainPanel instante;

	public MainPanel(String[] args) {
		super("Skynet");

		this.instante = this;
		this.args = args;
		MainMenuHandler handler = new MainMenuHandler();
		
		eightPuzzle = new JRadioButton("Jogo dos 8");
		eightPuzzle.addActionListener(handler);
		
		inferenceMotor = new JRadioButton("Motor de inferência");
		inferenceMotor.addActionListener(handler);
		
		executar = new JButton("Executar");
		executar.addActionListener(handler);
		
		sair = new JButton("Sair");
		sair.addActionListener(handler);
		
		JPanel painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.X_AXIS));
		
		JPanel painelOpcoes = new JPanel();
		painelOpcoes.setLayout(new BoxLayout(painelOpcoes, BoxLayout.X_AXIS));
		
		painelBotoes.add(executar);
		painelBotoes.add(sair);
		
		painelOpcoes.add(eightPuzzle);
		painelOpcoes.add(inferenceMotor);
		
		painelPrincipal.add(painelOpcoes);
		painelPrincipal.add(painelBotoes);
		
		add(painelPrincipal);
		
		setLayout(new GridBagLayout());
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,200);
		setVisible(true);
	}

	public class MainMenuHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == executar) {
				if (eightPuzzle.isSelected()) {
					setVisible(false);
					new EightPuzzlePanel(instante);
				} else if (inferenceMotor.isSelected()) {
					setVisible(false);
					new InferenceMotorPanel(args, instante);
				} else {
					JOptionPane.showMessageDialog(null, "Selecione ao menos uma opção.");
				}
			}
			if (e.getSource() == eightPuzzle) {
				if (eightPuzzle.isSelected()) {
					inferenceMotor.setEnabled(false);
				} else {
					inferenceMotor.setEnabled(true);
				}
			}
			if (e.getSource() == inferenceMotor) {
				if (inferenceMotor.isSelected()) {
					eightPuzzle.setEnabled(false);
				} else {
					eightPuzzle.setEnabled(true);
				}
			}
			if (e.getSource() == sair) {
				dispose();
			}
		}

	}
}

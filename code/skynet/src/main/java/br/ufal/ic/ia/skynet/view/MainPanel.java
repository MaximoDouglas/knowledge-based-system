package br.ufal.ic.ia.skynet.view;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class MainPanel extends JFrame {

	private JButton go, exit;
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
		
		go = new JButton("GO!");
		go.addActionListener(handler);
		
		exit = new JButton("Exit");
		exit.addActionListener(handler);
		
		add(eightPuzzle);
		add(inferenceMotor);
		add(go);
		add(exit);
		
		setLayout(new GridBagLayout());
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,200);
		setVisible(true);
	}

	public class MainMenuHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == go) {
				if (eightPuzzle.isSelected()) {
					setVisible(false);
					new EightPuzzlePanel(instante);
				} else if (inferenceMotor.isSelected()) {
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
			if (e.getSource() == exit) {
				dispose();
			}
		}

	}
}

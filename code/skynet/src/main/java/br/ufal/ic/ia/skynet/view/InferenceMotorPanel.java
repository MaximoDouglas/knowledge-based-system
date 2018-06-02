package br.ufal.ic.ia.skynet.view;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
@SuppressWarnings("serial")
public class InferenceMotorPanel extends JFrame {
	private JButton go, back;
	private JRadioButton eightPuzzle, inferenceMotor;
	private String[] args;
	private JFrame mainMenu;

	public InferenceMotorPanel(String[] args, JFrame mainMenu) {
		super("Skynet");

		this.mainMenu = mainMenu;
		this.args = args;
		MainMenuHandler handler = new MainMenuHandler();

		setLayout(new GridBagLayout());
		setResizable(true);

		eightPuzzle = new JRadioButton("Jogo dos 8");
		eightPuzzle.addActionListener(handler);
		
		inferenceMotor = new JRadioButton("Motor de inferência");
		inferenceMotor.addActionListener(handler);
		
		go = new JButton("GO!");
		go.addActionListener(handler);
		back = new JButton("Voltar");
		back.addActionListener(handler);
		
		add(eightPuzzle);
		add(inferenceMotor);
		add(go);
		add(back);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,200);
		setVisible(true);

	}

	public class MainMenuHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == go) {
				if (eightPuzzle.isSelected()) {

				} else if (inferenceMotor.isSelected()) {

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
			if (e.getSource() == back) {
				dispose();
				mainMenu.setVisible(true);
			}
		}

	}
}
package br.ufal.ic.ia.skynet.view;

import java.awt.GridBagLayout;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufal.ic.ia.skynet.exceptions.UnsovableException;
import br.ufal.ic.ia.skynet.jogo_dos_8.controller.EightPuzzleController;

@SuppressWarnings("serial")
public class EightPuzzlePanel extends JFrame {

	private JButton executar, voltar;
	private JTextField sequenceTxt;
	private JFrame mainMenu;

	public EightPuzzlePanel(JFrame mainMenu) {
		super("Jogo dos 8");
		this.mainMenu = mainMenu;
		
		EightPuzzleHandler handler = new EightPuzzleHandler();
		
		executar = new JButton("Executar");
		executar.addActionListener(handler);
		
		voltar = new JButton("Voltar");
		voltar.addActionListener(handler);
		
		sequenceTxt = new JTextField();
		sequenceTxt.setColumns(6);
		
		JPanel painelTxt = new JPanel();
		painelTxt.add(sequenceTxt);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.add(executar);
		painelBotoes.add(voltar);
		
		JPanel painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
		
		painelPrincipal.add(painelTxt);
		painelPrincipal.add(painelBotoes);
		
		add(painelPrincipal);
		setLayout(new GridBagLayout());
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,200);
		setVisible(true);
	}

	private class EightPuzzleHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == executar) {
				if (sequenceTxt.getText().length() == 9) {
					try {
						if (EightPuzzleController.isSoluvable(sequenceTxt.getText())) {
							try {
								EightPuzzleController controller = new EightPuzzleController(sequenceTxt.getText());
								
								JOptionPane.showMessageDialog(null, "Profundidade BFS: " + controller.bfs() + "\nProfundidade DFS: " + controller.dfs() + "\nProfundidade DFS Iterativo: " + controller.dfsIterativo(), "Resultados", MessageType.WARNING.ordinal());
								
							} catch (UnsovableException e1) {
								JOptionPane.showMessageDialog(null, "Sequência insolúvel. Tente novamente. \nOs 9 números devem estar num intervalo entre 0 e 8 e não podem haver elementos repetidos", "Sequência insolúvel", MessageType.WARNING.ordinal());
							}
						} else {
							JOptionPane.showMessageDialog(null, "Sequência insolúvel. Tente novamente. \nOs 9 números devem estar num intervalo entre 0 e 8 e não podem haver elementos repetidos", "Sequência insolúvel", MessageType.WARNING.ordinal());
						}
					} catch (UnsovableException e1) {
						JOptionPane.showMessageDialog(null, "Sequência insolúvel. Tente novamente. \nOs 9 números devem estar num intervalo entre 0 e 8 e não podem haver elementos repetidos", "Sequência insolúvel", MessageType.WARNING.ordinal());
					}
				} else {
					JOptionPane.showMessageDialog(null, "Os 9 números devem estar num intervalo entre 0 e 8 e não podem haver elementos repetidos.", "Preenchimento incorreto", MessageType.ERROR.ordinal());
				}
			}
			if (e.getSource() == voltar) {
				dispose();
				mainMenu.setVisible(true);
			}
		}

	}
}
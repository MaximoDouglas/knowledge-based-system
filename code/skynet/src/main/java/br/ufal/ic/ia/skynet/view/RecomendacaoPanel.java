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

import br.ufal.ic.ia.skynet.exceptions.InvalidArgs;
import br.ufal.ic.ia.skynet.motor_inferencia.controller.InferenceController;

@SuppressWarnings("serial")
public class RecomendacaoPanel extends JFrame {

	private JButton executar, voltar;
	private InferenceController infController;
	private List<JRadioButton> funcionalidades;
	private RecomendacaoHandler handler;
	private JFrame backMenu;

	public RecomendacaoPanel(JFrame backMenu) {
		super("Motor de recomendação");

		this.handler = new RecomendacaoHandler();
		this.backMenu = backMenu;
		this.executar = new JButton("Executar");
		executar.addActionListener(handler);
		this.voltar = new JButton("Voltar");
		voltar.addActionListener(handler);

		this.funcionalidades = new ArrayList<>();

		JPanel painelFuncionalidades = new JPanel();
		painelFuncionalidades.setLayout(new BoxLayout(painelFuncionalidades, BoxLayout.Y_AXIS));

		for (String funcName : InferenceController.getFuncionalidades()) {
			funcionalidades.add(new JRadioButton(funcName));
		}

		for (JRadioButton jRadioButton : funcionalidades) {
			painelFuncionalidades.add(jRadioButton);
		}

		JScrollPane barraRolagem = new JScrollPane(painelFuncionalidades);

		JPanel painelBotoes = new JPanel();
		painelBotoes.add(executar);
		painelBotoes.add(voltar);

		JPanel painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));

		painelPrincipal.add(barraRolagem);
		painelPrincipal.add(painelBotoes);
		add(painelPrincipal);
		setLayout(new FlowLayout());
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 600);
		setVisible(true);
	}

	private class RecomendacaoHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == executar) {
				List<String> funcSelecionadas = new ArrayList<>();

				for (JRadioButton func : funcionalidades) {
					if (func.isSelected()) {
						funcSelecionadas.add(func.getText());
					}
				}

				if (!funcSelecionadas.isEmpty()) {
					try {
						infController = new InferenceController(funcSelecionadas);
						
						List<String> forwardResult = infController.forward();
						
					} catch (InvalidArgs e1) {
						JOptionPane.showMessageDialog(null, "Algum erro ocorreu.");
						dispose();
						backMenu.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione ao menos uma opção", "Erro", MessageType.ERROR.ordinal());
				}
			}

			if (e.getSource() == voltar) {
				dispose();
				backMenu.setVisible(true);
			}

		}
	}
}

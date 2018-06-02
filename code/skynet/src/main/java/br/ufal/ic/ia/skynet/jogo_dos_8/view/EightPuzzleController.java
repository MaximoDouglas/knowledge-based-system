package br.ufal.ic.ia.skynet.jogo_dos_8.view;

import java.util.ArrayList;
import java.util.List;

import br.ufal.ic.ia.skynet.jogo_dos_8.modelo.Resolver;

public class EightPuzzleController {
	
	private static int[][] configGlobal;
	
	public EightPuzzleController() {
		Resolver resolver = new Resolver(configGlobal);
	}
	
	public static boolean isSoluvable(String initialConfig) {

		int[][] config = new int[3][3];

		config[0][0] = Integer.parseInt(String.valueOf(initialConfig.charAt(0)));
		config[0][1] = Integer.parseInt(String.valueOf(initialConfig.charAt(1)));
		config[0][2] = Integer.parseInt(String.valueOf(initialConfig.charAt(2)));
		config[1][0] = Integer.parseInt(String.valueOf(initialConfig.charAt(3)));
		config[1][1] = Integer.parseInt(String.valueOf(initialConfig.charAt(4)));
		config[1][2] = Integer.parseInt(String.valueOf(initialConfig.charAt(5)));
		config[2][0] = Integer.parseInt(String.valueOf(initialConfig.charAt(6)));
		config[2][1] = Integer.parseInt(String.valueOf(initialConfig.charAt(7)));
		config[2][2] = Integer.parseInt(String.valueOf(initialConfig.charAt(8)));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(config[i][j]);
			}
		}

		List<Integer> lista = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!lista.contains(config[i][j])) {
					lista.add(config[i][j]);
				} else {
					return false;
				}
			}
		}

		int counter = 0;

		for (int i = 0; i < lista.size(); i++) {
			for (int j = 0; j < lista.size(); j++) {
				if (j > i && lista.get(i) != 0 && lista.get(j) != 0 && lista.get(i) > lista.get(j)) {
					counter++;
				}
			}
		}

		if (counter % 2 == 0) {
			configGlobal = config;
		}
		
		return (counter % 2 == 0);
	}

}

package br.ufal.ic.ia.skynet.jogo_dos_8.controller;

import java.util.ArrayList;
import java.util.List;

import br.ufal.ic.ia.skynet.exceptions.UnsovableException;
import br.ufal.ic.ia.skynet.jogo_dos_8.modelo.Resolver;

public class EightPuzzleController {

	private Resolver resolver;

	public EightPuzzleController(String initialConfig) throws UnsovableException {
		if (!isSoluvable(initialConfig)) {
			throw new UnsovableException("Sequência insolúvel");
		}

		this.resolver = new Resolver(setConfig(initialConfig));
	}

	public int bfs() {
		return resolver.BFS();
	}

	public int dfs() {
		return resolver.DFS();
	}

	public int dfsIterativo() {
		return resolver.DFS_profundidade_iterativa();
	}

	public static boolean isSoluvable(String initialConfig) throws UnsovableException {
		
		try {

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

			List<Integer> lista = new ArrayList<>();

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (!lista.contains(config[i][j]) && config[i][j] <= 8 && config[i][j] >= 0) {
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

			return (counter % 2 == 0);

		} catch (Exception e) {
			throw new UnsovableException("Sequência insolúvel.");
		}
	}

	public int[][] setConfig(String initialConfig) {

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

		return config;
	}

}

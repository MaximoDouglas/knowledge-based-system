package br.ufal.ic.ia.skynet.jogo_dos_8.view;

import br.ufal.ic.ia.skynet.jogo_dos_8.modelo.Resolver;

public class Runner {

	public static void main(String[] args) {

		System.out.print("Configuração inicial:	");
		System.out.print("[");
		int[][] config = newConfig2();
		System.out.print("]");
		System.out.println("\n");

		Resolver resolver = new Resolver(config);

		if (resolver.isSoluvable()) {
			System.out.println("BFS: ");
			System.out.println("Iterações:	"+resolver.BFS());
			System.out.println();

			System.out.println("DFS: ");
			System.out.println("Iterações:	"+resolver.DFS());
			System.out.println();

			System.out.println("DFS Iterativo - Limite inicial: " + resolver.getLimit() + " - Taxa: " + resolver.getTaxa());
			int[] dfs_i = resolver.DFS_profundidade_iterativa();
			System.out.println("Profundidade:	"+dfs_i[1]);
			System.out.println("Iterações:	"+dfs_i[0]);
			System.out.println();
		} else {
			System.out.println("Configuração insolúvel.");
		}

	}

	public static int[][] newConfig(int i) {
		int[][] config = new int[3][3];

		int count = 0;
		int aux = 1;

		for (int l = 0; l < 3; l++) {
			for (int k = 0; k < 3; k++) {
				if (count == i) {
					config[l][k] = 0;
				} else {
					config[l][k] = aux;
					aux++;
				}

				System.out.print(config[l][k]);

				if (l*k != 4) {
					System.out.print(" ");
				}
				count++;
			}
		}

		return config;

	}

	//Lembrar sempre de configurar a VM com o parâmetro -Xss515m
	public static int[][] newConfig2() {
		int[][] config = new int[3][3];

		config[0][0] = 1;
		config[0][1] = 2;
		config[0][2] = 3;
		config[1][0] = 4;
		config[1][1] = 5;
		config[1][2] = 6;
		config[2][0] = 7;
		config[2][1] = 0;
		config[2][2] = 8;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(config[i][j]);
			}
		}

		return config;

	}

}

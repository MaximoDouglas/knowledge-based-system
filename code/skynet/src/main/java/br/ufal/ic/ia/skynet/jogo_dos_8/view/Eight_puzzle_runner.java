package br.ufal.ic.ia.skynet.jogo_dos_8.view;

import java.util.Scanner;

import br.ufal.ic.ia.skynet.jogo_dos_8.modelo.Resolver;

public class Eight_puzzle_runner {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);

		String initialConfig;
		int opt = 1;
		
		Resolver resolver = null;
		
		while (opt != 0) {
			System.out.println("Digite a sequência inicial do jogo:");
			initialConfig = ler.next();
			System.out.print("Configuração inicial:	");
			System.out.print("[");
			int[][] config = newConfig2(initialConfig);
			System.out.print("]");
			System.out.println("\n");
			resolver = new Resolver(config);
			
			if (!resolver.isSoluvable()) {
				System.out.println("Ordem digitada é insolúvel. Deseja tentar novamente? (1 - sim/2 - não)");
				opt = ler.nextInt();
				
				if (opt == 2) {
					return;
				}
			} else {
				opt = 0;
			}
		}

		if (resolver.isSoluvable()) {
			System.out.println("DFS: ");
			System.out.println("Altura:	"+resolver.DFS());
			System.out.println();

			System.out.println("BFS: ");
			System.out.println("Altura:	"+resolver.BFS());
			System.out.println();

			System.out.println("DFS Iterativo - Limite inicial: " + resolver.getLimit() + " - Taxa: " + resolver.getTaxa());
			System.out.println("Altura:	"+resolver.DFS_profundidade_iterativa());
			System.out.println();
		} else {
			System.out.println("Configuração insolúvel.");
		}
	}

	//Lembrar sempre de configurar a VM com o parâmetro -Xss515m
	public static int[][] newConfig2(String initialConfig) {
		
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

		return config;

	}

}

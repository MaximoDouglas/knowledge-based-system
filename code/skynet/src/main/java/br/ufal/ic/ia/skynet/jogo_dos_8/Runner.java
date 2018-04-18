package br.ufal.ic.ia.skynet.jogo_dos_8;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Runner {
	private static int iCounter = 0;
	private static int limite = 0;
	private static int pCounter = 0;
	private static boolean found = false;

	public static void main(String[] args) {
		State firstState = new State();

		System.out.print("Configuração inicial:	");
		System.out.print("[");
		firstState.config = newConfig2();
		System.out.print("]");
		System.out.println("\n");

		if (solvable(firstState.config)) {

			System.out.println("BFS: ");
			BFS(firstState);
			System.out.println();

			State.resetHash();
			iCounter = 0;

			System.out.println("DFS: ");
			DFS(firstState);
			System.out.println();

			State.resetHash();
			iCounter = 0;

			while(!found) {
				System.out.println("DFS iterativo - Limite inicial: "+ limite);
				DFS_profundidade_iterativa(firstState);
				System.out.println();
				limite = limite + 1000;
				State.resetHash();
				iCounter = 0;
				pCounter = 0;
			}
		} else {
			System.out.println("Configuração insolúvel");
		}
	}

	public static void BFS(State state) {

		Stack<State> stack = new Stack<State>();
		stack.push(state);

		while (!stack.isEmpty()) {

			State t = stack.pop();
			if (!t.verifyState()) {
				List<State> ts = State.newState(t);
				ts.forEach(s -> {if (!s.exists()) {
					iCounter++;
					stack.push(s);}});

			} else {
				System.out.println("Iterações:	"+iCounter);
			}
		}
	}

	public static void DFS(State state) {

		if (!state.verifyState()) {

			List<State> ts = State.newState(state);
			ts.forEach(s -> {if (!s.exists()) {
				iCounter++;
				DFS(s);}});

		} else {
			System.out.println("Iterações:	"+iCounter);
		}
	}

	public static void DFS_profundidade_iterativa(State state) {

		if (!state.verifyState()) {
			List<State> ts = State.newState(state);
			ts.forEach(s -> {if (!s.exists() && pCounter < limite) {
				iCounter++;
				pCounter++;
				DFS_profundidade_iterativa(s);
			} else if (pCounter == limite) {
				pCounter--;
			}});
		} else {
			System.out.println("Profundidade:	"+pCounter);
			System.out.println("Iterações:	"+iCounter);
			found = true;
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

	public static int[][] newConfig2() {
		int[][] config = new int[3][3];

		config[0][0] = 2;
		config[0][1] = 8;
		config[0][2] = 3;
		config[1][0] = 1;
		config[1][1] = 6;
		config[1][2] = 4;
		config[2][0] = 7;
		config[2][1] = 0;
		config[2][2] = 5;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(config[i][j]);
			}
		}

		return config;

	}

	private static boolean solvable(int[][] config) {

		List<Integer> lista = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				lista.add(config[i][j]);
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

		return (counter%2 == 0);
	}

}

package br.ufal.ic.ia.skynet.jogo_dos_8;

import java.util.List;
import java.util.Stack;

public class Runner {
	static int iCounter = 0;

	public static void main(String[] args) {
		State firstState = new State();

		for (int i = 0; i < 9; i++) {
			
			System.out.print("[");
			firstState.config = newConfig(i);
			System.out.print("]");
			
			System.out.println("\n");
			
			System.out.println("BFS: ");
			BFS(firstState);
			System.out.println();
			
			State.resetHash();
			iCounter = 0;
			
			System.out.println("DFS: ");
			DFS(firstState);
			System.out.println("---------------------------");
			System.out.println();
			
			State.resetHash();
			iCounter = 0;
		}
	}

	public static void BFS(State state) {

		Stack<State> stack = new Stack<State>();
		stack.push(state);

		while (!stack.isEmpty()) {
			iCounter++;
			State t = stack.pop();

			if (!t.verifyState()) {
				if (!t.exists()) {
					List<State> ts = State.newState(t);
					ts.forEach(s -> stack.push(s));
				}

			} else {
				System.out.println("	"+iCounter);
			}
		}
	}

	public static void DFS(State state) {
		iCounter++;

		if (!state.verifyState()) {
			if (!state.exists()) {
				List<State> ts = State.newState(state);
				ts.forEach(s -> DFS(s));
			}

		} else {
			System.out.println("	"+iCounter);
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

}

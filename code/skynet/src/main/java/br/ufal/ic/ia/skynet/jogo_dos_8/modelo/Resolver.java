package br.ufal.ic.ia.skynet.jogo_dos_8.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Resolver {

	private final int[][] initialConfiguration;
	private State initialState;
	private boolean found = false;
	private int limite = 0;
	private final int taxa = 1000;
	private int bfsReturn;
	private int dfsReturn;
	private int dfsReturnI;

	public Resolver (int[][] initialConfiguration){
		this.initialConfiguration = initialConfiguration;
		this.initialState = new State(initialConfiguration, 0);
	}

	public int BFS() {
		if (solvable(initialConfiguration)) {
			BFS(initialState);
		} else {
			return -1;
		}

		State.resetHash();
		
		return bfsReturn;
	}

	private void BFS(State state) {

		Stack<State> stack = new Stack<State>();
		stack.push(state);

		while (!stack.isEmpty()) {

			State t = stack.pop();
			if (!t.verifyState()) {
				List<State> ts = State.newState(t);
				ts.forEach(s -> {if (!s.exists()) {
					stack.push(s);}});

			} else {
				bfsReturn = t.getHight();
			}
		}
	}

	public int DFS() {
		if (solvable(initialConfiguration)) {
			DFS(initialState);
		} else {
			return -1;
		}

		State.resetHash();
		
		return dfsReturn;
	}

	private void DFS(State state) {

		if (!state.verifyState()) {

			List<State> ts = State.newState(state);
			ts.forEach(s -> {if (!s.exists()) {
				DFS(s);}});

		} else {
			dfsReturn = state.getHight();
		}
	}

	public int DFS_profundidade_iterativa() {
		
		if (solvable(initialConfiguration)) {
			while(!found) {
				DFS_profundidade_iterativa(initialState);
				limite = limite + taxa;
				State.resetHash();
			}
		}

		return dfsReturnI;
	}

	private void DFS_profundidade_iterativa(State state) {

		if (!state.verifyState()) {
			List<State> ts = State.newState(state);
			ts.forEach(s -> {if (!s.exists() && state.getHight() < limite) {
				DFS_profundidade_iterativa(s);
			}});
		} else {
			dfsReturnI = state.getHight();
			found = true;
		}
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

	public int getLimit() {
		return limite;
	}
	
	public int getTaxa() {
		return taxa;
	}
	
	public boolean isSoluvable() {
		return solvable(initialConfiguration);
	}
}

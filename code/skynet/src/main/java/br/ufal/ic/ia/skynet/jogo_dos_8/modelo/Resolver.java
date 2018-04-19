package br.ufal.ic.ia.skynet.jogo_dos_8.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Resolver {

	private int iCounter = 0;
	private final int[][] initialConfiguration;
	private State initialState;
	private boolean found = false;
	private int limite = 0;
	private final int taxa = 1000;
	private int pCounter = 0;
	private int bfsReturn;
	private int dfsReturn;
	private int dfsReturnI;
	private int dfsReturnP;

	public Resolver (int[][] initialConfiguration){
		this.initialConfiguration = initialConfiguration;
		this.initialState = new State(initialConfiguration);
	}

	public int BFS() {
		if (solvable(initialConfiguration)) {
			BFS(initialState);
		} else {
			return -1;
		}

		State.resetHash();
		iCounter = 0;
		
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
					iCounter++;
					stack.push(s);}});

			} else {
				bfsReturn = iCounter;
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
		iCounter = 0;
		
		return dfsReturn;
	}

	private void DFS(State state) {

		if (!state.verifyState()) {

			List<State> ts = State.newState(state);
			ts.forEach(s -> {if (!s.exists()) {
				iCounter++;
				DFS(s);}});

		} else {
			dfsReturn = iCounter;
		}
	}

	public int[] DFS_profundidade_iterativa() {
		int[] retorno = new int[2];
		
		if (solvable(initialConfiguration)) {
			while(!found) {
				DFS_profundidade_iterativa(initialState);
				limite = limite + taxa;
				State.resetHash();
				iCounter = 0;
				pCounter = 0;
			}
		} else {
			retorno[0] = -1;
			return retorno;
		}

		retorno[0] = dfsReturnI;
		retorno[1] = dfsReturnP;
		return retorno;
	}

	private void DFS_profundidade_iterativa(State state) {

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
			dfsReturnP = pCounter;
			dfsReturnI = iCounter;
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

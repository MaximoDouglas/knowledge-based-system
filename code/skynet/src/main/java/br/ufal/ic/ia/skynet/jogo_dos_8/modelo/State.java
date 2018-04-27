package br.ufal.ic.ia.skynet.jogo_dos_8.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State {
	
	private int hight;

	public void setHight(int hight) {
		this.hight = hight;
	}

	public int[][] config = new int[3][3];
	private static Map<String, State> hash = new HashMap<>();

	public State(int[][] config, int hight){
		this.config = config;
		this.hight = hight;
	}
	
	public static List<State> newState(State oldState) {

		List<State> novosEstados = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (oldState.config[i][j] == 0) {
					
					if (i - 1 >= 0) {
						
						int value = oldState.config[i - 1][j];

						int[][] config = new int[3][3];
						State s = new State(config, oldState.hight + 1);

						for (int l = 0; l < 3; l++) {
							for (int k = 0; k < 3; k++) {
								if (l == i - 1 && k == j) {
									s.config[l][k] = 0;
								} else if (l == i && k == j) {
									s.config[l][k] = value;
								} else {
									s.config[l][k] = oldState.config[l][k];
									
								}
							}
						}
						novosEstados.add(s);
					}

					if (i + 1 <= 2) {
						int value = oldState.config[i + 1][j];
						
						int[][] config = new int[3][3];
						State s = new State(config, oldState.hight + 1);						

						for (int l = 0; l < 3; l++) {
							for (int k = 0; k < 3; k++) {
								if (l == i + 1 && k == j) {
									s.config[l][k] = 0;
								} else if (l == i && k == j) {
									s.config[l][k] = value;
								} else {
									s.config[l][k] = oldState.config[l][k];
								}
							}
						}
						novosEstados.add(s);
					}

					if (j - 1 >= 0) {
						int value = oldState.config[i][j - 1];

						int[][] config = new int[3][3];
						State s = new State(config, oldState.hight + 1);

						for (int l = 0; l < 3; l++) {
							for (int k = 0; k < 3; k++) {
								if (k == j - 1 && l == i) {
									s.config[l][k] = 0;
								} else if (k == j && l == i) {
									s.config[l][k] = value;
								} else {
									s.config[l][k] = oldState.config[l][k];
								}
							}
						}
						novosEstados.add(s);
					}

					if (j + 1 <= 2) {
						int value = oldState.config[i][j + 1];

						int[][] config = new int[3][3];
						State s = new State(config, oldState.hight + 1);

						for (int l = 0; l < 3; l++) {
							for (int k = 0; k < 3; k++) {
								if (k == j + 1 && l == i) {
									s.config[l][k] = 0;
								} else if (k == j && l == i) {
									s.config[l][k] = value;
								} else {
									s.config[l][k] = oldState.config[l][k];
								}
							}
						}
						novosEstados.add(s);
					}
				}
			}
		}
		return novosEstados;
	}
	
	public boolean verifyState() {
		List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 0);
		int c = 0;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.config[i][j] != lista.get(c)) {
					return false;
				}
				c++;
			}
		}
		return true;
	}
	
	public String toString() {
		String retorno = "";
		
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				if (j == 0) {
					retorno += "|";
				}
				
				if (j == 1) {
					retorno += " ";
				}
				
				retorno += this.config[i][j];
				
				if (j == 1) {
					retorno += " ";
				}
				
				if (j == 2) {
					retorno += "|\n";
				}
			}
		}
		
		return retorno;
	}
	
	public boolean exists() {
		
		if (hash.containsKey(this.toString())) {
			return true;
		} else {
			hash.put(this.toString(), this);
			return false;
		}		
	}
	
	public static void resetHash() {
		hash = new HashMap<>();
	}
	

	public int getHight() {
		return hight;
	}
}

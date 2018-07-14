package br.ufal.ic.ia2.minicity.model;

public class City {

	public static void main(String[] args) {
		new City();

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				System.out.print(cells[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static String[][] cells = new String[19][19];
	private static Corner[] corners = new Corner[16];

	public City() {
		setup();
	}

	private void setup() {

		int cornerCounter = 0;
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {

				if ((i == 0 || i%6 == 0) && (j == 0 || j%6 == 0)) {
					if ((i == 0 && j == 0) || (i == 18 && j == 18) || (i == 0 && j == 18) || (i == 18 && j == 0)) {
						cells[i][j] = "o";
					} else {
						cells[i][j] = "i";
					}

					corners[cornerCounter++] = new Corner(i, j, cornerCounter);

				} else if ((i == 0 || i%6 == 0) && (j != 0 || j%6 != 0)) {
					if (i == 0 || i == 6) {
						cells[i][j] = "<";	
					} else if (i == 12 || i == 18) {
						cells[i][j] = ">";
					}
					
				} else if ((j == 0 || j%6 == 0) && (i != 0 || i%6 != 0)) {
					if (j == 0 || j == 6) {
						cells[i][j] = "v";	
					} else if (j == 12 || j == 18) {
						cells[i][j] = "^";
					}
				} else {
					cells[i][j] = " ";
				}
			}
		}
	}
}

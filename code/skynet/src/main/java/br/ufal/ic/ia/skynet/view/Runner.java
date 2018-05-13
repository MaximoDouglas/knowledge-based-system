package br.ufal.ic.ia.skynet.view;

import java.util.Scanner;

import br.ufal.ic.ia.skynet.jogo_dos_8.view.Eight_puzzle_runner;
import br.ufal.ic.ia.skynet.motor_inferencia.view.Inference_runner;

public class Runner {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		int opt = 0;

		while(true) {
			
			System.out.println("Menu:");
			System.out.println("1 - Motor de inferência.");
			System.out.println("2 - Sair...");

			opt = ler.nextInt();

			if (opt == 1) {
				Inference_runner.main(args);
			} else if (opt == 2){
				return;
			} else {
				System.out.println("Opção inválida.");
			}
		}
	}
}


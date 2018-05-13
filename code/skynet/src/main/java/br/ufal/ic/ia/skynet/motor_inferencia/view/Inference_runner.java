package br.ufal.ic.ia.skynet.motor_inferencia.view;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import br.ufal.ic.ia.skynet.motor_inferencia.model.Resolver;

public class Inference_runner {

	public static void main(String[] args) {

		if (args.length == 2) {
			File rules = new File(args[0]);
			File facts = new File(args[1]);

			Scanner ler = new Scanner(System.in);
			int opt = 1;

			try {
				Resolver resolver = new  Resolver(rules, facts);

				while (opt != 4) {
					System.out.println("Menu: ");
					System.out.println("1 - Forward");
					System.out.println("2 - Forward with explanation");
					System.out.println("3 - Backward");	
					System.out.println("4 - Voltar");
					
					opt = ler.nextInt();
					
					if (opt == 1) {
						System.out.println();
						System.out.println("Nova base de fatos:");
						resolver.forwardResult().forEach(System.out::println);
						System.out.println();
					} else if (opt == 2) {
						System.out.println("Forward com explicação: ");
						List<String> base = resolver.forwardResultExplained();
						System.out.println();
						System.out.println("Nova base de fatos: ");
						base.forEach(System.out::println);
						System.out.println();
					} else if (opt == 3) {
						System.out.print("Qual a variável objetivo? ");
						String goal = ler.next();
						System.out.println();
						
						System.out.println("'" + goal + "' é " + resolver.backwardResult(goal));
						System.out.println();
					} else if (opt == 4) {
						return;
					} else { 
						System.out.println("Opção inválida.");;
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}


		} else {
			System.out.println("Um ou mais arquivos não foram passados na execução do programa.");
			try {
				System.out.println("Retornando ao menu anterior...");
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

	public static boolean question (String object) {
		Scanner read = new Scanner(System.in);

		String response = "";

		while(!response.equals("s") && !response.equals("N")) {
			System.out.println("A proposição '"+ object + "' é verdade? (s/N)");
			response = read.next();
		}

		return (response.equals("s")) ? true : false; 
	}

}

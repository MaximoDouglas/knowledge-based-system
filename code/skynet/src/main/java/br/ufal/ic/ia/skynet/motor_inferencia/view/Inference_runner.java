package br.ufal.ic.ia.skynet.motor_inferencia.view;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import br.ufal.ic.ia.skynet.exceptions.InvalidArgs;
import br.ufal.ic.ia.skynet.motor_inferencia.model.Resolver;

public class Inference_runner {

	private static Scanner read;

	public static Scanner getRead() {
		return read;
	}

	public static void setRead(Scanner read) {
		Inference_runner.read = read;
	}

	public static void main(String[] args) throws InvalidArgs {

		if (args.length == 2) {
			File rules = new File(args[0]);
			File facts = new File(args[1]);

			if(read == null) {
				read = new Scanner(System.in);	
			}

			int opt = 1;

			Resolver resolver = new  Resolver(rules, facts);

			while (opt != 4) {
				System.out.println("Menu: ");
				System.out.println("1 - Forward");
				System.out.println("2 - Forward with explanation");
				System.out.println("3 - Backward");	
				System.out.println("4 - Voltar");

				opt = read.nextInt();

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
					String goal = read.next();
					System.out.println();

					System.out.println("'" + goal + "' é " + resolver.backwardResult(goal));
					System.out.println();
				} else if (opt == 4) {
					return;
				} else { 
					System.out.println("Opção inválida.");;
				}
			}

		} else {
			throw new InvalidArgs("Um ou mais arquivos não foram passados na execução do programa.");
		}
	}

	public static boolean question (String object) {

		if(read == null) {
			read = new Scanner(System.in);	
		}

		String response = "";

		while(!response.equals("s") && !response.equals("N")) {
			System.out.println("A proposição '"+ object + "' é verdade? (s/N)");
			response = read.next();
		}

		return (response.equals("s")) ? true : false; 
	}

}

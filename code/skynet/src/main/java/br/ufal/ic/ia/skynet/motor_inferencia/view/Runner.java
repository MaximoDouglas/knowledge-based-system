package br.ufal.ic.ia.skynet.motor_inferencia.view;

import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		
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

package br.ufal.ic.ia.skynet.exceptions;

@SuppressWarnings("serial")
public class InvalidArgs extends Exception{

	public InvalidArgs() {}
	
	public InvalidArgs(String message) {
		super(message);
	}
	
}

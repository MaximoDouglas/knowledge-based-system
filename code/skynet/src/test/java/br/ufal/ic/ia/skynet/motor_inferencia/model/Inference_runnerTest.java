package br.ufal.ic.ia.skynet.motor_inferencia.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import br.ufal.ic.ia.skynet.exceptions.InvalidArgs;
import br.ufal.ic.ia.skynet.motor_inferencia.view.Inference_runner;

class Inference_runnerTest {

	@Test
	void testMain() {
		
		String[] args = new String[0];
		assertThrows(InvalidArgs.class, () -> Inference_runner.main(args));
		
		String[] args1 = new String[2];
		args1[0] = "teste";
		args1[1] = "de software";
		assertThrows(InvalidArgs.class, () -> Inference_runner.main(args1));
		
	}

	@Test
	void testQuestion() {
		
		//Testa para caso o usuário digite 's'
		System.setIn(inputMock("s"));
		Inference_runner.setRead(new Scanner(System.in));
		assertEquals(true, Inference_runner.question("Objeto"));
		
		//Testa para caso o usuário digite 'N'
		System.setIn(inputMock("N"));
		Inference_runner.setRead(new Scanner(System.in));
		assertEquals(false, Inference_runner.question("Objeto"));
	}

	private InputStream inputMock(String msg){
		InputStream in = new ByteArrayInputStream(msg.getBytes());
		return in;
	}	
}

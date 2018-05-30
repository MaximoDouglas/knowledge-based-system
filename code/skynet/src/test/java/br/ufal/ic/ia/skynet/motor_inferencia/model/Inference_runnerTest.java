package br.ufal.ic.ia.skynet.motor_inferencia.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import br.ufal.ic.ia.skynet.exceptions.InvalidArgs;
import br.ufal.ic.ia.skynet.motor_inferencia.view.Inference_runner;

class Inference_runnerTest {

	private InputStream in;
	private PrintStream out;
	private File outputFile = new File("test/outputtest.txt");
	private File outputFileExpected = new File("test/outputtestExpected.txt");

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
	void testQuestion() throws InvalidArgs, IOException {

		//Testa para caso o usuário digite 's'
		inputMock("s");
		System.setIn(in);
		Inference_runner.setRead(new Scanner(System.in));
		assertEquals(true, Inference_runner.question("Objeto"));

		//Testa para caso o usuário digite 'N'
		inputMock("N");
		System.setIn(in);
		Inference_runner.setRead(new Scanner(System.in));
		assertEquals(false, Inference_runner.question("Objeto"));

		//Testa para quando entrar no menu
		inputMock("1");
		System.setIn(in);
		outputMock();
		System.setOut(out);
		Inference_runner.setRead(new Scanner(System.in));

		String[] args = new String[2];
		args[0] = "test/regras.txt";
		args[1] = "test/fatos.txt";
		Inference_runner.main(args);
		assertEquals(FileUtils.readFileToString(outputFileExpected, "utf-8"), FileUtils.readFileToString(outputFile, "utf-8"));

	}

	private void inputMock(String msg) throws IOException{
		in = new ByteArrayInputStream(msg.getBytes());
	}
	
	private void outputMock() throws FileNotFoundException {

		out = new PrintStream(new File("test/outputtest.txt"));

	}
	
}

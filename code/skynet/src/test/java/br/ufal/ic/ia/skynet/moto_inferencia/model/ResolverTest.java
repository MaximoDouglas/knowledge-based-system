package br.ufal.ic.ia.skynet.moto_inferencia.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.ufal.ic.ia.skynet.motor_inferencia.model.Resolver;

class ResolverTest {
	
	@Test
	void forWardTest() throws IOException {
		
		File rulesFile = new File("regras.txt");
		File factsFile = new File("fatos.txt");
		
		Resolver resolver = new Resolver(rulesFile, factsFile);
		
		List<String> expected = new ArrayList<String>();
		
		expected.add("nubla");
		expected.add("nao faz sol");
		expected.add("chove");
		expected.add("sono");
		expected.add("tempestade");
		expected.add("venta");
		
		List<String> actual = resolver.forwardResult();
		
		Collections.sort(expected);
		Collections.sort(actual);
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	void backWardTest() throws IOException {
		
		File rulesFile = new File("regras.txt");
		File factsFile = new File("fatos.txt");
		
		Resolver resolver = new Resolver(rulesFile, factsFile);
		
		List<String> expected = new ArrayList<String>();
		
		expected.add("nubla");
		expected.add("nao faz sol");
		expected.add("chove");
		expected.add("sono");
		expected.add("tempestade");
		expected.add("venta");
		
		List<String> actual = resolver.forwardResultExplained();
		
		Collections.sort(expected);
		Collections.sort(actual);
		
		assertEquals(expected, actual);
		
	}
	
//	@Test
//	void basicBackWardTest() throws IOException {
//		
//		File rulesFile = new File("regras.txt");
//		File factsFile = new File("fatos - Copia.txt");
//		
//		Resolver resolver = new Resolver(rulesFile, factsFile);
//		
//		String expected = "verdade";
//		
//		assertEquals(expected, resolver.backwardResult("D"));
//		
//	}

}

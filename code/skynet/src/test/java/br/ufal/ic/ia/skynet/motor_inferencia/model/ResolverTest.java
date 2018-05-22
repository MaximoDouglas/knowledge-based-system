package br.ufal.ic.ia.skynet.motor_inferencia.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.ufal.ic.ia.skynet.exceptions.InvalidArgs;

class ResolverTest {

	private File rulesFile = new File("test/regras.txt");
	private File factsFile = new File("test/fatos.txt");
	private Class<Resolver> reflected = Resolver.class;
	
	@SuppressWarnings("unchecked")
	@Test
	void testFindRules() throws InvalidArgs, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchFieldException {
		
		Field rulesHash = reflected.getDeclaredField("rulesHash");
		rulesHash.setAccessible(true);
		Method findRules = reflected.getDeclaredMethod("findRules");
		findRules.setAccessible(true);
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testFindFacts() throws InvalidArgs, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchFieldException {
		
		Field facts = reflected.getDeclaredField("facts");
		facts.setAccessible(true);
		Method findFacts = reflected.getDeclaredMethod("findFacts");
		findFacts.setAccessible(true);
		
		Resolver instance = getResolverInstance();
		List<String> lista = new ArrayList<String>();
		facts.set(instance, lista);
		
		List<String> newFacts = (List<String>) facts.get(instance);
		
		Collections.sort(lista);
		Collections.sort(newFacts);
		
		//Antes de findFacts ser executado. Listas vazias.
		assertEquals(lista, newFacts);
		
		//Execução de findFacts, que lê fatos do arquivo "facts.txt" e adiciona ao atributo facts, que é uma lista de string
		findFacts.invoke(instance);
		
		//Depois da execução de findFacts. Listas preenchidas.
		lista.add("nubla");
		lista.add("chove");
		Collections.sort(lista);
		newFacts = (List<String>) facts.get(instance);
		Collections.sort(newFacts);
		assertEquals(lista, newFacts);
	}
	
	private Resolver getResolverInstance() throws InvalidArgs{
		return new Resolver(rulesFile, factsFile);
	}
	
	@Test
	void testCanIProveIt() {
		
	}

	@Test
	void testAddNewFacts() {
		
	}
	
	@Test
	void testAddInStack(){
		
		
		
	}
	
	@Test
	void testResolver() {
		
		rulesFile = new File("teste");
		factsFile = new File("de software");
		
		assertThrows(InvalidArgs.class, () -> new Resolver(rulesFile, factsFile));
	}
	
	@Test
	void testForwardResult() {
		
	}

	@Test
	void testForwardResultExplained() {
		
	}

	@Test
	void testBackwardResult() {
		
	}
	
}

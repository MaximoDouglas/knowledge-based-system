package br.ufal.ic.ia2.minicity.model;

import java.util.List;

import javafx.util.Pair;

public class Semaphore {

	private int redTime = 2;
	private int greenTime = 2;
	private int congestions = 0;
	private List<Semaphore> semaphoresBefore;
	private List<Semaphore> semaphoresAfter;
	private Pair<Integer, Integer> pos;
	
	
	
}

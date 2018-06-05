package br.ufal.ic.ia.skynet.motor_inferencia.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.ufal.ic.ia.skynet.exceptions.InvalidArgs;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.DAOFactory;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.FatoExemploDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.RegraExemploDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.model.FatoExemplo;
import br.ufal.ic.ia.skynet.motor_inferencia.model.RegraExemplo;
import br.ufal.ic.ia.skynet.motor_inferencia.model.Resolver;

public class InferenceController {

	private Resolver resolver;
	private RegraExemploDAO reDAO;
	private FatoExemploDAO feDAO;
	
//	public static void main(String[] args) throws InvalidArgs {
//		(new InferenceController()).forward().forEach(System.out::println);
//	}

	public InferenceController() throws InvalidArgs {
		this.reDAO = DAOFactory.getFactory().getRegraExemploDAO();
		this.feDAO = DAOFactory.getFactory().getFatoExemploDAO();
		this.resolver = new Resolver(setRules(), setFacts());
	}
	

	public InferenceController(List<String> facts) throws InvalidArgs {
		this.resolver = new Resolver(getRules(), setFacts(facts));
	}


	public List<String> forward(){
		List<String> forwardResult = resolver.forwardResult();
		return forwardResult;
	}
	
	private Map<String, List<String>> setRules() {
		List<String> listaRegras = new ArrayList<>();

		listaRegras.add("chove -> nao faz sol");
		listaRegras.add("nubla -> chove");
		listaRegras.add("faz sol -> praia");
		listaRegras.add("chove & nubla -> sono");
		listaRegras.add("chove & nubla & sono -> tempestade");
		listaRegras.add("faz sol -> nao chove");

		Map<String, List<String>> hash = makeHash(listaRegras);

		return hash;
	}

	private Map<String, List<String>> makeHash(List<String> listaRegras) {
		Map<String, List<String>> hash = new HashMap<>();

		for (String string : listaRegras) {

			String key = string.split("->")[0].trim();
			String right = string.split("->")[1].trim();

			RegraExemplo re = new RegraExemplo();
			re.setAntecedente(key);
			re.setConsequente(right);

			reDAO.beginTransaction();
			reDAO.save(re);
			reDAO.commitTransaction();
		}

		List<RegraExemplo> res = reDAO.listAll();

		for (RegraExemplo regraExemplo : res) {
			if (!hash.containsKey(regraExemplo.getAntecedente())) {
				List<String> values = new ArrayList<>();

				values.add(regraExemplo.getConsequente());
				hash.put(regraExemplo.getAntecedente(), values);
			} else {
				List<String> values = hash.get(regraExemplo.getAntecedente());

				values.add(regraExemplo.getConsequente());
				
				hash.remove(regraExemplo.getAntecedente());
				hash.put(regraExemplo.getAntecedente(), values);
			}
		}

		return hash;
	}

	private List<String> setFacts() {
		
		List<String> facts = new ArrayList<>();
		
		
		facts.add("nubla");
		facts.add("venta");
		feDAO.beginTransaction();
		
		for (String string : facts) {
			
			FatoExemplo fato = new FatoExemplo();
			fato.setDescricao(string);
			
			feDAO.save(fato);
			
		}
		
		feDAO.commitTransaction();
		
		List<String> factsReturn = new ArrayList<>();
		
		for (FatoExemplo fe : feDAO.listAll()) {
			factsReturn.add(fe.getDescricao());
		}
		
		factsReturn.forEach(System.out::println);
		
		return factsReturn;
		
	}

	private Map<String, List<String>> getRules() {
		return null;
	}

	private List<String> setFacts(List<String> facts) {
		return null;
	}

	public static boolean question(String top) {
		return true;
	}
}

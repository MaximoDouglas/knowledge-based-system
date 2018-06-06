package br.ufal.ic.ia.skynet.motor_inferencia.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import br.ufal.ic.ia.skynet.exceptions.InvalidArgs;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.DAOFactory;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.FatoExemploDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.RegraExemploDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.model.FatoExemplo;
import br.ufal.ic.ia.skynet.motor_inferencia.model.RegraExemplo;
import br.ufal.ic.ia.skynet.motor_inferencia.model.Resolver;
import javafx.util.Pair;

@SuppressWarnings("restriction")
public class InferenceController {

	private Resolver resolver;
	private RegraExemploDAO reDAO;
	private FatoExemploDAO feDAO;

	public InferenceController() throws InvalidArgs {
		this.reDAO = DAOFactory.getFactory().getRegraExemploDAO();
		this.feDAO = DAOFactory.getFactory().getFatoExemploDAO();
		this.resolver = new Resolver(setRules(), setFacts());
	}

	public InferenceController(List<String> facts) throws InvalidArgs {
		this.resolver = new Resolver(getRules(), setFacts(facts));
	}

	public List<String> forward() {
		List<String> forwardResult = resolver.forwardResult();
		return forwardResult;
	}

	public List<String> forwardExplained() {
		List<String> forwardResult = resolver.forwardResultExplained();
		return forwardResult;
	}

	public List<Pair<String, String>> getExplicacoes() {
		return resolver.getExplicacoes();
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

	public void wipeData() {

		List<FatoExemplo> fes = feDAO.listAll();

		feDAO.beginTransaction();
		for (FatoExemplo fatoExemplo : fes) {
			feDAO.delete(fatoExemplo);
		}
		feDAO.commitTransaction();

		List<RegraExemplo> res = reDAO.listAll();

		reDAO.beginTransaction();
		for (RegraExemplo regraExemplo : res) {
			reDAO.delete(regraExemplo);
		}
		reDAO.commitTransaction();

		if (!resolver.getExplicacoes().isEmpty()) {
			resolver.setExplicacoes(new ArrayList<>());
		}
	}

	private Map<String, List<String>> makeHash(List<String> listaRegras) {
		Map<String, List<String>> hash = new HashMap<>();

		reDAO.beginTransaction();
		for (String string : listaRegras) {

			String key = string.split("->")[0].trim();
			String right = string.split("->")[1].trim();

			RegraExemplo re = new RegraExemplo();
			re.setAntecedente(key);
			re.setConsequente(right);

			reDAO.save(re);
		}
		reDAO.commitTransaction();

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

		return factsReturn;

	}

	private Map<String, List<String>> getRules() {
		return null;
	}

	private List<String> setFacts(List<String> facts) {
		return null;
	}

	public List<String> getFacts() {
		List<String> listaRetorno = new ArrayList<>();

		for (FatoExemplo fato : feDAO.listAll()) {
			listaRetorno.add(fato.getDescricao());
		}

		return listaRetorno;
	}
	
	public List<String> getVariaveis() {
		List<String> listaRetorno = new ArrayList<>();

		listaRetorno = resolver.getVariables();

		return listaRetorno;
	}
	
	public String backward(String goal) {
		return resolver.backwardResult(goal);
	}

	public List<Pair<String, String>> getRulePairs() {
		List<Pair<String, String>> listaRetorno = new ArrayList<>();

		for (RegraExemplo regra : reDAO.listAll()) {
			listaRetorno.add(new Pair<String, String>(regra.getAntecedente(), regra.getConsequente()));
		}

		return listaRetorno;
	}

	public static boolean question(String top) {

		String[] options = new String[] { "Sim", "Não" };
		int response = JOptionPane.showOptionDialog(null, "A variável '"+top+"' é verdade?", "Questão", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		return response == 0;
	}
}

package br.ufal.ic.ia.skynet.motor_inferencia.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import br.ufal.ic.ia.skynet.exceptions.InvalidArgs;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.DAOFactory;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.FatoExemploDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.FuncionalidadeDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.RegraDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.RegraExemploDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.model.FatoExemplo;
import br.ufal.ic.ia.skynet.motor_inferencia.model.Funcionalidade;
import br.ufal.ic.ia.skynet.motor_inferencia.model.Regra;
import br.ufal.ic.ia.skynet.motor_inferencia.model.RegraExemplo;
import br.ufal.ic.ia.skynet.motor_inferencia.model.Resolver;
import javafx.util.Pair;

@SuppressWarnings("restriction")
public class InferenceController {

	private Resolver resolver;
	private RegraExemploDAO reDAO;
	private FatoExemploDAO feDAO;
	private RegraDAO rDAO;

	public InferenceController() throws InvalidArgs {
		this.reDAO = DAOFactory.getFactory().getRegraExemploDAO();
		this.feDAO = DAOFactory.getFactory().getFatoExemploDAO();
		this.resolver = new Resolver(setRules(), setFacts());
	}

	public InferenceController(List<String> facts) throws InvalidArgs {
		this.rDAO = DAOFactory.getFactory().getRegraDAO();
		this.resolver = new Resolver(getRules(), facts);
	}

	private Map<String, List<String>> setRules() {
		List<String> listaRegras = new ArrayList<>();

		listaRegras.add("chove -> nao faz sol");
		listaRegras.add("nubla -> chove");
		listaRegras.add("faz sol -> praia");
		listaRegras.add("chove & nubla -> sono");
		listaRegras.add("chove & nubla & sono -> tempestade");
		listaRegras.add("faz sol -> nao chove");

		Map<String, List<String>> hash = makeHashExemplo(listaRegras);

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

	public List<String> forward() {
		List<String> forwardResult = resolver.forwardResult();
		return forwardResult;
	}

	public List<String> forwardExplained() {
		List<String> forwardResult = resolver.forwardResultExplained();
		return forwardResult;
	}

	public String backward(String goal) {
		return resolver.backwardResult(goal);
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

	private Map<String, List<String>> makeHashExemplo(List<String> listaRegras) {
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

	private Map<String, List<String>> makeHash(List<Regra> listaRegras) {
		Map<String, List<String>> hash = new HashMap<>();

		rDAO.beginTransaction();
		for (Regra regra : listaRegras) {
			rDAO.save(regra);
		}
		rDAO.commitTransaction();

		List<Regra> regras = rDAO.listAll();

		for (Regra regra : regras) {
			if (!hash.containsKey(regra.getAntecedente())) {
				List<String> values = new ArrayList<>();

				values.add(regra.getConsequente());
				hash.put(regra.getAntecedente(), values);
			} else {
				List<String> values = hash.get(regra.getAntecedente());

				values.add(regra.getConsequente());

				hash.remove(regra.getAntecedente());
				hash.put(regra.getAntecedente(), values);
			}
		}

		return hash;
	}

	private Map<String, List<String>> getRules() {
		List<Regra> listaRegras = new ArrayList<>();

		listaRegras.add(new Regra("smartphone", "nokia n8"));
		listaRegras.add(new Regra("smartphone", "blackberry q10"));
		listaRegras.add(new Regra("smartphone", "xiaomi mi mix"));
		listaRegras.add(new Regra("smartphone", "htc 10"));
		listaRegras.add(new Regra("smartphone", "galaxy s3"));
		listaRegras.add(new Regra("smartphone", "google pixel xl"));
		listaRegras.add(new Regra("smartphone", "moto e2"));
		listaRegras.add(new Regra("smartphone", "moto g4 play"));
		listaRegras.add(new Regra("smartphone", "moto g4"));
		listaRegras.add(new Regra("mp3", "siemens gs55-6"));
		listaRegras.add(new Regra("mp3", "multilaser p3298"));
		listaRegras.add(new Regra("colorido", "lg b220a"));
		listaRegras.add(new Regra("multichip", "multilaser p3298"));
		listaRegras.add(new Regra("4g", "moto e2"));
		listaRegras.add(new Regra("4g", "moto g4 play"));
		listaRegras.add(new Regra("4g", "moto g4"));
		listaRegras.add(new Regra("flash", "xiaomi mi mix"));
		listaRegras.add(new Regra("flash", "htc 10"));
		listaRegras.add(new Regra("flash", "moto g4 play"));
		listaRegras.add(new Regra("flash", "moto g4"));
		listaRegras.add(new Regra("leitor biometrico", "moto g4"));
		listaRegras.add(new Regra("camera frontal", "galaxy s3"));
		listaRegras.add(new Regra("camera frontal", "google pixel xl"));
		listaRegras.add(new Regra("tela hd", "google pixel xl"));
		listaRegras.add(new Regra("giroscopio", "htc 10"));
		listaRegras.add(new Regra("teclado fisico", "blackberry q10"));

		rDAO.beginTransaction();

		for (Regra regra : listaRegras) {
			rDAO.save(regra);
		}

		rDAO.commitTransaction();

		return makeHash(rDAO.listAll());
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

	public List<Pair<String, String>> getRulePairs() {
		List<Pair<String, String>> listaRetorno = new ArrayList<>();

		for (RegraExemplo regra : reDAO.listAll()) {
			listaRetorno.add(new Pair<String, String>(regra.getAntecedente(), regra.getConsequente()));
		}

		return listaRetorno;
	}

	public List<Pair<String, String>> getRulePairsOff() {
		List<Pair<String, String>> listaRetorno = new ArrayList<>();

		for (Regra regra : rDAO.listAll()) {
			listaRetorno.add(new Pair<String, String>(regra.getAntecedente(), regra.getConsequente()));
		}

		return listaRetorno;
	}

	public List<Pair<String, String>> getExplicacoes() {
		return resolver.getExplicacoes();
	}

	public static List<String> getFuncionalidades() {

		List<String> retorno = new ArrayList<>();
		FuncionalidadeDAO funcDAO = DAOFactory.getFactory().getFuncionalidadeDAO();
		List<Funcionalidade> funcionalidades = funcDAO.listAll();

		if (funcionalidades.isEmpty()) {
			funcionalidades = fillFuncionalidades(funcDAO);
		}

		for (Funcionalidade funcionalidade : funcionalidades) {
			retorno.add(funcionalidade.getName());
		}

		return retorno;
	}

	public static List<Funcionalidade> fillFuncionalidades(FuncionalidadeDAO funcDAO) {

		List<Funcionalidade> retorno = new ArrayList<>();

		retorno.add(new Funcionalidade("smartphone"));
		retorno.add(new Funcionalidade("mp3"));
		retorno.add(new Funcionalidade("colorido"));
		retorno.add(new Funcionalidade("multichip"));
		retorno.add(new Funcionalidade("4g"));
		retorno.add(new Funcionalidade("flash"));
		retorno.add(new Funcionalidade("leitor biometrico"));
		retorno.add(new Funcionalidade("camera frontal"));
		retorno.add(new Funcionalidade("tela hd"));
		retorno.add(new Funcionalidade("giroscopio"));
		retorno.add(new Funcionalidade("teclado fisico"));

		funcDAO.beginTransaction();

		for (Funcionalidade funcionalidade : retorno) {
			funcDAO.save(funcionalidade);
		}

		funcDAO.commitTransaction();

		return funcDAO.listAll();
	}

	public static boolean question(String top) {

		String[] options = new String[] { "Sim", "Não" };
		int response = JOptionPane.showOptionDialog(null, "A variável '" + top + "' é verdade?", "Questão",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		return response == 0;
	}

}

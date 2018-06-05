package br.ufal.ic.ia.skynet.motor_inferencia.dao.hibernate;

import br.ufal.ic.ia.skynet.motor_inferencia.dao.FatoExemploDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.model.FatoExemplo;

public class HibernateFatoExemploDAO extends HibernateDAO<FatoExemplo, Long> implements  FatoExemploDAO {
	
	public HibernateFatoExemploDAO(){

		super(FatoExemplo.class);

	}
}

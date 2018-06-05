package br.ufal.ic.ia.skynet.motor_inferencia.dao.hibernate;

import br.ufal.ic.ia.skynet.motor_inferencia.dao.FatoDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.model.Fato;

public class HibernateFatoDAO extends HibernateDAO<Fato, Long> implements  FatoDAO {
	
	public HibernateFatoDAO(){

		super(Fato.class);

	}
}

package br.ufal.ic.ia.skynet.motor_inferencia.dao.hibernate;

import br.ufal.ic.ia.skynet.motor_inferencia.dao.CelularDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.model.Celular;

public class HibernateCelularDAO extends HibernateDAO<Celular, Long> implements  CelularDAO {
	
	public HibernateCelularDAO(){

		super(Celular.class);

	}
}

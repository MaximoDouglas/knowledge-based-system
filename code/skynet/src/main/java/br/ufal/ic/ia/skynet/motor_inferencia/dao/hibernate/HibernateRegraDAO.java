package br.ufal.ic.ia.skynet.motor_inferencia.dao.hibernate;

import br.ufal.ic.ia.skynet.motor_inferencia.dao.RegraDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.model.Regra;

public class HibernateRegraDAO extends HibernateDAO<Regra, Long> implements  RegraDAO {
	
	public HibernateRegraDAO(){

		super(Regra.class);

	}
}

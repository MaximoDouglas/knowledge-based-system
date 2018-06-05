package br.ufal.ic.ia.skynet.motor_inferencia.dao.hibernate;

import br.ufal.ic.ia.skynet.motor_inferencia.dao.RegraExemploDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.model.RegraExemplo;

public class HibernateRegraExemploDAO extends HibernateDAO<RegraExemplo, Long> implements  RegraExemploDAO {
	
	public HibernateRegraExemploDAO(){

		super(RegraExemplo.class);

	}
}

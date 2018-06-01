package br.ufal.ic.ia.skynet.motor_inferencia.dao.hibernate;

import br.ufal.ic.ia.skynet.motor_inferencia.dao.FuncionalidadeDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.model.Celular;
import br.ufal.ic.ia.skynet.motor_inferencia.model.Funcionalidade;

class HibernateFuncionalidadeDAO extends HibernateDAO<Funcionalidade, Long> implements  FuncionalidadeDAO {

	public HibernateFuncionalidadeDAO() {
		super(Celular.class); 
	}

}
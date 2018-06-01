package br.ufal.ic.ia.skynet.motor_inferencia.dao.hibernate;

import br.ufal.ic.ia.skynet.motor_inferencia.dao.CelularDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.DAOFactory;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.FuncionalidadeDAO;

public class HibernateDAOFactory  extends DAOFactory{

	@Override
	public CelularDAO getCelularDAO() {

		return new HibernateCelularDAO();

	}

	@Override
	public FuncionalidadeDAO getFuncionalidadeDAO() {

		return new HibernateFuncionalidadeDAO();

	}

}
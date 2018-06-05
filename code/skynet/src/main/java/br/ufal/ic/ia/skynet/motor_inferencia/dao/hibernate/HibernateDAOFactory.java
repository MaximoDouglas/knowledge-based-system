package br.ufal.ic.ia.skynet.motor_inferencia.dao.hibernate;

import br.ufal.ic.ia.skynet.motor_inferencia.dao.CelularDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.DAOFactory;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.FatoDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.FatoExemploDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.FuncionalidadeDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.RegraDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.RegraExemploDAO;

public class HibernateDAOFactory extends DAOFactory{

	@Override
	public CelularDAO getCelularDAO() {
		return new HibernateCelularDAO();
	}

	@Override
	public FuncionalidadeDAO getFuncionalidadeDAO() {
		return new HibernateFuncionalidadeDAO();
	}

	@Override
	public RegraDAO getRegraDAO() {
		return new HibernateRegraDAO();
	}

	@Override
	public RegraExemploDAO getRegraExemploDAO() {
		return new HibernateRegraExemploDAO();
	}

	@Override
	public FatoDAO getFatoDAO() {
		return new HibernateFatoDAO();
	}

	@Override
	public FatoExemploDAO getFatoExemploDAO() {
		return new HibernateFatoExemploDAO();
	}

}
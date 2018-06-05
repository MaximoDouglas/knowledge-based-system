package br.ufal.ic.ia.skynet.motor_inferencia.dao;

import br.ufal.ic.ia.skynet.motor_inferencia.dao.hibernate.HibernateDAOFactory;

public abstract class DAOFactory {

	@SuppressWarnings("rawtypes")
	private static final Class FACTORY_CLASS = HibernateDAOFactory.class;
 
	public static DAOFactory getFactory(){

		try {

			return (DAOFactory) FACTORY_CLASS.newInstance();

		} catch (InstantiationException e) {  


			throw new RuntimeException();

		} catch (IllegalAccessException e) {

			
			throw new RuntimeException();

		}

	}

	public abstract CelularDAO getCelularDAO();

	public abstract FuncionalidadeDAO getFuncionalidadeDAO();
	
	public abstract RegraDAO getRegraDAO();
	
	public abstract RegraExemploDAO getRegraExemploDAO();
	
	public abstract FatoDAO getFatoDAO();
	
	public abstract FatoExemploDAO getFatoExemploDAO();

}

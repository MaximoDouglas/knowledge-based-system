package br.ufal.ic.ia.skynet.motor_inferencia.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;

import br.ufal.ic.ia.skynet.motor_inferencia.dao.GenericDAO;

public abstract class HibernateDAO<T, Type extends Serializable> implements GenericDAO<T, Type>{

	private Class<T> persistentClass;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HibernateDAO(Class persistentClass) {

		super();

		this.persistentClass = persistentClass;

	}

	@Override
	public void beginTransaction() {
		HibernateUtil.getSession().beginTransaction();
	}

	@Override
	public void commitTransaction() {
		HibernateUtil.getSession().getTransaction().commit();
	}	 
	
	@Override

	public void save(T entity) {
		HibernateUtil.getSession().save(entity);
	}
	
	@Override

	public void delete(T entity) {
		HibernateUtil.getSession().delete(entity);
	}

	@SuppressWarnings({"unchecked"})
	@Override

	public List<T> listAll() {

		Criteria criteria = HibernateUtil.getSession().createCriteria(persistentClass);
		
		return criteria.list();

	}

}

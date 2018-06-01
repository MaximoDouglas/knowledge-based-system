package br.ufal.ic.ia.skynet.motor_inferencia.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.ufal.ic.ia.skynet.motor_inferencia.dao.GenericDAO;

public abstract class HibernateDAO<T, Type extends Serializable> implements GenericDAO<T, Type>{
	
    private static final EntityManager manager = Persistence.createEntityManagerFactory("skynet").createEntityManager();
    
	private Class<T> persistentClass;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HibernateDAO(Class persistentClass) {

		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	@Override
	public void beginTransaction() {
		manager.getTransaction().begin();
	}

	@Override
	public void commitTransaction() { 
		manager.getTransaction().commit();
	}	 
	
	
	@Override

	public void save(T entity) {
		manager.persist(entity);
	}
	
	@Override

	public void delete(T entity) {
		manager.remove(entity);
	}

	@SuppressWarnings({"unchecked"})
	@Override

	public List<T> listAll() {

		return manager.createQuery("Select t from " + persistentClass.getSimpleName() + " t").getResultList();

	}

}

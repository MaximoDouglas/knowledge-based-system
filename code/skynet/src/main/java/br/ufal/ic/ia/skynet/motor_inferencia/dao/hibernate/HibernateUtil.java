package br.ufal.ic.ia.skynet.motor_inferencia.dao.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil { 

	private static final Session session = buildSessionFactory(); 

    private static Session buildSessionFactory() {
        try {
            Configuration config = new Configuration().configure();
            
            ServiceRegistry service = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            
            SessionFactory fabrica = config.buildSessionFactory(service);
            
            return fabrica.openSession();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return session;
    }
    
}
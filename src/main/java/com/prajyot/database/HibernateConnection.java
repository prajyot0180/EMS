package com.prajyot.database;



import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {
	private static SessionFactory factory;
	
	static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("SessionFactory initialization failed: " + e.getMessage());
        }
    }
	
	public static SessionFactory getSessionFactory() {
			return factory;
	}
	
	 public static void shutdown() {
	        if (factory != null) {
	            factory.close();
	        }
	 }
}

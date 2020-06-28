package br.com.Forum.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();

	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}

	private static SessionFactory criarFabricaDeSessoes() {
		try {
			Configuration configuracao = new Configuration().configure("hibernate.cfg.xml");
			
			//ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();
			
			SessionFactory fabrica = configuracao.buildSessionFactory();
			
			return fabrica;
			
		} catch (Throwable ex) {
			System.err.println("A fábrica de sessões não pode ser criada. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}
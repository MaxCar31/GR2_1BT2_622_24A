package net.javaguides.usermanagement.utl;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import net.javaguides.usermanagement.model.User;

/**
 * Esta clase es una clase de utilidad que se utiliza para inicializar y obtener la SessionFactory de Hibernate.
 * La SessionFactory es un objeto de Hibernate que crea objetos Session.
 * Un objeto Session es una interfaz de Hibernate que proporciona métodos para las operaciones de base de datos.
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory;

	// Método para obtener la SessionFactory.
	public static SessionFactory getSessionFactory() {
		// Si la SessionFactory no se ha inicializado, la inicializamos.
		if (sessionFactory == null) {
			try {
				// Creamos una nueva configuración de Hibernate.
				Configuration configuration = new Configuration();

				// Configuramos las propiedades de Hibernate equivalentes a las propiedades en hibernate.cfg.xml.
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/demo?useSSL=false");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "admin");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

				settings.put(Environment.SHOW_SQL, "true");

				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				settings.put(Environment.HBM2DDL_AUTO, "create-drop");

				// Aplicamos las propiedades a la configuración.
				configuration.setProperties(settings);
				// Añadimos la clase User a la configuración.
				configuration.addAnnotatedClass(User.class);

				// Creamos el ServiceRegistry a partir de la configuración.
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				System.out.println("Hibernate Java Config serviceRegistry created");
				// Creamos la SessionFactory a partir del ServiceRegistry.
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				return sessionFactory;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
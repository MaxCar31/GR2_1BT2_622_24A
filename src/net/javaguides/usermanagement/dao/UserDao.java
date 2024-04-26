package net.javaguides.usermanagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.javaguides.usermanagement.model.User;
import net.javaguides.usermanagement.utl.HibernateUtil;

/**
 * Esta clase es un DAO (Data Access Object) que proporciona operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para la entidad User.
 * Utiliza la clase HibernateUtil para obtener la SessionFactory de Hibernate, que se utiliza para obtener sesiones de Hibernate.
 * Cada método en esta clase abre una nueva sesión de Hibernate, inicia una transacción, realiza la operación de base de datos y luego cierra la transacción y la sesión.
 */
public class UserDao {

	/**
	 * Guarda un usuario en la base de datos.
	 * @param user
	 */
	public void saveUser(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Inicia una transacción
			transaction = session.beginTransaction();
			// Guarda el objeto usuario
			session.save(user);
			// Confirma la transacción
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza un usuario en la base de datos.
	 * @param user
	 */
	public void updateUser(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Inicia una transacción
			transaction = session.beginTransaction();
			// Actualiza el objeto usuario
			session.update(user);
			// Confirma la transacción
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un usuario de la base de datos.
	 * @param id
	 */
	public void deleteUser(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Inicia una transacción
			transaction = session.beginTransaction();

			// Elimina un objeto usuario
			User user = session.get(User.class, id);
			if (user != null) {
				session.delete(user);
				System.out.println("user is deleted");
			}

			// Confirma la transacción
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene un usuario por ID.
	 * @param id
	 * @return
	 */
	public User getUser(int id) {

		Transaction transaction = null;
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Inicia una transacción
			transaction = session.beginTransaction();
			// Obtiene un objeto usuario
			user = session.get(User.class, id);
			// Confirma la transacción
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Obtiene todos los usuarios.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {

		Transaction transaction = null;
		List<User> listOfUser = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Inicia una transacción
			transaction = session.beginTransaction();
			// Obtiene una lista de usuarios

			listOfUser = session.createQuery("from User").getResultList();

			// Confirma la transacción
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfUser;
	}
}
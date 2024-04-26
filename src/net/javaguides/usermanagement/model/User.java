package net.javaguides.usermanagement.model;

/**
 * Esta aplicación es un sistema de gestión de usuarios.
 * Permite crear, leer, actualizar y eliminar (CRUD) usuarios.
 * Cada usuario tiene un id, nombre, correo electrónico y país.
 * La información del usuario se almacena en una base de datos.
 * Esta clase User representa la entidad Usuario en la base de datos.
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// La anotación @Entity indica que esta clase es una entidad JPA.
// La anotación @Table se utiliza para especificar el nombre de la tabla en la base de datos a la que esta entidad está mapeada.
@Entity
@Table(name="instructor")
public class User {

	// Las anotaciones @Id y @GeneratedValue se utilizan para indicar que el campo id es la clave primaria de la entidad y que su valor se genera automáticamente.
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected int id;

	// La anotación @Column se utiliza para mapear el campo name a la columna name en la tabla instructor en la base de datos.
	@Column(name="name")
	protected String name;

	// La anotación @Column se utiliza para mapear el campo email a la columna email en la tabla instructor en la base de datos.
	@Column(name="email")
	protected String email;

	// La anotación @Column se utiliza para mapear el campo country a la columna country en la tabla instructor en la base de datos.
	@Column(name="country")
	protected String country;

	// Constructor vacío requerido por JPA.
	public User() {
	}

	// Constructor que acepta nombre, correo electrónico y país.
	public User(String name, String email, String country) {
		super();
		this.name = name;
		this.email = email;
		this.country = country;
	}

	// Constructor que acepta id, nombre, correo electrónico y país.
	public User(int id, String name, String email, String country) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.country = country;
	}

	// Getter para id.
	public int getId() {
		return id;
	}

	// Setter para id.
	public void setId(int id) {
		this.id = id;
	}

	// Getter para nombre.
	public String getName() {
		return name;
	}

	// Setter para nombre.
	public void setName(String name) {
		this.name = name;
	}

	// Getter para correo electrónico.
	public String getEmail() {
		return email;
	}

	// Setter para correo electrónico.
	public void setEmail(String email) {
		this.email = email;
	}

	// Getter para país.
	public String getCountry() {
		return country;
	}

	// Setter para país.
	public void setCountry(String country) {
		this.country = country;
	}
}
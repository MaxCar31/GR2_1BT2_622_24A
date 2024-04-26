package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.usermanagement.dao.UserDao;
import net.javaguides.usermanagement.model.User;

/**
 * Esta clase es un servlet que maneja todas las solicitudes HTTP relacionadas con los usuarios.
 * Proporciona métodos para listar, crear, editar y eliminar usuarios.
 * Utiliza UserDao para interactuar con la base de datos.
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	// Inicializa el UserDao.
	public void init() {
		userDao = new UserDao();
	}

	// Maneja las solicitudes POST redirigiéndolas a doGet.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// Maneja todas las solicitudes GET.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
				case "/new":
					// Muestra el formulario para crear un nuevo usuario.
					showNewForm(request, response);
					break;
				case "/insert":
					// Inserta un nuevo usuario en la base de datos.
					insertUser(request, response);
					break;
				case "/delete":
					// Elimina un usuario de la base de datos.
					deleteUser(request, response);
					break;
				case "/edit":
					// Muestra el formulario para editar un usuario existente.
					showEditForm(request, response);
					break;
				case "/update":
					// Actualiza un usuario existente en la base de datos.
					updateUser(request, response);
					break;
				default:
					// Lista todos los usuarios.
					listUser(request, response);
					break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	// Lista todos los usuarios.
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = userDao.getAllUser();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}
	// Muestra el formulario para crear un nuevo usuario.
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}
	// Muestra el formulario para editar un usuario existente.
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDao.getUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);
	}
	// Inserta un nuevo usuario en la base de datos.
	private void insertUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User newUser = new User(name, email, country);
		userDao.saveUser(newUser);
		response.sendRedirect("list");
	}
	// Actualiza un usuario existente en la base de datos.
	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");

		User user = new User(id, name, email, country);
		userDao.updateUser(user);
		response.sendRedirect("list");
	}

	// Elimina un usuario de la base de datos.
	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDao.deleteUser(id);
		response.sendRedirect("list");
	}
}
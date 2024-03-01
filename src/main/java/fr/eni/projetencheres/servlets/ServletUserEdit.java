package fr.eni.projetencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.UserManager;
import fr.eni.projetencheres.bo.User;

/**
 * Servlet implementation class ServletUserEdit
 */
@WebServlet("/user/edit")
public class ServletUserEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User u = (User) request.getSession().getAttribute("user");
		u.setUsername(request.getParameter("username"));
		u.setFirstName(request.getParameter("firstName"));
		u.setLastName(request.getParameter("lastName"));
		u.setEmail(request.getParameter("email"));
		u.setPhoneNumber(request.getParameter("phone"));
		u.setStreet(request.getParameter("street"));
		u.setZipCode(request.getParameter("zipCode"));
		u.setCity(request.getParameter("city"));
		u.setPassword(request.getParameter("password"));
		
		UserManager.editUser(u);
		request.getSession().setAttribute("user", u);
		request.setAttribute("user", u);
		
		//redirection vers la servlet user
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/user");
		rd.forward(request, response);
		
	}

}

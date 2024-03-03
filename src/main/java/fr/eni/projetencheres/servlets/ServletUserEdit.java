package fr.eni.projetencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.BusinessException;
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
		
		User u = (User) request.getSession().getAttribute("userConnected");
		if (u == null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		request.setAttribute("user", u);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/user.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User) request.getSession().getAttribute("userConnected");
		if (u == null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("confirmPassword");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String street = request.getParameter("street");
		String postalCode = request.getParameter("zipCode");
		String city = request.getParameter("city");
		User editedUser = new User(u.getId(), username, lastName, firstName, email, phone, street, postalCode, city, password);

		try {
			UserManager.checkUser(editedUser);
			UserManager.comparePwd(editedUser.getPassword(), checkPassword);
			UserManager.editUser(editedUser);
			request.getSession().setAttribute("user", editedUser);
			request.setAttribute("message", null);
			
		} catch (BusinessException e) {
			request.setAttribute("message", e.getMessage());
		}
		//A definir si on garde dans la session ou la requete
		
		//redirection vers la servlet user
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user.jsp");
		rd.forward(request, response);
		
	}

}

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
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/user/user.jsp");
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
		String username = request.getParameter("editUsername");
		String passwordValidation = request.getParameter("editPasswordValidation");
		String password = request.getParameter("editPassword");
		String confirmEditPassword = request.getParameter("confirmEditPassword");
		String firstName = request.getParameter("editFirstName");
		String lastName = request.getParameter("editLastName");
		String email = request.getParameter("editEmail");
		String phone = request.getParameter("editPhone");
		String street = request.getParameter("editStreet");
		String zipCode = request.getParameter("editZipCode");
		String city = request.getParameter("editCity");
		User editedUser = new User(u.getId(), username, lastName, firstName, email, phone, street, zipCode, city, passwordValidation);
		editedUser.setCredit(u.getCredit());
		editedUser.setAdmin(u.isAdmin());
		try {
			UserManager.checkUser(editedUser);
			if (password != null && !password.isEmpty()) {
				UserManager.comparePwd(password, confirmEditPassword);
				editedUser.setPassword(password);
			}
			UserManager.editUser(editedUser);
			request.getSession().setAttribute("userConnected", editedUser);
			request.setAttribute("message", null);
			
		} catch (BusinessException e) {
			request.setAttribute("message", e.getMessage());
		}
		//A definir si on garde dans la session ou la requete
		
		//redirection vers la servlet user
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/user.jsp");
		rd.forward(request, response);
		
	}

}

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
import fr.eni.projetencheres.dal.DataException;

/**
 * Servlet implementation class ServletUserEdit
 */
@WebServlet("/utilisateur/modifier")
public class ServletUserEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User u = (User) request.getSession().getAttribute("userConnected");
		if (u == null) {
			response.sendRedirect(request.getContextPath() + "/accueil");
			return;
		}
		request.setAttribute("user", u);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/user/user.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/user.jsp");
		User u = (User) request.getSession().getAttribute("userConnected");
		if (u == null) {
			response.sendRedirect(request.getContextPath() + "/accueil");
		}
		String username = request.getParameter("editUsername");
		String passwordValidation = request.getParameter("passwordValidation");
		String password = request.getParameter("editPassword");
		String confirmEditPassword = request.getParameter("confirmEditPassword");
		String firstName = request.getParameter("editFirstName");
		String lastName = request.getParameter("editLastName");
		String email = request.getParameter("editEmail");
		String phone = request.getParameter("editPhone");
		String street = request.getParameter("editStreet");
		String zipCode = request.getParameter("editZipCode");
		String city = request.getParameter("editCity");
		User editedUser = new User(u.getId(), username, lastName, firstName, email, phone, street, zipCode, city,
				passwordValidation);
		editedUser.setCredit(u.getCredit());
		editedUser.setAdmin(u.isAdmin());
		try {
			UserManager.login(u.getUsername(), passwordValidation);
			UserManager.checkUserInfo(editedUser, false);
			if ((password.trim() != "")) {
				System.out.println("password : " + password + " confirmEditPassword : " + confirmEditPassword);
				UserManager.comparePwd(password, confirmEditPassword);
				UserManager.checkUserInfo(editedUser);
				editedUser.setPassword(UserManager.hashPwd(password));
				System.out.println(editedUser.getPassword());
			}

			UserManager.editUser(editedUser);
			request.getSession().setAttribute("userConnected", editedUser);
		} catch (BusinessException e) {
			request.setAttribute("error", e.getMessage());
		} catch (DataException e) {
			System.out.println(e);
			response.sendError(500);
		} finally {
			rd.forward(request, response);
		}
		// A definir si on garde dans la session ou la requete

		// redirection vers la servlet user

	}

}

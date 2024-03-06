package fr.eni.projetencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bll.UserManager;
import fr.eni.projetencheres.bo.User;

/**
 * Servlet implementation class ServletCreateAccount
 */
@WebServlet("/Register")
public class ServletRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// VERIFICATION DES ENTREES DE L'UTILISATEUR
		String userName = request.getParameter("UserName");
		String lastName = request.getParameter("LastName");
		String firstName = request.getParameter("FirstName");
		String email = request.getParameter("Email");
		String phone = request.getParameter("Phone");
		String street = request.getParameter("Street");
		String zipCode = request.getParameter("ZipCode");
		String city = request.getParameter("City");
		String password = request.getParameter("Password");
		String checkPassword = request.getParameter("CheckPassword");
		// AFFICHAGE DES INFOS SAISIES PAR L'UTILISATEUR
		System.out.println("Pseudo : " + userName);
		System.out.println("Nom de l'utilisateur : " + lastName);
		System.out.println("Prénom de l'utilisateur :" + firstName);
		System.out.println("Email : " + email);
		System.out.println("Telephone : " + phone);
		System.out.println("Rue : " + street);
		System.out.println("Code Postal :" + zipCode);
		System.out.println("Mot de passe : " + password);
		System.out.println("Vérification du mot de passe : " + checkPassword);

//  	Création de l'instance de type utilisateur sur la base des renseignements fournis juste au-dessus
		User new_user = new User(userName, lastName, firstName, email, phone, street, zipCode, city, password);

		try {
// 			Vérification saisie du pseudo
//        	UserManager.check_username(userName);
			UserManager.check_cheaters(userName, lastName, firstName, email, street, zipCode, city, password);

//        	TODO virer le doublon de comparaison des mots de passe (la meme méthode est appelée en dessous)
//      	Comparaison des saisies mot de passe
			UserManager.comparePwd(password, checkPassword);

//        	Vérification concordance pseudo et mot de passe dans la BDD
			UserManager.createUser(new_user, checkPassword);

			HttpSession session = request.getSession();
			session.setAttribute("userConnected", new_user);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(request, response);

		} catch (BusinessException e) {
			String errorMessage = e.getMessage();
			request.setAttribute("error", errorMessage);
			doGet(request, response);
		}

//      response.sendRedirect(request.getContextPath());
	}
}
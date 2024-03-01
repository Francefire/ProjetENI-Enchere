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
 * Servlet implementation class ServletSeConnecter
 */
@WebServlet("/Login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Mise en place de la session
		HttpSession session = request.getSession(); // cette méthode retourne une nouvelle session si aucune session
													// n'existe
		int countSession = 0;
		if (session.getAttribute("compteurAcces") != null) {
			countSession = (int) session.getAttribute("compteurAcces");
		}
		countSession += 1;
		session.setAttribute("compteurAcces", countSession);

		// vérification avec la BDD des infos fournies par l'user.
		String userName, password;
		userName = request.getParameter("UserName");
		password = request.getParameter("Password");
		User u;
		UserManager um = new UserManager();
		System.out.println("je suis sur le point de me connecter");
		try {
			u = um.login(userName, password);
			System.out.println("je suis dans le login");
			if (u == null) {
				doGet(request, response);
			} else {
				session.setAttribute("userConnected", u);
				System.out.println("je suis connecté");
				request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
			}
		} catch (BusinessException e) {
			String errorMessage = e.getMessage();
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
			System.out.println("je suis dans le catch");
		}

		// Redirection vers la page d'accueil lors de la connexion
//		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}
}

//from annuaire
//Cookie gato;
//gato = new Cookie("lastLogin", u.getEmail());
//gato.setMaxAge(60 * 60 * 24 * 30);
//response.addCookie(gato);
//response.sendRedirect("lister");
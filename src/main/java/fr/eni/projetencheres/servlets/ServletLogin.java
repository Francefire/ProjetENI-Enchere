package fr.eni.projetencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bll.UserManager;
import fr.eni.projetencheres.bo.User;
import fr.eni.projetencheres.dal.DataException;

//TODO : faire quelque chose de "mot de passe oublié"		
//TODO : déplacer les jsp dans un unique dossier 

/**
 * Servlet implementation class ServletSeConnecter
 */
@WebServlet("/connexion")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final int timing = 1296000; // durée équivalente à 15jours

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");
		Cookie cook = null;
		User u;
		int cookieMaxAge = 1296000; // définit la durée de vie du cookie (en secondes -- 15jours)
		int pingTimeout = 3000; // définit la durée de vie de la session (en secondes -- 5min).

		// Mise en place de la session
		HttpSession session = request.getSession(); // cette méthode retourne une nouvelle session si aucune session
													// n'existe

		RequestDispatcher rd = null;

		// vérification avec la BDD des infos fournies par l'user.
		try {
			u = UserManager.login(username, password);
// 				*********COOKIE DE SESSION -- SE SOUVENIR DE MOI **********
			if (rememberMe != null) {
				cook = new Cookie("lastLogin", u.getUsername());
				cook.setMaxAge(cookieMaxAge);
				response.addCookie(cook);
			}
			// ***********************************************************
			session.setAttribute("userConnected", u);

			// Destruction de la session au bout de x min

			session.setMaxInactiveInterval(pingTimeout);
			if (request.getParameter("targetUrl") != null) {
				request.setAttribute("message", "Vous devez être connecté pour accéder à cette page");
				response.sendRedirect(request.getContextPath() + request.getParameter("targetUrl"));
			} else {
				response.sendRedirect(request.getContextPath() + "/accueil");
			}
		} catch (BusinessException e) {
			String errorMessage = e.getMessage();
			request.setAttribute("error", errorMessage);
			rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			rd.forward(request, response);
		} catch (DataException e) {
			// TODO Log exception
			response.sendError(503);
		}
	}
}

//*********VÉRIFICATION DU TEMPS RESTANT EN CONSOLE**********
//long sessionStart = session.getCreationTime();
//int sessionEnd = session.getMaxInactiveInterval();
//
//long currentTime = System.currentTimeMillis();
//long elapsedTime = currentTime - sessionStart;
//long remainingTime = pingTimeOut * 1000 - elapsedTime;
//
//System.out.println(pingTimeOut);
//System.out.println("Temps restant avant déconnexion (en secondes) : " + (remainingTime / 1000));
//remainingTime = pingTimeOut * 1000 - (System.currentTimeMillis() - sessionStart);
//System.out.println("Temps restant avant déconnexion (en secondes) : " + (remainingTime / 1000));
//if (remainingTime <= 0) {
//	System.out.println("Ping TimeOut dépassé : vous êtes déconnecté");
//	}
//***********************************************************

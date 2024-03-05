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




//TODO:	 faire quelque chose de "se souvenir de moi"
//TODO : faire quelque chose de "mot de passe oublié"		
//TODO : déplacer les jsp dans un unique dossier 
//TODO : mettre contrainte unicité sur le pseudo



/**
 * Servlet implementation class ServletSeConnecter
 */
@WebServlet("/Login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final int timing = 1296000 ; //durée équivalente à 15jours
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
		String userName, password;
		String rememberMe = request.getParameter("rememberMe");
		Cookie cook = null;
		int pingTimeOut = 3000; //définit la durée de vie de la session (en secondes).
		
		
		// Mise en place de la session
		HttpSession session = request.getSession(); // cette méthode retourne une nouvelle session si aucune session n'existe
		
		// vérification avec la BDD des infos fournies par l'user.
		userName = request.getParameter("UserName");
		password = request.getParameter("Password");
		User u;
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		try {
			u = UserManager.login(userName, password);
			System.out.println("je suis dans le login");
			if (u == null) {
				doGet(request, response);
			} else {
				session.setAttribute("userConnected", u);
				System.out.println("je suis connecté");
				
				//Destruction de la session au bout de x min 
				session.setMaxInactiveInterval(pingTimeOut) ; 
				
// 				*********COOKIE DE SESSION -- SE SOUVENIR DE MOI **********
					if (u != null) {
						request.getSession().setAttribute("userConnected", u);
						cook = new Cookie("lastLogin", u.getUsername());
						cook.setMaxAge(60 * 60 * 24 * 30);
						response.addCookie(cook);
//						response.sendRedirect("lister");
					}
//				***********************************************************
				rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
			}
		} catch (BusinessException e) {
			String errorMessage = e.getMessage();
			request.setAttribute("error", errorMessage);
			rd = request.getRequestDispatcher("/WEB-INF/Login.jsp");
			System.out.println("je suis dans le catch");
		}
		finally {
			rd.forward(request, response) ;
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


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
import fr.eni.projetencheres.dal.DataException;

/**
 * Servlet implementation class ServletUser
 */
@WebServlet("/utilisateur")
public class ServletUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User userConnected = (User) session.getAttribute("userConnected");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/user.jsp");
		String idParam = request.getParameter("id");
		User u = null;

		if (idParam != null) {
			try {
				int id = Integer.parseInt(idParam);

				u = UserManager.getUserById(id);
				
				if (u == null) {
					response.sendError(404);
				} else {
					if (u.getId() != userConnected.getId()) {
						request.setAttribute("displayUser", u);
					} else {
						// Ceci permettra d'afficher directement la page de modification du profil si
						// l'utilisateur veut voir son propre profile
						request.setAttribute("user", null);
					}
					
					rd.forward(request, response);
				}
			} catch (DataException e) {
				System.out.println(e);
				response.sendError(500);
			} catch (NumberFormatException e) {
				request.setAttribute("error", "ID non valide");
				rd.forward(request, response);
			}
		} else {
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

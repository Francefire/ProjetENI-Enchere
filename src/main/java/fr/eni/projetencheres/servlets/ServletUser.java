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
 * Servlet implementation class ServletUser
 */
@WebServlet("/user")
public class ServletUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user.jsp");
		String idParam = request.getParameter("id");
		User u = null;
		if (session.getAttribute("userConnected") == null) {
			
			request.setAttribute("from", rd);
			request.setAttribute("message", "Vous devez être connecté pour accéder à cette page.");
			rd = getServletContext().getRequestDispatcher("/Login");
			rd.forward(request, response);
			return;
		} else {
			if (idParam != null) {
				try {
                    int id = Integer.parseInt(idParam);
                    
                    u = UserManager.getUserById(id);
                    request.setAttribute("user", u);
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "ID non valide");
                    response.sendError(500);
                    return;
				} catch (BusinessException e) {
					request.setAttribute("message", e.getMessage());
					response.sendError(500);
					return;
				}
				
			}
			
		}

		rd.forward(request, response);
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

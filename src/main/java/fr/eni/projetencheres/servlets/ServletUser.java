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
		// TODO Auto-generated method stub
		if (request.getSession().getAttribute("user") == null) {
			if (request.getParameter("id") != null) {
				String paramId = request.getParameter("id");
				System.out.println(paramId);
				User u = UserManager.getUserById(Integer.parseInt(paramId));
				request.getSession().setAttribute("user", u);
			} else {
				response.sendRedirect(request.getContextPath() + "/home");
				return;
			}
		} else {
			User u = (User) request.getSession().getAttribute("user");
			request.setAttribute("user", u);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

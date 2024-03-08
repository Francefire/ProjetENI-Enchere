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
 * Servlet implementation class ServletUserDelete
 */
@WebServlet("/utilisateur/supprimer")
public class ServletUserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) request.getSession().getAttribute("userConnected");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/user.jsp");
		String password = request.getParameter("deletePassword");
		String confirmPassword = request.getParameter("deleteConfirmPassword");

		try {
			UserManager.checkPwd(u, password);
			UserManager.comparePwd(password, confirmPassword);
			UserManager.getInstance().delete(u.getId());
			session.invalidate();
			rd = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		} catch (BusinessException e) {
			request.setAttribute("error", e.getMessage());
			rd = request.getRequestDispatcher("/WEB-INF/jsp/user/user.jsp");
		} catch (DataException e) {
			System.out.println(e);
			response.sendError(500);
		}

		rd.forward(request, response);
	}

}

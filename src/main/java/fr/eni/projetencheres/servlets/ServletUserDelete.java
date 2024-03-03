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
 * Servlet implementation class ServletUserDelete
 */
@WebServlet("/user/delete")
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
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user.jsp");
		if (u == null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		try {
			System.out.println(u.getPassword().trim() + " " + request.getParameter("password").trim());
			UserManager.checkPwd(u, request.getParameter("password"));
			UserManager.comparePwd(request.getParameter("password"), request.getParameter("confirmPassword"));
			UserManager.getInstance().delete(u.getId());
			session.invalidate();
			rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		} catch (BusinessException e) {
			request.setAttribute("message", e.getMessage());
			rd = request.getRequestDispatcher("/WEB-INF/user.jsp");
		}

		rd.forward(request, response);
	}

}

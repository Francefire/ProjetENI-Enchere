package fr.eni.projetencheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletBidsEdit
 */
@WebServlet({"/bids/edit", "/encheres/modifier"})
public class ServletBidsEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articleId = request.getParameter("id");
		
		if (articleId == null || articleId.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/bids");
		} else {
			request.getRequestDispatcher("/WEB-INF/jsp/bids/bids_edit.jsp").forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articleId = request.getParameter("id");
		
		if (articleId == null || articleId.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/bids");
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/bids/bids_edit.jsp").forward(request, response);
	}
}

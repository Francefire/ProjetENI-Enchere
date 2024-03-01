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
@WebServlet({"/bids/delete", "/encheres/supprimer"})
public class ServletBidsDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articleId = request.getParameter("id");
		
		if (articleId == null || articleId.isEmpty()) {
			// ArticlesManager.deleteArticleByArticleId(Integer.parseInt(articleId));
			
			response.sendRedirect(request.getContextPath() + "/bids");	
		} else {
			response.sendRedirect(request.getContextPath() + "/bids");	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

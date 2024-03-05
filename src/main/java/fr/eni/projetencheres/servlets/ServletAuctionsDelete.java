package fr.eni.projetencheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.ArticlesManager;
import fr.eni.projetencheres.bll.BusinessException;

/**
 * Servlet implementation class ServletauctionsDelete
 */
@WebServlet({"/auctions/delete", "/encheres/supprimer"})
public class ServletAuctionsDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		if (id == null || id.isEmpty()) {
			response.sendError(404);
		} else {
			response.sendRedirect(request.getContextPath() + "/auctions");	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		if (id == null || id.isEmpty()) {
			response.sendError(404);
		} else {
			try {
				int articleId = Integer.parseInt(id);
				
				ArticlesManager.deleteArticleByArticleId(articleId);
				
				response.sendRedirect(request.getContextPath() + "/auctions");
			} catch (BusinessException e) {
				request.setAttribute("message", e);
				response.sendRedirect(request.getContextPath() + "/auctions?id=" + id);
			} catch (NumberFormatException e) {
				request.setAttribute("message", e);
				response.sendRedirect(request.getContextPath() + "/auctions?id=" + id);
			}
		}
	}
}

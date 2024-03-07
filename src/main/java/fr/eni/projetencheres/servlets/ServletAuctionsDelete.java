package fr.eni.projetencheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.ArticlesManager;
import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bll.CategoryManager;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.dal.DataException;

/**
 * Servlet implementation class ServletAuctionsDelete
 */
@WebServlet("/encheres/supprimer")
public class ServletAuctionsDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/encheres");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Article article = (Article) request.getAttribute("article");
		
		try {
			ArticlesManager.deleteArticleByArticleId(article.getId());
			
			// Supprimer la catégorie associée à l'article *
            CategoryManager categoryManager = new CategoryManager();
            categoryManager.deleteCategory(article.getCategoryId());

			response.sendRedirect(request.getContextPath() + "/encheres");
		} catch (BusinessException e) {
			request.setAttribute("error", e);
			response.sendRedirect(request.getContextPath() + "/encheres?id="+article.getId());
		} catch (DataException e) {
			// TODO Log exception
			response.sendError(503);	
		}
	}
}

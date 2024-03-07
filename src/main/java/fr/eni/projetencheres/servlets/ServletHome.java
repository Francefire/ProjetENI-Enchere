package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.ArticlesManager;
import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.dal.DataException;

/**
 * Servlet implementation class ServletHome
 */
@WebServlet({ "", "/accueil" })
public class ServletHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			List<Article> articles = ArticlesManager.getAllArticles();
			
		    request.setAttribute("articles", articles);
		    request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		} catch (BusinessException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		} catch (DataException e) {
			System.out.println(e);
			response.sendError(500);
		}
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

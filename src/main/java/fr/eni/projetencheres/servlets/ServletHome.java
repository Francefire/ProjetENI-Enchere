package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bo.Article;

/**
 * Servlet implementation class ServletHome
 */
@WebServlet({ "", "/accueil" })
public class ServletHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Article> articles = null;
//		List<String> breadCrumb 
//		Article = articleManager.getAllArticles();
		
//		try {
//	        ArticleDAO articleDAO = new ArticleDAO();
//	        articles = articleDAO.selectAllArticles();
//	    } catch (BusinessException e) {
//	        // Gérer les exceptions
//	    }
	    request.setAttribute("articles", articles);
	    
	    request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

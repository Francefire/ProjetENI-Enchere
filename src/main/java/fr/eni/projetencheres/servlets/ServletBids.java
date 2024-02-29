package fr.eni.projetencheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletBids
 */
@WebServlet({"/bids", "/encheres"})
public class ServletBids extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String articleId = request.getParameter("id");
		
		if (articleId == null || articleId.isEmpty()) {
			// TODO ArticlesManager.getAllWhere()
			
			request.getRequestDispatcher("/WEB-INF/jsp/bids/bids.jsp").forward(request, response);
		} else {
			/*
			Article article = ArticlesManager.getArticleByArticleId(Integer.parseInt(articleId));
			request.setAttribute("article", article);
			*/
			request.setAttribute("id", articleId);
			request.getRequestDispatcher("/WEB-INF/jsp/bids/bids_bid.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

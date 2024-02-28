package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.ArticlesManager;
import fr.eni.projetencheres.bll.BidsManager;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Bid;

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
		/*try {
			List<Article> articles = ArticlesManager.getAllArticles();
			request.setAttribute("articles", articles);
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		request.getRequestDispatcher("/WEB-INF/bids.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

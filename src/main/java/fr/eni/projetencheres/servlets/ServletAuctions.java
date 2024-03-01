package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.ArticlesManager;
import fr.eni.projetencheres.bll.BidsManager;
import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Bid;

/**
 * Servlet implementation class ServletAuctions
 */
@WebServlet({"/auctions", "/encheres"})
public class ServletAuctions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String id = request.getParameter("id");
		
		if (id == null || id.isEmpty()) {
			String name = request.getParameter("name");
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			// LocalDate startDate = request.getParameter("startDate");
			// LocalDate endDate = request.getParameter("endDate");
			
			try {
				List<Article> articles = ArticlesManager.getAllArticlesWhere(name, categoryId, null, null);
				request.setAttribute("articles", articles);
				request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions.jsp").forward(request, response);
			} catch (BusinessException e) {
				request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions.jsp").forward(request, response);
			}
			
		} else {
			try {
				int articleId = Integer.parseInt(id);

				Article article = ArticlesManager.getArticleByArticleId(articleId);
				List<Bid> bids = BidsManager.getauctionsByArticleId(articleId);
				
				request.setAttribute("article", article);
				request.setAttribute("bids", bids);
				request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_article.jsp").forward(request, response);
			} catch (BusinessException e) {
				request.setAttribute("message", e.getMessage());
				request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				request.setAttribute("message", "L'identifiant donné n'est pas valide");
				request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

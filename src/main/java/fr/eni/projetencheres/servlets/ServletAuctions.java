package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

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

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String id = request.getParameter("id");
		
		if (id == null || id.isEmpty()) {
			try {
				if (request.getParameterMap().size() == 0) {
					List<Article> articles = ArticlesManager.getAllArticles();
					request.setAttribute("articles", articles);
					request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions.jsp").forward(request, response);	
				} else {
					String paramName = request.getParameter("name");
					String paramCategoryId = request.getParameter("categoryId");
					String paramStartDate = request.getParameter("startDate");
					String paramEndDate = request.getParameter("endDate");
					
					int categoryId = 0;
					LocalDate startDate = null;
					LocalDate endDate = null;
					
					if (paramCategoryId != null) {
						categoryId = Integer.parseInt(paramCategoryId);
					}
					
					if (paramStartDate != null && !paramStartDate.isEmpty()) {
						startDate = LocalDate.parse(paramStartDate, ServletAuctions.formatter);
					}
					
					if (paramEndDate != null && !paramEndDate.isEmpty()) {
						endDate = LocalDate.parse(paramEndDate, ServletAuctions.formatter);
					}
					
					List<Article> articles = ArticlesManager.getAllArticlesWhere(paramName, categoryId, startDate, endDate);
					
					request.setAttribute("articles", articles);
					request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions.jsp").forward(request, response);	
				}
			} catch (BusinessException e) {
				request.setAttribute("message", e.getMessage());
				request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions.jsp").forward(request, response);	
			} catch (NumberFormatException | DateTimeParseException e) {
				System.out.println(e.getMessage());
				request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions.jsp").forward(request, response);	
			}			
		} else {
			try {
				int articleId = Integer.parseInt(id);

				Article article = ArticlesManager.getArticleByArticleId(articleId);
				// List<Bid> bids = BidsManager.getBidsByArticleId(article.getId());
				
				request.setAttribute("article", article);
				// request.setAttribute("bids", bids);
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

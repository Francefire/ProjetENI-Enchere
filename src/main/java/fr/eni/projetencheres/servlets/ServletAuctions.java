package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.ArticlesManager;
import fr.eni.projetencheres.bll.BidsManager;
import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bll.CategoryManager;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Bid;
import fr.eni.projetencheres.bo.User;
import fr.eni.projetencheres.bo.Category;
import fr.eni.projetencheres.dal.DataException;

/**
 * Servlet implementation class ServletAuctions
 */
@WebServlet("/encheres")
public class ServletAuctions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		RequestDispatcher auctionsRd = request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions.jsp");

		if (id == null || id.isEmpty()) {
			try { 
				CategoryManager categoryManager = new CategoryManager();
	            List<Category> categories = categoryManager.getAllCategories();
	            request.setAttribute("categories", categories);
	            
				if (request.getParameterMap().size() == 0) {
					List<Article> articles = ArticlesManager.getAllArticles();
					request.setAttribute("articles", articles);
					auctionsRd.forward(request, response);
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

					List<Article> articles = ArticlesManager.getAllArticlesWhere(paramName, categoryId, startDate,
							endDate);

					request.setAttribute("articles", articles);
					auctionsRd.forward(request, response);
				}
			} catch (BusinessException e) {
				request.setAttribute("error", e.getMessage());
				auctionsRd.forward(request, response);
			} catch (DataException e) {
				// TODO Log exception
				response.sendError(500);
			} catch (NumberFormatException | DateTimeParseException e) {
				request.setAttribute("error", BusinessException.BLL_FIELDS_INVALID_VALUES_ERROR);
				auctionsRd.forward(request, response);
			}
		} else {
			try {
				int articleId = Integer.parseInt(id);

				Article article = ArticlesManager.getArticleByArticleId(articleId);
				// List<Bid> bids = BidsManager.getBidsByArticleId(article.getId());

				User user = (User) request.getSession().getAttribute("userConnected");

				request.setAttribute("article", article);
				// request.setAttribute("bids", bids);

				RequestDispatcher auctionsArticleRd = request
						.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_article.jsp");

				if (user == null) {
					auctionsArticleRd.forward(request, response);
				} else {
					if (article.getAuctionState() == "ENDED") {
						Bid bid = BidsManager.getLastBidForArticle(article.getId());

						if (bid.getUserId() == user.getId()) {
							response.sendRedirect(request.getContextPath() + "/encheres/retrait?id=" + article.getId());
						}
					} else {
						auctionsArticleRd.forward(request, response);
					}
				}
			} catch (BusinessException e) {
				request.setAttribute("error", e.getMessage());
				auctionsRd.forward(request, response);
			} catch (DataException e) {
				// TODO Log exception
				System.out.println(e);
				response.sendError(500);
			} catch (NumberFormatException e) {
				request.setAttribute("error", "L'identifiant donné n'est pas valide");
				auctionsRd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

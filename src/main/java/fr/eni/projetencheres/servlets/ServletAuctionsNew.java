package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.ArticlesManager;
import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bo.Article;

/**
 * Servlet implementation class ServletauctionsNew
 */
@WebServlet({ "/auctions/new", "/encheres/nouvelle" })
public class ServletAuctionsNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// List<Category> categories = CategoriesManager.getAllCategories();
		// request.setAttribute("categories", categories);

		request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_new.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String paramName = request.getParameter("name");
			String paramDescription = request.getParameter("description");
			String paramStartDate = request.getParameter("startDate");
			String paramEndDate = request.getParameter("endDate");
			String paramInitialPrice = request.getParameter("initialPrice");
			String paramCategoryId = request.getParameter("categoryId");

			LocalDate startDate = null;
			LocalDate endDate = null;
			int categoryId = 0;

			if (paramName == null && paramName.isEmpty()) {
	
			}

			if (paramCategoryId != null) {
				categoryId = Integer.parseInt(paramCategoryId);
			}

			if (paramStartDate != null && !paramStartDate.isEmpty()) {
				startDate = LocalDate.parse(paramStartDate, ServletAuctionsNew.formatter);
			}

			if (paramEndDate != null && !paramEndDate.isEmpty()) {
				endDate = LocalDate.parse(paramEndDate, ServletAuctionsNew.formatter);
			}

			Article article = new Article();
			article.setName(paramName);
			
			ArticlesManager.addArticle(article);

			response.sendRedirect(request.getContextPath() + "/auctions?id=" + article.getId());
		} catch (BusinessException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_new.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_new.jsp").forward(request, response);
		}
	}
}

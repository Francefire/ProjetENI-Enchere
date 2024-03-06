package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.ArticlesManager;
import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.User;

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

		request.setAttribute("dateNow", LocalDate.now());
		
		request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_new.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String name = request.getParameter("name").trim();
			String description = request.getParameter("description").trim();
			LocalDate startDate = LocalDate.parse(request.getParameter("startDate"), formatter);
			LocalDate endDate = LocalDate.parse(request.getParameter("endDate"), formatter);
			double initialPrice = Double.parseDouble(request.getParameter("initialPrice"));
			User user = (User) request.getSession().getAttribute("userConnected");
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));

			Article article = new Article();
			article.setName(name);
			article.setDescription(description);
			article.setStartDate(startDate);
			article.setEndDate(endDate);
			article.setInitialPrice(initialPrice);
			article.setSellingPrice(initialPrice);
			article.setUserId(user.getId());
			article.setCategoryId(categoryId);
			
			ArticlesManager.addArticle(article);

			response.sendRedirect(request.getContextPath() + "/auctions?id=" + article.getId());
		} catch (BusinessException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_new.jsp").forward(request, response);
		} catch (NumberFormatException | DateTimeParseException e) {
			request.setAttribute("message", BusinessException.BLL_EMPTY_FIELDS_ERROR);
			request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_new.jsp").forward(request, response);
		}
	}
}

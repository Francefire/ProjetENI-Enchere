package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.ArticlesManager;
import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bll.CategoryManager;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Category;
import fr.eni.projetencheres.dal.DataException;

/**
 * Servlet implementation class ServletAuctionsEdit
 */
@WebServlet("/encheres/modifier")
public class ServletAuctionsEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try { 
			CategoryManager categoryManager = new CategoryManager();
	         List<Category> categories = categoryManager.getAllCategories();
	         request.setAttribute("categories", categories);
			 request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_edit.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String paramName = request.getParameter("name").trim();
			String paramDescription = request.getParameter("description").trim();
			String paramStartDate = request.getParameter("startDate");
			String paramEndDate = request.getParameter("startDate");
			String paramInitialPrice = request.getParameter("initialPrice");
			String paramCategoryId = request.getParameter("categoryId");

			Article article = (Article) request.getAttribute("article");
			
			if (paramName != null && !paramName.isEmpty()) {
				article.setName(paramName);
			}

			if (paramDescription != null && !paramDescription.isEmpty()) {
				article.setDescription(paramDescription);
			}

			if (paramStartDate != null) {
				article.setStartDate(LocalDate.parse(paramStartDate));
			}

			if (paramEndDate != null) {
				article.setEndDate(LocalDate.parse(paramEndDate));
			}

			if (paramInitialPrice != null) {
				double initialPrice = Double.parseDouble(paramInitialPrice);
				
				article.setInitialPrice(initialPrice);
				article.setSellingPrice(initialPrice);
			}

			if (paramCategoryId != null) {
				article.setCategoryId(Integer.parseInt(paramCategoryId));
			}
			ArticlesManager.editArticle(article);

			response.sendRedirect(request.getContextPath() + "/encheres?id=" + article.getId());
		} catch (BusinessException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_edit.jsp").forward(request, response);
		} catch (DataException e) {
			// TODO Log exception
			response.sendError(503);	
		} catch (NumberFormatException | DateTimeParseException e) {
			request.setAttribute("error", BusinessException.BLL_FIELDS_INVALID_VALUES_ERROR);
			request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_edit.jsp").forward(request, response);
		}
	}
}

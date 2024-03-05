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
 * Servlet implementation class ServletauctionsEdit
 */
@WebServlet({ "/auctions/edit", "/encheres/modifier" })
public class ServletAuctionsEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		if (id == null || id.isEmpty()) {
			response.sendError(404);
		} else {
			response.sendRedirect(request.getContextPath() + "/auctions?id=" + id);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		if (id == null || id.isEmpty()) {
			response.sendError(404);
		} else {
			try {
				String paramName = request.getParameter("name").trim();
				String paramDescription = request.getParameter("description").trim();
				String paramStartDate = request.getParameter("startDate");
				String paramEndDate = request.getParameter("startDate");
				String paramInitialPrice = request.getParameter("InitialPrice ");
				String paramCategoryId = request.getParameter("CategoryId");

				User user = (User) request.getSession().getAttribute("user");

				Article article = new Article();

				article.setId(Integer.parseInt(id));
				
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
					article.setInitialPrice(Double.parseDouble(paramInitialPrice));
				}

				if (paramCategoryId != null) {
					article.setCategoryId(Integer.parseInt(paramCategoryId));
				}

				article.setUserId(user.getId());

				ArticlesManager.editArticle(article);

				response.sendRedirect(request.getContextPath() + "/auctions?id=" + id);
			} catch (BusinessException e) {
				request.setAttribute("message", e.getMessage());
				request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_edit.jsp").forward(request, response);
			} catch (NumberFormatException | DateTimeParseException e) {
				request.setAttribute("message", BusinessException.BLL_EMPTY_FIELDS_ERROR);
				request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_edit.jsp").forward(request, response);
			}
		}
	}
}

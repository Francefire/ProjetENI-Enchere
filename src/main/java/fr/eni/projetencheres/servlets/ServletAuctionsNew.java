package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.ArticlesManager;
import fr.eni.projetencheres.bo.Article;

/**
 * Servlet implementation class ServletauctionsNew
 */
@WebServlet({"/auctions/new", "/encheres/nouvelle"})
public class ServletAuctionsNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_new.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {
			String name = (String) request.getAttribute("name");
			String description = (String) request.getAttribute("description");
			LocalDate startDate = (LocalDate) request.getAttribute("startDate");
			LocalDate endDate = (LocalDate) request.getAttribute("endDate");
			double initialPrice = Double.parseDouble((String) request.getAttribute("initialPrice"));
			int categoryId = Integer.parseInt((String) request.getAttribute("categoryId"));
			
			Article article = new Article();
			article.setName(name);
			article.setDescription(description);
			article.setauctionstartDate(startDate);
			article.setBidEndDate(endDate);
			article.setInitialPrice(initialPrice);
			article.setCategoryId(categoryId);
			
			// ArticlesManager.addArticle(article);
			response.sendRedirect(request.getContextPath() + "/auctions?id=" + article.getId());
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_new.jsp").forward(request, response);
		}
	}
}

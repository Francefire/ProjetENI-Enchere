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
 * Servlet implementation class ServletBidsEdit
 */
@WebServlet({"/bids/new", "/encheres/nouvelle"})
public class ServletBidsNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/bids/bids_new.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Article article = new Article();
		
		try {
			String name = (String) request.getAttribute("name");
			String description = (String) request.getAttribute("description");
			LocalDate bidStartDate = (LocalDate) request.getAttribute("bidStartDate");
			LocalDate bidEndDate = (LocalDate) request.getAttribute("bidEndDate");
			double initialPrice = (double) request.getAttribute("initialPrice");
			int categoryId = (int) request.getAttribute("categoryId");
			
			article.setName(name);
			article.setDescription(description);
			article.setBidStartDate(bidStartDate);
			article.setBidEndDate(bidEndDate);
			article.setInitialPrice(initialPrice);
			article.setCategoryId(categoryId);
		} catch (Exception e) {
			request.setAttribute("article", article);	
			request.getRequestDispatcher("/WEB-INF/jsp/bids/bids_new.jsp").forward(request, response);
		}
		
		// response.sendRedirect(request.getContextPath() + "/bids?id=" + article.getId());
	}
}

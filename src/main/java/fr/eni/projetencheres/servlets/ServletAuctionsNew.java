package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import fr.eni.projetencheres.bo.User;
import fr.eni.projetencheres.dal.DataException;

/**
 * Servlet implementation class ServletauctionsNew
 */
@WebServlet("/encheres/nouvelle")
public class ServletAuctionsNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
            // Récupération de la liste des catégories depuis le CategoryManager
            CategoryManager categoryManager = new CategoryManager();
            List<Category> categories = categoryManager.getAllCategories();
            request.setAttribute("categories", categories);

            // Transmission de la liste des catégories à la JSP
            request.setAttribute("dateNow", LocalDate.now());
            request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_new.jsp").forward(request, response);

            // Redirection vers la JSP pour l'affichage
        } catch (BusinessException e) {
            // Gestion de l'exception
            e.printStackTrace(); // À adapter selon la gestion des erreurs
        } catch (DataException e) {
        	 request.setAttribute("error", "Une erreur est survenue lors de l'accès aux données: " + e.getMessage());
        	    doGet(request, response); // Redirige vers la page avec le formulaire*
		}
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
			
			//ajout de l'article
			
			ArticlesManager.addArticle(article);
			
			response.sendRedirect(request.getContextPath() + "/encheres?id=" + article.getId());
		} catch (BusinessException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_new.jsp").forward(request, response);
		}  catch (DataException e) {
			// TODO Log exception
			response.sendError(503);	
		} catch (NumberFormatException | DateTimeParseException e) {
			request.setAttribute("error", BusinessException.BLL_FIELDS_INVALID_VALUES_ERROR);
			request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_new.jsp").forward(request, response);
		}
	}
}

package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bll.CategoryManager;
import fr.eni.projetencheres.bo.Category;
import fr.eni.projetencheres.dal.DataException;

/**
 * Servlet implementation class ServletAdmin
 */
@WebServlet("/admin/categories")
public class ServletAdminCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CategoryManager categoryManager = new CategoryManager();
			List<Category> categories = categoryManager.getAllCategories();
			
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_categories.jsp").forward(request, response);
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

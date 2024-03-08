package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Category> categories = CategoryManager.getAllCategories();

			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_categories.jsp").forward(request, response);
		} catch (BusinessException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_categories.jsp").forward(request, response);
		} catch (DataException e) {
			System.out.println(e);
			response.sendError(500);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_categories.jsp");

		if (action != null && !action.isEmpty()) {
			String paramId = request.getParameter("id");

			if (paramId != null && !paramId.isEmpty()) {
				try {
					int id = Integer.parseInt(paramId);

					switch (action) {
					case "edit": {
						String paramLabel = request.getParameter("label");

						Category category = new Category();
						category.setId(id);
						category.setLabel(paramLabel);

						CategoryManager.updateCategory(category);
						break;
					}
					case "delete": {
						CategoryManager.deleteCategory(id);
						break;
					}
					default:
						request.setAttribute("error", "L'action donnée n'est pas valide");
						rd.forward(request, response);
					}
					
					response.sendRedirect(request.getContextPath() + "/admin/categories");
				} catch (BusinessException e) {
					request.setAttribute("error", e.getMessage());
					rd.forward(request, response);
				} catch (DataException e) {
					System.out.println(e);
					response.sendError(500);
				} catch (NumberFormatException e) {
					request.setAttribute("error", "L'identifiant donné n'est pas valide");
					rd.forward(request, response);
				}
			} else {
				if (action.equals("add")) {
					String paramLabel = request.getParameter("label");

					Category category = new Category();
					category.setLabel(paramLabel);
					
					try {
						CategoryManager.createCategory(category);
						response.sendRedirect(request.getContextPath() + "/admin/categories");
					} catch (BusinessException e) {
						request.setAttribute("error", e.getMessage());
						rd.forward(request, response);
					} catch (DataException e) {
						System.out.println(e);
						response.sendError(500);
					}
				} else {
					request.setAttribute("error", "L'action donnée n'est pas valide");
					rd.forward(request, response);
				}
			}
		} else {
			request.setAttribute("error", "L'action donnée n'est pas valide");
			rd.forward(request, response);
		}
	}
}

package fr.eni.projetencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bll.UserManager;
import fr.eni.projetencheres.bo.User;

/**
 * Servlet implementation class ServletCredits
 */
@WebServlet("/credits")
public class ServletCredits extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/credits.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramAmount = request.getParameter("amount");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/credits.jsp");
		
		if (paramAmount == null || paramAmount.isEmpty()) {
			request.setAttribute("message", "Vous devez entrer un montant de crédits à acheter");
			request.getRequestDispatcher("/WEB-INF/jsp/credits.jsp").forward(request, response);
		} else {
			try {
				double amount = Double.parseDouble(paramAmount);
				
				User user = (User) request.getSession().getAttribute("userConnected");
				
				UserManager.addCreditsToUser(amount, user.getId());
				
				rd.forward(request, response);
			} catch (BusinessException e) {
				request.setAttribute("message", e.getMessage());
				rd.forward(request, response);
			} catch (NumberFormatException e) {
				request.setAttribute("message", "Le montant donné n'est pas valide");
				rd.forward(request, response);
			}
		}
	}
}

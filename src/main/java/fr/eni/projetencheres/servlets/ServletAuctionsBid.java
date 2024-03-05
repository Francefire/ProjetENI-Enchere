package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.ArticlesManager;
import fr.eni.projetencheres.bll.BidsManager;
import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Bid;

/**
 * Servlet implementation class ServletauctionsBid
 */
@WebServlet({"/auctions/bid", "/encheres/encherir"})
public class ServletAuctionsBid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		if (id == null || id.isEmpty()) {
			response.sendError(404);
		} else {
			response.sendRedirect(request.getContextPath() + "/auctions?id=" + id);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		if (id == null || id.isEmpty()) {			
			response.sendError(404);	
		} else {
			try {
				int articleId = Integer.parseInt(id);
				double amount = Double.parseDouble(request.getParameter("amount"));
				
				Bid bid = new Bid();
				bid.setUserId(0);
				bid.setArticleId(articleId);
				bid.setDate(LocalDate.now());
				bid.setAmount(amount);
				
				BidsManager.addBid(bid);

				request.setAttribute("message", "Enchère effectuée");
				response.sendRedirect(request.getContextPath() + "/auctions?id=" + id);
			} catch (BusinessException e) {
				request.setAttribute("message", e.getMessage());
				response.sendRedirect(request.getContextPath() + "/auctions?id=" + id);
			} catch (NumberFormatException e) {
				request.setAttribute("message", e.getMessage());
				response.sendRedirect(request.getContextPath() + "/auctions?id=" + id);
			}
		}
	}
}

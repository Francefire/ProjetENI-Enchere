package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.BidsManager;
import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Bid;
import fr.eni.projetencheres.bo.User;
import fr.eni.projetencheres.dal.DataException;

/**
 * Servlet implementation class ServletAuctionsBid
 */
@WebServlet("/encheres/encherir")
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
			response.sendRedirect(request.getContextPath() + "/encheres?id=" + id);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Article article = (Article) request.getAttribute("article");
		
		try {
			double amount = Double.parseDouble(request.getParameter("amount"));
		
			User user = (User) request.getSession().getAttribute("userConnected");
			
			if (article.getUserId() == user.getId()) {
				response.sendRedirect(request.getContextPath() + "/encheres?id=" + article.getId());
			} else {
				if (user.getCredit() < article.getSellingPrice()+1) {
					response.sendRedirect(request.getContextPath() + "/encheres?id=" + article.getId());	
				} else {
					Bid bid = new Bid();
					bid.setUserId(user.getId());
					bid.setArticleId(article.getId());
					bid.setDateTime(LocalDateTime.now());
					bid.setAmount(amount);
					
					BidsManager.addBid(bid);

					request.setAttribute("message", "Enchère effectuée");
					response.sendRedirect(request.getContextPath() + "/encheres?id=" + article.getId());	
				}
			}
		} catch (BusinessException e) {
			request.setAttribute("error", e.getMessage());
			response.sendRedirect(request.getContextPath() + "/encheres?id=" + article.getId());
		}  catch (DataException e) {
			// TODO Log exception
			response.sendError(503);				
		} catch (NumberFormatException e) {
			request.setAttribute("error", "Le montant entré n'est pas valide");
			response.sendRedirect(request.getContextPath() + "/encheres?id=" + article.getId());
		}
	}
}

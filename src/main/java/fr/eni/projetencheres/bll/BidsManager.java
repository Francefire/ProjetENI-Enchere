package fr.eni.projetencheres.bll;

import java.util.List;

import fr.eni.projetencheres.bo.Bid;
import fr.eni.projetencheres.dal.BidDAO;
import fr.eni.projetencheres.dal.DAOFactory;

public class BidsManager {
	private static BidDAO bidDAO;
	
	private static BidDAO getIntance() {
		if (bidDAO == null) {
			bidDAO = DAOFactory.getBidDAO();
		}
		
		return bidDAO;
	}
	
	public static void addBid(Bid b) throws BusinessException {
		if (b.getAmount() < 1) {
			throw new BusinessException(BusinessException.BLL_BID_AMOUNT_NOT_ENOUGH);
		}
		
		BidsManager.getIntance().insertBid(b);
		ArticlesManager.getIntance().updateArticleSellingPrice(b.getArticleId(), b.getAmount());
	}
	
	public static List<Bid> getauctionsByArticleId(int articleId) throws BusinessException {
		return BidsManager.getIntance().selectBidsByArticleId(articleId);
	}
}

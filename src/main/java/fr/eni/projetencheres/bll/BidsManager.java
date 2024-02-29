package fr.eni.projetencheres.bll;

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
		BidsManager.getIntance().insertBid(b);
	}
	
	public static Bid getBidByArticleId(int articleId) throws BusinessException {
		return BidsManager.getIntance().selectBidByArticleId(articleId);
	}
}

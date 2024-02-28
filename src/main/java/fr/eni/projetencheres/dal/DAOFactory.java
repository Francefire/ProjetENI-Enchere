package fr.eni.projetencheres.dal;

public class DAOFactory {
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAO();
	}
	
	public static BidDAO getBidDAO() {
		return new BidDAO();
	}
}

package fr.eni.projetencheres.dal;

public abstract class DAOFactory {
	public static UserDAO getUserDAO() {
		return new UserDAOJdbcImpl();
	}

	public static ArticleDAO getArticleDAO() {
		return new ArticleDAO();
	}
	
	public static BidDAO getBidDAO() {
		return new BidDAO();
	}
	public static CategoriesDAO getCategoriesDAO() {
		return new CategoriesDAO();
	}
}

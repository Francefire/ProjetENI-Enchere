package fr.eni.projetencheres.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.dal.ArticleDAO;
import fr.eni.projetencheres.dal.DAOFactory;

public class ArticlesManager {
	private static ArticleDAO articleDAO;
	
	private static ArticleDAO getIntance() {
		if (articleDAO == null) {
			articleDAO = DAOFactory.getArticleDAO();
		}
		
		return articleDAO;
	}
	
	public static void addArticle(Article a) throws SQLException {
		ArticlesManager.getIntance().insertArticle(a);
	}
	
	public static Article getArticleByArticleId(int articleId) throws SQLException {
		return ArticlesManager.getIntance().selectArticleByArticleId(articleId);
	}
	
	public static List<Article> getAllArticles() throws SQLException {
		return ArticlesManager.getIntance().selectAllArticles();
	}
}

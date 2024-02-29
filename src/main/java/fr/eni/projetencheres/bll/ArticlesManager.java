package fr.eni.projetencheres.bll;

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
	
	public static void addArticle(Article a) throws BusinessException {
		ArticlesManager.getIntance().insertArticle(a);	
	}
	
	public static void editArticle(Article a) throws BusinessException {
		ArticlesManager.getIntance().updateArticle(a);	
	}
	
	public static Article getArticleByArticleId(int articleId) throws BusinessException {
		return ArticlesManager.getIntance().selectArticleByArticleId(articleId);
	}
	
	public static List<Article> getAllArticles() throws BusinessException {
		return ArticlesManager.getIntance().selectAllArticles();
	}
	
	public static void deleteArticleByArticleId(int articleId) throws BusinessException {
		ArticlesManager.getIntance().deleteArticle(articleId);
	}
}

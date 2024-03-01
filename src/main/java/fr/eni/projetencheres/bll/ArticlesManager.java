package fr.eni.projetencheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.dal.ArticleDAO;
import fr.eni.projetencheres.dal.DAOFactory;

public class ArticlesManager {
	private static ArticleDAO articleDAO;
	
	public static ArticleDAO getIntance() {
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
		Article article = ArticlesManager.getIntance().selectArticleByArticleId(articleId);
	
		if (article == null) {
			throw new BusinessException(BusinessException.BLL_ARTICLE_NULL);
		}
		
		return article;
	}
	
	public static List<Article> getAllArticles() throws BusinessException {
		List<Article> articles = ArticlesManager.getIntance().selectAllArticles();
		
		if (articles == null || articles.isEmpty()) {
			throw new BusinessException(BusinessException.BLL_ARTICLES_NULL);
		}
		
		return articles;
	}
	
	public static List<Article> getAllArticlesWhere(String name, int categoryId, LocalDate startDate, LocalDate endDate) throws BusinessException {
		List<Article> articles = ArticlesManager.getIntance().selectArticlesWhere(name, categoryId, startDate, endDate);
		
		if (articles == null || articles.isEmpty()) {
			throw new BusinessException(BusinessException.BLL_ARTICLES_NULL);
		}
		
		return articles;
	}
	
	
	public static void deleteArticleByArticleId(int articleId) throws BusinessException {
		ArticlesManager.getIntance().deleteArticleByArticleId(articleId);
	}
}

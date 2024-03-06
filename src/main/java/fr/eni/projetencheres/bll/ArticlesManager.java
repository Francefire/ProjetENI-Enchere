package fr.eni.projetencheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.dal.ArticleDAO;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.DataException;

public class ArticlesManager {
	private static ArticleDAO articleDAO;

	public static ArticleDAO getIntance() {
		if (articleDAO == null) {
			articleDAO = DAOFactory.getArticleDAO();
		}

		return articleDAO;
	}

	public static void addArticle(Article a) throws BusinessException, DataException {
		Utils.verifyStringField("nom", a.getName(), 0, 30);
		Utils.verifyStringField("description", a.getDescription(), 0, 300);

		if (a.getStartDate().isAfter(a.getEndDate())) {
			throw new BusinessException(BusinessException.BLL_ADD_ARTICLE_START_DATE_AFTER_END_DATE_ERROR);
		}

		Utils.verifyMoneyField("prix initial", a.getInitialPrice(), 0);

		ArticlesManager.getIntance().insertArticle(a);
	}

	public static void editArticle(Article a) throws BusinessException, DataException {
		ArticlesManager.getIntance().updateArticle(a);
	}

	public static Article getArticleByArticleId(int articleId) throws BusinessException, DataException {
		Article article = ArticlesManager.getIntance().selectArticleByArticleId(articleId);

		if (article == null) {
			throw new BusinessException(BusinessException.BLL_GET_ARTICLE_NULL);
		}

		return article;
	}

	public static List<Article> getAllArticles() throws BusinessException, DataException {
		List<Article> articles = ArticlesManager.getIntance().selectAllArticles();

		if (articles == null) {
			throw new BusinessException(BusinessException.BLL_GET_ALL_ARTICLES_NULL);
		}

		return articles;
	}

	public static List<Article> getAllArticlesWhere(String name, int categoryId, LocalDate startDate, LocalDate endDate)
			throws BusinessException, DataException {
		List<Article> articles = ArticlesManager.getIntance().selectArticlesWhere(name, categoryId, startDate, endDate);

		if (articles == null) {
			throw new BusinessException(BusinessException.BLL_GET_ALL_ARTICLES_NULL);
		}

		return articles;
	}

	public static void deleteArticleByArticleId(int articleId) throws BusinessException, DataException {
		ArticlesManager.getIntance().deleteArticleByArticleId(articleId);
	}
}

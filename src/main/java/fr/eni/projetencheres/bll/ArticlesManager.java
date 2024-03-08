package fr.eni.projetencheres.bll;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.Part;

import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.dal.ArticleDAO;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.DataException;

public class ArticlesManager {
	private static final String PUBLIC_DIR = "/public";

	private static ArticleDAO articleDAO;

	public static ArticleDAO getIntance() {
		if (articleDAO == null) {
			articleDAO = DAOFactory.getArticleDAO();
		}

		return articleDAO;
	}

	public static void addArticle(Article a, String imagePath, Part imagePart) throws BusinessException, DataException {
		Utils.verifyStringField("nom", a.getName(), 0, 30);
		Utils.verifyStringField("description", a.getDescription(), 0, 300);

		if (a.getStartDate().isAfter(a.getEndDate())) {
			throw new BusinessException(BusinessException.BLL_ADD_ARTICLE_START_DATE_AFTER_END_DATE_ERROR);
		}

		Utils.verifyMoneyField("prix initial", a.getInitialPrice(), 0);

		if (imagePart != null) {
			String fileName = getFilename(imagePart);

			if (fileName != null && !fileName.isEmpty()) {
				try {
					String path = imagePath + PUBLIC_DIR;
					saveImage(imagePart, path, fileName);
					a.setImageUrl(PUBLIC_DIR + "/" + fileName);
				} catch (IOException e) {
					System.out.println(e);
					throw new BusinessException(BusinessException.BLL_IMAGE_SAVE_FAILED);
				}
			}
		}

		ArticlesManager.getIntance().insertArticle(a);
	}

	public static void editArticle(Article a, String imagePath, Part imagePart)
			throws BusinessException, DataException {
		if (imagePart != null) {
			String fileName = getFilename(imagePart);

			if (fileName != null && !fileName.isEmpty()) {
				try {
					String path = imagePath + PUBLIC_DIR;
					saveImage(imagePart, path, fileName);
					a.setImageUrl(PUBLIC_DIR + "/" + fileName);
				} catch (IOException e) {
					System.out.println(e);
					throw new BusinessException(BusinessException.BLL_IMAGE_SAVE_FAILED);
				}
			}
		}

		ArticlesManager.getIntance().updateArticle(a);
	}
	
	public static void editArticleAuctionState(int articleId, String auctionState) throws DataException {
		ArticlesManager.getIntance().updateArticleAuctionState(articleId, auctionState);
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

	public static List<Article> getTopArticles(int top) throws BusinessException, DataException {
		List<Article> articles = ArticlesManager.getIntance().selectTopArticles(top);

		if (articles == null) {
			throw new BusinessException(BusinessException.BLL_GET_ALL_ARTICLES_NULL);
		}

		return articles;
	}

	public static void deleteArticleByArticleId(int articleId) throws BusinessException, DataException {
		ArticlesManager.getIntance().deleteArticleByArticleId(articleId);
	}

	// Source: https://docs.oracle.com/javaee/6/tutorial/doc/glraq.html
	private static void saveImage(Part part, String path, String fileName) throws IOException {
		Files.createDirectories(Paths.get(path));
		
		FileOutputStream out = new FileOutputStream(new File(path + "/" + fileName));
		InputStream imageContent = part.getInputStream();

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = imageContent.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
	}

	private static String getFilename(Part part) {
		String filename = null;

		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE
			}
		}

		return null;
	}
}

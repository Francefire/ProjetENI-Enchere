package fr.eni.projetencheres.bll;

import java.util.List;

import fr.eni.projetencheres.bo.Category;
import fr.eni.projetencheres.dal.CategoriesDAO;
import fr.eni.projetencheres.dal.DataException;

public class CategoryManager {
	public static CategoriesDAO categoriesDAO = null;

	public static CategoriesDAO getInstance() {
		if (categoriesDAO == null) {
			categoriesDAO = new CategoriesDAO();
		}
		return categoriesDAO;
	}

	public List<Category> getAllCategories() throws BusinessException, DataException {
		return CategoryManager.getInstance().selectAllCategories();
	}
}

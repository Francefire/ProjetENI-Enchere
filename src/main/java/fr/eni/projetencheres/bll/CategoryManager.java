package fr.eni.projetencheres.bll;

import java.util.List;

import fr.eni.projetencheres.bo.Category;
import fr.eni.projetencheres.dal.CategoriesDAO;

public class CategoryManager {	
		public static CategoriesDAO categoriesDAO = null;
		
		public static CategoriesDAO getInstance() {
				if(categoriesDAO == null) {
					categoriesDAO = new CategoriesDAO();
				}
				return categoriesDAO;
		}
		
		public List<Category> recupererCategories() throws BusinessException {
			return CategoryManager.getInstance().selectAllCategory();
		}

}

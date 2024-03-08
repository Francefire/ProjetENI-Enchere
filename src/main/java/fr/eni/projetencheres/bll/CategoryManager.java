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
	
		public static List<Category> getAllCategories() throws BusinessException, DataException {
			return CategoryManager.getInstance().selectAllCategories();
		}
	
		// Méthode pour créer une nouvelle catégorie
		public static void createCategory(Category category) throws BusinessException, DataException {
			validateCategory(category);
			CategoryManager.getInstance().createCategory(category);
		}
	
		// Méthode pour mettre à jour une catégorie existante
		public static void updateCategory(Category category) throws BusinessException, DataException {
			validateCategory(category);
			CategoryManager.getInstance().updateCategory(category);
		}
	
		// Méthode pour supprimer une catégorie existante
		public static void deleteCategory(int categoryId) throws BusinessException, DataException {
			CategoryManager.getInstance().deleteCategory(categoryId);
		}
	
		// Méthode pour valider les propriétés d'une catégorie
	
		private static void validateCategory(Category category) throws BusinessException {
			if (category == null) {
				throw new BusinessException("La catégorie ne peut pas être nulle");
			}
			if (category.getLabel() == null || category.getLabel().trim().isEmpty()) {
				throw new BusinessException("Le nom de la catégorie ne peut pas être vide");
			}
		}
	}

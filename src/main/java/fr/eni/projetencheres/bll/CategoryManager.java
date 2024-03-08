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
		public void createCategory(Category categorie) throws BusinessException, DataException {
			validateCategory(categorie);
			CategoryManager.getInstance().createCategory(categorie);
		}
	
		// Méthode pour mettre à jour une catégorie existante
		public void updateCategory(Category categorie) throws BusinessException, DataException {
			validateCategory(categorie);
			CategoryManager.getInstance().updateCategory(categorie);
		}
	
		// Méthode pour supprimer une catégorie existante
		public void deleteCategory(int idCategorie) throws BusinessException, DataException {
			CategoryManager.getInstance().deleteCategory(idCategorie);
		}
	
		// Méthode pour valider les propriétés d'une catégorie
	
		private void validateCategory(Category categorie) throws BusinessException {
			if (categorie == null) {
				throw new BusinessException("La catégorie ne peut pas être nulle");
			}
			if (categorie.getLibelle() == null || categorie.getLibelle().trim().isEmpty()) {
				throw new BusinessException("Le nom de la catégorie ne peut pas être vide");
			}
		}
	}

package fr.eni.projetencheres.bll;

import java.util.List;

import fr.eni.projetencheres.bo.Category;
import fr.eni.projetencheres.dal.CategoriesDAO;

public class CategoryManager {	
		public static CategoriesDAO categoriesDAO = null; // Instance de CategoriesDAO pour interagir avec la base de données*
		
		public static CategoriesDAO getInstance() { // Constructeur qui initialise l'instance de CategoriesDAO
				if(categoriesDAO == null) {
					categoriesDAO = new CategoriesDAO();
				}
				return categoriesDAO;
		}
		// Méthode pour récupérer toutes les catégories*
		public List<Category> recupererCategories() throws BusinessException {
			try {
			return CategoryManager.getInstance().selectAllCategory();
			}catch (BusinessException e) {
				throw e;
			}
		}
//		// Méthode pour créer une nouvelle catégorie
//		public void ajouterCategorie(Category categorie) throws BusinessException {
//		    try {
//		       // CategoryManager.getInstance().insererCategorie(categorie);
//		    } catch (BusinessException e) {
//		        throw e;
//		    }
//		}
//		// Méthode pour mettre à jour une catégorie existante
//		public void mettreAJourCategorie(Category categorie) throws BusinessException {
//		    try {
//		       // CategoryManager.getInstance().mettreAJourCategorie(categorie);
//		    } catch (BusinessException e) {
//		        throw e;
//		    }
//		}
//		// Méthode pour supprimer une catégorie existante
//		public void supprimerCategorie(int idCategorie) throws BusinessException {
//		    try {
//		        //CategoryManager.getInstance().supprimerCategorie(idCategorie);
//		    } catch (BusinessException e) {
//		        throw e;
//		    }
//		}
//		// Méthode pour valider les propriétés d'une catégorie
//		
//		private void validerCategorie(Category categorie) throws BusinessException {
//		    if (categorie == null) {
//		        throw new BusinessException("La catégorie ne peut pas être nulle");
//		    }
//		    if (categorie.getLibelle() == null || categorie.getLibelle().trim().isEmpty()) {
//		        throw new BusinessException("Le nom de la catégorie ne peut pas être vide");
//		    }
//		}
}


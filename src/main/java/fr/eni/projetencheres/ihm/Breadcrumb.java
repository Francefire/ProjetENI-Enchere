package fr.eni.projetencheres.ihm;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class Breadcrumb {
	//TODO : prévoir les éléments à ne pas afficher (ProjetENI)
	
	public static List<String> breadcrumb(HttpServletRequest request) {
		
		//j'affecte le chemin actuel à la variable path
		String path = request.getRequestURI();
		
		//je print en console
		System.out.println("yep" +path);
		
		//je supprime le context d'application
		String contextPath = request.getContextPath();
			if (path.startsWith(contextPath)) {
				path = path.substring(contextPath.length());
			}
		
		//je supprime le premier slash qui ne servira à rien
			if (path.startsWith("/")) {
				path = path.substring(1);
			}
			
		//je splitte la chaine de caractère au niveau des slash
		String[] cut = path.split("/");
		
		//je prépare une liste qui recevra les morceaux splittés
		List <String> liste = new ArrayList<>();
		
		//j'ajoute les morceaux au fil d'ariane
		for (String morceau: cut) {
			liste.add(morceau);
		}
		return liste;
	}
		

//		request.setAttribute ("breadcrumb",);
		
}

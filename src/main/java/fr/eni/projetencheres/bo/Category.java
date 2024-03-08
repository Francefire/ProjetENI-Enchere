package fr.eni.projetencheres.bo;

public class Category {
	private int id;
	private String label;
	// CONSTRUCTEURS
	// TODO: Constructeur vide
	// TODO: Constructeur sans id
	
	public Category() {
		
	}
	
	public Category(int id, String label) {
		this.id = id;
		this.label = label;
	}

	// GETTERS & SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	// ToString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categories [no_categorie=");
		builder.append(id);
		builder.append(", libelle=");
		builder.append(label);
		builder.append("]");
		return builder.toString();
	}
}

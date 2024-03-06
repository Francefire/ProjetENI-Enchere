package fr.eni.projetencheres.bo;

public class Breadcrumb {
	String url;
	String name;
	
	private final static String USER_SERVLET = "user";
	private final static String USER_EDIT_SERVLET = "edit";
	private final static String AUCTIONS_SERVLET = "auctions";
	private final static String AUCTIONS_NEW_SERVLET = "new";
	
	
	public Breadcrumb(String url, String name) {
		this.url = url;
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getName() {
		return name;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}

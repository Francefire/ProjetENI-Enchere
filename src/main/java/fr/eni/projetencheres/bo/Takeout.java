package fr.eni.projetencheres.bo;

import java.time.LocalDate;

public class Takeout {
	private int articleId;
	private String street;
	private String zipCode;
	private String city;
	private LocalDate date;
	
	public Takeout() {
		
	}
	
	public Takeout(int articleId, String street, String zipCode, String city, LocalDate date) {
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.date = date;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}

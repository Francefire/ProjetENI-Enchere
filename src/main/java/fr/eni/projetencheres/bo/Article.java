package fr.eni.projetencheres.bo;

import java.time.LocalDate;

public class Article {
	private int id;
	private String name;
	private String description;
	private LocalDate bidStartDate;
	private LocalDate bidEndDate;
	private double initialPrice;
	private double sellingPrice;
	private String bidState;
	private int userId;
	private int categoryId;

	public Article() {

	}

	public Article(String name, String description, LocalDate bidStartDate, LocalDate bidEndDate, double initialPrice,
			double sellingPrice, String bidState, int userId, int categoryId) {
		this.name = name;
		this.description = description;
		this.bidStartDate = bidStartDate;
		this.bidEndDate = bidEndDate;
		this.initialPrice = initialPrice;
		this.sellingPrice = sellingPrice;
		this.bidState = bidState;
		this.userId = userId;
		this.categoryId = categoryId;
	}

	public Article(int id, String name, String description, LocalDate bidStartDate, LocalDate bidEndDate,
			double initialPrice, double sellingPrice, String bidState, int userId, int categoryId) {
		this(name, description, bidStartDate, bidEndDate, initialPrice, sellingPrice, bidState, userId, categoryId);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getBidStartDate() {
		return bidStartDate;
	}

	public void setBidStartDate(LocalDate bidStartDate) {
		this.bidStartDate = bidStartDate;
	}

	public LocalDate getBidEndDate() {
		return bidEndDate;
	}

	public void setBidEndDate(LocalDate bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	public double getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getBidState() {
		return bidState;
	}

	public void setBidState(String bidState) {
		this.bidState = bidState;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}

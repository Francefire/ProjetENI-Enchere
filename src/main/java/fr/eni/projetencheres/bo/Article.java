package fr.eni.projetencheres.bo;

import java.time.LocalDate;

public class Article {
	private int id;
	private String name;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private double initialPrice;
	private double sellingPrice;
	private String auctionState;
	private String imageUrl = "/assets/images/article_placeholder.jpg";
	private int userId;
	private int categoryId;   // Nouvel attribut pour la référence à la catégorie *

	public Article() {

	}
	
	public Article(String name, String description, LocalDate startDate, LocalDate endDate,
			double initialPrice, double sellingPrice, String auctionState, int userId, int categoryId) {
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.initialPrice = initialPrice;
		this.sellingPrice = sellingPrice;
		this.auctionState = auctionState;
		this.userId = userId;
		this.categoryId = categoryId;
	}

	public Article(int id, String name, String description, LocalDate startDate, LocalDate endDate, double initialPrice,
			double sellingPrice, String auctionState, int userId, int categoryId) {
		this(name, description, startDate, endDate, initialPrice, sellingPrice, auctionState, userId, categoryId);
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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

	public String getAuctionState() {
		return auctionState;
	}

	public void setAuctionState(String auctionState) {
		this.auctionState = auctionState;
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
    
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	// ToString *
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", initialPrice=" + initialPrice +
                ", sellingPrice=" + sellingPrice +
                ", auctionState='" + auctionState + '\'' +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                '}';
    }
}

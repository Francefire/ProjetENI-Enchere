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
	private String auctionstate;
	private int userId;
	private int categoryId;

	public Article() {

	}

	public Article(String name, String description, LocalDate startDate, LocalDate endDate, double initialPrice,
			double sellingPrice, String auctionstate, int userId, int categoryId) {
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.initialPrice = initialPrice;
		this.sellingPrice = sellingPrice;
		this.auctionstate = auctionstate;
		this.userId = userId;
		this.categoryId = categoryId;
	}

	public Article(int id, String name, String description, LocalDate startDate, LocalDate endDate,
			double initialPrice, double sellingPrice, String auctionstate, int userId, int categoryId) {
		this(name, description, startDate, endDate, initialPrice, sellingPrice, auctionstate, userId, categoryId);
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

	public LocalDate getauctionstartDate() {
		return startDate;
	}

	public void setauctionstartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getBidEndDate() {
		return endDate;
	}

	public void setBidEndDate(LocalDate endDate) {
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

	public String getauctionstate() {
		return auctionstate;
	}

	public void setauctionstate(String auctionstate) {
		this.auctionstate = auctionstate;
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

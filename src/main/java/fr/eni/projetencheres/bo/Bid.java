package fr.eni.projetencheres.bo;

import java.time.LocalDate;

public class Bid {
	private int userId;
	private int articleId;
	private LocalDate date;
	private double amount;
	
	public Bid() {
		
	}
	
	public Bid(LocalDate date, double amount) {
		this.date = date;
		this.amount = amount;
	}	
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int bidId) {
		this.articleId = bidId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double total) {
		this.amount = total;
	}
}

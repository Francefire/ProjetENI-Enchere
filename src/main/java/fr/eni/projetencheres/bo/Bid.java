package fr.eni.projetencheres.bo;

import java.time.LocalDate;

public class Bid {
	private int userId;
	private int articleId;
	private LocalDate date;
	private double total;
	
	public Bid() {
		
	}
	
	public Bid(LocalDate date, double total) {
		this.date = date;
		this.total = total;
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}

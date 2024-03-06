package fr.eni.projetencheres.bo;

import java.time.LocalDateTime;

public class Bid {
	private int userId;
	private int articleId;
	private LocalDateTime datetime;
	private double amount;
	
	public Bid() {
		
	}
	
	public Bid(LocalDateTime datetime, double amount) {
		this.datetime = datetime;
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

	public LocalDateTime getDateTime() {
		return datetime;
	}

	public void setDateTime(LocalDateTime localDateTime) {
		this.datetime = localDateTime;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double total) {
		this.amount = total;
	}
	// ToString *
    @Override
    public String toString() {
        return "Bid{" +
                "userId=" + userId +
                ", articleId=" + articleId +
                ", date=" + datetime +
                ", amount=" + amount +
                '}';
    }
	
}

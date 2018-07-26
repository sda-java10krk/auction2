package srallegro.auction;
import srallegro.Category;
import srallegro.user.User;

import java.math.BigDecimal;

public class Auction {

    private String title;
    private String description;
    private Category category;
    private User seller;
    private User winner;
    private BigDecimal price;
    int auctionNumber;
    private  int bids =0;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public User getSeller() {
        return seller;
    }

    public void setAuctionNumber(int auctionNumber) {
        this.auctionNumber = auctionNumber;
    }

    public int getAuctionNumber() {
        return auctionNumber;
    }

    //dodac do aukcji wyjatki jestli tytul jest pusty title.lengt=0 throw new itp,
    public Auction(String title, String description, Category category, User seller, User winner, BigDecimal price, int auctionNumber, int bids ) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.seller = seller;
        this.winner = winner;
        this.price = price;
        this.bids = bids;
    }



    public void setBids(int bids) {
        this.bids = bids;
    }

    public User getWinner() {
        return winner;
    }

    public int getBids() {
        return bids;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }



    @Override
    public String toString() {
        return "srallegro.auction.auction{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", seller=" + seller +
                ", winner=" + winner +
                ", price=" + price +
                ", auction number= " + auctionNumber +
                '}';
    }
}

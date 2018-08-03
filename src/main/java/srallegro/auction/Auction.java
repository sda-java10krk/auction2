package srallegro.auction;

import srallegro.Category;
import srallegro.exception.AuctionPriceIsBelowZeroOrZeroException;
import srallegro.exception.EmptyDescriptionException;
import srallegro.exception.EmptyTitleException;
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
    private int bids = 0;

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

    public Auction(String title, String description, Category category, User seller, User winner, BigDecimal price, int auctionNumber, int bids) throws Exception {
        this.title = title;
        if (title.length() == 0) {
            throw new EmptyTitleException();
        }
        this.description = description;
        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        }
        this.category = category;

        this.seller = seller;
        this.winner = winner;
        if(winner == null){
            User defaultWinner = new User("defwin","defwin",4,"defwin","defwin@deffault.com","defwin","Nikt nie zalicytowal tej aukcji");
            setWinner(defaultWinner);

        }

        this.price = price;
        if ((price.compareTo(BigDecimal.valueOf(0)) < 0) || price.equals(BigDecimal.valueOf(0))) {
            throw new AuctionPriceIsBelowZeroOrZeroException();
        }
        this.bids = bids;
        this.auctionNumber = auctionNumber;
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
        return "\n" + "{" +
                "title='" + title + '\'' +
                ", category=" + category.getName() +
                ", seller=" + seller.getNick() +
                ", winner=" + winner.getNick() +
                ", price=" + price + "PLN" +
                ", auction number= " + auctionNumber +
                '}' ;
    }
}

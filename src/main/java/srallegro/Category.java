package srallegro;


import java.util.LinkedList;
import java.util.List;

import srallegro.auction.Auction;
import srallegro.exception.EmptyCategoryNameException;
import srallegro.exception.EmptyTitleException;


public class Category {
    private String name;
    private List<Auction> auctions;
    private List<Category> subcategories;

    public Category(String name) throws EmptyCategoryNameException {
        this.name = name;
        if (name.length() == 0) {
            throw new EmptyCategoryNameException();
        }
        this.auctions = new LinkedList<>();
        this.subcategories = new LinkedList<>();
    }

    public void addAuction(Auction auction) {
        this.auctions.add(auction);
    }

    public void addSubcategory(Category category) {
        this.subcategories.add(category);
    }

    public String getName() {
        return name;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }


    @Override
    public String toString() {
        return name;
    }

}

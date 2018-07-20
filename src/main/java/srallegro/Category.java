package srallegro;

import java.util.Set;
import java.util.TreeSet;

public class Category {
    private String name;
    private Set<Auction> auctions;
    private Set<Category> subcategories;

    public Category(String name){
        this.name = name;
        this.auctions = new TreeSet<>();
        this.subcategories = new TreeSet<>();
    }

    public void addAuction(Auction auction){
        this.auctions.add(auction);
    }

    public void addSubcategory(Category category){
        this.subcategories.add(category);
    }

    public String getName() {
        return name;
    }
}

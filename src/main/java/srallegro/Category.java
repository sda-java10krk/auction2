package srallegro;

import java.io.PrintStream;
import java.util.Set;
import java.util.TreeSet;



public class Category {
    protected String name;
    private Set<Auction> auctions;
    protected static Set<Category> subcategories;

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

    public Set<Category> getSubcategories() {
        return subcategories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(Set<Auction> auctions) {
        this.auctions = auctions;
    }

    public static void setSubcategories(Set<Category> subcategories) {
        Category.subcategories = subcategories;
    }
}

package srallegro;

import java.util.Set;
import java.util.TreeSet;



public class Category {
    private String name;
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

    public void printCategories(int level) {
        if (this.name != null) {
            System.out.print("+");
            for (int i = 0; i < level; i++) {
                System.out.print("-");
            }
            System.out.println(this.name);
        }
        for (Category subcategory : this.subcategories) {
            subcategory.printCategories(level + 1);
        }
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

    public static Set<Category> getSubcategories() {
        return subcategories;
    }

    public static void setSubcategories(Set<Category> subcategories) {
        Category.subcategories = subcategories;
    }
}

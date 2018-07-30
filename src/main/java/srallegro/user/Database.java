package srallegro.user;
import srallegro.Category;
import srallegro.auction.Auction;

import java.io.*;
import java.util.*;


public class Database {

    private static Database instance;
    private Database(){}
    public static synchronized Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }
    private static Map<String, Category> allCategoriesByName = new HashMap<>();
    private static Map <Integer, Auction> allAuctionsByNumber = new HashMap<>();
    private static Map<String, User> allUsersByNickname = new HashMap<>();


    //regarding Categories
    public void addCategoryToAllCategories (Category category) {
        allCategoriesByName.put(category.getName(), category);
    }
    public Category getCategoryByName (String catname) {
        try {
            Category category = allCategoriesByName.get(catname);
            return category;
        } catch (NullPointerException npe) {
            System.out.println("Nie ma takiej kategorii");
        }
        return null;
    }
    public Map<String, Category> getAllCategoriesByName () {
        return allCategoriesByName;
    }
    // regarding Auctions
    public void addAuctionToAllAuctions (Auction auction) {
        allAuctionsByNumber.put(auction.getAuctionNumber(), auction);
    }

    public Auction getAuctionByNumber (Integer number) {
        try {
            Auction auction = allAuctionsByNumber.get(number);
            return auction;
        } catch (NullPointerException npe) {
            System.out.println("Nie ma takiej aukcji");
        }
        return null;
    }
    public Map<Integer, Auction> getAllAuctionsByNumber() {
        return allAuctionsByNumber;
    }
    //regarding Users
    public Map<String, srallegro.user.User> getAllUsersByNickname() {
        return allUsersByNickname;
    }
    public void addUserToAllUsers (User user) {
        allUsersByNickname.put(user.getNick(), user);
    }
    public User getUserByNickname (String nickname) {
        try {
            User user = allUsersByNickname.get(nickname);
            return user;
        } catch (NullPointerException npe) {
            System.out.println("Nie ma takiego użytkownika");
        }
        return null;
    }

    /*  never used! usunąć?
    public static void addUser(User user) throws IOException {
        String userNick = user.getNick();
        addUserToAllUsers(user);
        SaveUserOnDisk.writeCsvFile("databaseUser.csv", user);
    }
    */
}

package srallegro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Integer auctionNumber = 0;  //bedzie zwiekszany przy kazdej dodanej aukcji. tylko na razie nie działa

        HashSet<User> allUsers = new HashSet<>();
        Map<String, User> usersByName = new HashMap<>();
        User tempUser = new User ("Piotr", "Nowak", 0, "", "", "", "Nickname" );
        usersByName.put(tempUser.getNick(), tempUser); //tak mozna latwo dodawac nowych

        //w jakiej kolekcji beda siedziec wszystkie kategorire?
        Map<String, Category> categoriesByName = new HashMap<>();
        Category tempCat = new Category ("Huśtawki pneumatyczne");
        categoriesByName.put(tempCat.getName(), tempCat);

        Map<Integer, Auction> auctionsByNumber = new HashMap<>();
        //Auction tempAuction = new Auction(null, null, null, null, null, null, auctionNumber, 0);
    }
}

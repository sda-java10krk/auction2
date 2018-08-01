package srallegro.auction;

import srallegro.Category;
import srallegro.CategoryController;
import srallegro.user.Database;
import srallegro.exception.*;
import srallegro.user.User;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;
import static java.lang.System.setOut;

public class AuctionController {

    public static BigDecimal bidUp(Auction auction, BigDecimal bidUp, User user) throws Exception {
        if (auction.getPrice().compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new AuctionPriceIsBelowZeroOrZeroException();
        }
        if (auction.getWinner().equals(user) || auction.getSeller().equals(user)) {
            throw new YouCantBidUpYourOwnAuctionException();
        }
        BigDecimal newPrice = bidUp;
        if (newPrice.compareTo(auction.getPrice()) < 0) {
            throw new PriceTooLowException();

        } else {
            auction.setPrice(newPrice);
            auction.setWinner(user);
            auction.setBids(auction.getBids() + 1);

            if (auction.getBids() == 3) {
                System.out.println("aukcja zakonczona, zwyciezył " + user.getNick());
                user.getMyWonList().add(auction);
            }
        }
        return auction.getPrice();
    }

//zrobiic tak jak sprawdzanie daty urodzenia
    public static Auction createAuction(User currentUser, String title, String description, Category category, BigDecimal price) throws Exception {
        Database database = Database.getInstance();
        //do bani ten system numerowania aukcji. Ale działa
        Random rd = new Random();
        Integer auctNumber = rd.nextInt(10000);
        while (database.getAuctionByNumber(auctNumber) != null) {
            auctNumber = rd.nextInt(10000);
        }

        Auction newAuction = new Auction(title, description, category, currentUser,null, price, auctNumber, 0);
        if (title.length() == 0) {
            throw new EmptyTitleException();
        }
        if (price.compareTo(BigDecimal.valueOf(0)) < 0 || price.equals(BigDecimal.valueOf(0))) {
            throw new AuctionPriceIsBelowZeroOrZeroException();
        }
        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        }
        if (category.getSubcategories().size() > 0) {
            throw new NotFinalCategoryException();
        }
//        try {
//            category.getSubcategories().size() > 0;
//        }catch (NotFinalCategoryException e){
//            throw new NotFinalCategoryException() ;
//        }
        database.addAuctionToAllAuctions(newAuction);
        currentUser.getMySellingList().add(newAuction);
        category.addAuction(newAuction);
        SaveAuctionOnDisk.writeCsvFile("databaseAuction.csv", newAuction);
        return newAuction;
    }

    // tu będą sysouty do tworzenia aukcji, a zebrane z nich dane posłużą do wywołania na końcu createAuction.
    public static Auction createAuctionMain(User currentUser) throws Exception {
        Category allcategories = CategoryController.createCategoryTree();
        Database database = Database.getInstance();
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj tytuł aukcji");
        String title = sc.nextLine();
        System.out.println("Podaj opis");
        String description = sc.nextLine();
        System.out.println("Podaj cenę wywoławczą");
        BigDecimal price=BigDecimal.valueOf(1);
        boolean priceCheck = true;
        while (priceCheck) {
            try {
                price = sc.nextBigDecimal();
                priceCheck =false;
            } catch (InputMismatchException e) {
                priceCheck = true;
                System.out.println("Cena jest liczba");
                price = BigDecimal.valueOf(1);
                sc.nextLine();
            }
        }
        System.out.println("Wybierz kategorię");
        CategoryController.printCategories(allcategories, 0, out);
        String chosenCategory = sc.next();
        boolean auctionCheck =true;

        while(auctionCheck) {

            try {
               Auction newAuction = AuctionController.createAuction(currentUser, title, description, database.getCategoryByName(chosenCategory), price);
                //auctionCheck=false;
                return newAuction;

            } catch (NotFinalCategoryException e) {
                chosenCategory = sc.next();
                auctionCheck=true;

            } catch (NullPointerException npe) {
                System.out.println("Nie ma takiej kategorii, podaj kategorie finalna");
                chosenCategory = sc.next();
                auctionCheck=true;
            }
        }
return null ;
    }


    public static List<Auction> viewSellersAuctions(User loggedInUser) {
        return loggedInUser.getMySellingList();
    }

    public static List<Auction> viewWonAuctions(User loggedInUser) {
        return loggedInUser.getMyWonList();
    }
}

package srallegro.auction;

import srallegro.Category;
import srallegro.user.Database;
import srallegro.exception.*;
import srallegro.user.User;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class AuctionController {

    //jezeli currentoffer !=null , throw new exce
    public static BigDecimal bidUp(Auction auction, BigDecimal bidUp, User user) throws PriceTooLowException, YouCantBidUpYourOwnAuctionException, AuctionPriceIsBelowZeroOrZeroException {
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


    public static Auction createAuction(User currentUser, String title, String description, Category category, double amount) throws EmptyTitleException, AuctionPriceIsBelowZeroOrZeroException, EmptyDescriptionException {
        Database database = Database.getInstance();
        Random rd = new Random();
        Integer auctNumber = rd.nextInt(10000);
        while (database.getAuctionByNumber(auctNumber) != null) {
            auctNumber = rd.nextInt(10000);
        }

        Auction newAuction = new Auction(title, description, category, currentUser, null, new BigDecimal(amount), auctNumber, 0);
        if (title.length() == 0) {
            throw new EmptyTitleException();
        }
        if (amount < 0 || amount == 0) {
            throw new AuctionPriceIsBelowZeroOrZeroException();
        }
        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        }
        database.addAuctionToAllAuctions(newAuction);
        currentUser.getMySellingList().add(newAuction);
        category.addAuction(newAuction);
        SaveAuctionOnDisk.writeCsvFile("databaseAuction.txt", newAuction);
        return newAuction;
    }
    public static List<Auction> viewSellersAuctions(User loggedInUser) {
        return loggedInUser.getMySellingList();
    }
    public static List<Auction> viewWonAuctions(User loggedInUser) {
        return loggedInUser.getMyWonList();
    }
}

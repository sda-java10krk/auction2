package srallegro;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AuctionController {


    public static void  endOfAuction( Auction auction, User user){
        System.out.println("aukcja zakonczona, zwyciezył " + user.getNick());
        user.myWonList.add(auction);
    }

    public static BigDecimal bidUp(Auction auction , BigDecimal bidUp, User user) throws PriceTooLowException {
         BigDecimal newPrice = bidUp;

        if(newPrice.compareTo(auction.getPrice()) < 0 ){
         throw new PriceTooLowException();
        }else {
            auction.setPrice(newPrice);
            auction.setWinner(user);
            auction.setBids( auction.getBids()+1);

            if (auction.getBids() == 3){

                endOfAuction(auction, auction.getWinner());
            }
        }
        return auction.getPrice();
    }

    public static Auction createAuction(User currentUser, String title, String description, Category category, double amount)  {
        Integer auctionNumber = Database.allAuctions.size()+1;
        Auction newAuction = new Auction(title, description, category, currentUser, null, new BigDecimal(amount), auctionNumber, 0);
        Database.allAuctions.add(newAuction);
        currentUser.getMySellingList().add(newAuction);
        return newAuction;
    }

    public static List<Auction> viewSellersAuctions (User loggedInUser) {
        return loggedInUser.getMySellingList();
    }

    public static List<Auction> viewWonAuctions (User loggedInUser) {
        return loggedInUser.getMyWonList();
        }



}

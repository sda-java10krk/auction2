package srallegro;

import srallegro.Auction;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AuctionController {


    public static void  endOfAuction( Auction auction, User user){
        System.out.println("aukcja zakonczona, zwyciezył " + user);

    }



    public static BigDecimal bidUp(Auction auction , BigDecimal bidUp) throws PriceTooLowException {
         BigDecimal newPrice = bidUp;
        if(auction.getPrice().compareTo(newPrice) <0 ){
         throw new PriceTooLowException();
        }else {
            auction.setPrice(newPrice);
            if (auction.getBids() == 2){
                endOfAuction(auction, auction.getWinner());
            }
        }
        return null;
    }

    public static Auction createAuction(User currentUser, String title, String description, Category category, double amount)  {
        Integer auctionNumber = DatabaseUsers.allAuctions.size()+1;
        Auction newAuction = new Auction(title, description, category, currentUser, User.noWinner(), new BigDecimal(amount), auctionNumber, 0);
        DatabaseUsers.allAuctions.add(newAuction);
        return newAuction;
    }

}

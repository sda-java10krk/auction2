package srallegro;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AuctionController {

    public static BigDecimal BidUp(Auction auction , BigDecimal bidUp) throws IllegalArgumentException {
         BigDecimal newPrice= bidUp.add(auction.getPrice());
        if(auction.getPrice().compareTo(newPrice) <0 ){
         throw new IllegalArgumentException("Podaj oferte wyzsza od obecnej") ;
        }else {
            auction.setPrice(newPrice);
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

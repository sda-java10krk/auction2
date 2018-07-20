import java.math.BigDecimal;

public class AuctionActions {

    public static BigDecimal BidUp(Auction auction , BigDecimal bidUp) throws IllegalArgumentException{
         BigDecimal newPrice= bidUp.add(auction.getPrice());
        if(auction.getPrice().compareTo(newPrice) <0 ){
         throw new IllegalArgumentException("Podaj oferte wyzsza od obecnej") ;
        }else {
            auction.setPrice(newPrice);
        }
        return null;
    }



}

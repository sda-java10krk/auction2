package srallegro;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AuctionControlerTest {


    @Test
    public void testIfBidUpSetsPriceToNewPrice () throws PriceTooLowException {
        Category cat = new Category("lol");
        User user1 = new User("sad","sdf",124,"432","fsd", "fds" , "32");
        Auction sad = new Auction("lol","fs", cat,user1,user1,BigDecimal.valueOf(1000),0);
        BigDecimal result = AuctionController.bidUp(sad, BigDecimal.valueOf(324));
        BigDecimal expected = BigDecimal.valueOf(1324);
        assertEquals(expected,result);
    }



}

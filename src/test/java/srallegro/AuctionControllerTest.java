package srallegro;

import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AuctionControllerTest {

    User testSeller = new User ("", "", null, "", "", "", "Seller");
    Category category = new Category("CategoryName");
    Auction testAuction1 = AuctionController.createAuction(testSeller, "Title1", "Description1", category, 150.0);
    Auction testAuction2 = AuctionController.createAuction(testSeller, "Title2", "Description2", category, 372.5);

    @Test
    public void testIfBidUpSetsPriceToNewPrice () throws PriceTooLowException {
        Category cat = new Category("lol");
        User user1 = new User("sad","sdf",124,"432","fsd", "fds" , "32");
        Auction sad = new Auction("lol","fs", cat,user1,user1,BigDecimal.valueOf(1000),0, 0);
        BigDecimal result = AuctionController.bidUp(sad, BigDecimal.valueOf(324));
        BigDecimal expected = BigDecimal.valueOf(1324);
        assertEquals(expected,result);
    }

    @Test
    public void testCreateAuction() {

        TestCase.assertEquals(testSeller.getNick(), testAuction1.getSeller().getNick());
        TestCase.assertEquals(testAuction1.getTitle(), "Title1");
        TestCase.assertEquals(testAuction1.getDescription(), "Description1");
        TestCase.assertEquals(testAuction1.getCategory(), category);
        TestCase.assertEquals(testAuction1.getPrice(), new BigDecimal(150.0));
        TestCase.assertEquals(testAuction1.getWinner(), null);

        TestCase.assertEquals(testSeller.getNick(), testAuction2.getSeller().getNick());
        TestCase.assertEquals(testAuction2.getTitle(), "Title2");
        TestCase.assertEquals(testAuction2.getDescription(), "Description2");
        TestCase.assertEquals(testAuction2.getCategory(), category);
        TestCase.assertEquals(testAuction2.getPrice(), new BigDecimal(372.5));
        TestCase.assertEquals(testAuction2.getWinner(), null);

        TestCase.assertEquals(Database.allAuctions.size(), 2);

        List<Auction> testList = new LinkedList<>();
        testList.add(testAuction1);
        testList.add(testAuction2);
        TestCase.assertEquals(testSeller.mySellingList, testList);
    }

    @Test
    public void testViewSellersAuctions() {

        List<Auction> testList = new LinkedList<>();
        testList.add(testAuction1);
        testList.add(testAuction2);
        assertEquals(AuctionController.viewSellersAuctions(testSeller), testList);
    }

    @Test
    public void testViewWonAuctions() {

        List<Auction> testList2 = new LinkedList<>();
        testList2.add(testAuction1);
        testList2.add(testAuction2);
        testSeller.getMyWonList().add(testAuction1);
        testSeller.getMyWonList().add(testAuction2);
        assertEquals(AuctionController.viewWonAuctions(testSeller), testList2);
    }




}

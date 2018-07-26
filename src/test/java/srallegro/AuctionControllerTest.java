package srallegro;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import srallegro.auction.Auction;
import srallegro.auction.AuctionController;
import srallegro.exception.*;
import srallegro.user.Database;
import srallegro.user.User;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AuctionControllerTest {

    User testSeller = new User ("", "", null, "", "", "", "Seller");
    Category category = new Category("CategoryName");
    Auction testAuction1 = AuctionController.createAuction(testSeller, "Title1", "Description1", category, 150.0);
    Auction testAuction2 = AuctionController.createAuction(testSeller, "Title2", "Description2", category, 372.5);

    public AuctionControllerTest() throws Exception {
    }

    //before i database na nowy hashset
    @Before
    public void allAuctionsSizeSetToZero () {
        Database.allAuctions = new HashSet<>();
    }

    @Test (expected = EmptyTitleException.class)
    public void testCreateAuctionThrowEmptyTitleException () throws Exception{
        Auction auction = AuctionController.createAuction(testSeller ,"","lol", category,342);
    }
    @Test (expected = AuctionPriceIsBelowZeroOrZeroException.class)
    public void testCreateAuctionThrowBelowZeroException () throws Exception{
        Auction auction = AuctionController.createAuction(testSeller ,"fsd","lol", category,-32);

    }
    @Test (expected = EmptyDescriptionException.class)
    public void testCreateAuctionThrowEmptyDescribtionEmpty () throws Exception{
        Auction auction = AuctionController.createAuction(testSeller ,"sfd","", category,342);
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

    @Test
    public void testIfBidUpSetsPriceToNewPrice () throws Exception {
        Category cat = new Category("lol");
        User user1 = new User("saf","sdf",124,"432","fsd", "fds" , "32");
        User user2 = new User("sad","sdf",124,"432","fsd", "fds" , "32");

        Auction sad = new Auction("lol","fs", cat,user1,user1,BigDecimal.valueOf(1000),0, 0);
        BigDecimal result = AuctionController.bidUp(sad, BigDecimal.valueOf(1524),user2);
        BigDecimal expected = BigDecimal.valueOf(1524);
        assertEquals(expected,result);
    }

    @Test
    public void testIsBidUpIncreaseBids() throws Exception {
        Category cat = new Category("lol");
        User user1 = new User("sad","sdf",124,"432","fsd", "fds" , "32");
        User user2 = new User("saf", "sdf", 124, "432", "fsd", "fds", "32");
        User user3 = new User("s2", "sdf", 124, "432", "fsd", "fds", "32");
        Auction auction = new Auction("lol", "fs", cat, user1, user1, BigDecimal.valueOf(1000), 0, 0);
        BigDecimal bidUpAuction = AuctionController.bidUp(auction, BigDecimal.valueOf(1524), user2);
        BigDecimal bidUpAuction1 = AuctionController.bidUp(auction, BigDecimal.valueOf(1624), user3);
        int result = auction.getBids();
        assertEquals(2, result);

    }
    @Test
    public void testIsPersonWhoBidIsTheNewWinner() throws Exception {
        Category cat = new Category("lol");
        User user1 = new User("sad", "sdf", 124, "432", "fsd", "fds", "32");
        User user2 = new User("saf", "sdf", 124, "432", "fsd", "fds", "32");
        User user3 = new User("s2", "sdf", 124, "432", "fsd", "fds", "32");
        Auction auction = new Auction("lol", "fs", cat, user1, user1, BigDecimal.valueOf(1000), 0, 0);
        BigDecimal bidUpAuction = AuctionController.bidUp(auction, BigDecimal.valueOf(1524), user2);
        BigDecimal bidUpAuction2 = AuctionController.bidUp(auction, BigDecimal.valueOf(1524), user3);
        User winner = auction.getWinner();
        assertEquals(user3 , auction.getWinner());
        }

    @Test (expected = PriceTooLowException.class)
    public void testIsExceptionIsThrown () throws Exception {
         Category cat = new Category("lol");
         User user1 = new User("sad", "sdf", 124, "432", "fsd", "fds", "32");
        User user2 = new User("saf", "sdf", 124, "432", "fsd", "fds", "32");

         Auction auction = new Auction("lol", "fs", cat, user1, user1, BigDecimal.valueOf(1000), 0, 0);
         BigDecimal bidUpAuction = AuctionController.bidUp(auction, BigDecimal.valueOf(194), user2);
     }

     @Test
     public void testIsAuctionIsAddedToUserWonList () throws Exception {
         Category cat = new Category("lol");
         User user1 = new User("sad", "Kowalski", 124, "432", "fsd", "fds", "32");
         User user2 = new User("sad", "Jan", 124, "432", "fsd", "fds", "32");
         User user3 = new User("saf", "sf", 124, "432", "fsd", "fds", "32");
         Auction auction = new Auction("lol", "fs", cat, user1, user1, BigDecimal.valueOf(1000), 0, 0);
         BigDecimal bidUpAuction = AuctionController.bidUp(auction, BigDecimal.valueOf(1524), user2);
         BigDecimal bidUpAuction2 = AuctionController.bidUp(auction, BigDecimal.valueOf(1524), user3);
         BigDecimal bidUpAuction3 = AuctionController.bidUp(auction, BigDecimal.valueOf(1724), user2);
         boolean expected = user1.myWonList.contains(auction);
         boolean result = auction.getWinner() == user1;
         assertEquals(expected, result);
     }
     @Test (expected = YouCantBidUpYourOwnAuctionException.class)
     public void testYouCantBidUpYourOwnAuction () throws Exception {
         Category cat = new Category("lol");
         User seller = new User("sad", "sdf", 124, "432", "fsd", "fds", "32");
         User user1 = new User("sa", "sdf", 124, "432", "fsd", "fds", "32");
         Auction auction = new Auction("lol", "fs", cat, seller, user1, BigDecimal.valueOf(1000), 0, 0);
         BigDecimal bidUpAuction = AuctionController.bidUp(auction, BigDecimal.valueOf(1194), seller);
     }
    @Test (expected = YouCantBidUpYourOwnAuctionException.class)
    public void testYouCantBidUpAuctionThatYouBiddedUp () throws Exception {
        Category cat = new Category("lol");
        User seller = new User("sad", "sdf", 124, "432", "fsd", "fds", "32");
        User user1 = new User("sa", "sdf", 124, "432", "fsd", "fds", "32");
        Auction auction = new Auction("lol", "fs", cat, seller, user1, BigDecimal.valueOf(1000), 0, 0);
        BigDecimal bidUpAuction = AuctionController.bidUp(auction, BigDecimal.valueOf(1194), user1);
        BigDecimal bidUpAuction1 = AuctionController.bidUp(auction, BigDecimal.valueOf(1294), user1);
    }



}

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
import java.util.*;

import static org.junit.Assert.assertEquals;

public class AuctionControllerTest {

    User testSeller = new User("", "", 12 - 12 - 1992, "", "mymsil@mymail.com", "ieterw", "Seller");
    Category category = new Category("CategoryName");


    public AuctionControllerTest() throws Exception {
    }

    Database database = Database.getInstance();
    Map<Integer, Auction> allAuctions = database.getAllAuctionsByNumber();


    @Before
    public void allAuctionsSizeSetToZero() {
        database.getAllAuctionsByNumber().clear();
        allAuctions.clear();
    }

    @Test(expected = EmptyTitleException.class)
    public void testCreateAuctionThrowEmptyTitleException() throws Exception {
        Auction auction = AuctionController.createAuction(testSeller, "", "lol", category, BigDecimal.valueOf(342));
    }

    @Test(expected = AuctionPriceIsBelowZeroOrZeroException.class)
    public void testCreateAuctionThrowBelowZeroException() throws Exception {
        Auction auction = AuctionController.createAuction(testSeller, "fsd", "lol", category, BigDecimal.valueOf(-32));

    }

    @Test(expected = EmptyDescriptionException.class)
    public void testCreateAuctionThrowEmptyDescribtionEmpty() throws Exception {
        Auction auction = AuctionController.createAuction(testSeller, "sfd", "", category, BigDecimal.valueOf(342));
    }

    @Test
    public void testCreateAuction() throws Exception {
        Auction testAuction1 = null;

            testAuction1 = AuctionController.createAuction(testSeller, "Title1", "Description1", category, BigDecimal.valueOf(150));

        Auction testAuction2 = null;


            testAuction2 = AuctionController.createAuction(testSeller, "Title2", "Description2", category, BigDecimal.valueOf(372.5));
        User defaultWinner = new User("defwin","defwin",4,"defwin","defwin@deffault.com","defwin","Nikt nie zalicytowal tej aukcji");


        TestCase.assertEquals(testSeller.getNick(), testAuction1.getSeller().getNick());
        TestCase.assertEquals(testAuction1.getTitle(), "Title1");
        TestCase.assertEquals(testAuction1.getDescription(), "Description1");
        TestCase.assertEquals(testAuction1.getCategory(), category);
        TestCase.assertEquals(testAuction1.getPrice(), new BigDecimal(150));
        TestCase.assertEquals(testAuction1.getWinner(), defaultWinner);

        TestCase.assertEquals(testSeller.getNick(), testAuction2.getSeller().getNick());
        TestCase.assertEquals(testAuction2.getTitle(), "Title2");
        TestCase.assertEquals(testAuction2.getDescription(), "Description2");
        TestCase.assertEquals(testAuction2.getCategory(), category);
        TestCase.assertEquals(testAuction2.getPrice(), new BigDecimal(372.5));
        TestCase.assertEquals(testAuction2.getWinner(), defaultWinner);
        TestCase.assertEquals(database.getAllAuctionsByNumber().size(), 2);

        List<Auction> testList = new LinkedList<>();
        testList.add(testAuction1);
        testList.add(testAuction2);
        TestCase.assertEquals(testSeller.mySellingList, testList);
    }

    @Test
    public void testViewSellersAuctions() throws Exception {
        Auction testAuction1 = AuctionController.createAuction(testSeller, "Title1", "Description1", category, BigDecimal.valueOf(150.0));
        Auction testAuction2 = AuctionController.createAuction(testSeller, "Title2", "Description2", category, BigDecimal.valueOf(372.5));

        List<Auction> testList = new LinkedList<>();
        testList.add(testAuction1);
        testList.add(testAuction2);
        assertEquals(AuctionController.viewSellersAuctions(testSeller), testList);
    }

    @Test
    public void testViewWonAuctions() throws Exception {
        Auction testAuction1 = AuctionController.createAuction(testSeller, "Title1", "Description1", category, BigDecimal.valueOf(150.0));
        Auction testAuction2 = AuctionController.createAuction(testSeller, "Title2", "Description2", category, BigDecimal.valueOf(372.5));

        List<Auction> testList2 = new LinkedList<>();
        testList2.add(testAuction1);
        testList2.add(testAuction2);
        testSeller.getMyWonList().add(testAuction1);
        testSeller.getMyWonList().add(testAuction2);
        assertEquals(AuctionController.viewWonAuctions(testSeller), testList2);
    }

    @Test
    public void testIfBidUpSetsPriceToNewPrice() throws Exception {
        Category cat = new Category("lol");
        User user1 = new User("saf", "sdf", 124, "432", "fsdfsdaa@gmail.com", "fddgs", "32");
        User user2 = new User("sad", "sdf", 124, "432", "fsdfsdaa@gmail.com", "fgfdgdds", "32");

        Auction sad = new Auction("lol", "fs", cat, user1, user1, BigDecimal.valueOf(1000), 0, 0);
        BigDecimal result = AuctionController.bidUp(sad, BigDecimal.valueOf(1524), user2);
        BigDecimal expected = BigDecimal.valueOf(1524);
        assertEquals(expected, result);
    }

    @Test
    public void testIsBidUpIncreaseBids() throws Exception {
        Category cat = new Category("lol");
        User user1 = new User("sad", "sdf", 124, "432", "fsdfsdaa@gmail.com", "fdswer", "32");
        User user2 = new User("saf", "sdf", 124, "432", "fsdfsdaa@gmail.com", "fdstre", "32");
        User user3 = new User("s2", "sdf", 124, "432", "fsdfsdaa@gmail.com", "fdsfgd", "32");
        Auction auction = new Auction("lol", "fs", cat, user1, user1, BigDecimal.valueOf(1000), 0, 0);
        BigDecimal bidUpAuction = AuctionController.bidUp(auction, BigDecimal.valueOf(1524), user2);
        BigDecimal bidUpAuction1 = AuctionController.bidUp(auction, BigDecimal.valueOf(1624), user3);
        int result = auction.getBids();
        assertEquals(2, result);

    }

    @Test
    public void testIsPersonWhoBidIsTheNewWinner() throws Exception {
        Category cat = new Category("lol");
        User user1 = new User("sad", "sdf", 124, "432", "fsdaa@gmail.com", "fgdgds", "32");
        User user2 = new User("saf", "sdf", 124, "432", "fsdaa@gmail.com", "fgdfgdds", "32");
        User user3 = new User("s2", "sdf", 124, "432", "fsdfsdaa@gmail.com", "fdfgdgdfs", "32");
        Auction auction = new Auction("lol", "fs", cat, user1, user1, BigDecimal.valueOf(1000), 0, 0);
        BigDecimal bidUpAuction = AuctionController.bidUp(auction, BigDecimal.valueOf(1524), user2);
        BigDecimal bidUpAuction2 = AuctionController.bidUp(auction, BigDecimal.valueOf(1524), user3);
        User winner = auction.getWinner();
        assertEquals(user3, auction.getWinner());
    }

    @Test(expected = PriceTooLowException.class)
    public void testIsExceptionIsThrown() throws Exception {
        Category cat = new Category("lol");
        User user1 = new User("sad", "sdf", 124, "432", "fsdaa@gmail.com", "ffdgdfds", "32");
        User user2 = new User("saf", "sdf", 124, "432", "fsdaa@gmail.com", "fdfdgds", "32");

        Auction auction = new Auction("lol", "fs", cat, user1, user1, BigDecimal.valueOf(1000), 0, 0);
        BigDecimal bidUpAuction = AuctionController.bidUp(auction, BigDecimal.valueOf(194), user2);
    }

    @Test
    public void testIsAuctionIsAddedToUserWonList() throws Exception {
        Category cat = new Category("lol");
        User user1 = new User("sad", "Kowalski", 124, "432", "fsdaa@gmail.com", "fdgfds", "32");
        User user2 = new User("sad", "Jan", 124, "432", "fsdaa@gmail.com", "fdfdgs", "32");
        User user3 = new User("saf", "sf", 124, "432", "fsdaa@gmail.com", "gfdfds", "32");
        Auction auction = new Auction("lol", "fs", cat, user1, user1, BigDecimal.valueOf(1000), 0, 0);
        BigDecimal bidUpAuction = AuctionController.bidUp(auction, BigDecimal.valueOf(1524), user2);
        BigDecimal bidUpAuction2 = AuctionController.bidUp(auction, BigDecimal.valueOf(1524), user3);
        BigDecimal bidUpAuction3 = AuctionController.bidUp(auction, BigDecimal.valueOf(1724), user2);
        boolean expected = user1.myWonList.contains(auction);
        boolean result = auction.getWinner() == user1;
        assertEquals(expected, result);
    }

    @Test(expected = YouCantBidUpYourOwnAuctionException.class)
    public void testYouCantBidUpYourOwnAuction() throws Exception {
        Category cat = new Category("lol");
        User seller = new User("sad", "sdf", 124, "432", "fsdaa@gmail.com", "dfgfds", "32");
        User user1 = new User("sa", "sdf", 124, "432", "fsdaa@gmail.com", "ffgdds", "32");
        Auction auction = new Auction("lol", "fs", cat, seller, user1, BigDecimal.valueOf(1000), 0, 0);
        BigDecimal bidUpAuction = AuctionController.bidUp(auction, BigDecimal.valueOf(1194), seller);
    }

    @Test(expected = YouCantBidUpYourOwnAuctionException.class)
    public void testYouCantBidUpAuctionThatYouBiddedUp() throws Exception {
        Category cat = new Category("lol");
        User seller = new User("sad", "sdf", 124, "432", "fsdaa@gmail.com", "fdfgds", "32");
        User user1 = new User("sa", "sdf", 124, "432", "fsdaa@gmail.com", "ffdgds", "32");
        Auction auction = new Auction("lol", "fs", cat, seller, user1, BigDecimal.valueOf(1000), 0, 0);
        BigDecimal bidUpAuction = AuctionController.bidUp(auction, BigDecimal.valueOf(1194), user1);
        BigDecimal bidUpAuction1 = AuctionController.bidUp(auction, BigDecimal.valueOf(1294), user1);
    }


}

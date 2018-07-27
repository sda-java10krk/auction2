package srallegro;

import org.junit.Before;
import org.junit.Test;
import srallegro.auction.Auction;
import srallegro.auction.AuctionController;
import srallegro.exception.AuctionPriceIsBelowZeroOrZeroException;
import srallegro.exception.EmptyDescriptionException;
import srallegro.exception.EmptyTitleException;
import srallegro.user.Database;
import srallegro.user.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DatabaseTest {
    Database database = Database.getInstance();

    @Before
    public void clearAllDatabaseCollections() {
        database.getAllAuctionsByNumber().clear();
        database.getAllUsersByNickname().clear();
        database.getAllCategoriesByName().clear();
    }

    @Test
    public void testIfCategoriesAreStoredCorrectly() {
        CategoryController.createCategoryTree();
        assertEquals(database.getAllCategoriesByName().size(), 12);
        assertNotNull(database.getCategoryByName("Samochody"));
        assertNotNull(database.getCategoryByName("Stormtrooperzy"));
        assertNotNull(database.getCategoryByName("Elektroniczne konie"));
        assertNotNull(database.getCategoryByName("Telewizory"));
        assertNull(database.getCategoryByName("NullCategory"));
        Category testCategory = new Category ("Test");
        database.addCategoryToAllCategories(testCategory);
        assertNotNull(database.getCategoryByName("Test"));
        assertEquals(database.getAllCategoriesByName().size(), 13);
    }

    @Test
    public void testIfAuctionsAreStoredCorrectly() throws EmptyTitleException, EmptyDescriptionException, AuctionPriceIsBelowZeroOrZeroException {
        Category testCategory = new Category ("Test");
        User janek = new User("janek", "janek", 0, "janek", "janek", "janek", "janek");
        Auction testAuction1 = AuctionController.createAuction(janek, "Title1", "descr1", testCategory, 10.0);
        Auction testAuction2 = AuctionController.createAuction(janek, "Title2", "descr2", testCategory, 12.0);
        Auction testAuction3 = AuctionController.createAuction(janek, "Title3", "descr3", testCategory, 13.0);
        Auction testAuction4 = AuctionController.createAuction(janek, "Title4", "descr4", testCategory, 14.0);

        assertEquals(database.getAllAuctionsByNumber().size(), 4);

        assertNotNull(database.getAuctionByNumber(testAuction1.getAuctionNumber()));
        assertNotNull(database.getAuctionByNumber(testAuction2.getAuctionNumber()));
        assertNotNull(database.getAuctionByNumber(testAuction3.getAuctionNumber()));
        assertNotNull(database.getAuctionByNumber(testAuction4.getAuctionNumber()));

        assertEquals(database.getAuctionByNumber(testAuction1.getAuctionNumber()), testAuction1);
        assertEquals(database.getAuctionByNumber(testAuction2.getAuctionNumber()), testAuction2);
        assertEquals(database.getAuctionByNumber(testAuction3.getAuctionNumber()), testAuction3);
        assertEquals(database.getAuctionByNumber(testAuction4.getAuctionNumber()), testAuction4);
    }

    @Test
    public void testIfUsersAreStoredCorrectly() {
        Database database = Database.getInstance();

        User janek = new User("janek", "janek", 0, "janek", "janek", "janek", "janek");
        User janek2 = new User("janek", "janek", 0, "janek", "janek", "janek", "janek2");
        User janek3 = new User("janek", "janek", 0, "janek", "janek", "janek", "janek3");
        User janek4 = new User("piotrek", "janek", 0, "janek", "janek", "janek", "janek4");

        database.addUserToAllUsers(janek);
        database.addUserToAllUsers(janek2);
        database.addUserToAllUsers(janek3);
        database.addUserToAllUsers(janek4);

        assertEquals(database.getAllUsersByNickname().size(), 4);
        assertEquals(database.getUserByNickname("janek3").getName(), "janek");
        assertEquals(database.getUserByNickname("janek4").getName(), "piotrek");
    }
}

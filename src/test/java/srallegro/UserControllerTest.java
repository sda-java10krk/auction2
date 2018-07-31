package srallegro;

import org.junit.Before;
import org.junit.Test;
import srallegro.auction.Auction;
import srallegro.exception.*;
import srallegro.user.Database;
import srallegro.user.User;
import srallegro.user.UserController;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class UserControllerTest {

    User testSeller = new User("", "", 42, "", "", "SellerPassword", "Seller");
    Database database = Database.getInstance();

    public UserControllerTest() throws BirthdayException, PasswordTooShortException, EmptyNickException, UserWithSameNicknameExists {
    }

    @Before
    public void clearCollections() {
        database.getAllUsersByNickname().clear();
        database.getAllCategoriesByName().clear();
        database.getAllAuctionsByNumber().clear();
    }

    @Test
    public void testCreateUser() throws BirthdayException, UserWithSameNicknameExists, EmptyNickException, PasswordTooShortException, EmptyCategoryNameException, EmptyTitleException, EmptyDescriptionException, AuctionPriceIsBelowZeroOrZeroException {
        User testUser = UserController.createUser2("Test", "Testson", 12-22-4323, "Testtown", "Test@test.com", "testpass", "tester");

        assertEquals(testUser.getName(),"Test");
        assertEquals(testUser.getLastName(),"Testson");
        assertEquals(testUser.getAddress(),"Testtown");
        assertEquals(testUser.getMail(),"Test@test.com");
        assertEquals(testUser.getPassword(),"testpass");
        assertEquals(testUser.getNick(),"tester");

        assertEquals(testUser, database.getUserByNickname("tester"));
        assertNotNull(database.getUserByNickname("tester").getMySellingList());
        assertNotNull(database.getUserByNickname("tester").getMyWonList());
    }

    @Test
    public void testLogin() throws Exception {
        database.addUserToAllUsers(testSeller);
        assertEquals(UserController.login("Seller", "SellerPassword"), testSeller);
        assertEquals(UserController.login("nonExistingDude", "noPassword"), null);
    }

    @Test(expected = EmptyNickException.class)
     public void testIsEmptyNickExceptionIsThrown() throws Exception {
        User testUser = new User("re","reg", 324,"konwaliowa","gdf","testtest","");
    }

    @Test (expected = BirthdayException.class)
      public  void testIsBirthdayExceptionIsThrown() throws Exception {
        User testUser = new User("re","reg", null,"konwaliowa","gdf","testtest","ijj");

    }
    @Test (expected = PasswordTooShortException.class)
    public void testIsPasswordToShortExceptionIsThrown() throws Exception{
        User testUser = new User("re","reg", 87,"konwaliowa","gdf","test","ijj");
    }

    @Test (expected = UserWithSameNicknameExists.class)
    public void testIfUserWithSameNicknameExists() throws Exception {
        User testUser = new User("re","reg", 5,"konwaliowa","gdf","testtest","mateusz");
        database.addUserToAllUsers(testUser);
        User testUser2 = new User("rde","rdeg", 56,"konwaliowad","gdfh","testbtest","mateusz");
    }
}

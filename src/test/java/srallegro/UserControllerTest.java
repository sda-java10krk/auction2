package srallegro;

import org.junit.Before;
import org.junit.Test;
import srallegro.exception.*;
import srallegro.user.Database;
import srallegro.user.User;
import srallegro.user.UserController;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class UserControllerTest {

    Database database = Database.getInstance();

    public UserControllerTest() throws BirthdayException, PasswordTooShortException, EmptyNickException, UserWithSameNicknameExists, IncorrectEmailFormatException {
    }

    @Before
    public void clearCollections() {
        database.getAllUsersByNickname().clear();
        database.getAllCategoriesByName().clear();
        database.getAllAuctionsByNumber().clear();
    }

    @Test
    public void testCreateUser() throws BirthdayException, UserWithSameNicknameExists, EmptyNickException, PasswordTooShortException, EmptyCategoryNameException, EmptyTitleException, EmptyDescriptionException, AuctionPriceIsBelowZeroOrZeroException, IncorrectEmailFormatException {
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
        User testSeller = new User("", "", 42, "", "eee@eee.com", "SellerPassword", "Seller");
        database.addUserToAllUsers(testSeller);
        assertEquals(UserController.login("Seller", "SellerPassword"), testSeller);
        assertEquals(UserController.login("nonExistingDude", "noPassword"), null);
    }

    @Test(expected = EmptyNickException.class)
     public void testIsEmptyNickExceptionIsThrown() throws Exception {
        User testUser = new User("re","reg", 324,"konwaliowa","gdf@ooo.com","testtest","");
    }

    @Test (expected = BirthdayException.class)
      public  void testIsBirthdayExceptionIsThrown() throws Exception {
        User testUser = new User("re","reg", null,"konwaliowa","gdf@ooo.com","testtest","iajj");

    }
    @Test (expected = PasswordTooShortException.class)
    public void testIsPasswordToShortExceptionIsThrown() throws Exception{
        User testUser = new User("re","reg", 87,"konwaliowa","gdf@oo.com","test","ijaj");
    }

    @Test (expected = UserWithSameNicknameExists.class)
    public void testIfUserWithSameNicknameExists() throws Exception {
        User testUser = new User("re","reg", 5,"konwaliowa","gdfdd@odo.com","testtest","mateusz");
        database.addUserToAllUsers(testUser);
        User testUser2 = new User("rde","rdeg", 56,"konwaliowad","gdfh@sds.com","testbtest","mateusz");
    }

    @Test (expected = IncorrectEmailFormatException.class)
    public void testIfEmailExceptionIsThrownWhenEmailAddressIsRandom() throws Exception {
        User testUser = new User("re","reg", 5,"konwaliowa","exceptionplease","testtest","mateusz");
    }

    @Test (expected = IncorrectEmailFormatException.class)
    public void testIfEmailExceptionIsThrownWhenEmailAddressLacksAt() throws Exception {
        User testUser = new User("re","reg", 5,"konwaliowa","exceptionplease.com","testtest","mateusz");
    }

    @Test (expected = IncorrectEmailFormatException.class)
    public void testIfEmailExceptionIsThrownWhenEmailAddressLacksEnding() throws Exception {
        User testUser = new User("re","reg", 5,"konwaliowa","exception@please","testtest","mateusz");
    }

    @Test (expected = IncorrectEmailFormatException.class)
    public void testIfEmailExceptionIsThrownWhenWeirdDigitsAreUsed() throws Exception {
        User testUser = new User("re","reg", 5,"konwaliowa","ęxceptióń@please??ł#%((]}żź?.ćóm","testtest","mateusz");
    }
}

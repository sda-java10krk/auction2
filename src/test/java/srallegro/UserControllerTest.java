package srallegro;

import org.junit.Test;
import srallegro.exception.*;
import srallegro.exception.BirthdayException;
import srallegro.exception.EmptyNickException;
import srallegro.exception.PasswordTooShortException;
import srallegro.exception.UserWithSameNicknameExists;
import srallegro.user.Database;
import srallegro.user.RegisterUser;
import srallegro.user.User;
import srallegro.user.UserController;

import static org.junit.Assert.assertEquals;

public class UserControllerTest {

    User testSeller = new User("", "", 42, "", "", "SellerPassword", "Seller");
    Database database = Database.getInstance();

    //to jest chyba chujowe ale nie wiem bo bez tego nie dziala
    public UserControllerTest() throws BirthdayException, PasswordTooShortException, EmptyNickException, UserWithSameNicknameExists {
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

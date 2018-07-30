package srallegro;

import org.junit.Test;
import srallegro.exception.*;
import srallegro.user.Database;
import srallegro.user.RegisterUser;
import srallegro.user.User;
import srallegro.user.UserController;

import static org.junit.Assert.assertEquals;

public class UserControllerTest {

    User testSeller = new User("", "", 42, "", "", "SellerPassword", "Seller");
    User mrNull = null;

    //to jest chyba chujowe ale nie wiem bo bez tego nie dziala
    public UserControllerTest() throws BirthdayException, PasswordTooShortException, EmptyNickException {
    }

    @Test
    public void testLogin() throws Exception {
        Database.addUserToAllUsers(testSeller);
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

}

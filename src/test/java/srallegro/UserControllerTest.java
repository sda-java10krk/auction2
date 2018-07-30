package srallegro;

import org.junit.Test;
import srallegro.exception.BirthdayException;
import srallegro.exception.EmptyNickException;
import srallegro.exception.PasswordTooShortException;
import srallegro.exception.UserWithSameNicknameExists;
import srallegro.user.Database;
import srallegro.user.User;
import srallegro.user.UserController;

import static org.junit.Assert.assertEquals;

public class UserControllerTest {

    User testSeller = new User("", "", null, "", "", "SellerPassword", "Seller");
    User mrNull = null;

    //to jest chyba chujowe ale nie wiem bo bez tego nie dziala
    public UserControllerTest() throws BirthdayException, PasswordTooShortException, EmptyNickException, UserWithSameNicknameExists {
    }

    @Test
    public void testLogin() throws Exception {
        Database.addUserToAllUsers(testSeller);
        assertEquals(UserController.login("Seller", "SellerPassword"), testSeller);
        assertEquals(UserController.login("nonExistingDude", "noPassword"), null);
    }
}

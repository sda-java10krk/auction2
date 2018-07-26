package srallegro;

import org.junit.Test;
import srallegro.exception.BirthdayException;
import srallegro.exception.EmptyNickException;
import srallegro.exception.PasswordTooShortException;
import srallegro.user.Database;
import srallegro.user.User;
import srallegro.user.UserController;

import static org.junit.Assert.assertEquals;

public class UserControllerTest  {

    User testSeller = new User("", "", null, "", "", "SellerPassword", "Seller");
    User mrNull = null;



    @Test
    public void testLogin()  throws Exception {
        Database.usersByName.put("Seller", testSeller);
        assertEquals(UserController.login("Seller", "SellerPassword"), testSeller);
        assertEquals(UserController.login( "nonExistingDude", "noPassword"), null);

    }
}

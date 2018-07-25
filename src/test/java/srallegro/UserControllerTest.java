package srallegro;

import org.junit.Test;
import srallegro.User.Database;
import srallegro.User.User;
import srallegro.User.UserController;

import static org.junit.Assert.assertEquals;

public class UserControllerTest {

    User testSeller = new User("", "", null, "", "", "SellerPassword", "Seller");
    User mrNull = null;


    @Test
    public void testLogin() {
        Database.usersByName.put("Seller", testSeller);
        assertEquals(UserController.login("Seller", "SellerPassword"), testSeller);
        assertEquals(UserController.login( "nonExistingDude", "noPassword"), null);

    }
}

package srallegro;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserControllerTest {

    User testSeller = new User ("", "", null, "", "", "SellerPassword", "Seller");
    User mrNull = null;


    @Test
    public void testLogin() {
        Database.usersByName.put("Seller", testSeller);
        assertEquals(UserController.login("Seller", "SellerPassword"), testSeller);
        assertEquals(UserController.login( "nonExistingDude", "noPassword"), null);

    }
}

import org.junit.Test;
import srallegro.*;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class AuctionControllerTest {

    @Test
    public void testCreateAuction() {
        User testSeller = new User ("", "", null, "", "", "", "Seller");
        Category category = new Category("CategoryName");

        Auction testAuction1 = AuctionController.createAuction(testSeller, "Title1", "Description1", category, 150.0);
        assertEquals(testSeller.getNick(), testAuction1.getSeller().getNick());
        assertEquals(testAuction1.getTitle(), "Title1");
        assertEquals(testAuction1.getDescription(), "Description1");
        assertEquals(testAuction1.getCategory(), category);
        assertEquals(testAuction1.getPrice(), new BigDecimal(150.0));

        Auction testAuction2 = AuctionController.createAuction(testSeller, "Title2", "Description2", category, 372.5);
        assertEquals(testSeller.getNick(), testAuction2.getSeller().getNick());
        assertEquals(testAuction2.getTitle(), "Title2");
        assertEquals(testAuction2.getDescription(), "Description2");
        assertEquals(testAuction2.getCategory(), category);
        assertEquals(testAuction2.getPrice(), new BigDecimal(372.5));

        assertEquals(DatabaseUsers.allAuctions.size(), 2);
    }

}

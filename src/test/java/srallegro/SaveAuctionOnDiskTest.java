package srallegro;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import srallegro.auction.Auction;
import srallegro.auction.SaveAuctionOnDisk;
import srallegro.exception.*;
import srallegro.user.Database;
import srallegro.user.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class SaveAuctionOnDiskTest {
    Database database = Database.getInstance();

    @Before
    public void clearCollections() throws IOException {
        database.getAllUsersByNickname().clear();
        database.getAllAuctionsByNumber().clear();
        database.getAllCategoriesByName().clear();
        //czyści plik. Trzeba to robić, bo inaczej dubluje userów. Ale coś nie chce działać,
        //prawie zawsze psuje to wczytywanie userów z pliku.
        FileUtils.writeStringToFile(new File("TestDatabaseAuction.csv"), "");
    }

    @Test
    public void testSaveAuction () throws Exception {
        User testSeller = new User("RandomName", "RandomLastName", 19870101, "RandomAddress", "RandomMail@gmail.com", "RandomPassword", "RandomNick");
        User testWinner = new User("RandomName2", "RandomLastName2", 19870102, "RandomAddress2", "RandomMail@gmail.pl", "RandomPassword2", "RandomNick2");
        Category testCategory = new Category("RandomCatName");
        Auction testAuction = new Auction("RandomTitle", "RandomDescription", testCategory, testSeller, testWinner, BigDecimal.valueOf(100), 1, 0);
        SaveAuctionOnDisk.writeCsvFile("TestDatabaseAuction.csv", testAuction);
        BufferedReader fr = new BufferedReader(new FileReader("TestDatabaseAuction.csv"));
        assertEquals("RandomTitle,RandomDescription,RandomCatName,RandomNick,RandomNick2,"+100+","+1+","+0, fr.readLine());

    }
}

package srallegro;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import srallegro.exception.*;
import srallegro.user.Database;
import srallegro.user.LoadUserFromDisk;
import srallegro.user.SaveUserOnDisk;
import srallegro.user.User;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class SaveUserOnDiskTest {

    Database database = Database.getInstance();

    @Before
    public void clearCollections() throws IOException {
        database.getAllUsersByNickname().clear();
        database.getAllAuctionsByNumber().clear();
        database.getAllCategoriesByName().clear();

        //czyści plik. Trzeba to robić, bo inaczej dubluje userów. Ale coś nie chce działać,
        //prawie zawsze psuje to wczytywanie userów z pliku.
        FileUtils.writeStringToFile(new File("TestDatabaseUser.csv"), "");
    }

    public void testUserSaving() throws UserWithSameNicknameExists, PasswordTooShortException, EmptyNickException, BirthdayException, IOException {
        User testUser = new User("RandomName", "RandomLastName", 19870101, "RandomAddress", "RandomMail", "RandomPassword", "RandomNick");
        SaveUserOnDisk.writeCsvFile("TestDatabaseUser.csv", testUser);
        BufferedReader fr = new BufferedReader(new FileReader("TestDatabaseUser.csv"));
        assertEquals("RandomName,RandomLastName,19870101,RandomAddress,RandomMail,RandomPassword,RandomNick", fr.readLine());
    }

    @Test
    public void testUsersLoading() throws UserWithSameNicknameExists, PasswordTooShortException, EmptyNickException, BirthdayException, IOException {
        Database database2 = Database.getInstance();
        User testUser = new User("RandomName", "RandomLastName", 19870101, "RandomAddress", "RandomMail", "RandomPassword", "RandomNick");
        SaveUserOnDisk.writeCsvFile("TestDatabaseUser.csv", testUser);
        LoadUserFromDisk.readFileCSV("TestDatabaseUser.csv");
        Database database = Database.getInstance();
        User newUser = database2.getUserByNickname("RandomNick");

        assertEquals(testUser, database.getAllUsersByNickname().get("RandomNick"));

        System.out.println(database2.getAllUsersByNickname().size());   //!! tu widać, że nie dodaje usera do mapy
        System.out.println(newUser);    //a tutaj, że w ogóle go nie tworzy (drukuje "null")

        assertEquals(newUser.getName(), "RandomName");
    }
}






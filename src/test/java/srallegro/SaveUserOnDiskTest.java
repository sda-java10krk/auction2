package srallegro;

import org.junit.Before;
import org.junit.Test;
import srallegro.exception.*;
import srallegro.user.Database;
import srallegro.user.LoadUserFromDisk;
import srallegro.user.SaveUserOnDisk;
import srallegro.user.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SaveUserOnDiskTest {

    @Test
    public void testIfUsersAreSavedOnDisk() throws Exception {
        User testUser = new User("RandomName", "RandomLastName", 19870101,"RandomAddress","RandomMail","RandomPassword","RandomNick");
        SaveUserOnDisk.writeCsvFile("TestDatabaseUser.csv", testUser );
        LoadUserFromDisk.readFileCSV("TestDatabaseUser.csv");
        Database database = Database.getInstance();
        assertEquals(testUser, database.getAllUsersByNickname().get("RandomNick"));
    }


    }






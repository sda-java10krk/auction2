package srallegro.user;

import srallegro.exception.BirthdayException;
import srallegro.exception.EmptyNickException;
import srallegro.exception.PasswordTooShortException;
import srallegro.exception.UserWithSameNicknameExists;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadUserFromDisk {
    private static LoadUserFromDisk instance;

    private LoadUserFromDisk() {
    }

    private static LoadUserFromDisk getInstance() {
        if (instance == null) {
            instance = new LoadUserFromDisk();
        }
        return instance;
    }

    private static final String COMMA_SEPARATOR = ",";
    private static final int USER_NAME = 0;
    private static final int USER_LASTNAME = 1;
    private static final int USER_BIRTHDAY = 2;
    private static final int USER_ADDRESS = 3;
    private static final int USER_MAIL = 4;
    private static final int USER_PASSWORD = 5;
    private static final int USER_NICK = 6;

    public static void readFileCSV(String fileName) throws BirthdayException, PasswordTooShortException, EmptyNickException, UserWithSameNicknameExists, UserWithSameNicknameExists {

        Database database = Database.getInstance();
        BufferedReader fileReader = null;
        String line = "";
        try {
            fileReader = new BufferedReader(new FileReader(fileName));
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                String[] data = line.split(COMMA_SEPARATOR);
                if (data.length > 0) {
                    User user = new User(data[USER_NAME], data[USER_LASTNAME], Integer.parseInt(data[USER_BIRTHDAY]), data[USER_ADDRESS],
                            data[USER_MAIL], data[USER_PASSWORD], data[USER_NICK]);
                    database.addUserToAllUsers(user);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
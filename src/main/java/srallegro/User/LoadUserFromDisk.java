package srallegro.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LoadUserFromDisk {
    private static LoadUserFromDisk instance;

    private  LoadUserFromDisk(){
    }

    private static LoadUserFromDisk getInstance() {
        if (instance == null) {
            instance = new LoadUserFromDisk();
        }
        return instance;
    }

    private static final String COMMA_SEPARATOR = ",";private static final int USER_ADDRESS =3;
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final int USER_NAME =0;
    private static final int USER_LASTNAME =1;
    private static final int USER_BIRTHDAY =2;

    private static final int USER_MAIL =4;
    private static final int USER_PASSWORD =5;
    private static final int USER_NICK =6;

    public static void readFileCSV(String fileName, User user){

        BufferedReader filereader =null;

        String line ="";
        try {
            filereader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }k
    }



}

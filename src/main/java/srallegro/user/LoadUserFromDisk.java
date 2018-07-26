package srallegro.user;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

        BufferedReader fileReader =null;

        String line ="";
        try {
            fileReader = new BufferedReader(new FileReader(fileName));
            fileReader.readLine();

            while ((line = fileReader.readLine()) != null){
                String[] tokens =line.split(COMMA_SEPARATOR);
                if (tokens.length>0){
                    //   User user1 = new User();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
package srallegro.User;

import java.io.FileWriter;
import java.io.IOException;

public class SaveUserOnDisk {
    private static SaveUserOnDisk instance;

    private SaveUserOnDisk() {
    }

    private static SaveUserOnDisk getInstance() {
        if (instance == null) {
            instance = new SaveUserOnDisk();
        }
        return instance;
    }

    private static final String COMMA_SEPARATOR = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String PARAMETRS = "name,lastName,,birthDay,address,mail,password,nick";

    public static void writeCsvFile(String filename, User user) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(filename);
            fileWriter.append(PARAMETRS.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);

            fileWriter.append(user.getName());
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(user.getLastName());
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(String.valueOf(user.getBirthday()));
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(user.getAddress());
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(user.getMail());
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(user.getPassword());
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(user.getNick());
            fileWriter.append(NEW_LINE_SEPARATOR);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

package srallegro.user;

import java.util.HashMap;
import java.util.Map;

public class UsersMap {


    private static UsersMap instance;

    private UsersMap(){}

    public static synchronized UsersMap getInstance() {
        if(instance == null) {
            instance = new UsersMap();
        }
        return instance;
    }

    private static Map<String, srallegro.user.User> allUsersByNickname = new HashMap<>();

    public static Map<String, srallegro.user.User> getAllUsersByNickname() {
        return allUsersByNickname;
    }



    public static void addUserToAllUsers (srallegro.user.User user) {
        allUsersByNickname.put(user.getNick(), user);
    }

    public static User getUserByNickname (String nickname) {
        try {
            User user = allUsersByNickname.get(nickname);
            return user;
        } catch (NullPointerException npe) {
            System.out.println("Nie ma takiego użytkownika");
        }
        return null;
    }

}

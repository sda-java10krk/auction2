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

    private static Map<String, User> allUsersByNickname = new HashMap<>();

    public static Map<String, User> getAllUsersByNickname() {
        return allUsersByNickname;
    }

    public static void addUserToAllUsers (User user) {
        allUsersByNickname.put(user.getNick(), user);
    }

}

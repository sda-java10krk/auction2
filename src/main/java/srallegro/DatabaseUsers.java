package srallegro;

import java.util.HashMap;
import java.util.Map;


public class DatabaseUsers {
    Map<String, User> usersByName = new HashMap<>();

    protected void addUser(User user) {
        String  userNick = user.getNick();
        usersByName.put(userNick, user);
    }
}

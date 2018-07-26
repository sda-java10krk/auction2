package srallegro.User;

public class UserController {

    public static User login (String loginn, String passwordd) {
        UsersMap allusers = UsersMap.getInstance();
        try {
            if (UsersMap.getAllUsersByNickname().get(loginn).getPassword().equals(passwordd)) {
                System.out.println("Dziękuję za zalogowanie");
                return UsersMap.getAllUsersByNickname().get(loginn);
            } else {
                System.out.println("Błędne hasło");
                return null;
            }
        } catch (NullPointerException npe) {
            System.out.println("Nie ma takiego użytkownika");
            return null;
        }

    }
}

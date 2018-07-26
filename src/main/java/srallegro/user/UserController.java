package srallegro.user;

public class UserController {

    public static srallegro.user.User login (String loginn, String passwordd) {
        srallegro.user.UsersMap allusers = srallegro.user.UsersMap.getInstance();
        try {
            if (srallegro.user.UsersMap.getAllUsersByNickname().get(loginn).getPassword().equals(passwordd)) {
                System.out.println("Dziękuję za zalogowanie");
                return srallegro.user.UsersMap.getAllUsersByNickname().get(loginn);
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

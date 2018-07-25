package srallegro;

public class UserController {

    public static User login (String loginn, String passwordd) {
        try {
            if (Database.usersByName.get(loginn).getPassword().equals(passwordd)) {
                System.out.println("Dziękuję za zalogowanie");
                return Database.usersByName.get(loginn);
            } else {
                return null;
            }
        } catch (NullPointerException npe) {
            System.out.println("Nie ma takiego użytkownika");
            return null;
        }

    }
}

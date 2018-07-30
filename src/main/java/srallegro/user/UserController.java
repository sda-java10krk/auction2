package srallegro.user;

public class UserController {


    public static srallegro.user.User login(String login, String passwordd) {

        Database database = Database.getInstance();
        try {
            if (
                    database.getUserByNickname(login).getPassword().equals(passwordd)) {
                System.out.println("Dziękuję za zalogowanie");
                return database.getUserByNickname(login);
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

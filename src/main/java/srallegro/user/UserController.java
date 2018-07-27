package srallegro.user;

public class UserController {



    public static srallegro.user.User login (String loginn, String passwordd) {

        //Zmienić usersmap na databejsy!

        Database database = Database.getInstance();
        try {
            if (
                    database.getUserByNickname(loginn).getPassword().equals(passwordd) ) {
                System.out.println("Dziękuję za zalogowanie");
                return database.getUserByNickname(loginn);
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

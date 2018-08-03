package srallegro.user;

import srallegro.exception.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserController {

    public static User login(String login, String passwordd) {
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

    public static User createUser() throws PasswordTooShortException, EmptyNickException, BirthdayException, InterruptedException, UserWithSameNicknameExists, IncorrectEmailFormatException {
        Database database = Database.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj Imię");
        String userName = scanner.nextLine();
        System.out.println("Podaj datę urodzin");
        Integer userBirthday = 0;
        while (userBirthday == 0) {
            try {
                userBirthday = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Data urodzenia powinna byc liczba ");
                userBirthday = 0;
                scanner.nextLine();
            }
        }
        System.out.println("Podaj Nazwisko");
        String userLastName = scanner.next();

        System.out.println("Podaj adres zamieszkania");
        String userAdrdess = scanner.next();

        System.out.println("Podaj e-mail");
        String userMail = scanner.next();

        String userNick = null;
        while (userNick == null) {
            System.out.println("Podaj nick");
            userNick = scanner.next();
            if (database.getUserByNickname(userNick) != null) {
                System.out.println("Taki użytkownik już istnieje. Podaj inny nick");
                userNick = null;
            }
        }
        System.out.println("Podaj hasło");
        String password = null;
        int passwordCheck = 0;
        while (passwordCheck == 0) {
            password = scanner.next();
            if (password.length() < 5) {
                passwordCheck = 0;
                System.out.println("Haslo powinno byc dluzsze niz 5 znakow");
            } else {
                passwordCheck = 1;
            }
        }
        System.out.println("Powtórz hasło");
        String password2 = scanner.next();
        while (true) {
            if (password.length() < 5) {
                System.out.println("Hasło jest za krótkie");
                password = scanner.next();
            } else {
                break;
            }
        }
        while (true) {
            if (password.equals(password2)) {
                break;
            } else {
                System.out.println("Hasła nie są takie same");
                password2 = scanner.next();
            }
        }
        User newUser = createUser2(userName, userLastName, userBirthday, userAdrdess, userMail, password, userNick);
        if (newUser == null) {
            System.out.println("Rejestracja nieudana. Spróbuj ponownie");
        } else {
            System.out.println("Zarejestrowano nowego użytkownika");
        }
        return newUser;
    }

    public static User createUser2(String name, String lastname, Integer birthday, String address, String email, String password, String nick) throws UserWithSameNicknameExists, PasswordTooShortException, EmptyNickException, BirthdayException, IncorrectEmailFormatException {
        Database database = Database.getInstance();
        User newUser = new User(name, lastname, birthday, address, email, password, nick);
        database.addUserToAllUsers(newUser);
        SaveUserOnDisk.writeCsvFile("databaseUser.csv", newUser);
        return newUser;
    }
}

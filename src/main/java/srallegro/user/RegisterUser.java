package srallegro.user;

import srallegro.exception.BirthdayException;
import srallegro.exception.EmptyNickException;
import srallegro.exception.PasswordTooShortException;
import srallegro.exception.UserWithSameNicknameExists;

import java.util.*;

public class RegisterUser {

    public static User createUser() throws PasswordTooShortException, EmptyNickException, BirthdayException, InterruptedException, UserWithSameNicknameExists {
        Database database = Database.getInstance();


        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj Imię");
        String userName = scanner.nextLine();
//        int userNameCheck =0;
//        while(userNameCheck ==0){
//            userName =  scanner.nextLine();
//            for (int i=0 ; i<userName.length();i++){
//                if (userName.indexOf(i) ) ;
//            }
//        }


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
        User newUser = new User(userName, userLastName, userBirthday, userAdrdess, userMail, password, userNick);
        database.addUserToAllUsers(newUser);
        SaveUserOnDisk.writeCsvFile("databaseUser.csv", newUser);
        return newUser;
    }
}

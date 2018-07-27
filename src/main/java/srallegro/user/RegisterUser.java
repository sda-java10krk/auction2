package srallegro.user;

import srallegro.exception.BirthdayException;
import srallegro.exception.EmptyNickException;
import srallegro.exception.PasswordTooShortException;

import java.util.*;

public class RegisterUser {
    public static User createUser() throws PasswordTooShortException, EmptyNickException, BirthdayException {
        Database database = Database.getInstance();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj Imię");
        String userName = scanner.nextLine();

        System.out.println("Podaj datę urodzin");
        Integer userBirthday = scanner.nextInt();

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
        String password = scanner.next();


//        while (true) {
//            if (password.length() < 5) {
//                throw new PasswordTooShortException();
//            } else {
//                break;
//            }
//
//        }

        System.out.println("Powtórz hasło");
        String password2 = scanner.next();

                while (true){
                if (password.equals(password2)){
                    break;
                } else {
                    System.out.println("Hasła nie są takie same");
                     password2 = scanner.next();
                    }
                }

                User newUser = new User(userName, userLastName,userBirthday,userAdrdess,userMail,password,userNick);

       //         UsersMap allusers = UsersMap.getInstance();
        //try {
        database.addUserToAllUsers(newUser);
        SaveUserOnDisk.writeCsvFile("databaseUser.txt", newUser);
       /* } catch (FileNotFoundException e) {
            System.out.println("Nie udało się. Plik");;
        } catch (IOException e) {
            e.printStackTrace();
        } */
        return newUser;
    }
}

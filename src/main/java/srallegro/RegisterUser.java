package srallegro;

import javax.xml.crypto.Data;
import java.util.*;

public class RegisterUser {

    protected static User createUser (){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj Imię");
        String userName = scanner.nextLine();
        System.out.println("Podaj datę urodzin");
        Integer userBirthday = scanner.nextInt();
        System.out.println("Podaj Nazwisko");
        String userLastName = scanner.nextLine();
        System.out.println("Podaj adres zamieszkania");
        String userAdress = scanner.nextLine();
        System.out.println("Podaj e-mail");
        String userMail = scanner.nextLine();
        System.out.println("Podaj hasło");
        String password = scanner.nextLine();
        System.out.println("Powtórz hasło");
        String password2 = scanner.nextLine();
                while (true){
                if (password.equals(password2)){
                    break;
                } else {
                    System.out.println("Hasła nie są takie same");
                     password2 = scanner.nextLine();
                    }
                }
        String userNick = scanner.nextLine();
                return new User(userName, userLastName,userBirthday,userAdress,userMail,password,userNick);
    }
}

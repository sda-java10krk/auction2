package srallegro.exception;

public class UserIsntSavedException extends Exception {
    public UserIsntSavedException () {
        System.out.println("Użytkownik nie został zapisany");
    }
}

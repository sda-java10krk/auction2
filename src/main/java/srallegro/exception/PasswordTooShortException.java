package srallegro.exception;

public class PasswordTooShortException extends Exception {
    public PasswordTooShortException() {
        System.out.println("Password should be longer than 5 letters");
    }
}

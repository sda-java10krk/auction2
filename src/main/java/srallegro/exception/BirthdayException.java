package srallegro.exception;

public class BirthdayException extends Exception {
    public BirthdayException () {
        System.out.println("Birthday date should be DD-MM-YYYY");
    }
}

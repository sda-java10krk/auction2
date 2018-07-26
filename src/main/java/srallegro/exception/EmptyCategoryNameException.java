package srallegro.exception;

public class EmptyCategoryNameException extends Exception {
    public EmptyCategoryNameException (){
        System.out.println("Nie podales nazwy kategorii ");
    }
}

package srallegro.exception;

public class FileExisting extends Exception {
    public FileExisting (){
        System.out.println("Nie ma gdzie zapisać danych, plik nie istnieje");
    }
}

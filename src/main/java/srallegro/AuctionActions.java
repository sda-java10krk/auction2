package srallegro;

import srallegro.Auction;

import java.math.BigDecimal;
import java.util.Scanner;

public class AuctionActions {

    public static BigDecimal BidUp(Auction auction , BigDecimal bidUp) throws IllegalArgumentException{
         BigDecimal newPrice = bidUp.add(auction.getPrice());
        if(auction.getPrice().compareTo(newPrice) <0 ){
         throw new IllegalArgumentException("Podaj oferte wyzsza od obecnej") ;
        }else {
            auction.setPrice(newPrice);
        }
        return null;
    }


    public static Auction createAuction(User currentUser) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj tytuł aukcji");
        String title = sc.nextLine();
        System.out.println("Podaj opis aukcji");
        String description = sc.nextLine();
        System.out.println("Wybierz kategorię");
        //tutaj wyświetlają się dostępne kategorie. Wyświetlają się tak długo, póki nie dojdziemy do "najgłębszej" (pętla).
        //Dodamy, jak będziemy mieli kolekcję z kategoriami
        String cat = sc.nextLine(); //po nazwie kategorii będzie ją wybierał
        System.out.println("Podaj cenę początkową");
        double amount = sc.nextDouble();
        //nieistniejący, domyślny wygrywający aukcję, który będzie zwracał info,
        //że nikt nie wygrywa aktualnie aukcji? Nie mam lepszego pomysłu na to.

        Auction newAuction = new Auction(title, description, null, currentUser, User.noWinner(), new BigDecimal(amount));
        //zamiast "Category cat" będzie kategoria wyjęta z kolekcji po nazwie;
        System.out.println("Wystawiłeś przedmiot na aukcję: " + newAuction.toString());

        return newAuction;
    }

}

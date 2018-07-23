package srallegro;

import srallegro.Auction;

import java.math.BigDecimal;
import java.util.Scanner;

public class AuctionController {


    public static void  endOfAuction( Auction auction, User user){
        System.out.println("aukcja zakonczona, zwyciezył " + user);

    }



    public static BigDecimal bidUp(Auction auction , BigDecimal bidUp) throws PriceTooLowException {
         BigDecimal newPrice = bidUp;

        if(newPrice.compareTo(auction.getPrice()) < 0 ){
         throw new PriceTooLowException();
        }else {
            auction.setPrice(newPrice);
            if (auction.getBids() == 2){
                endOfAuction(auction, auction.getWinner());
            }
        }
        return auction.getPrice();
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
//        User noWinner = new User(); //nieistniejący, domyślny wygrywający aukcję, który będzie zwracał info,
        //że nikt nie wygrywa aktualnie aukcji? Nie mam lepszego pomysłu na to.

//        Auction newAuction = new Auction(title, description, new Category("xxx"), currentUser, noWinner, new BigDecimal(amount));
//        //zamiast "Category cat" będzie kategoria wyjęta z kolekcji po nazwie;
//        System.out.println("Wystawiłeś przedmiot na aukcję: " + newAuction.toString());

//        return newAuction;
        return null; // FIXME
    }

}

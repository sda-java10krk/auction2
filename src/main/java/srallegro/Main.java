package srallegro;

import srallegro.auction.Auction;
import srallegro.auction.AuctionController;
import srallegro.auction.LoadAuctionFromDisk;
import srallegro.user.*;

import java.math.BigDecimal;
import java.util.Scanner;

import static java.lang.System.out;
import static srallegro.Main.State.LOGGED_IN;

public class Main {

    public enum State {
        INIT,
        DURING_LOGIN,
        DURING_REGISTRATION,
        DURING_VIEVING_AUCTION,
        LOGGED_IN,
        STOP,
    }

    public static void main(String[] args) throws Exception {
        Category allcategories = CategoryController.createCategoryTree();
        Database database = Database.getInstance();
        LoadUserFromDisk.readFileCSV("databaseUser.csv");
        LoadAuctionFromDisk.loadAuctionCSV("databaseAuction.csv");

        Scanner sc = new Scanner(System.in);
        User currentUser = null;
        State state = State.INIT;
        while (state != State.STOP) {
            switch (state) {
                case INIT: {
                    printMenu1();
                    String answer = sc.next();
                    switch (answer) {
                        case "1":
                            state = State.DURING_LOGIN;
                            break;
                        case "2":
                            state = State.DURING_REGISTRATION;
                            break;
                        case "0":
                            state = State.STOP;
                            break;
                        default:
                            System.out.println("Zła odpowiedź");
                            state = State.INIT;
                            break;
                    }
                    break;
                }
                case DURING_LOGIN: {
                    System.out.println("Podaj login");
                    String login = sc.next();
                    System.out.println("Podaj hasło");
                    String password = sc.next();
                    currentUser = UserController.login(login, password);
                    if (currentUser != null) {
                        state = LOGGED_IN;
                    } else {
                        state = State.DURING_LOGIN;
                    }
                    break;
                }
                case DURING_REGISTRATION: {
                    currentUser = UserController.createUser();
                    if (currentUser != null) {
                        state = LOGGED_IN;
                        break;
                    }
                    state = State.DURING_REGISTRATION;
                    break;
                }
                case STOP: {
                    state = State.STOP;
                }
                case DURING_VIEVING_AUCTION: {
                    System.out.println("Podaj nr aukcji, która Cię interesuje");
                    Auction currentAuction = database.getAuctionByNumber(sc.nextInt());
                    printMenu3();

                    int choice = sc.nextInt();
                    if (choice == 1) {
                        System.out.println("Licytujesz przedmiot: " + currentAuction.getTitle() + ". Obecna kwota: " + currentAuction.getPrice() + ". Podaj kwotę");
                        BigDecimal price = new BigDecimal(sc.nextInt());
                        AuctionController.bidUp(currentAuction, price, currentUser);
                        System.out.println("Wygrywasz tę aukcję!");
                    } else if (choice == 2) {
                        System.out.println(currentAuction.getDescription());
                        break;
                    }

                }
                case LOGGED_IN: {
                    printMenu2();
                    String answer = sc.next();
                    switch (answer) {
                        case "1": {
                            AuctionController.createAuctionMain(currentUser, allcategories);
                            break;
                        }

                        case "2": {
                            CategoryController.printCategories(allcategories, 0, out);
                            System.out.println("Wybierz kategorię");
                            sc.nextLine();
                            String chosenCat = sc.nextLine();
                            try {
                                System.out.println(CategoryController.listAuctionsByCategory(database.getCategoryByName(chosenCat)));
                            } catch (NullPointerException npe) {
                                System.out.println("Nie ma takiej kategorii");
                                state = state.LOGGED_IN;
                            }
                            state = State.DURING_VIEVING_AUCTION;
                            break;
                        }
                        case "3": {
                            System.out.println(AuctionController.viewSellersAuctions(currentUser));
                            break;
                        }
                        case "4": {
                            System.out.println(AuctionController.viewWonAuctions(currentUser));
                            break;
                        }
                        case "5": {
                            state = State.INIT;
                            break;
                        }
                        default: {
                            System.out.println("Zła odpowiedź");
                            state = LOGGED_IN;
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    public static void printMenu1() {
        System.out.println("Dzień dobry,\n Co chcesz zrobić ? \n 1 - Zaloguj się \n 2 - Zarejestruj się \n 0 - wyjdź");

    }

    public static void printMenu2() {
        System.out.println("Co chcesz zrobić? \n 1. Wystaw przedmiot \n 2. Pokaż aukcje wg kategorii \n " +
                "3. Wyświetl moje aukcje \n 4. Wyświetl aukcje, które wygrałem \n 5. Wyloguj  sie ");
    }

    public static void printMenu3() {
        System.out.println("Co chcesz zrobic ? \n 1.Zalicytuj \n 2.Zobacz opis \n 0.wróc ");
    }
}
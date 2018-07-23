package srallegro;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        User first = User.personBuilder().withName(name).build();

        System.out.println(first.toString());
    }
}

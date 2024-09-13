package service;

import org.w3c.dom.ls.LSOutput;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Warehouse {

    public static Scanner sc = new Scanner(System.in);
    public static String goBack = ("\nTryck \"Enter\" för att gå till huvudmenyn");

    public static void main(String[] args) {

        String choice;
        do {
            //Main menu
            System.out.println("\nWarehouse");
            System.out.println("========");
            System.out.println("1. Inmatning");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("e. Avsluta");
            System.out.println("\nAnge menyval + enter:");


            choice = sc.nextLine();

            switch (choice) {

                case "1":

                    System.out.println(goBack);
                    sc.nextLine();
                    break;
                case "2":

                    break;

                case "3":
                    break;

                default:

            }
        } while (!choice.equalsIgnoreCase("e"));

        System.out.println("Avslutar...");


    }
}
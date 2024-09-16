package service;
import service.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Warehouse {

    public static Scanner sc = new Scanner(System.in);
    public static String goBack = ("\nTryck \"Enter\" för att gå till huvudmenyn");

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
    public void addProduct(Product product) {
        products.add(product);
    }
    public void removeProduct(Product product) {
        products.remove(product);
    }


    public static void main(String[] args) {

        Warehouse Warehouse = new Warehouse();

        Product banana = new Product(1, "Banana", "Fruit", 5, null, null);
        Product apple = new Product(2, "Apple", "Fruit", 4, null, null);
        Product orange = new Product(3, "Orange", "Fruit", 3, null, null);
        Product pear = new Product(4, "Pear", "Fruit", 2, null, null);
        Product lemon = new Product(5, "Lemon", "Fruit", 1, null, null);

        Warehouse.addProduct(banana);
        Warehouse.addProduct(apple);
        Warehouse.addProduct(orange);
        Warehouse.addProduct(pear);
        Warehouse.addProduct(lemon);

        String choice;
        do {
            //Main menu
            System.out.println("\nWarehouse");
            System.out.println("========");
            System.out.println("1. Add product");
            System.out.println("2. Remove product");
            System.out.println("3. Show all products");
            System.out.println("4. ");
            System.out.println("e. Avsluta");
            System.out.println("\nAnge menyval + enter:");


            choice = sc.nextLine();

            switch (choice) {

                case "1":
                    Warehouse.getProducts();
                    System.out.println(goBack);
                    sc.nextLine();
                    break;
                case "2":

                    break;

                case "3":
                    System.out.println("All products:\n");
                    System.out.println("id\tName\t\tCategory\t\tRating\tCreated at\t\tUpdated at");
                    System.out.println(Warehouse.getProducts());
                    sc.nextLine();
                    break;

                default:

            }
        } while (!choice.equalsIgnoreCase("e"));

        System.out.println("Avslutar...");


    }
}
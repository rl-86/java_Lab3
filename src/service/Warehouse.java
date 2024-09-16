package service;
import service.entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Warehouse {

    public static Scanner sc = new Scanner(System.in);
    public static String goBack = ("\nPush \"Enter\" to go back to main menu");

    private List<Product> products = new ArrayList<>();
    private static int nextId = 1;

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

        Stream.of(
            new Product(nextId++, "Bread", "Food", 4, LocalDate.of(2024, 9, 12), null),
            new Product(nextId++, "Milk", "Food", 5, LocalDate.of(2024, 9, 12), null),
            new Product(nextId++, "Butter", "Food", 3, LocalDate.of(2024, 9, 12), null),
            new Product(nextId++, "Cheese", "Food", 4, LocalDate.of(2024, 9, 12), null),
            new Product(nextId++, "Apple", "Food", 5, LocalDate.of(2024, 9, 12), null),
            new Product(nextId++, "Banana", "Food", 4, LocalDate.of(2024, 9, 12), null),
            new Product(nextId++, "Table", "Furniture", 3, LocalDate.of(2024, 9, 12), null),
            new Product(nextId++, "Chair", "Furniture", 4, LocalDate.of(2024, 9, 12), null),
            new Product(nextId++, "Sofa", "Furniture", 5, LocalDate.of(2024, 9, 12), null),
            new Product(nextId++, "Screwdriver", "Tools", 4, LocalDate.of(2024, 9, 12), null),
            new Product(nextId++, "Hammer", "Tools", 5, LocalDate.of(2024, 9, 12), null),
            new Product(nextId++, "Saw", "Tools", 4, LocalDate.of(2024, 9, 12), null),
            new Product(nextId++, "Drill", "Tools", 5, LocalDate.of(2024, 9, 12), null),
            new Product(nextId++, "Screw", "Tools", 4, LocalDate.of(2024, 9, 12), null)
        ).forEach(Warehouse::addProduct);



        String choice;
        do {
            //Main menu
            System.out.println("\nWarehouse");
            System.out.println("========");
            System.out.println("1. Add product");
            System.out.println("2. Remove product");
            System.out.println("3. Show all products");
            System.out.println("4. ");
            System.out.println("e. Exit");
            System.out.println("\nMenu option + enter:");


            choice = sc.nextLine();

            switch (choice) {

                case "1":
                    String name = "";
                    String category;
                    int rating = 0;
                    System.out.println("Add new product");
                    while (name.isBlank()){
                        System.out.println("Enter product name:");
                        name = sc.nextLine();
                        if (name.isBlank()){
                            System.out.println("Name can't be empty, try again!");
                        }
                    }
                    System.out.println("Enter product category:");
                    category = sc.nextLine();
                    if (category.isBlank()){
                        category = "Unknown";
                    }
                    System.out.println("Enter product rating (0-10):");
                    rating = sc.nextInt();
                    if (rating < 0 || rating > 10){
                        rating = 0;
                    }
                    sc.nextLine();
                    Warehouse.products.add(new Product(nextId++, name, category, rating, LocalDate.now(), LocalDate.now()));
                    System.out.println(name+" was successfully added");
                    System.out.println(goBack);
                    sc.nextLine();
                    break;
                case "2":

                    break;

                case "3":
                    System.out.println("All products:\n");
                    System.out.println("id\t\tName\t\t\tCategory\t\tRating\t\tCreated at\t\t\tUpdated at");
                    Warehouse.getProducts().forEach(System.out::println);
                    System.out.println(goBack);
                    sc.nextLine();
                    break;

                default:

            }
        } while (!choice.equalsIgnoreCase("e"));

        System.out.println("Avslutar...");


    }
}
package service;
import service.entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Warehouse {

    public static Scanner sc = new Scanner(System.in);
    public static String goBack = ("\nPush \"Enter\" to go back to main menu");

    private List<Product> products = new ArrayList<>();
    private static int nextId = 1;

    // Get a copy of the products list
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
    // Add product
    public void addProduct(Product product) {
        products.add(product);
    }
    // Find product by ID
    public Product getProductById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);  // Om ingen produkt med givet ID hittas, returnera null
    }

    public void printProductById(int id) {
        Product product = getProductById(id);

        if (product != null) {
            System.out.println(product);  // Anropa toString() på Product-klassen
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }
    // Find product by id to remove
    public boolean removeProductById(int id) {
        Product productToRemove = products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);

        if (productToRemove != null) {
            products.remove(productToRemove);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        Warehouse Warehouse = new Warehouse();

        Stream.of(
                new Product(nextId++, "Bread",Product.Category.FOOD, 4,LocalDate.of(2024, 9, 12), null),
                new Product(nextId++, "Milk", Product.Category.FOOD, 5, LocalDate.of(2024, 9, 12), null),
                new Product(nextId++, "Butter", Product.Category.FOOD, 3, LocalDate.of(2024, 9, 12), null),
                new Product(nextId++, "Cheese", Product.Category.FOOD, 4, LocalDate.of(2024, 9, 12), null),
                new Product(nextId++, "Apple", Product.Category.FOOD, 5, LocalDate.of(2024, 9, 12), null),
                new Product(nextId++, "Banana", Product.Category.FOOD, 4, LocalDate.of(2024, 9, 12), null),
                new Product(nextId++, "Table", Product.Category.FURNITURE, 3, LocalDate.of(2024, 9, 12), null),
                new Product(nextId++, "Chair", Product.Category.FURNITURE, 4, LocalDate.of(2024, 9, 12), null),
                new Product(nextId++, "Sofa", Product.Category.FURNITURE, 5, LocalDate.of(2024, 9, 12), null),
                new Product(nextId++, "Screwdriver", Product.Category.TOOLS, 4, LocalDate.of(2024, 9, 12), null),
                new Product(nextId++, "Hammer", Product.Category.TOOLS, 5, LocalDate.of(2024, 9, 12), null),
                new Product(nextId++, "Saw", Product.Category.TOOLS, 4, LocalDate.of(2024, 9, 12), null),
                new Product(nextId++, "Drill", Product.Category.TOOLS, 5, LocalDate.of(2024, 9, 12), null),
                new Product(nextId++, "Screw", Product.Category.TOOLS, 4, LocalDate.of(2024, 9, 12), null)
        ).forEach(Warehouse::addProduct);


        String choice;
        do {
            //Main menu
            System.out.println("\nWarehouse");
            System.out.println("========");
            System.out.println("1. Add product");
            System.out.println("2. Modify product");
            System.out.println("3. Remove product");
            System.out.println("4. Show all products");
            System.out.println("5. ");
            System.out.println("e. Exit");
            System.out.println("\nMenu option + enter:");


            choice = sc.nextLine();

            switch (choice) {

                case "1":
                    // Enter name
                    String name = "";
                    System.out.println("Add new product");
                    while (name.isBlank()){
                        System.out.println("Enter product name:");
                        name = sc.nextLine();
                        if (name.isBlank()){
                            System.out.println("Name can't be empty, try again!");
                        }
                    }
                    // Enter category
                    Product.Category category;
                    System.out.println("Enter product category, Choose from: 1. Food, 2. Furniture, 3. Tools or press Enter for \"Other\"");
                    String input = sc.nextLine();

                    switch (input) {
                        case "1":
                            category = Product.Category.FOOD;
                            break;
                        case "2":
                            category = Product.Category.FURNITURE;
                            break;
                        case "3":
                            category = Product.Category.TOOLS;
                            break;
                        default:
                            category = Product.Category.OTHER;
                    }
                    // Enter rating
                    int rating = -1;
                    while (rating < 0 || rating > 10) {
                        System.out.println("Enter product rating (0-10):");

                        try {
                            rating = sc.nextInt();

                            if (rating < 0 || rating > 10) {
                                System.out.println("Invalid input, rating must be between 0 and 10.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input, please enter a number between 0 and 10.");
                            sc.next();
                        }
                    }
                    // New product added
                    sc.nextLine();
                    Warehouse.products.add(new Product(nextId++, name, category, rating, LocalDate.now(), LocalDate.now()));
                    System.out.println(name+" was successfully added");
                    System.out.println(goBack);
                    sc.nextLine();
                    break;

                case "2":
                    // Modify product by id
                    int modifyId;
                    System.out.println("Modify product by id:");
                    modifyId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("id\t\tName\t\t\tCategory\t\tRating\t\tCreated at\t\t\tUpdated at");
                    System.out.println(Warehouse.getProductById(modifyId));
                    System.out.println("What do you want to modify?");
                    System.out.println("1. Name, 2. Category, 3. Rating");
                    String modifyChoice = sc.nextLine();
                    switch (modifyChoice){
                        case "1":
                            String newName = "";
                            while (newName.isBlank()){
                                System.out.println("Enter the new product name:");
                               newName = sc.nextLine();
                                if (newName.isBlank()){
                                    System.out.println("Name can't be empty, try again!");
                                }
                            }
                            Warehouse.getProductById(modifyId).setName(newName);
                            System.out.println("Name was successfully updated");
                            break;
                        case "2":
                            System.out.println("Enter new category, Choose from: 1. Food, 2. Furniture, 3. Tools or press Enter for \"Other\"");
                            String newCategory = sc.nextLine();
                            Product.Category category1;
                            switch (newCategory) {
                                case "1":
                                    category1 = Product.Category.FOOD;
                                    break;
                                case "2":
                                    category1 = Product.Category.FURNITURE;
                                    break;
                                case "3":
                                    category1 = Product.Category.TOOLS;
                                    break;
                                default:
                                    category1 = Product.Category.OTHER;
                            }
                            Warehouse.getProductById(modifyId).setCategory(category1);
                            System.out.println("Category was successfully updated");
                            break;
                        case "3":
                            int newRating = -1;
                            while (newRating < 0 || newRating > 10) {
                                System.out.println("Enter product rating (0-10):");

                                try {
                                    newRating = sc.nextInt();

                                    if (newRating < 0 || newRating > 10) {
                                        System.out.println("Invalid input, rating must be between 0 and 10.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input, please enter a number between 0 and 10.");
                                    sc.next();
                                }
                            }
                            Warehouse.getProductById(modifyId).setRating(newRating);
                            System.out.println("Rating was successfully updated");
                            break;
                        default:
                            System.out.println("Invalid input");
                    }
                    break;

                case "3":
                    // Remove product by id
                    int removeId;
                    System.out.print("Remove product by id: ");
                    removeId = sc.nextInt();
                    sc.nextLine();
                    Warehouse.removeProductById(removeId);
                    System.out.println("Product with id "+removeId+" was successfully removed");
                    System.out.println(goBack);
                    sc.nextLine();
                    break;

                case "4":
                    // List all products
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
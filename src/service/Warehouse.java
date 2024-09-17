package service;
import service.entities.Product;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Warehouse {

    public static Scanner sc = new Scanner(System.in);
    public static String index = ("id\t\tName\t\t\tCategory\t\tRating\t\tCreated at\t\t\tUpdated at");
    public static String goBack = ("\nPress \"Enter\" to go back to main menu");

    private static List<Product> products = new ArrayList<>();
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
    // Case "5"  Method to display products by category
    public static void displayProductsByCategory() {
        System.out.println("Choose a category: 1. FOOD, 2. FURNITURE, 3. TOOLS or \"Enter\" for OTHER");
        String choice = sc.nextLine();

        Product.Category category;

        switch (choice) {
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


        List<Product> sortedProducts = products.stream()
                .filter(product -> product.getCategory() == category)
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());

        if (sortedProducts.isEmpty()) {
            System.out.println("No products found in the selected category.");
        } else {
            System.out.println(index);
            sortedProducts.forEach(System.out::println);
        }
    }

    // Case 6 Show all new products
    public static List<Product> getProductsCreatedAfter(LocalDate date) {
        return products.stream()
                .filter(product -> product.getCreatedAt().isAfter(date))
                .collect(Collectors.toList());
    }
    public static void displayProductsCreatedAfter() {
        System.out.println("Find products created after a specific date");
        System.out.println("Enter the date (yyyy-mm-dd) to find products created after:");
        String inputDate = sc.nextLine();
        LocalDate date;

        try {
            date = LocalDate.parse(inputDate);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            return;
        }
        System.out.println(index);
        List<Product> productsCreatedAfter = getProductsCreatedAfter(date);

        if (productsCreatedAfter.isEmpty()) {
            System.out.println("No products found created after " + date);
        } else {
            productsCreatedAfter.forEach(System.out::println);
        }
        System.out.println(goBack);
        sc.nextLine();
    }



    public static void main(String[] args) {

        Warehouse Warehouse = new Warehouse();

        Stream.of(
                new Product(nextId++, "Bread",Product.Category.FOOD, 4,LocalDate.of(2024, 9, 12), LocalDate.of(2024, 9, 12)),
                new Product(nextId++, "Milk", Product.Category.FOOD, 5, LocalDate.of(2024, 9, 12), LocalDate.of(2024, 9, 12)),
                new Product(nextId++, "Butter", Product.Category.FOOD, 3, LocalDate.of(2024, 9, 12), LocalDate.of(2024, 9, 12)),
                new Product(nextId++, "Cheese", Product.Category.FOOD, 4, LocalDate.of(2024, 9, 12), LocalDate.of(2024, 9, 12)),
                new Product(nextId++, "Apple", Product.Category.FOOD, 5, LocalDate.of(2024, 9, 12), LocalDate.of(2024, 9, 12)),
                new Product(nextId++, "Banana", Product.Category.FOOD, 4, LocalDate.of(2024, 9, 12), LocalDate.of(2024, 9, 12)),
                new Product(nextId++, "Table", Product.Category.FURNITURE, 3, LocalDate.of(2024, 9, 12), LocalDate.of(2024, 9, 12)),
                new Product(nextId++, "Chair", Product.Category.FURNITURE, 4, LocalDate.of(2024, 9, 12), LocalDate.of(2024, 9, 12)),
                new Product(nextId++, "Sofa", Product.Category.FURNITURE, 5, LocalDate.of(2024, 9, 12), LocalDate.of(2024, 9, 12)),
                new Product(nextId++, "Screwdriver", Product.Category.TOOLS, 4, LocalDate.of(2024, 9, 12), LocalDate.of(2024, 9, 12)),
                new Product(nextId++, "Hammer", Product.Category.TOOLS, 5, LocalDate.of(2024, 9, 12), LocalDate.of(2024, 9, 12)),
                new Product(nextId++, "Saw", Product.Category.TOOLS, 4, LocalDate.of(2024, 9, 12), LocalDate.of(2024, 9, 12)),
                new Product(nextId++, "Drill", Product.Category.TOOLS, 5, LocalDate.of(2024, 9, 12), LocalDate.of(2024, 9, 12)),
                new Product(nextId++, "Screw", Product.Category.TOOLS, 4, LocalDate.of(2024, 9, 12), LocalDate.of(2024, 9, 12))
        ).forEach(Warehouse::addProduct);


        String choice;
        do {
            //Main menu
            System.out.println("\nThe Warehouse");
            System.out.println("========");
            System.out.println("1. Add product");
            System.out.println("2. Modify product");
            System.out.println("3. Remove product");
            System.out.println("4. Show all products");
            System.out.println("5. Sort by category");
            System.out.println("6. Find products by date");
            System.out.println("7. Show all modified products");
            System.out.println("e. Exit");
            System.out.println("\nMenu option + \"Enter\"");


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
                    System.out.println(index);
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
                    System.out.println(index);
                    Warehouse.getProducts().forEach(System.out::println);
                    System.out.println(goBack);
                    sc.nextLine();
                    break;

                case "5":
                    System.out.println("Display products by category");
                    displayProductsByCategory();
                    System.out.println(goBack);
                    sc.nextLine();
                    break;

                case "6":
                    displayProductsCreatedAfter();



                    break;

                case "7":





                    break;


                default:

            }
        } while (!choice.equalsIgnoreCase("e"));

        System.out.println("Avslutar...");


    }
}
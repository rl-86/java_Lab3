package service;

import entities.Product;
import entities.ProductRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;

public class WarehouseTest {

    private Warehouse warehouse;

    @BeforeEach
    public void setUp() {
        warehouse = new Warehouse();
        warehouse.resetNextId();
        warehouse.addProduct(new Product(0, "Laptop", Product.Category.TOOLS, 5, LocalDate.of(2023, 1, 1)));
        warehouse.addProduct(new Product(0, "Chair", Product.Category.FURNITURE, 3, LocalDate.of(2024, 1, 1)));
        warehouse.addProduct(new Product(0, "Apple", Product.Category.FOOD, 5, LocalDate.of(2022, 5, 1)));
    }

    @Test
    void testAddProduct() {
        warehouse.addProduct(new Product(0, "Sofa", Product.Category.FURNITURE, 3, LocalDate.of(2024, 9, 12)));
        assertEquals(4, warehouse.getProducts().size());
        assertEquals("Sofa", warehouse.getProductById(4).name());
    }

    @Test
    void testAddProductWithoutName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> warehouse.addProduct(new Product(0, "", Product.Category.FURNITURE, 3, LocalDate.of(2024, 9, 12))));
        assertEquals("Product name cannot be null or blank", exception.getMessage());
    }

    @Test
    void testGetProducts() {
        List<ProductRecord> products = warehouse.getProducts();
        assertEquals(3, products.size());
    }

    @Test
    void testGetProductById() {
        ProductRecord product = warehouse.getProductById(1);
        assertNotNull(product);
        assertEquals("Laptop", product.name());
    }

    @Test
    void testGetProductByIdNotFound() {
        ProductRecord product = warehouse.getProductById(99);
        assertNull(product);
    }

    @Test
    void testRemoveProductById() {
        boolean isRemoved = warehouse.removeProductById(1);
        assertTrue(isRemoved);
        assertEquals(2, warehouse.getProducts().size());
    }

    @Test
    void testRemoveProductByIdNotFound() {
        boolean isRemoved = warehouse.removeProductById(99);
        assertFalse(isRemoved);
    }

    @Test
    void testGetProductsByCategory() {
        List<ProductRecord> products = warehouse.getProducts().stream()
                .filter(p -> p.category() == Product.Category.FOOD)
                .toList();
        assertEquals(1, products.size());
        assertEquals("Apple", products.getFirst().name());
    }

    @Test
    void testGetProductsCreatedAfter() {
        List<ProductRecord> products = warehouse.getProductsCreatedAfter(LocalDate.of(2023, 6, 1));
        assertEquals(1, products.size());
        assertEquals("Chair", products.getFirst().name());
    }

    @Test
    void testGetProductsModifiedAfterCreation() {
        Product product = warehouse.getProductByIdAsProduct(1);
        product.setRating(4); // Modify product
        List<ProductRecord> modifiedProducts = warehouse.getProductsModifiedAfterCreation();
        assertEquals(1, modifiedProducts.size());
        assertEquals("Laptop", modifiedProducts.getFirst().name());
    }
}
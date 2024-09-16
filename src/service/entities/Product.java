package service.entities;
import java.time.LocalDate;

public class Product {



    private final int id;
    private String name;
    private String category;
    private int rating;
    private final LocalDate createdAt;
    private LocalDate updatedAt;

    public Product(int id, String name, String category, int rating, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.createdAt = createdAt;
        this.updatedAt = LocalDate.now();
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public int getRating() {
        return rating;
    }
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return id + "\t\t" +
                 name + "\t\t\t\t" + category + "\t\t\t\t" + rating +  "\t\t\t\t" +createdAt +  "\t\t\t\t" +updatedAt;
    }
}

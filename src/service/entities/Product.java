package service.entities;
import java.time.LocalDateTime;

public class Product {



    private final int id;
    private String name;
    private String category;
    private int rating;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(int id, String name, String category, int rating, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
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
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return id + "\t" +
                 name + "\t\t" + category + "\t\t\t" + rating +  "\t\t" +createdAt +  "\t\t" +updatedAt +"\n";
    }
}

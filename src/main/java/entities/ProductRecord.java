package entities;

import java.time.LocalDate;

public record ProductRecord(int id, String name, Product.Category category, int rating, LocalDate createdAt, LocalDate updatedAt) {

    @Override
    public String toString() {
        return id + "\t\t" +
                name + "\t\t\t\t" + category + "\t\t\t\t" + rating +  "\t\t\t\t" +createdAt +  "\t\t\t\t" +updatedAt;
    }
}
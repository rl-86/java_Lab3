import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {


    @Test
    @DisplayName("Add a and b")
    void add() {
        var result = Warehouse.add(2,3);
        assertEquals(5,result);
    }

}
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void getValue() {
        Cell t = new Cell(2,3);
        t.setbox(3);
        assertEquals(3,t.getValue());
    }

    @Test
    void getI() {
        Cell t = new Cell(2,3);
        assertEquals(2,t.getI());
    }
    @Test
    void setbox() {
        Cell t = new Cell(2,3);
        t.setbox(4);
        assertEquals(4,t.getValue());
    }

    @Test
    void getJ() {
        Cell t = new Cell(2,3);
        assertEquals(3,t.getJ());
    }
}
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DuidokuLogicTest {


    @Test
    void setDuidoku(){
        DuidokuLogic t = new DuidokuLogic();
        t.setDuidoku(2,3,1);
        assertEquals(1,t.get_duidar_value(2,3));
    }
    @Test
    void pcMove() {
        DuidokuLogic t = new DuidokuLogic();
        assertTrue(t.PcMove());
    }

    @Test
    void get_duidar_value() {
        DuidokuLogic t = new DuidokuLogic();
        assertEquals(0,t.get_duidar_value(0,3));
    }

    @Test
    void isInRow() {
        DuidokuLogic t = new DuidokuLogic();
        assertFalse(t.isInRow(2, 5));
    }

    @Test
    void isInCol() {
        DuidokuLogic t = new DuidokuLogic();
        assertFalse(t.isInCol(2, 5));
    }

    @Test
    void isInBox() {
        DuidokuLogic t = new DuidokuLogic();
        assertFalse(t.isInBox(1,2, 5));
    }

    @Test
    void isOk() {
        DuidokuLogic t = new DuidokuLogic();
        assertTrue(t.isOk(3,2, 5));
    }
}
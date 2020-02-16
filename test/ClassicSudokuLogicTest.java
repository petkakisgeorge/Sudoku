import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassicSudokuLogicTest {


    @Test
    void getBoard() {
        ClassicSudokuLogic t = new ClassicSudokuLogic(0,0,0);
        t.setBoard(2,3,5);
        assertEquals(5,t.getBoard(2,3));
    }

    @Test
    void isInRow() {
        ClassicSudokuLogic t = new ClassicSudokuLogic(0,0,0);
        t.setBoard(2,3,5);
        assertTrue(t.isInRow(2, 5));
    }

    @Test
    void isInCol() {
        ClassicSudokuLogic t = new ClassicSudokuLogic(0,0,0);
        t.setBoard(2,3,5);
        assertTrue(t.isInCol(3, 5));
    }

    @Test
    void isInBox() {
        ClassicSudokuLogic t = new ClassicSudokuLogic(0,0,0);
        t.setBoard(2,4,5);
        assertTrue(t.isInBox(2,4, 5));
    }

    @Test
    void isOk() {
        ClassicSudokuLogic t = new ClassicSudokuLogic(0,0,0);
        t.setBoard(1,7,7);
        assertFalse(t.isOk(1,7, 7));
    }
}
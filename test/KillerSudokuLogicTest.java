import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KillerSudokuLogicTest {

    @Test
    void getBoard() {
        KillerSudokuLogic t = new KillerSudokuLogic(0,0);
        assertEquals(0,t.getBoard(0,3));
    }

    @Test
    void check() {
        KillerSudokuLogic t = new KillerSudokuLogic(0,0);
        assertTrue(t.check(1, 3, 4));
    }
    @Test
    void SetBoxNumber(){
        KillerSudokuLogic t = new KillerSudokuLogic(0,0);
        t.SetBoxNumber(1,3,5);
        assertEquals(5,t.getBoard(1,3));
    }

}
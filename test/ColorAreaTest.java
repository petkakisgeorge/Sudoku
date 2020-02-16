import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ColorAreaTest {

    @Test
    void checkIfAllCellsAreFilled() {
        ColorArea t = new ColorArea();
        assertFalse(t.checkIfAllCellsAreFilled());
    }

    @Test
    void checksum() {
        ColorArea t = new ColorArea();
        assertFalse(t.checksum(1));
    }
}
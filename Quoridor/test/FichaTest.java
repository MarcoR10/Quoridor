
import domain.*;
import java.awt.Point;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FichaTest {
    private Ficha ficha;

    @BeforeEach
    public void setUp() {
        ficha = new Ficha('F', new Point(0, 0));
    }

    @Test
    public void testMover() {
        ficha.mover(2, 3);
        assertEquals(new Point(2, 3), ficha.getCoordenadas());
    }

    @Test
    public void testEstaEn() {
        ficha.mover(1, 1);
        assertTrue(ficha.estaEn(1, 1));
    }
}

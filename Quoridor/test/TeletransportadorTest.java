
import domain.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeletransportadorTest {
    private Teletransportador teletransportador;

    @BeforeEach
    public void setUp() {
        teletransportador = new Teletransportador(0, 0, null);
    }

    @Test
    public void testPermiteMovimiento() {
        assertTrue(teletransportador.permiteMovimiento(0, 1, null));
        assertTrue(teletransportador.permiteMovimiento(1, 0, null));
        assertTrue(teletransportador.permiteMovimiento(1, 1, null));
        assertFalse(teletransportador.permiteMovimiento(2, 2, null));
        assertFalse(teletransportador.permiteMovimiento(3, 3, null));
        assertFalse(teletransportador.permiteMovimiento(10, 10, null));
    }
}

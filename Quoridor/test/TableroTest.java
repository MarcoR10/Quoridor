
import domain.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TableroTest {
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;

    @BeforeEach
    public void setUp() {
        jugador1 = new Humano("Zen", "Rojo");
        jugador2 = new Humano("Bigotes", "Azul");
        tablero = new Tablero(3, 3, jugador1, jugador2, false, true);
    }

    @Test
    public void testInicioPosiciones() {
        assertEquals(jugador1, tablero.getCasilla(0, 1).getJugador());
        assertEquals(jugador2, tablero.getCasilla(2, 1).getJugador());
    }

    @Test
    public void testMoverFicha() {
        tablero.moverFicha(jugador1, 1, 1);
        assertEquals(jugador1, tablero.getCasilla(1, 1).getJugador());
        assertNull(tablero.getCasilla(0, 1).getJugador());
    }

    // @Test
    public void testColocarPared() {
        tablero.colocarPared(1, 0, 1, 1, jugador1, true);
        assertTrue(tablero.getCasilla(1, 0).getBarrera() instanceof BarreraNormal);
        assertTrue(tablero.getCasilla(1, 1).getBarrera() instanceof BarreraNormal);
    }
}

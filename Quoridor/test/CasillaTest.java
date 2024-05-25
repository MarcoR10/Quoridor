
import domain.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CasillaTest {
    private Casilla casilla;
    private Jugador jugador;

    @BeforeEach
    public void setUp() {
        casilla = new Normal(0, 0, "normal");
        jugador = new Humano("Zen", "Rojo");
    }

    @Test
    public void testSetJugador() {
        casilla.setJugador(jugador);
        assertEquals(jugador, casilla.getJugador());
    }

    @Test
    public void testSetJugadorNull() {
        casilla.setJugador(jugador);
        casilla.setJugadorNull();
        assertNull(casilla.getJugador());
    }
}

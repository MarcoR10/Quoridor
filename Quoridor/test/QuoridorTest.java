
import domain.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuoridorTest {
    private Quoridor quoridor;
    private Jugador jugador1;
    private Jugador jugador2;

    @BeforeEach
    public void setUp() {
        jugador1 = new Humano("Jugador1", "Rojo");
        jugador2 = new Humano("Jugador2", "Azul");
        quoridor = new Quoridor(8, 8, jugador1, jugador2, true, true, "Normal");
    }

    @Test
    public void testGanador() {
        assertFalse(quoridor.Ganador());
        jugador1.getFicha().mover(7, 0);
        assertTrue(quoridor.Ganador());
    }

    @Test
    public void testCambiarTurno() {
        assertEquals(jugador1, quoridor.getJugadorActual());
        quoridor.cambiarTurno();
        assertEquals(jugador2, quoridor.getJugadorActual());
    }
}

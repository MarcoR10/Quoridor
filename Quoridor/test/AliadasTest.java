
import domain.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AliadasTest {
    private Aliadas aliadas;
    private Jugador jugador;

    @BeforeEach
    public void setUp() {
        jugador = new Humano("Jugador", "Rojo");
        aliadas = new Aliadas(0, 0, jugador);
    }

    @Test
    public void testBloqueaCamino() {
        assertFalse(aliadas.bloqueaCamino(0, 1, jugador));
        assertTrue(aliadas.bloqueaCamino(0, 1, new Humano("OtroJugador", "Azul")));
    }
}


import domain.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JugadorTest {
    private Jugador jugador;

    @BeforeEach
    public void setUp() {
        jugador = new Humano("Zen", "Rojo");
    }

    @Test
    public void testRestarBarreras() {
        int barrerasIniciales = jugador.getBarreras();
        jugador.restaBarrreras();
        assertEquals(barrerasIniciales - 1, jugador.getBarreras());
    }
}

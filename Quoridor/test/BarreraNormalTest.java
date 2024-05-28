
import domain.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BarreraNormalTest {
    private BarreraNormal barreraNormal;
    private Jugador jugador;

    @BeforeEach
    public void setUp() {
        jugador = new Humano("Jugador", "Rojo");
        barreraNormal = new BarreraNormal(0, 0, jugador);
    }

    @Test
    public void testBloqueaCamino() {
        assertTrue(barreraNormal.bloqueaCamino(0, 1, jugador));
    }
}

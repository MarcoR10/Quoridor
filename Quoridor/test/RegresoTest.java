
import domain.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegresoTest {
    private Regreso regreso;
    private Jugador jugador;
    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        regreso = new Regreso(0, 0, "Regreso");
        jugador = new Humano("Jugador", "Rojo");
        tablero = new Tablero(8, 8, jugador, jugador, true, true);
    }

    @Test
    public void testRetrocederFicha() {
        tablero.actualizarCasilla(2, 2, 4, 4, jugador);
        regreso.retrocederFicha(jugador, tablero);
    }
}

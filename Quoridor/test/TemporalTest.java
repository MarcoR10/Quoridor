
import domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TemporalTest {
    private Temporal temporal;

    @BeforeEach
    public void setUp() {
        temporal = new Temporal(0, 0, new Humano("temporal","Rojo"));
    }

    @Test
    public void testDescontarDuracion() {
        temporal.reducirTurno();
        assertTrue(temporal.getTurnosRestantes() == 1);
        temporal.reducirTurno();
        assertTrue(temporal.getTurnosRestantes() == 0);
        temporal.reducirTurno();
        assertFalse(temporal.getTurnosRestantes() < 0);
    }

    @Test
    public void testReducirTurno() {
        temporal.reducirTurno();
        assertEquals(4, temporal.getTurnosRestantes());
    }

    @Test
    public void testEsActiva() {
        assertTrue(temporal.esActiva());
        for (int i = 0; i < 5; i++) {
            temporal.reducirTurno();
        }
        assertFalse(temporal.esActiva());
    }

}


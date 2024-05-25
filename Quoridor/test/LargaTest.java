
import domain.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LargaTest {
    private Larga larga;

    @BeforeEach
    public void setUp() {
        larga = new Larga(0, 0, null);
    }

    @Test
    public void testBloqueaCamino() {
        assertFalse(larga.bloqueaCamino(0, 1, null));
        assertFalse(larga.bloqueaCamino(1, 0, null));
    }
}

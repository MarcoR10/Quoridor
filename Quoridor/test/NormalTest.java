
import domain.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NormalTest {
    private Normal normal;

    @BeforeEach
    public void setUp() {
        normal = new Normal(0, 0, "Normal");
    }

    @Test
    public void testConstructor() {
        assertNotNull(normal);
    }
}

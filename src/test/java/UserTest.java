import fr.iut.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserTest {
    User user;

    @BeforeEach
    void setUp() {
        user = new User("John", "Doe");
    }

    @Test
    void testCreateUser() {
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals(0, user.getFidelityPoints());
    }

    @Test
    void testAddFidelityPoints() {
        assertEquals(0, user.getFidelityPoints());
        user.addFidelityPoints(10);
        assertEquals(10, user.getFidelityPoints());
    }

    @Test
    void testToString() {
        assertEquals("John Doe (0 points de fidélité)", user.toString());
    }

    @Test
    void testSetFidelityPoints() {
        user.setFidelityPoints(3);
        assertEquals(3, user.getFidelityPoints());
    }
}

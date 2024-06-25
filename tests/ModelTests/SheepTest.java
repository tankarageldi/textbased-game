package ModelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Sheep;

import static org.junit.jupiter.api.Assertions.*;

class SheepTest {
    private Sheep sheep;

    @BeforeEach
    void setUp() {
        sheep = new Sheep("yellow");
    }

    @Test
    void testInitialConditions() {
        assertEquals("yellow", sheep.getColor(), "Sheep color should be initialized to 'White'");
        assertFalse(sheep.isScared(), "Sheep should not be scared initially");
    }

    @Test
    void testScare() {
        sheep.scare();
        assertTrue(sheep.isScared(), "Sheep should be scared after scare() method is called");
    }

    @Test
    void testBecomeBrave() {
        sheep.scare(); // First, make the sheep scared
        sheep.becomeBrave(); // Then, make it brave
        assertFalse(sheep.isScared(), "Sheep should not be scared after becomeBrave() method is called");
    }
}

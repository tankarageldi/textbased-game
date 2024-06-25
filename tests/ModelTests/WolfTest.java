package ModelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Wolf;

import static org.junit.jupiter.api.Assertions.*;

public class WolfTest {

    private Wolf wolf;

    @BeforeEach
    void setUp() {
        wolf = new Wolf();
    }

    @Test
    void testGetName() {
        assertEquals("Wolf", wolf.getName(), "The name should correctly be 'Wolf'");
    }

    @Test
    void testGetDescription() {
        String expectedDescription = "THAT NIGHTMARE WOLF IS NO BIG DEAL. THREE EYES AND IT STILL DOESN'T SEE US SNEAKING PAST! NOPE, DEFINITELY NOT SCARY AT ALL... *HOOOOOOOOOOWL*\r\n"
                +
                "THE WOLF IS RIGHT BEHIND ME ISN'T IT?!?!";
        assertEquals(expectedDescription, wolf.getDescription(),
                "The description should correctly match the expected description");
    }

    @Test
    void testGetDifficulty() {
        assertEquals(1, wolf.getDifficulty(), "The difficulty level should correctly be 1");
    }

}
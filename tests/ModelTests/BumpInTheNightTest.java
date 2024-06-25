package ModelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.BumpInTheNight;

import static org.junit.jupiter.api.Assertions.*;

class BumpInTheNightTest {

    private BumpInTheNight bumpInTheNight;

    @BeforeEach
    void setUp() {
        bumpInTheNight = new BumpInTheNight();
    }

    @Test
    void testName() {
        assertEquals("Bump In The Night", bumpInTheNight.getName(), "The name should be 'Bump In The Night'");
    }

    @Test
    void testDescription() {
        String expectedDescription = "*THUD* WHAT WAS THAT SOUND? PROBABLY NOTHING, THERE'S ONLY SHADOWS. *THUD* I THINK I HEARD A BUMP IN THE NIGHT AGAIN. WAS IT CLOSER? MAYBE I SHOULD GO SEE IF THERE'S ANYTHING THERE... *THUD SQUEEELCH* THERE WAS DEFINITELY SOMETHING THERE!!!";
        assertEquals(expectedDescription, bumpInTheNight.getDescription(),
                "The description should be '" + expectedDescription + "'");
    }

    @Test
    void testDifficulty() {
        assertEquals(1, bumpInTheNight.getDifficulty(), "The difficulty should be 1");
    }
}

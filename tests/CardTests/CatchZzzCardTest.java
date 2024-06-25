package CardTests;

import models.cards.CatchZzzCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CatchZzzCardTest {

    private CatchZzzCard catchZzzCard;

    @BeforeEach
    void setUp() {
        catchZzzCard = new CatchZzzCard(1);
    }

    @Test
    void testGetInformation() {
        String expectedInformation = "Catch 1 zzz";
        assertEquals(expectedInformation, catchZzzCard.getInformation(),
                "The information should be the expected information");
    }

    @Test
    void testIsNightmare() {
        assertFalse(catchZzzCard.isNightmare(), "The card should not be a nightmare");
    }
}
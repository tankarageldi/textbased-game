package CardTests;

import models.cards.CatchZzzCard;
import models.cards.MoveSpacesCard;
import models.cards.DualActionChoiceCard;
import models.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DualActionChoiceCardTest {

    private DualActionChoiceCard dualActionChoiceCard;

    @BeforeEach
    void setUp() {
        Card[] actions = new Card[]{new CatchZzzCard(1), new MoveSpacesCard(2)}; 
        dualActionChoiceCard = new DualActionChoiceCard(actions);
    }

    @Test
    void testGetInformation() {
        String expectedInformation = "'Catch 1 zzz' or 'Move 2 spaces'";
        assertEquals(expectedInformation, dualActionChoiceCard.getInformation(), "The information should be the expected information");
    }

    @Test
    void testIsNightmare() {
        assertFalse(dualActionChoiceCard.isNightmare(), "The card should not be a nightmare");
    }
}
package ModelTests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import models.Deck;
import models.NightmareDeckCreator;

public class NightmareDeckCreatorTest {

    @Test
    public void testCreateNightmareCards_Wolf() {
        Deck deck = new Deck();
        String nightmareChoice = "Wolf";
        NightmareDeckCreator.createNightmareCards(deck, nightmareChoice);
        assertNotNull(deck.drawCard(), "Deck should not be empty");
    }

    @Test
    public void testCreateNightmareCards_BumpInTheNight() {
        Deck deck = new Deck();
        String nightmareChoice = "BumpInTheNight";
        NightmareDeckCreator.createNightmareCards(deck, nightmareChoice);
        assertNotNull(deck.drawCard(), "Deck should not be empty");
    }

}
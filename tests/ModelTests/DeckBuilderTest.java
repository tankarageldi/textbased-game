package ModelTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import controllers.DeckController;
import models.DeckBuilder;

public class DeckBuilderTest {

    @Test
    public void testCreateDeck() {
        DeckController deckController = DeckBuilder.createDeck();

        assertNotNull(deckController);
        assertNotNull(deckController.drawCard());
    }
}
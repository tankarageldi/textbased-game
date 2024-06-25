package ModelTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import models.TileDeck;
import models.TileDeckBuilder;

public class TileDeckBuilderTest {

    @Test
    public void testCreateTiles() {
        TileDeck deck = TileDeckBuilder.createTiles();
        assertEquals(4, deck.getNumOfTiles(), "Tile market should have 4 tiles");
    }

}
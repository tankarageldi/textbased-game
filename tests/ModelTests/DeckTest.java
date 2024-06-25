package ModelTests;

import models.Card;
import models.Deck;
import models.cards.ScareNightmareCard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void testAddCard() {
        Card card = new ScareNightmareCard();
        deck.addCard(card);
        assertEquals(card, deck.drawCard());
    }

    @Test
    public void testDrawCard() {
        Card card = new ScareNightmareCard();
        deck.addCard(card);
        assertEquals(card, deck.drawCard());
    }

}
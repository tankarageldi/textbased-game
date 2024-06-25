package ControllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import models.Deck;
import models.cards.MoveNightmareCard;
import models.cards.MoveSpacesCard;
import models.Card;
import controllers.DeckController;

public class DeckControllerTest {

    private DeckController deckController;
    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
        deck.addCard(new MoveSpacesCard(1));
        deck.addCard(new MoveNightmareCard(1));
        deckController = new DeckController(deck);
    }

    @Test
    void testGetModel() {
        assertEquals(deck, deckController.getModel(), "The model deck should match the initialized deck");
    }

    @Test
    void testDrawCard() {
        Card drawnCard = deckController.drawCard();
        assertEquals("Move one space", drawnCard.getInformation(), "The drawn card should be the first in the deck since it was not shuffled");
        deckController.drawCard();
        assertNotNull(deckController.drawCard(), "The deck should be shuffled and a card should be drawn");
    }
}
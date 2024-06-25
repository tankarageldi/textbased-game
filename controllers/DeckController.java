package controllers;

import models.Card;
import models.Deck;

/**
 * The DeckController class represents a controller for a deck of cards.
 * It provides methods to interact with the deck, such as drawing a card.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class DeckController {
    private Deck deck;

    /**
     * Constructs a DeckController object with the specified deck.
     *
     * @param deck the deck to be controlled
     */
    public DeckController(Deck deck) {
        this.deck = deck;
    }

    /**
     * Returns the model deck associated with this controller.
     *
     * @return the model deck
     */
    public Deck getModel() {
        return deck;
    }

    /**
     * Draws a card from the deck.
     *
     * @return the drawn card
     */
    public Card drawCard() {
        return deck.drawCard();
    }
}
package models;

import controllers.DeckController;
import models.cards.DualActionCard;
import models.cards.CatchZzzCard;
import models.cards.GainWinksCard;
import models.cards.MoveSpacesCard;
import models.cards.DualActionChoiceCard;

/**
 * The DeckBuilder class is responsible for creating a deck of cards for the game.
 * It provides methods to create different types of cards and a method to create a deck controller.
 * 
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class DeckBuilder {
    
    /**
     * Creates a deck of cards for the game.
     * 
     * @return The deck controller that manages the deck of cards.
     */
    public static DeckController createDeck() {
        Deck deck = new Deck();
        
        // Add all SheepyCards to the deck
        for (int i = 0; i < 2; i++) {
            deck.addCard(createDualActionChoiceCard(new MoveSpacesCard(1), new MoveSpacesCard(5)));
        }
        
        for (int i = 0; i < 7; i++) {
            deck.addCard(createDualActionChoiceCard(new MoveSpacesCard(4), new CatchZzzCard(1)));
        }
        
        for (int i = 0; i < 2; i++) {
            deck.addCard(createDualActionCard(new MoveSpacesCard(1), new CatchZzzCard(1)));
        }
        
        for (int i = 0; i < 2; i++) {
            Card card1 = new MoveSpacesCard(1);
            Card card2 = new MoveSpacesCard(2);
            Card card3 = createDualActionChoiceCard(card1, card2);
            Card card4 = new CatchZzzCard(1);
            deck.addCard(createDualActionCard(card3, card4));
        }
        
        for (int i = 0; i < 2; i++) {
            deck.addCard(createDualActionChoiceCard(new MoveSpacesCard(5), new GainWinksCard(2)));
        }
        
        for (int i = 0; i < 3; i++) {
            deck.addCard(createDualActionChoiceCard(new MoveSpacesCard(2), new CatchZzzCard(2)));
        }
        
        for (int i = 0; i < 3; i++) {
            deck.addCard(createDualActionChoiceCard(new MoveSpacesCard(6), new GainWinksCard(3)));
        }
        
        for (int i = 0; i < 7; i++) {
            deck.addCard(createDualActionChoiceCard(new MoveSpacesCard(3), new CatchZzzCard(1)));
        }

        for (int i = 0; i < 2; i++) {
            deck.addCard(new CatchZzzCard(2));
        }

        DeckController deckController = new DeckController(deck);
        return deckController;
    }
    
    /**
     * Creates a DualActionChoiceCard by combining two cards using the OR logic.
     * 
     * @param card1 The first card to be combined.
     * @param card2 The second card to be combined.
     * @return The DualActionChoiceCard created by combining the two cards.
     */
    private static Card createDualActionChoiceCard(Card card1, Card card2) {
        return new DualActionChoiceCard(new Card[] { card1, card2 });
    }
    
    /**
     * Creates a DualActionCard by combining two cards using the AND logic.
     * 
     * @param card1 The first card to be combined.
     * @param card2 The second card to be combined.
     * @return The DualActionCard created by combining the two cards.
     */
    private static Card createDualActionCard(Card card1, Card card2) {
        return new DualActionCard(new Card[] { card1, card2 });
    }
}

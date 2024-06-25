package models;

import models.cards.JumpNightmareCard;
import models.cards.MoveNightmareCard;
import models.cards.ScareNightmareCard;

/**
 * The NightmareDeckCreator class is responsible for creating nightmare cards and adding them to a deck.
 * It provides methods to create different types of nightmare cards based on the user's choice.
 * 
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class NightmareDeckCreator {
    
    /**
     * Creates nightmare cards based on the user's choice and adds them to the specified deck.
     * 
     * @param deck The deck to which the nightmare cards will be added.
     * @param nightmareChoice The user's choice of nightmare cards.
     */
    public static void createNightmareCards(Deck deck, String nightmareChoice) {
        switch (nightmareChoice) {
            case "Wolf":
                addMoveNightmareCards(deck, 4, 2);
                addMoveNightmareCards(deck, 4, 1);
                addScareNightmareCards(deck, 2);
                break;
            case "BumpInTheNight":
                addJumpNightmareCards(deck, 3, -1);
                addJumpNightmareCards(deck, 4, 2);
                addJumpNightmareCards(deck, 2, 3);
                deck.addCard(new JumpNightmareCard(1));
                break;
            default:
                break;
        }
        deck.shuffleDeck();
    }
    
    /**
     * Adds a specified number of move nightmare cards with a given move amount to the deck.
     * 
     * @param deck The deck to which the move nightmare cards will be added.
     * @param count The number of move nightmare cards to add.
     * @param moveAmount The amount of move for each move nightmare card.
     */
    private static void addMoveNightmareCards(Deck deck, int count, int moveAmount) {
        for (int i = 0; i < count; i++) {
            deck.addCard(new MoveNightmareCard(moveAmount));
        }
    }
    
    /**
     * Adds a specified number of jump nightmare cards with a given jump amount to the deck.
     * 
     * @param deck The deck to which the jump nightmare cards will be added.
     * @param count The number of jump nightmare cards to add.
     * @param jumpAmount The amount of jump for each jump nightmare card.
     */
    private static void addJumpNightmareCards(Deck deck, int count, int jumpAmount) {
        for (int i = 0; i < count; i++) {
            deck.addCard(new JumpNightmareCard(jumpAmount));
        }
    }
    
    /**
     * Adds a specified number of scare nightmare cards to the deck.
     * 
     * @param deck The deck to which the scare nightmare cards will be added.
     * @param count The number of scare nightmare cards to add.
     */
    private static void addScareNightmareCards(Deck deck, int count) {
        for (int i = 0; i < count; i++) {
            deck.addCard(new ScareNightmareCard());
        }
    }
}

package models;

import controllers.DeckController;
import controllers.GameBoardController;
import controllers.PlayerController;
import views.UserInput;

/**
 * The Phase interface represents a phase in the game.
 * It provides methods for executing a move and determining if it is a racing
 * phase.
 * 
 * @author Adil Alimzhanov
 */
public interface Phase {
    /**
     * Executes a move in the phase.
     *
     * @param gameBoardController  the game board controller
     * @param deckController       the deck controller
     * @param tileDeck             the tile deck
     * @param userInput            the user input
     * @param playerController     the player controller
     */
    public void executeMove(GameBoardController gameBoardController, DeckController deckController,
            TileDeck tileDeck, UserInput userInput, PlayerController playerController);

    /**
     * Checks if the phase is a racing phase.
     *
     * @return true if the phase is a racing phase, false otherwise
     */
    public boolean isRacingPhase();
}
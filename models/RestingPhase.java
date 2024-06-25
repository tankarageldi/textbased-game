package models;

import controllers.GameBoardController;
import controllers.DeckController;
import controllers.PlayerController;
import views.UserInput;

/**
 * Represents the resting phase of the game.
 * Implements the Phase interface.
 * 
 * @author Adil Alimzhanov
 */
public class RestingPhase implements Phase {

    /**
     * Executes the move for the resting phase.
     * This method is called when it's the player's turn to make a move during the resting phase.
     * It uses the RestingPhaseLogic class to play the resting move.
     *
     * @param gameBoardController  The controller for the game board.
     * @param deckController       The controller for the deck.
     * @param tileDeck             The deck of tiles.
     * @param userInput            The user input.
     * @param playerController     The controller for the player.
     */
    @Override
    public void executeMove(GameBoardController gameBoardController, DeckController deckController, 
                            TileDeck tileDeck, UserInput userInput, PlayerController playerController) {
        RestingPhaseLogic restingPhaseLogic = new RestingPhaseLogic();
        restingPhaseLogic.playRestingMove(gameBoardController, userInput, tileDeck, playerController);
    }

    /**
     * Checks if the current phase is the racing phase.
     *
     * @return false, indicating that the current phase is not the racing phase.
     */
    @Override
    public boolean isRacingPhase() {
        return false;
    }
}

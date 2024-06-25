package models;

import controllers.GameBoardController;
import controllers.DeckController;
import controllers.PlayerController;
import views.UserInput;

/**
 * Represents the racing phase of the game.
 * Implements the Phase interface.
 * 
 * @author Adil Alimzhanov
 */
public class RacingPhase implements Phase {

    /**
     * Executes the move for the racing phase.
     * 
     * @param gameBoardController The game board controller.
     * @param deckController The deck controller.
     * @param tileDeck The tile deck.
     * @param userInput The user input.
     * @param playerController The player controller.
     */
    @Override
    public void executeMove(GameBoardController gameBoardController, DeckController deckController,
            TileDeck tileDeck, UserInput userInput, PlayerController playerController) {
        RacingPhaseLogic racingPhaseLogic = new RacingPhaseLogic();
        racingPhaseLogic.playRacingMove(playerController, gameBoardController, deckController, userInput);
    }

    /**
     * Checks if the current phase is the racing phase.
     * 
     * @return true if the current phase is the racing phase, false otherwise.
     */
    @Override
    public boolean isRacingPhase() {
        return true;
    }
}

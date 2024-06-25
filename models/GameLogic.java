package models;

import java.util.List;

import controllers.DeckController;
import controllers.GameBoardController;
import controllers.PlayerController;
import controllers.ScoreBoardController;
import views.GameView;
import views.UserInput;

/**
 * This class represents the game logic for the SheepyTime game.
 * It contains methods for playing the game, checking win conditions, and taking
 * turns.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class GameLogic {

    private List<PlayerController> playerControllers;
    private GameBoardController gameBoardController;
    private DeckController deckController;
    private ScoreBoardController scoreBoardController;
    private GameView gameView;
    private TileDeck tileDeck;
    private UserInput userInput;

    /**
     * Constructs a new instance of the GameLogic class.
     *
     * @param playerControllers    the list of player controllers
     * @param gameBoardController  the game board controller
     * @param deckController       the deck controller
     * @param scoreBoardController the score board controller
     * @param gameView             the game view
     * @param tileDeck             the tile deck
     * @param userInput            the user input
     */
    public GameLogic(List<PlayerController> playerControllers, GameBoardController gameBoardController,
            DeckController deckController, ScoreBoardController scoreBoardController, GameView gameView,
            TileDeck tileDeck, UserInput userInput) {
        this.playerControllers = playerControllers;
        this.gameBoardController = gameBoardController;
        this.deckController = deckController;
        this.scoreBoardController = scoreBoardController;
        this.gameView = gameView;
        this.tileDeck = tileDeck;
        this.userInput = userInput;
    }

    /**
     * Plays the game by executing the game phases until the game ends.
     * The game consists of alternating racing and resting phases.
     * 
     * @return true if the game ended, false otherwise
     */
    public void playGame() {
        boolean gameEnded = false;
        while (!gameEnded) {
            while(!gameBoardController.isTurnOver()){
                gameEnded = playPhase(new RacingPhase());
            }
            resetGame();
            if (!gameEnded) {
                gameEnded = playPhase(new RestingPhase());
            }
        }
    }

    /**
     * Executes the play phase of the game for each player.
     * 
     * @param phase The current phase of the game.
     * @return true if a player has won the game, false otherwise.
     */
    private boolean playPhase(Phase phase) {
        for (PlayerController playerController : playerControllers) {
            gameView.showPlayerTurn(playerController.getPlayerName(), phase.isRacingPhase());
            phase.executeMove(gameBoardController, deckController, tileDeck, userInput,
                    playerController);
            if (checkWinConditions()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Resets the game by resetting the positions on the game board and updating the
     * score board.
     */
    private void resetGame() {
        gameBoardController.resetPositions();
        scoreBoardController.endOfTurn();
        scoreBoardController.displayScoreBoard();
    }

    /**
     * Checks if the win conditions for the game are met.
     *
     * @param scoreBoardController the score board controller
     * @return true if the win conditions are met, false otherwise
     */
    private boolean checkWinConditions() {
        return scoreBoardController.isGameOver();
    }

}

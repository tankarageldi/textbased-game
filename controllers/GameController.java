package controllers;

import java.util.List;

import models.GameLogic;
import models.TileDeck;

import views.UserInput;
import views.GameView;

/**
 * The GameController class represents the controller for the game. It manages
 * the game logic and interactions between
 * different components of the game.
 * 
 * @author Adil Alimzhanov
 */
public class GameController {
    private GameView gameView;
    private GameLogic gameLogic;

    /**
     * Constructs a GameController object with the specified number of players and
     * user input.
     *
     * @param numOfPlayers The number of players in the game.
     * @param userInput    The user input object used for player interactions.
     */
    public GameController(
            List<PlayerController> playerControllers,
            GameBoardController gameBoardController,
            DeckController deckController,
            ScoreBoardController scoreBoardController,
            NightmareController nightmareController,
            GameView gameView,
            TileDeck tileDeck,
            UserInput userInput) {
        this.gameView = gameView;
        this.gameLogic = new GameLogic(playerControllers, gameBoardController, deckController,
                scoreBoardController, gameView, tileDeck, userInput);
    }

    /**
     * Starts the game by displaying a welcome message, playing the game logic, and
     * concluding the game.
     */
    public void startGame() {
        gameView.showWelcomeMessage();
        gameLogic.playGame();
        concludeGame();
    }

    /**
     * Concludes the game by displaying a game over message.
     */
    private void concludeGame() {
        gameView.showGameOver();
    }
}

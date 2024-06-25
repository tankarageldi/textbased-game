import java.util.List;

import controllers.*;
import models.DeckBuilder;
import models.GameBoardCreator;
import models.NightmareFactory;
import models.PlayerCreator;
import models.ScoreBoardCreator;
import models.TileDeck;
import models.TileDeckBuilder;
import views.GameView;
import views.NightmareView;
import views.UserInput;

/**
 * The Main class is the entry point of the SheepyTime game.
 * It initializes the necessary objects and starts the game.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */

public class Main {
    /**
     * The main method is the entry point of the program.
     * It creates an instance of UserInput, sets the number of players,
     * creates an instance of GameController, and starts the game.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserInput userInput = new UserInput();
        GameView gameView = new GameView();
        int numOfPlayers = 1;
        List<PlayerController> playerControllers = PlayerCreator.intialisePlayers(userInput, numOfPlayers);
        GameBoardController gameBoardController = GameBoardCreator.initializeGameBoard();
        DeckController deckController = DeckBuilder.createDeck();
        ScoreBoardController scoreBoardController = ScoreBoardCreator.initializeScoreBoard(playerControllers);
        NightmareView nightmareView = new NightmareView();
        NightmareController nightmareController = new NightmareController(NightmareFactory.createNightmare(userInput, deckController),nightmareView);
        TileDeck tileDeck = TileDeckBuilder.createTiles();
        GameController gameController = new GameController(playerControllers, gameBoardController,
                deckController, scoreBoardController, nightmareController, gameView, tileDeck, userInput);
        gameBoardController.addNightmareToBoard(nightmareController.getModel());

        for (PlayerController playerController : playerControllers) {
            gameBoardController.addMovableToBoard(playerController.getModel());
        }
        gameController.startGame();
    }
}
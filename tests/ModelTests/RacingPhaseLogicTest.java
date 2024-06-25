package ModelTests;

import controllers.DeckController;
import controllers.GameBoardController;
import controllers.PlayerController;
import controllers.TileController;
import models.Deck;
import models.GameBoard;
import models.Nightmare;
import models.Player;
import models.RacingPhaseLogic;
import models.Tile;
import models.Wolf;
import models.cards.MoveNightmareCard;
import models.cards.MoveSpacesCard;
import models.tiles.ActionHeroTile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import views.GameBoardView;
import views.PlayerView;
import views.UserInput;

import static org.junit.jupiter.api.Assertions.*;

public class RacingPhaseLogicTest {
    private PlayerController playerController;
    private GameBoardController gameBoardController;
    private DeckController deckController;
    private UserInput userInput;
    private RacingPhaseLogic racingPhaseLogic;
    private Nightmare nightmare;

    @BeforeEach
    public void setup() {
        Player player = new Player("Adil", "blue");
        playerController = new PlayerController(player, new PlayerView());
        GameBoard gameBoard = new GameBoard();
        nightmare = new Wolf();
        gameBoard.addNightmareToBoard(nightmare);
        gameBoard.placeMovable(player, 1);
        GameBoardView gameBoardView = new GameBoardView();
        gameBoardController = new GameBoardController(gameBoard, gameBoardView);
        
        racingPhaseLogic = new RacingPhaseLogic();
    }

    @Test
    public void testPlayRacingMove() {
        // Scenario 1:
        // Player draws "move 1 spaces card"
        // Player draws Nightmare card and resolves it
        // Player gets scared
        // Player draws "move 2 spaces card"
        // Player plays "move 1 spaces card"
        // Player draws "move 3 spaces card"
        // Player does not need a card
        Deck deck = new Deck();
        deck.addCard(new MoveSpacesCard(1));
        deck.addCard(new MoveNightmareCard(1));
        deck.addCard(new MoveSpacesCard(2));
        deck.addCard(new MoveSpacesCard(3));
        deck.addCard(new MoveSpacesCard(4));
        deckController = new DeckController(deck);
        userInput = new UserInput(){
            @Override
            public int getCardSelection() {
                return 0;
            }
        };
        racingPhaseLogic.playRacingMove(playerController, gameBoardController, deckController, userInput);
        assertEquals(1, gameBoardController.getNightmarePosition(), "The nightmare should move by 1 after the nightmare card is drawn");
        assertTrue(playerController.getSheep().isScared(), "The sheep should be scared after nightmare moves onto it");
        assertEquals(2, gameBoardController.getMovablePosition(playerController.getModel()), "The player should move by 1 after choosing the first card");
        assertFalse(playerController.needsACard(), "Player should not need a card");
    }
    @Test
    public void testPlayRacingMoveCrossFence() {
        // Scenario 2:
        // Player draws "move 11 spaces card"
        // Player draws "move 2 spaces card"
        // Player moves 11 spaces and crosses the fence
        // Player draws "move 3 spaces card"
        // PLayer chooses to call it a night

        Deck deck = new Deck();
        deck.addCard(new MoveSpacesCard(11));
        deck.addCard(new MoveSpacesCard(2));
        deck.addCard(new MoveSpacesCard(3));
        deckController = new DeckController(deck);
        userInput = new UserInput(){
            @Override
            public int getCardSelection() {
                return 0;
            }
            public boolean getCallItANightDecision(){
                return true;
            }
        };
        assertFalse(gameBoardController.isTurnOver(), "The game should not be over at the start of the test");
        racingPhaseLogic.playRacingMove(playerController, gameBoardController, deckController, userInput);
        assertTrue(gameBoardController.isTurnOver(), "The game should be over after the player calls it a night");
    }
    @Test
    public void testPlayRacingMoveActivateTile() {
        // Scenario 3:
        // Board has a tile at position 3
        // Player is scared
        // Player draws "move 2 spaces card"
        // Player draws "move 3 spaces card"
        // Player moves 2 spaces and activates the tile and gains 3 winks

        Deck deck = new Deck();
        deck.addCard(new MoveSpacesCard(2));
        deck.addCard(new MoveSpacesCard(3));
        deckController = new DeckController(deck);
        playerController.getModel().becomeScared();
        Tile actionHeroTile = new ActionHeroTile();
        actionHeroTile.placeZzzs(playerController.catchZZZs(1), false);
        gameBoardController.placeTile(3, new TileController(actionHeroTile, null));
        userInput = new UserInput(){
            @Override
            public int getCardSelection() {
                return 0;
            }
            @Override
            public boolean getActivateTileDecision(String tileName){
                return true;
            }
        };
        assertEquals(0, playerController.getModel().getWinks());
        racingPhaseLogic.playRacingMove(playerController, gameBoardController, deckController, userInput);
        assertEquals(3, playerController.getModel().getWinks(), "The player should gain 3 winks after activating the tile");
        assertFalse(actionHeroTile.hasZzzs(), "The tile should not have any zzzs after the player activates it");
        assertEquals(9, playerController.getNumOfZzzs(), "The player should have 9 zzzs after activating the tile");
    }
}
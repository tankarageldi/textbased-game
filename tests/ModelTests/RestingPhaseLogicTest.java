package ModelTests;

import controllers.GameBoardController;
import controllers.PlayerController;
import controllers.TileController;
import models.GameBoard;
import models.Player;
import models.RestingPhaseLogic;
import models.Tile;
import models.TileDeck;
import models.tiles.ActionHeroTile;
import views.TileView;
import views.UserInput;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RestingPhaseLogicTest {

    private GameBoardController gameBoardController;
    private PlayerController playerController;
    private TileDeck tileDeck;
    private RestingPhaseLogic restingPhaseLogic;
    private UserInput userInput;

    @Before
    public void setUp() {
        gameBoardController = new GameBoardController(new GameBoard(), null);
        playerController = new PlayerController(new Player("Adil", "blue"), null);
        tileDeck = new TileDeck();
        restingPhaseLogic = new RestingPhaseLogic();
    }

    @Test
    public void testPlayRestingMove() {
        // Scenario 1:
        // Player selects a tile from the tile deck and places it on the game board
        // Player gets placement bonus zzzs on that tile
        // The top tile is placed on the next location
        // Player does not get placement bonus zzzs on that tile
        Tile tile1 = new ActionHeroTile();
        tileDeck.addTile(new TileController(tile1, new TileView()));
        Tile tile2 = new ActionHeroTile();
        tileDeck.addTile(new TileController(tile2, new TileView()));
        Tile tile3 = new ActionHeroTile();
        tileDeck.addTile(new TileController(tile3, new TileView()));
        Tile tile4 = new ActionHeroTile();
        tileDeck.addTile(new TileController(tile4, new TileView()));
        Tile tile5 = new ActionHeroTile();
        tileDeck.addTile(new TileController(tile5, new TileView()));
        Tile tile6 = new ActionHeroTile();
        tileDeck.addTile(new TileController(tile6, new TileView()));
        tileDeck.initializeMarket();
        userInput = new UserInput() {
            @Override
            public boolean getRestingMoveDecision() {
                return true;
            }

            // Select the first tile
            @Override
            public int getTileSelection(int numOfTiles) {
                return 1;
            }

            // Place the tile at position 1
            @Override
            public int getTilePlacementPosition() {
                return 1;
            }
        };

        restingPhaseLogic.playRestingMove(gameBoardController, userInput, tileDeck, playerController);
        assertTrue(gameBoardController.isTilePlaced(1));
        assertTrue(gameBoardController.isTilePlaced(2));
        assertTrue("Tile 1 should have placement bonus zzzs", gameBoardController.getTile(1).hasZzzs());
        assertFalse("Tile 2 (Top tile for single player) should  not have placement bonus zzzs",
                gameBoardController.getTile(2).hasZzzs());
    }

    @Test
    public void testPlayRestingMoveCatchZzzs() {
        // Scenario 1:
        // Player catches zzzs onto one tile
        gameBoardController.placeTile(1, new TileController(new ActionHeroTile(), new TileView()));
        userInput = new UserInput() {
            @Override
            public boolean getRestingMoveDecision() {
                return false;
            }

            @Override
            public boolean getCatchZzzsDecision() {
                return true;
            }

            @Override
            public int getCatchTileIndex() {
                return 1;
            }
        };
        assertFalse(gameBoardController.getTile(1).hasZzzs());
        restingPhaseLogic.playRestingMove(gameBoardController, userInput, tileDeck, playerController);
        assertTrue(gameBoardController.getTile(1).hasZzzs());
        assertEquals("The player should have 8 zzzs left after catching zzzs", 8, playerController.getNumOfZzzs());
    }

}

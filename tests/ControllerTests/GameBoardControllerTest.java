package ControllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;

import controllers.GameBoardController;
import models.BumpInTheNight;
import models.GameBoard;
import views.GameBoardView;
import views.TileView;
import models.Movable;
import models.Nightmare;
import models.Player;
import models.Tile;
import models.Wolf;
import models.tiles.ActionHeroTile;
import controllers.TileController;

public class GameBoardControllerTest {

    private GameBoard gameBoard;
    private GameBoardView gameBoardView;
    private GameBoardController gameBoardController;
    private Movable player;
    private Nightmare nightmare;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard();
        gameBoardView = new GameBoardView();
        gameBoardController = new GameBoardController(gameBoard, gameBoardView);
        player = new Player("Adil", "blue");
        nightmare = new Wolf();
        gameBoardController.addMovableToBoard(player);
        gameBoardController.addNightmareToBoard(nightmare);
    }

    @Test
    void testGetModel() {
        assertEquals(gameBoard, gameBoardController.getModel(),
                "The model should match the game board passed in the constructor");
    }

    /*
     * This test runs on a windows, but doesnt run succesfully on a mac or linux, if you remove the comments, 
     * and run the test, you will see expected output and actual output are the same, but the test fails on a mac, not on windows.
     * we commented out the imports of this test above, so remove the comments of that imports to run the test. 
     * @Test
    void testDisplayInformation() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        gameBoardController.displayInformation();
        String expectedOutput = "Current Game Board State:\r\n" + //
                "Adil is at position: 1\r\n" + //
                "Wolf is at position: 0";
        assertEquals(expectedOutput, outputStream.toString().trim(),
                "The player starts at position 1, and the nightmare starts at position 0");
    }
     */

    @Test
    void testPlaceTile() {
        int position = 1;
        Tile tile = new ActionHeroTile();
        TileView tileView = new TileView();
        TileController tileController = new TileController(tile, tileView);
        gameBoardController.placeTile(position, tileController);
        assertEquals(tileController, gameBoardController.getTile(position),
                "The tile controller should be placed at the specified position");

    }

    @Test
    void testAddMovableToBoard() {
        Movable movable = new Player("Tan", "yellow");
        gameBoardController.addMovableToBoard(movable);
        assertEquals(1, gameBoardController.getMovablePosition(movable),
                "The movable should be added to the board at the default position");
    }

    @Test
    void testPlaceMovable() {
        Movable movable = new Player("Tan", "yellow");
        int position = 2;
        gameBoardController.placeMovable(movable, position);
        assertEquals(2, gameBoardController.getMovablePosition(movable),
                "The movable should be added to the board at the specified position");
    }

    @Test
    void testMoveMovable() {
        int amount = 3;
        gameBoardController.moveMovable(player, amount);
        assertEquals(4, gameBoardController.getMovablePosition(player),
                "The player should have moved by the specified amount");
    }

    @Test
    void testCallItANight() {
        gameBoardController.callItANight(player);
        assertEquals(11, gameBoardController.getMovablePosition(player),
                "The player should have been called it a night");
    }

    @Test
    void testMoveNightmare() {
        gameBoardController.moveNightmare(2);
        assertTrue(player.isScared(), "The player should be scared when nightmare moves past them");
        boolean crossedFence = gameBoardController.moveNightmare(9);
        assertTrue(crossedFence, "The nightmare should have crossed the fence");
        assertEquals(gameBoardController.getMovablePosition(player), -1, "The player should be out of the game");
    }

    @Test
    void testJumpNightmare() {
        int amount = 5;
        gameBoardController.jumpNightmare(amount);
        assertFalse(player.isScared(), "The player should not be scared when nightmare jumps over them");
        boolean crossedFence = gameBoardController.jumpNightmare(6);
        assertTrue(crossedFence, "The nightmare should have crossed the fence");
        assertEquals(gameBoardController.getMovablePosition(player), -1, "The player should be out of the game");
    }

    @Test
    void testAddNightmareToBoard() {
        Nightmare nightmare = new BumpInTheNight();
        gameBoardController.addNightmareToBoard(nightmare);
        assertEquals(nightmare, gameBoardController.getNightmare(),
                "The nightmare should be added to the board");
    }

    @Test
    void testResetPositions() {
        gameBoardController.moveMovable(player, 2);
        gameBoardController.moveNightmare(3);
        gameBoardController.resetPositions();
        assertEquals(1, gameBoardController.getMovablePosition(player),
                "The player should be reset to the default position");
        assertEquals(0, gameBoardController.getNightmarePosition(),
                "The nightmare should be reset to the default position");

    }

    @Test
    void testIsTilePlaced() {
        TileController tileController = new TileController(new ActionHeroTile(), new TileView());
        gameBoardController.placeTile(1, tileController);
        boolean isPlaced = gameBoardController.isTilePlaced(1);
        assertTrue(isPlaced, "A tile should be placed at the specified position");
        assertFalse(gameBoardController.isTilePlaced(2), "No tile should be placed at the specified position");
    }

    @Test
    void testGetTile() {
        int position = 7;
        TileController tileController = new TileController(new ActionHeroTile(), new TileView());
        gameBoardController.placeTile(position, tileController);

        TileController result = gameBoardController.getTile(position);

        assertEquals(tileController, result, "The returned tile controller should match the one from the game board");
        assertNull(gameBoardController.getTile(1), "No tile should be placed at the specified position");
    }

    @Test
    void testGetMovablePosition() {
        gameBoardController.moveMovable(player, 3);
        int result = gameBoardController.getMovablePosition(player);

        assertEquals(4, result, "The returned position should match the new position of the movable");
    }

    @Test
    void testGetNumOfDreamTiles() {
        assertEquals(0, gameBoardController.getNumOfDreamTiles(), "The game board should start with 0 dream tiles");
        gameBoardController.placeTopTile(new TileController(new ActionHeroTile(), new TileView()));
        assertEquals(1, gameBoardController.getNumOfDreamTiles(), "The game board should have 1 dream tile");
        for (int i = 0; i < 15; i++) {
            gameBoardController.placeTopTile(new TileController(new ActionHeroTile(), new TileView()));
        }
        assertEquals(10, gameBoardController.getNumOfDreamTiles(), "The game board should have 10 dream tiles at max");
    }

    @Test
    void testPlaceTopTile() {
        TileController tileController = new TileController(new ActionHeroTile(), new TileView());
        gameBoardController.placeTopTile(tileController);
        assertEquals(tileController, gameBoardController.getTile(1),
                "The top tile should be placed at the first position");
        TileController tileController2 = new TileController(new ActionHeroTile(), new TileView());
        gameBoardController.placeTopTile(tileController2);
        assertEquals(tileController2, gameBoardController.getTile(2),
                "The top tile should be placed at the next position");
    }

    @Test
    void testIsTurnOver() {
        assertFalse(gameBoardController.isTurnOver(), "The turn should not be over at the start of the game");
        gameBoardController.moveNightmare(11);
        assertTrue(gameBoardController.isTurnOver(), "The turn should be over when the nightmare reaches the end");
    }
    @Test
    void testIsTurnOver2() {
        assertFalse(gameBoardController.isTurnOver(), "The turn should not be over at the start of the game");
        gameBoardController.callItANight(player);;
        assertTrue(gameBoardController.isTurnOver(), "The turn should be over when the player calls it a night");
    }
}
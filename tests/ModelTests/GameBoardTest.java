package ModelTests;

import models.GameBoard;
import models.Movable;
import models.Nightmare;
import models.Player;
import models.Wolf;
import models.tiles.FinalSprintTile;
import views.TileView;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.TileController;

import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {

    private GameBoard gameBoard;
    private TileController tile;
    private Movable movable;
    private Nightmare nightmare;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard();
        tile = new TileController(new FinalSprintTile(), new TileView());
        movable = new Player("Adil", "yellow");
        nightmare = new Wolf();
    }

    @Test
    void testPlaceTile() {
        gameBoard.placeTile(1, tile);
        assertTrue(gameBoard.isTilePlaced(1));
        assertEquals(tile, gameBoard.getTile(1));
    }

    @Test
    void testPlaceTileInvalidPosition() {
        assertThrows(IllegalArgumentException.class, () -> gameBoard.placeTile(0, tile));
        assertThrows(IllegalArgumentException.class, () -> gameBoard.placeTile(11, tile));
    }

    @Test
    void testPlaceTilePositionOccupied() {
        gameBoard.placeTile(1, tile);
        assertThrows(IllegalArgumentException.class, () -> gameBoard.placeTile(1, tile));
    }

    @Test
    void testGetNumOfDreamTiles() {
        gameBoard.placeTile(1, tile);
        gameBoard.placeTile(3, tile);
        gameBoard.placeTile(5, tile);
        assertEquals(3, gameBoard.getNumOfDreamTiles());
    }

    @Test
    void testPlaceTopTile() {
        gameBoard.placeTopTile(tile);
        assertTrue(gameBoard.isTilePlaced(1));
        assertEquals(tile, gameBoard.getTile(1));
        assertFalse(gameBoard.isTilePlaced(2));
        TileController newTile = new TileController(new FinalSprintTile(), new TileView());
        gameBoard.placeTopTile(newTile);
        assertTrue(gameBoard.isTilePlaced(2));
        assertEquals(newTile, gameBoard.getTile(2));
    }

    @Test
    void testPlaceMovable() {
        gameBoard.placeMovable(movable, 1);
        assertEquals(1, gameBoard.getMovablePosition(movable));
    }

    @Test
    void testMoveMovable() {
        gameBoard.placeMovable(movable, 1);
        gameBoard.moveMovable(movable, 3);
        assertEquals(4, gameBoard.getMovablePosition(movable));
    }

    @Test
    void testMoveMovableNotOnBoard() {
        assertThrows(IllegalArgumentException.class, () -> gameBoard.moveMovable(movable, 1));
    }

    @Test
    void testMoveMovableCalledItANight() {
        gameBoard.placeMovable(movable, 1);
        gameBoard.callItANight(movable);
        assertThrows(IllegalArgumentException.class, () -> gameBoard.moveMovable(movable, 1));
    }

    @Test
    void testMoveMovableWokenUp() {
        gameBoard.placeMovable(movable, 1);
        gameBoard.wakeUpMovable(movable);
        assertThrows(IllegalArgumentException.class, () -> gameBoard.moveMovable(movable, 1));
    }

    @Test
    void testMoveMovable_CircularBoard() {
        gameBoard.placeMovable(movable, 9);
        gameBoard.moveMovable(movable, 3);
        assertEquals(2, gameBoard.getMovablePosition(movable));
        gameBoard.moveMovable(movable, -3);
        assertEquals(1, gameBoard.getMovablePosition(movable), "Player cannot cross the fence backwards");
    }

    @Test
    void testMoveMovable_ScareMovable() {
        gameBoard.addNightmareToBoard(nightmare);
        gameBoard.moveNightmare(2);
        gameBoard.placeMovable(movable, 1);
        gameBoard.moveMovable(movable, 1);
        assertTrue(movable.isScared(), "Player should be scared");
        gameBoard.moveNightmare(1);
        gameBoard.moveMovable(movable, 1);
        assertTrue(gameBoard.isAwake(movable), "Player should be woken up");
    }

    @Test
    void testScareMovablesAtPosition() {
        gameBoard.placeMovable(movable, 1);
        gameBoard.scareMovablesAtPosition(1);
        assertTrue(movable.isScared());
    }

    @Test
    void testWakeUpMovable() {
        gameBoard.placeMovable(movable, 1);
        gameBoard.wakeUpMovable(movable);
        assertTrue(gameBoard.isAwake(movable));
    }

    @Test
    void testWakeEveryone() {
        gameBoard.placeMovable(movable, 1);
        gameBoard.wakeEveryone();
        assertTrue(gameBoard.isAwake(movable));
    }

    @Test
    void testCallItANight() {
        gameBoard.placeMovable(movable, 1);
        gameBoard.callItANight(movable);
        assertEquals(11, gameBoard.getMovablePosition(movable));
    }

    @Test
    void testIsTurnOverWakeUp() {
        gameBoard.placeMovable(movable, 1);
        assertFalse(gameBoard.isTurnOver());
        gameBoard.wakeUpMovable(movable);
        assertTrue(gameBoard.isTurnOver());
    }

    @Test
    void testIsTurnOverCallItANight() {
        gameBoard.placeMovable(movable, 1);
        assertFalse(gameBoard.isTurnOver());
        gameBoard.callItANight(movable);
        assertTrue(gameBoard.isTurnOver());
    }

    @Test
    void testAddNightmareToBoard() {
        gameBoard.addNightmareToBoard(nightmare);
        assertEquals(nightmare, gameBoard.getNightmare());
        assertEquals(0, gameBoard.getNightmarePos());
    }

    @Test
    void testMoveNightmare() {
        gameBoard.addNightmareToBoard(nightmare);
        gameBoard.moveNightmare(3);
        assertEquals(3, gameBoard.getNightmarePos());
    }

    @Test
    void testJumpNightmare() {
        gameBoard.addNightmareToBoard(nightmare);
        gameBoard.jumpNightmare(3);
        assertEquals(3, gameBoard.getNightmarePos());
    }

    @Test
    void testMoveNightmareScaring() {
        gameBoard.addNightmareToBoard(nightmare);
        gameBoard.placeMovable(movable, 2);
        gameBoard.moveNightmare(3); // Scare when passing a movable
        assertTrue(movable.isScared());
    }

    @Test
    void testJumpNightmareScaring() {
        gameBoard.addNightmareToBoard(nightmare);
        gameBoard.placeMovable(movable, 2);
        gameBoard.jumpNightmare(3); // Do not care when passing a movable
        assertFalse(movable.isScared());
    }

    @Test
    void testMoveNightmareCrossedFence() {
        gameBoard.addNightmareToBoard(nightmare);
        assertFalse(gameBoard.moveNightmare(10));
        assertTrue(gameBoard.moveNightmare(1));
    }

    @Test
    void testJumpNightmareCrossedFence() {
        gameBoard.addNightmareToBoard(nightmare);
        assertFalse(gameBoard.jumpNightmare(10));
        assertTrue(gameBoard.jumpNightmare(1));
    }

    @Test
    void testResetPositions() {
        gameBoard.placeMovable(movable, 1);
        gameBoard.addNightmareToBoard(nightmare);
        gameBoard.jumpNightmare(3);
        gameBoard.resetPositions();
        assertEquals(1, gameBoard.getMovablePosition(movable));
        assertEquals(0, gameBoard.getNightmarePos());
    }
}
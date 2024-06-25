package ControllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controllers.TileController;
import models.GameBoard;
import models.Player;
import models.Tile;
import models.tiles.ActionHeroTile;
import static org.junit.jupiter.api.Assertions.*;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
import views.TileView;
import views.UserInput;

public class TileControllerTest {

    private TileController tileController;
    private Tile tile;
    private TileView view;
    private Player player;
    private GameBoard gameBoard;
    private UserInput userInput;

    @BeforeEach
    void setUp() {
        tile = new ActionHeroTile();
        view = new TileView();
        tileController = new TileController(tile, view);
        player = new Player("Adil", "blue");
        gameBoard = new GameBoard();
        userInput = new UserInput();
        gameBoard.placeMovable(player, 1);
    }

    /*
     * This test runs on a windows, but doesnt run succesfully on a mac or linux, if you remove the comments,
     * and run the test, you will see expected output and actual output are the same, but the test fails on a mac, not on windows.
     * we commented out the imports of this test above, so remove the comments of that imports to run the test.
     * @Test
    void testDisplayTileInfo() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        tileController.displayTileInfo();
        String expectedOutput = "Tile name: Action Hero\r\n" + //
                "Tile ability: If you are SCARED, gain 3 winks\r\n" + //
                "Placement bonus: 1 infinite zzzs";
        assertEquals(expectedOutput, outputStream.toString().trim(), "The tile info should be displayed correctly");
    }
     */

    @Test
    void testActivateTileEffect() {
        player.becomeScared();
        tileController.placeZzzs(1, false);
        tileController.activateTileEffect(player, gameBoard, userInput);
        assertEquals(3, player.getWinks(), "The player should gain 3 winks");
        assertFalse(tileController.hasZzzs(), "The tile should not have any zzzs");
        tileController.activateTileEffect(player, gameBoard, userInput);
        assertEquals(3, player.getWinks(), "The player should not gain any winks");
    }

    @Test
    void testActivateTileEffectInfinite() {
        player.becomeScared();
        tileController.placeZzzs(1, true);
        tileController.activateTileEffect(player, gameBoard, userInput);
        assertEquals(3, player.getWinks(), "The player should gain 3 winks");
        assertTrue(tileController.hasZzzs(), "The tile should have zzzs");
        tileController.activateTileEffect(player, gameBoard, userInput);
        assertEquals(6, player.getWinks(), "The player should gain 3 winks");
        assertTrue(tileController.hasZzzs(), "The infinite zzzs should still be on the tile");
    }

    @Test
    void testIsInfinite() {
        assertTrue(tileController.isInfinite(), "The tile should have an infinite placement bonus");
    }

    @Test
    void testPlaceZzzs() {
        tileController.placeZzzs(1, false);
        assertTrue(tileController.hasZzzs(), "The tile should have zzzs");
    }

    @Test
    void testGetTileName() {
        assertEquals("Action Hero", tileController.getTileName(), "The tile name should be Action Hero");
    }

    @Test
    void testHasZzzs() {
        assertFalse(tileController.hasZzzs(), "The tile should not have zzzs");
        tileController.placeZzzs(1, false);
        assertTrue(tileController.hasZzzs(), "The tile should have zzzs");
    }

    @Test
    void testGetModel() {
        assertEquals(tile, tileController.getModel(), "The model should be the same as the tile");
    }
}
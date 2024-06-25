package TileTests;

import org.junit.jupiter.api.Test;

import controllers.TileController;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import models.tiles.PerfectLandingTile;
import models.Player;
import models.GameBoard;
import views.UserInput;

public class PerfectLandingTileTest {
    private PerfectLandingTile tile;
    private Player player;
    private GameBoard board;
    private UserInput userInput;

    @BeforeEach
    public void setUp() {
        tile = new PerfectLandingTile();
        player = new Player("Adil", "blue");
        board = new GameBoard();
        userInput = null;
        board.placeTile(3, new TileController(tile, null));
        tile.placeZzzs(1, false);
        board.placeMovable(player, 1);
    }   
    @Test
    public void testActivateEffectCrossedFence() {
        board.moveMovable(player, 12);
        player.resetWinks();    // to offset the winks gained from crossing the fence
        tile.activateEffect(player, board, userInput);
        assertEquals(3, player.getWinks(), "Player should gain 3 winks");
    }
    @Test
    public void testActivateEffectNotCrossedFence() {
        board.moveMovable(player, 2);
        tile.activateEffect(player, board, userInput);
        assertEquals(0, player.getWinks(), "Player should not gain any winks");
    }
}
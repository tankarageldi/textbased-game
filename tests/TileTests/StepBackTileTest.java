package TileTests;

import org.junit.jupiter.api.Test;
import controllers.TileController;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import models.tiles.StepBackTile;
import models.Player;
import models.GameBoard;
import views.UserInput;

public class StepBackTileTest {
    private StepBackTile tile;
    private Player player;
    private GameBoard board;
    private UserInput userInput;

    @BeforeEach
    public void setUp() {
        tile = new StepBackTile();
        player = new Player("Adil", "blue");
        board = new GameBoard();
        board.placeMovable(player, 2);
        board.placeTile(2, new TileController(tile, null));
        userInput = null;
        tile.placeZzzs(1, false);
    }

    @Test
    public void testActivateEffectNotScared() {
        tile.activateEffect(player, board, userInput);
        assertEquals(1, board.getMovablePosition(player), "The player should be moved back 1 space");
        assertFalse(player.isScared());
    }
    @Test
    public void testActivateEffectScared() {
        player.becomeScared();
        tile.activateEffect(player, board, userInput);
        assertEquals(1, board.getMovablePosition(player), "The player should be moved back 1 space");
        assertFalse(player.isScared());
    }
    @Test
    public void testActivateEffectOverFence() {
        player.becomeScared();
        board.moveMovable(player, -1);
        assertEquals(1, board.getMovablePosition(player), "The player should be moved back 1 space");
        tile.activateEffect(player, board, userInput);
        assertEquals(1, board.getMovablePosition(player), "The player should not move over the fence");
        assertFalse(player.isScared());
    }

}
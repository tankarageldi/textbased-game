package TileTests;

import models.GameBoard;
import models.Player;
import models.tiles.FinalSprintTile;
import views.UserInput;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FinalSprintTileTest {

    private Player player;
    private FinalSprintTile finalSprintTile;
    private GameBoard board;
    private UserInput userInput;

    @Before
    public void setUp() {
        finalSprintTile = new FinalSprintTile();
        board = new GameBoard();
        userInput = null;
        player = new Player("Adil", "blue");
        board.placeMovable(player, 1);
        finalSprintTile.placeZzzs(1, false);
    }

    @Test
    public void testActivateEffectMovesScaredPlayerForward() {
        player.becomeScared();
        finalSprintTile.activateEffect(player, board, userInput);
        assertEquals("Scared player should move forward 7 spaces", 8, board.getMovablePosition(player));
    }


    @Test
    public void testActivateEffectDoesNotMoveNotScaredPlayer() {
        finalSprintTile.activateEffect(player, board, userInput);
        assertEquals("Not scared player should not move", 1, board.getMovablePosition(player));
    }

}
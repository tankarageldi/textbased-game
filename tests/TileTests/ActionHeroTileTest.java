package TileTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import models.tiles.ActionHeroTile;
import models.Player;
import models.GameBoard;
import views.UserInput;

public class ActionHeroTileTest {

    private ActionHeroTile actionHeroTile;
    private Player player;
    private GameBoard board;
    private UserInput userInput;
    @BeforeEach
    public void setUp() {
        actionHeroTile = new ActionHeroTile();
        player = new Player("Adil", "blue");
        board = new GameBoard();
        userInput = null;
        board.placeMovable(player, 1);
        actionHeroTile.placeZzzs(1, false);
    }
    @Test
    public void testActivateEffectNotScared() {
        actionHeroTile.activateEffect(player, board, userInput);
        assertEquals(0, player.getWinks(), "The player should not gain any winks when not scared");
    }

    @Test
    public void testActivateEffectScared() {
        player.becomeScared();
        actionHeroTile.activateEffect(player, board, userInput);
        assertEquals(3, player.getWinks(), "The player should gain 3 winks when scared");
    }


}
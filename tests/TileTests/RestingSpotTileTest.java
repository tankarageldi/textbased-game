package TileTests;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import models.tiles.RestingSpotTile;
import models.Player;
import models.GameBoard;
import views.UserInput;

public class RestingSpotTileTest {
    private RestingSpotTile tile;
    private Player player;
    private GameBoard board;
    private UserInput userInput;

    @BeforeEach
    public void setUp() {
        tile = new RestingSpotTile();
        player = new Player("Adil", "blue");
        board = null;
        userInput = null;
        tile.placeZzzs(1, false);
    }

    @Test
    public void testActivateEffectNotScared() {
        tile.activateEffect(player, board, userInput);
        assertEquals(9, player.getNumOfZzzs());
        assertFalse(player.isScared());
    }

    @Test
    public void testActivateEffectScared() {
        player.becomeScared();
        tile.activateEffect(player, board, userInput);
        assertEquals(9, player.getNumOfZzzs());
        assertFalse(player.isScared());
    }
}
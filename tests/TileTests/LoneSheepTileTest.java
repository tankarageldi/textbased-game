package TileTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controllers.TileController;
import static org.junit.jupiter.api.Assertions.*;
import models.tiles.ActionHeroTile;
import models.tiles.LoneSheepTile;
import models.Tile;
import models.Player;
import models.GameBoard;
import views.UserInput;

public class LoneSheepTileTest {
    private LoneSheepTile tile;
    private Player player;
    private GameBoard board;
    private UserInput userInput;
    private Tile randomTile;

    @BeforeEach
    public void setUp() {
        tile = new LoneSheepTile();
        player = new Player("Adil", "blue");
        board = new GameBoard();
        userInput = null;
        randomTile = new ActionHeroTile();
        board.placeTile(1, new TileController(tile, null));
        board.placeTile(2, new TileController(randomTile, null));
        tile.placeZzzs(1, false);
    }

    @Test
    public void testActivateEffectWithNoAdjacentZzzs() {
        tile.activateEffect(player, board, userInput);
        assertEquals(3, player.getWinks(), "Player should gain 3 winks");
    }
    @Test
    public void testActivateEffectWithAdjacentZzzs() {
        randomTile.placeZzzs(1, false);
        tile.activateEffect(player, board, userInput);
        assertEquals(0, player.getWinks(), "Player should not gain any winks");
    }

}
package TileTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import models.tiles.IntenseDreamsTile;
import models.Player;
import models.GameBoard;
import views.UserInput;

public class IntenseDreamsTileTest {

    private IntenseDreamsTile intenseDreamsTile;
    private Player player;
    private GameBoard board;
    private UserInput userInput;

    @BeforeEach
    public void setUp() {
        intenseDreamsTile = new IntenseDreamsTile();
        player = new Player("Adil", "blue");
        board = null;
        userInput = null;
        intenseDreamsTile.placeZzzs(1, false);
    }

    @Test
    public void testActivateEffectPlayerIsNotScared() {
        intenseDreamsTile.activateEffect(player, board, userInput);
        assertTrue(player.isScared(), "Player should be scared");
        assertEquals(4, player.getWinks(), "Player should gain 4 winks");
    }

    @Test
    public void testActivateEffectPlayerIsScared() {
        player.becomeScared();
        intenseDreamsTile.activateEffect(player, board, userInput);
        assertEquals(0, player.getWinks(), "Player should not gain if they are already scared");
    }
}
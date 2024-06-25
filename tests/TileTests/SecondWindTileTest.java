package TileTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import models.tiles.SecondWindTile;
import models.Player;
import models.cards.MoveSpacesCard;
import models.GameBoard;
import views.UserInput;

public class SecondWindTileTest {

    private SecondWindTile tile;
    private Player player;
    private GameBoard board;
    private UserInput userInput;

    @BeforeEach
    public void setUp() {
        tile = new SecondWindTile();
        player = new Player("Adil", "blue");
        board = null;
        userInput = null;
        tile.placeZzzs(1, false);
    }
    @Test
    public void testActivateEffectNotScared() {
        player.gainCard(new MoveSpacesCard(1));
        tile.activateEffect(player, board, userInput);
        assertNull(player.getCard(0));
        assertFalse(player.isScared());
    }
    @Test
    public void testActivateEffectScared() {
        player.gainCard(new MoveSpacesCard(1));
        player.becomeScared();
        tile.activateEffect(player, board, userInput);
        assertNull(player.getCard(0));
        assertFalse(player.isScared());
    }


}
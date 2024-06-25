package TileTests;

import models.GameBoard;
import models.Player;
import models.cards.MoveSpacesCard;
import models.tiles.DoubleDutchTile;
import views.UserInput;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import models.Card;

public class DoubleDutchTileTest {

    private Player player;
    private DoubleDutchTile doubleDutchTile;
    private GameBoard board; 
    private UserInput userInput;
    private Card primaryCard, secondaryCard;

    @Before
    public void setUp() {
        doubleDutchTile = new DoubleDutchTile();
        board = new GameBoard(); 
        userInput = null; 

        primaryCard = new MoveSpacesCard(3);
        secondaryCard = new MoveSpacesCard(2); 
        player = new Player("Adil", "blue");
        board.placeMovable(player, 1);
        player.gainCard(primaryCard);
        player.gainCard(secondaryCard);
    }

    @Test
    public void testActivateEffectWithZZZs() {
        player.playCard(0).executeAction(player, board, userInput);
        assertEquals("Player should move 3 spaces", 4, board.getMovablePosition(player));
        doubleDutchTile.placeZzzs(1, false);
        doubleDutchTile.activateEffect(player, board, userInput);

        assertEquals("Player should move by 2 spaces", 6, board.getMovablePosition(player));
    }

    @Test 
    public void testActivateEffectPlayerHasNoZzzs() {
        player.playCard(0).executeAction(player, board, userInput);
        assertEquals("Player should move 3 spaces", 4, board.getMovablePosition(player));
        doubleDutchTile.activateEffect(player, board, userInput);
        assertEquals("Player should not move as they have no zzzs on the tile", 4, board.getMovablePosition(player));
    }

}
package CardTests;

import models.cards.MoveNightmareCard;
import models.GameBoard;
import models.Nightmare;
import models.Player;
import models.Wolf;
import views.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoveNightmareCardTest {

    private MoveNightmareCard moveNightmareCard;
    private Player player;
    private GameBoard gameBoard;
    private UserInput input;
    private Nightmare nightmare;

    @BeforeEach
    void setUp() {
        int spaces = 1;
        moveNightmareCard = new MoveNightmareCard(spaces);
        player = new Player("Adil","blue"); 
        gameBoard = new GameBoard();
        nightmare = new Wolf();
        gameBoard.addNightmareToBoard(nightmare);
        gameBoard.placeMovable(player, 1);
    }

    @Test
    void testGetInformation() {
        String expectedInformation = "The nightmare moves 1 spaces forward.";
        assertEquals(expectedInformation, moveNightmareCard.getInformation(), "The information should be the expected information");
    }

    @Test
    void testExecuteAction() {
        moveNightmareCard.executeAction(player, gameBoard, input);
        assertEquals(1, gameBoard.getNightmarePos(), "The nightmare should be at position 1");
        assertTrue(player.isScared(), "The player should be scared");
    }
    @Test
    void testExecuteAction2() {
        moveNightmareCard = new MoveNightmareCard(2);
        moveNightmareCard.executeAction(player, gameBoard, input);
        assertEquals(2, gameBoard.getNightmarePos(), "The nightmare should be at position 1");
        assertTrue(player.isScared(), "The player should be scared when a nightmare moves past them");
    }

    @Test
    void testIsNightmare() {
        assertTrue(moveNightmareCard.isNightmare(), "The card should be a nightmare");
    }
}
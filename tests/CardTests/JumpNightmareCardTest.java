package CardTests;

import models.cards.JumpNightmareCard;
import models.GameBoard;
import models.Nightmare;
import models.Player;
import models.Wolf;
import views.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JumpNightmareCardTest {

    private JumpNightmareCard jumpNightmareCard;
    private Player player;
    private GameBoard gameBoard;
    private UserInput input;
    private Nightmare nightmare;

    @BeforeEach
    void setUp() {
        int spaces = 1;
        jumpNightmareCard = new JumpNightmareCard(spaces);
        player = new Player("Adil","blue"); 
        gameBoard = new GameBoard();
        nightmare = new Wolf();
        gameBoard.addNightmareToBoard(nightmare);
        gameBoard.placeMovable(player, 1);
    }

    @Test
    void testGetInformation() {
        String expectedInformation = "The nightmare jumps 1 spaces forward.";
        assertEquals(expectedInformation, jumpNightmareCard.getInformation(), "The information should be the expected information");
    }

    @Test
    void testExecuteAction() {
        jumpNightmareCard.executeAction(player, gameBoard, input);
        assertEquals(1, gameBoard.getNightmarePos(), "The nightmare should be at position 1");
        assertTrue(player.isScared(), "The player should be scared");
    }
    @Test
    void testExecuteAction2() {
        jumpNightmareCard = new JumpNightmareCard(2);
        jumpNightmareCard.executeAction(player, gameBoard, input);
        assertEquals(2, gameBoard.getNightmarePos(), "The nightmare should be at position 1");
        assertFalse(player.isScared(), "The player should not be scared");
    }

    @Test
    void testIsNightmare() {
        assertTrue(jumpNightmareCard.isNightmare(), "The card should be a nightmare");
    }
}
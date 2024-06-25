package CardTests;

import models.cards.GainWinksCard;
import models.GameBoard;
import models.Player;
import views.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GainWinksCardTest {

    private GainWinksCard gainWinksCard;
    private Player player;
    private GameBoard gameBoard;
    private UserInput input;

    @BeforeEach
    void setUp() {
        int winks = 1;
        gainWinksCard = new GainWinksCard(winks);
        player = new Player("Adil","blue");
        gameBoard = new GameBoard(); 
        input = new UserInput(); 
    }

    @Test
    void testGetInformation() {
        String expectedInformation = "Gain one wink";
        assertEquals(expectedInformation, gainWinksCard.getInformation(), "The information should be the expected information");
    }

    @Test
    void testExecuteAction() {
        gainWinksCard.executeAction(player, gameBoard, input);
        assertEquals(1, player.getWinks(), "The player should have 1 wink");
    }

    @Test
    void testIsNightmare() {
        assertFalse(gainWinksCard.isNightmare(), "The card should not be a nightmare");
    }
}
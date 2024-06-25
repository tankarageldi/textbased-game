package CardTests;

import models.cards.ScareNightmareCard;
import models.GameBoard;
import models.Player;
import models.Wolf;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScareNightmareCardTest {

    @Test
    public void testExecuteAction() {
        ScareNightmareCard card = new ScareNightmareCard();
        GameBoard gameBoard = new GameBoard();
        Player player = new Player("Adil", "blue");
        gameBoard.addNightmareToBoard(new Wolf());
        gameBoard.placeMovable(player, 1);
        card.executeAction(player, gameBoard, null);
        assertTrue(player.isScared());
    }

    @Test
    public void testGetInformation() {
        ScareNightmareCard card = new ScareNightmareCard();
        String info = "The nightmare scares all sheep on its space and adjacent spaces.";
        assertEquals(info, card.getInformation());
    }

    @Test
    public void testIsNightmare() {
        ScareNightmareCard card = new ScareNightmareCard();
        assertTrue(card.isNightmare());
    }
}
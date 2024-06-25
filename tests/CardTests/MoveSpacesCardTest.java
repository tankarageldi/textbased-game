package CardTests;

import models.cards.MoveSpacesCard;
import models.GameBoard;
import models.Player;
import views.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoveSpacesCardTest {

    private MoveSpacesCard moveSpacesCard;
    private Player player;
    private GameBoard gameBoard;
    private UserInput input;

    @BeforeEach
    void setUp() {
        int spaces = 1;
        moveSpacesCard = new MoveSpacesCard(spaces);
        player = new Player("tolga","blue"); 
        gameBoard = new GameBoard(); 
        gameBoard.placeMovable(player, 1);
        input = new UserInput(); 
    }

    @Test
    void testGetInformation() {
        String expectedInformation = "Move one space";
        assertEquals(expectedInformation, moveSpacesCard.getInformation(), "The information should be the expected information");
    }

    @Test
    void testExecuteAction() {
        assertEquals(1, gameBoard.getMovablePosition(player));
        moveSpacesCard.executeAction(player, gameBoard, input);
        assertEquals(2, gameBoard.getMovablePosition(player));
    }

    @Test
    void testExecuteAction2() {
        Player player = new Player("Adil","blue");
        GameBoard gameBoard = new GameBoard();
        gameBoard.placeMovable(player, 1);
        moveSpacesCard.executeAction(player, gameBoard, input);
        assertEquals(2, gameBoard.getMovablePosition(player), "Player should move to the next space.");
    }
    @Test
    void testExecuteAction3() {
        Player player = new Player("Adil","blue");
        GameBoard gameBoard = new GameBoard();
        gameBoard.placeMovable(player, 10);
        moveSpacesCard.executeAction(player, gameBoard, input);
        assertEquals(1, gameBoard.getMovablePosition(player), "Player should wrap around to the first space.");
    }
    @Test
    void testExecuteAction4() {
        Player player = new Player("Adil","blue");
        GameBoard gameBoard = new GameBoard();
        gameBoard.placeMovable(player, 1);
        MoveSpacesCard moveSpacesCard2 = new MoveSpacesCard(-1);
        moveSpacesCard2.executeAction(player, gameBoard, input);
        assertEquals(1, gameBoard.getMovablePosition(player), "Player should not cross the fence backwards.");
    }

    @Test
    void testIsNightmare() {
        assertFalse(moveSpacesCard.isNightmare(), "The card should not be a nightmare");
    }
}
package ModelTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import controllers.GameBoardController;
import models.GameBoardCreator;

public class GameBoardCreatorTest {

    @Test
    public void testInitializeGameBoard() {
        GameBoardController gameBoardController = GameBoardCreator.initializeGameBoard();
        assertNotNull(gameBoardController);
        assertNotNull(gameBoardController.getModel());
    }
}
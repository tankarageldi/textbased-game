package ModelTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import controllers.PlayerController;
import models.Player;
import models.ScoreBoardCreator;
import views.PlayerView;
import controllers.ScoreBoardController;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardCreatorTest {

    @Test
    public void testInitializeScoreBoard() {
        List<PlayerController> playerControllers = new ArrayList<>();
        Player player = new Player("Adil", "blue");
        playerControllers.add(new PlayerController(player, new PlayerView()));
        ScoreBoardController scoreBoardController = ScoreBoardCreator.initializeScoreBoard(playerControllers);
        assertNotNull(scoreBoardController);
        scoreBoardController.gainWinks(player, 2);
        assertEquals(2, player.getWinks());
    }

}
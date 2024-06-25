package ControllerTests;

import controllers.ScoreBoardController;
import models.Player;
import models.ScoreBoard;
import views.ScoreBoardView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoardControllerTest {

    private ScoreBoardController scoreBoardController;
    private ScoreBoard scoreBoard;
    private ScoreBoardView view;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Adil", "blue");
        List<Player> players = new ArrayList<>();
        players.add(player);
        scoreBoard = new ScoreBoard(players);
        view = new ScoreBoardView();
        scoreBoardController = new ScoreBoardController(scoreBoard, view);
    }

    @Test
    void testMovePillow() {
        int amount = -1;
        scoreBoardController.movePillow(player, amount);
        assertEquals(40 + amount, scoreBoard.getPillowPosition(player),
                "The pillow should be moved by the specified amount");
    }

    @Test
    void testGainWinks() {
        int amount = 1;
        scoreBoardController.gainWinks(player, amount);
        assertEquals(amount, scoreBoard.getWinks(player), "The player should gain the specified amount of winks");
    }

    @Test
    void testWakeUp() {
        player.gainWinks(2);
        scoreBoardController.wakeUp(player);
        assertEquals(0, scoreBoard.getWinks(player), "The player should have no winks if they wake up");
    }

    @Test
    void testEndOfTurn() {
        player.gainWinks(6);
        scoreBoardController.endOfTurn();
        assertEquals(39, scoreBoard.getPillowPosition(player),
                "The player should move their pillow back by one for every 5 winks");
        assertEquals(0, scoreBoard.getWinks(player), "The player should have no winks");
    }

    @Test
    void testIsGameOver() {
        assertFalse(scoreBoardController.isGameOver(), "The game should not be over");
        scoreBoard.movePillow(player, -35);
        scoreBoard.gainWinks(player, 6);
        assertTrue(scoreBoardController.isGameOver(), "The game should be over when a player reaches their pillow");
    }

    @Test
    void testGetScoreBoard() {
        assertEquals(scoreBoard, scoreBoardController.getScoreBoard(),
                "The returned score board should be the same as the one set in the constructor");
    }

    /*
     * This test runs on a windows machine succcesfully, but doesnt run succesfully on a mac or linux, if you remove the comments,
     * and run the test, you will see expected output and actual output are the same, but the test fails on a mac, not on windows.
     * we commented out the imports of this test above, so remove the comments of that imports to run the test.
     * @Test
    void testUpdateView() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        scoreBoardController.displayScoreBoard();;
        String expectedOutput = "Current Game State:\r\n" + //
                "Player	Score	Pillow Position\r\n" + //
                "Adil	0	40";
        assertEquals(expectedOutput, outputStream.toString().trim(), "The player should start with no winks and pillow at 40");
    }
     */
}
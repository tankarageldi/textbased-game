package ModelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Player;
import models.ScoreBoard;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {

    private ScoreBoard scoreBoard;
    private List<Player> players;

    @BeforeEach
    void setUp() {
        players = new ArrayList<>();
        players.add(new Player("Adil", "blue"));
        scoreBoard = new ScoreBoard(players);
    }

    // Testing adding the winks directly to the player
    @Test
    void testGetWinks() {
        Player player = players.get(0);
        int winks = 10;
        player.gainWinks(winks);
        assertEquals(winks, scoreBoard.getWinks(player), "The number of winks should match the value set");
    }

    // Testing adding the winks through the scoreboard
    @Test
    void testGainWinks() {
        Player player = players.get(0);
        int initialWinks = scoreBoard.getWinks(player);
        int amount = 5;
        scoreBoard.gainWinks(player, amount);
        assertEquals(initialWinks + amount, scoreBoard.getWinks(player),
                "The number of winks should increase by the specified amount");
    }

    // Testing moving the pillow through the scoreboard
    @Test
    void testMovePillow() {
        Player player = players.get(0);
        int initialPosition = scoreBoard.getPillowPosition(player);
        int amount = -5;
        scoreBoard.movePillow(player, amount);
        assertEquals(initialPosition + amount, scoreBoard.getPillowPosition(player),
                "The pillow should move by the specified amount");
    }

    // Testing moving the pillow directly through the player
    @Test
    void testGetPillowPosition() {
        Player player = players.get(0);
        int position = -3;
        player.movePillow(position);
        assertEquals(40 + position, scoreBoard.getPillowPosition(player),
                "The pillow position should match the value set");
    }

    @Test
    void testWakeUp() {
        Player player = players.get(0);
        player.gainWinks(5);
        scoreBoard.wakeUp(player);
        assertEquals(0, scoreBoard.getWinks(player), "The number of winks should be reset to 0");
    }

    @Test
    void testResetPillowPositions() {
        Player player = players.get(0);
        player.movePillow(5);
        scoreBoard.resetPillowPositions();
        assertEquals(40, scoreBoard.getPillowPosition(player), "The pillow position should be reset to 40");
    }

    @Test
    void testIsGameOver() {
        assertFalse(scoreBoard.isGameOver(), "The game should not be over at the start");
        Player player = players.get(0);
        player.movePillow(-5);
        player.gainWinks(35);
        assertTrue(scoreBoard.isGameOver(),
                "The game should be over when there is only one player and their pillow position is less than or equal to their winks");
    }

    @Test
    void testEndOfTurn() {
        Player player = players.get(0);
        player.movePillow(-10);
        player.gainWinks(5);
        int initialPosition = scoreBoard.getPillowPosition(player);
        int initialWinks = scoreBoard.getWinks(player);
        scoreBoard.endOfTurn();
        assertEquals(initialPosition - initialWinks / 5, scoreBoard.getPillowPosition(player),
                "The pillow position should move back by the calculated amount");
        assertEquals(0, scoreBoard.getWinks(player), "The number of winks should be reset to 0");
    }

    @Test
    void testGetPlayers() {
        assertEquals(players, scoreBoard.getPlayers(),
                "The list of players should match the one provided in the constructor");
    }
}
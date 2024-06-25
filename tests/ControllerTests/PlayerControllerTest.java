package ControllerTests;

import controllers.PlayerController;
import models.Player;
import models.cards.MoveSpacesCard;
import models.Card;
import views.PlayerView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerControllerTest {

    private PlayerController playerController;
    private Player player;
    private PlayerView view;

    @BeforeEach
    void setUp() {
        player = new Player("Adil", "blue"); 
        view = new PlayerView(); 
        playerController = new PlayerController(player, view);
    }

    @Test
    void testUpdateView() {
        assertDoesNotThrow(() -> playerController.updateView());
    }

    @Test
    void testGetPlayerName() {
        String expectedName = "Adil";
        assertEquals(expectedName, playerController.getPlayerName(), "The name should be Tolga");
    }

    @Test
    void testGetSheep() {
        assertEquals("blue", playerController.getSheep().getColor(), "The sheep should be blue");
    }

    @Test
    void testGetNumOfZzzs() {
        int expectedNumOfZzzs = 10;
        assertEquals(expectedNumOfZzzs, playerController.getNumOfZzzs(), "The number of Zzzs should be 10");
        playerController.catchZZZs(3);
        expectedNumOfZzzs -= 3;
        assertEquals(expectedNumOfZzzs, playerController.getNumOfZzzs(), "The number of Zzzs should be 13");
    }

    @Test
    void testGainCard() {
        Card card = new MoveSpacesCard(1);
        playerController.gainCard(card);

        assertEquals(card, player.getCard(0), "The player should have the card that was gained");
    }

    @Test
    void testNeedsACard() {
        assertTrue(playerController.needsACard(), "The player should need a card if the hand is empty");
    }

    @Test
    void testPlayCard() {
        Card card = new MoveSpacesCard(1); 
        player.gainCard(card);
        assertEquals(card, playerController.playCard(0), "The played card should be the card that was gained");
        assertNull(player.getCard(0), "The card should be removed from the player's hand");
    }

    @Test
    void testCatchZZZs() {
        int amount = 1;
        assertEquals(amount, playerController.catchZZZs(amount), "The player should catch the specified amount of Zzzs");
        assertEquals(9, player.getNumOfZzzs(), "The number of Zzzs should be 9");
        assertEquals(9, playerController.catchZZZs(10), "The player can only catch it's remaining Zzzs");
    }

    @Test
    void testGetModel() {
        assertEquals(player, playerController.getModel(), "The returned model should be the same as the one set in the constructor");
    }
}
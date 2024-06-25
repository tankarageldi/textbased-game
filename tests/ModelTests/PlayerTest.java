package ModelTests;

import models.Player;
import models.Sheep;
import models.cards.MoveSpacesCard;
import models.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Adil", "yellow");
    }

    @Test
    void testGetName() {
        assertEquals("Adil", player.getName(), "The name should correctly be 'Adil'");
    }

    @Test
    void testGetSheep() {
        Sheep sheep = player.getSheep();
        assertNotNull(sheep, "The sheep object should not be null");
        assertEquals("yellow", sheep.getColor(), "The sheep color should correctly be 'yellow'");
    }

    @Test
    void testGetCard() {
        assertNull(player.getCard(0), "The card at index 0 should be null initially");
        assertNull(player.getCard(1), "The card at index 1 should be null initially");
        Card card1 = new MoveSpacesCard(2);
        Card card2 = new MoveSpacesCard(3);
        player.gainCard(card1);
        player.gainCard(card2);
        assertEquals(card1, player.getCard(0), "The card at index 0 should be 'Card 1'");
        assertEquals(card2, player.getCard(1), "The card at index 1 should be 'Card 2'");
        assertThrows(IllegalArgumentException.class, () -> player.getCard(2), "2 is invalid index");
        assertThrows(IllegalArgumentException.class, () -> player.getCard(-1), "-1 is invalid index");
    }

    @Test
    void testGetNumOfZzzs() {
        assertEquals(10, player.getNumOfZzzs(), "The number of Zzzs should correctly be 10");
    }

    @Test
    void testGainCard() {
        Card card = new MoveSpacesCard(1);
        player.gainCard(card);
        assertEquals(card, player.getCard(0), "The gained card should be at index 0");
        Card card2 = new MoveSpacesCard(2);
        player.gainCard(card2);
        assertEquals(card2, player.getCard(1), "The gained card should be at index 1");
        Card card3 = new MoveSpacesCard(3);
        assertThrows(IllegalStateException.class, () -> player.gainCard(card3),
                "The player should not be able to gain more than 2 cards");
    }

    @Test
    void testGainWinks() {
        player.gainWinks(3);
        assertEquals(3, player.getWinks(), "The winks count should be increased to 3");
    }

    @Test
    void testResetWinks() {
        player.gainWinks(5);
        player.resetWinks();
        assertEquals(0, player.getWinks(), "The winks count should be reset to 0");
    }

    @Test
    void testPlayCard() {
        Card card = new MoveSpacesCard(1);
        player.gainCard(card);
        Card playedCard = player.playCard(0);
        assertEquals(card, playedCard, "The played card should be the same as the gained card");
        assertNull(player.getCard(0), "The card at index 0 should be null after playing");
        assertThrows(IllegalArgumentException.class, () -> player.playCard(2), "2 is invalid index");
        assertThrows(IllegalArgumentException.class, () -> player.playCard(-1), "-1 is invalid index");
    }

    @Test
    void testBecomeScared() {
        player.becomeScared();
        assertTrue(player.isScared(), "The player should be scared after becoming scared");
    }

    @Test
    void testIsScared() {
        assertFalse(player.isScared(), "The player should not be scared initially");
        player.becomeScared();
        assertTrue(player.isScared(), "The player should be scared after becoming scared");
    }

    @Test
    void testBecomeBrave() {
        player.becomeScared();
        player.becomeBrave();
        assertFalse(player.isScared(), "The player should not be scared after becoming brave");
    }

    @Test
    void testDiscardHand() {
        Card card1 = new MoveSpacesCard(2);
        Card card2 = new MoveSpacesCard(3);
        player.gainCard(card1);
        player.gainCard(card2);
        player.discardHand();
        assertNull(player.getCard(0), "The card at index 0 should be null after discarding");
        assertNull(player.getCard(1), "The card at index 1 should be null after discarding");
    }

    @Test
    void testCrossFence() {
        player.crossFence();
        assertTrue(player.hasCrossedFence(), "The player should have crossed the fence");
    }

    @Test
    void testResetFence() {
        player.crossFence();
        player.resetFence();
        assertFalse(player.hasCrossedFence(), "The player should not have crossed the fence after resetting");
    }

    @Test
    void testGetOtherCardToPlay() {
        Card card1 = new MoveSpacesCard(2);
        Card card2 = new MoveSpacesCard(3);
        player.gainCard(card1);
        player.gainCard(card2);
        player.playCard(0);
        assertEquals(card2, player.getOtherCardToPlay(),
                "The other card to play should be 'Card 2' after playing the first card");
        assertNull(player.getCard(1), "The card at index 1 should be null after playing it");
        player.gainCard(card1);
        player.gainCard(card2);
        player.playCard(1);
        assertEquals(card1, player.getOtherCardToPlay(),
                "The other card to play should be 'Card 1' after playing the second card");

    }

    @Test
    void testMovePillow() {
        player.movePillow(-5);
        assertEquals(35, player.getPillowPosition(), "The pillow should move down by 5");
    }

    @Test
    void testResetPillowPosition() {
        player.movePillow(10);
        player.resetPillowPosition();
        assertEquals(40, player.getPillowPosition(), "The pillow position should be reset to 40");
    }

    @Test
    void testCatchZZZs() {
        assertEquals(5, player.catchZZZs(5), "The player should catch 5 Zzzs");
        assertEquals(5, player.getNumOfZzzs(), "The number of Zzzs should be decreased to 5");
        assertEquals(5, player.catchZZZs(10), "The player should catch the remaining 5 Zzzs");
        assertEquals(0, player.getNumOfZzzs(), "The number of Zzzs should be decreased to 0");
        assertEquals(0, player.catchZZZs(5), "The player should not catch any Zzzs");
    }

    @Test
    void testNeedsACard() {
        assertTrue(player.needsACard(), "The player should initially need a card");
        player.gainCard(new MoveSpacesCard(1));
        assertTrue(player.needsACard(), "The player should need a card");
        player.gainCard(new MoveSpacesCard(1));
        assertFalse(player.needsACard(), "The player should not need a card after gaining two");
    }

}
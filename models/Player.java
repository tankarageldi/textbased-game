package models;

import java.util.Arrays;

/**
 * Represents a player in the SheepyTime game.
 * Each player has a name, a sheep, a hand of cards, and various attributes such as zzzs, winks, and pillow position.
 * Players can perform actions such as gaining cards, playing cards, moving the pillow, catching zzzs, and crossing the fence.
 * Players can also check their current state, such as whether they are scared or if they need a card.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class Player implements Movable {

    private String name;
    private Sheep sheep;
    private Card[] hand;
    private int zzzs;
    private int winks;
    private int pillowPosition;
    private boolean crossedTheFence;

    /**
     * Constructs a new Player object with the specified name and sheep color.
     *
     * @param name        the name of the player
     * @param sheepColor  the color of the player's sheep
     */
    public Player(String name, String sheepColor) {
        this.name = name;
        this.sheep = new Sheep(sheepColor);
        hand = new Card[2];
        zzzs = 10;
        winks = 0;
        pillowPosition = 40;
        crossedTheFence = false;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the sheep owned by the player.
     *
     * @return the sheep owned by the player
     */
    public Sheep getSheep() {
        return sheep;
    }

    /**
     * Returns the card at the specified index in the player's hand.
     *
     * @param index  the index of the card
     * @return the card at the specified index
     */
    public Card getCard(int index) {
        if (index < 0 || index > 1) {
            throw new IllegalArgumentException("Invalid index. Only 0 and 1 are valid arguments.");
        }
        return hand[index];
    }

    /**
     * Returns the number of zzzs the player has.
     *
     * @return the number of zzzs the player has
     */
    public int getNumOfZzzs() {
        return zzzs;
    }

    /**
     * Adds a new card to the player's hand.
     *
     * @param newCard  the new card to be added
     * @throws IllegalStateException if the player's hand is full
     */
    public void gainCard(Card newCard) {
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == null) {
                hand[i] = newCard;
                return;
            }
        }
        throw new IllegalStateException("Hand is full");
    }

    /**
     * Resets the number of winks to zero.
     */
    public void resetWinks() {
        winks = 0;
    }

    /**
     * Plays the card at the specified index in the player's hand.
     * Returns the played card.
     *
     * @param index  the index of the card to be played
     * @return the played card
     */
    public Card playCard(int index) {
        if (index < 0 || index > 1) {
            throw new IllegalArgumentException("Invalid index. Only 0 and 1 are valid arguments.");
        }
        Card playerCard = hand[index];
        hand[index] = null;
        return playerCard;
    }

    /**
     * Checks if the player's sheep is scared.
     *
     * @return true if the sheep is scared, false otherwise
     */
    public boolean isScared() {
        return sheep.isScared();
    }

    /**
     * Makes the player's sheep become brave.
     */
    public void becomeBrave() {
        sheep.becomeBrave();
    }

    /**
     * Increases the number of winks by the specified amount.
     *
     * @param amount  the amount to increase the winks by
     */
    public void gainWinks(int amount) {
        winks += amount;
    }

    /**
     * Returns the number of winks the player has.
     *
     * @return the number of winks the player has
     */
    public int getWinks() {
        return winks;
    }

    /**
     * Discards all cards in the player's hand.
     */
    public void discardHand() {
        Arrays.fill(hand, null);
    }

    /**
     * Resets the crossedTheFence attribute to false.
     */
    public void resetFence() {
        crossedTheFence = false;
    }

    /**
     * Checks if the player has crossed the fence.
     *
     * @return true if the player has crossed the fence, false otherwise
     */
    public boolean hasCrossedFence() {
        return crossedTheFence;
    }

    /**
     * Returns the other card in the player's hand that can be played.
     * If the first card is null, returns the second card.
     * If the second card is null, returns the first card.
     *
     * @return the other card to play
     */
    public Card getOtherCardToPlay() {
        if (hand[0] != null) {
            Card temp = hand[0];
            hand[0] = null;
            return temp;
        } else {
            Card temp = hand[1];
            hand[1] = null;
            return temp;
        }
    }

    /**
     * Moves the pillow by the specified amount.
     *
     * @param amount  the amount to move the pillow by
     */
    public void movePillow(int amount) {
        pillowPosition += amount;
    }

    /**
     * Returns the current position of the pillow.
     *
     * @return the current position of the pillow
     */
    public int getPillowPosition() {
        return pillowPosition;
    }

    /**
     * Resets the position of the pillow to the default position.
     */
    public void resetPillowPosition() {
        pillowPosition = 40;
    }

    /**
     * Catches the specified amount of zzzs.
     * If the amount is greater than the number of zzzs the player has,
     * catches all the remaining zzzs and returns the amount caught.
     * Otherwise, catches the specified amount of zzzs and returns the same amount.
     *
     * @param amount  the amount of zzzs to catch
     * @return the amount of zzzs caught
     */
    public int catchZZZs(int amount) {
        if (amount > zzzs) {
            int temp = zzzs;
            zzzs = 0;
            return temp;
        }
        zzzs -= amount;
        return amount;
    }

    /**
     * Checks if the player needs a card.
     *
     * @return true if the player needs a card, false otherwise
     */
    public boolean needsACard() {
        return hand[0] == null || hand[1] == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void crossFence() {
        crossedTheFence = true;
        winks += 5;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void becomeScared() {
        sheep.scare();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void wakeUp() {
        winks = 0;
    }
}

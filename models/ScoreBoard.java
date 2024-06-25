package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a scoreboard that keeps track of players' scores and game progress.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class ScoreBoard {
    private List<Player> players;

    /**
     * Constructs a ScoreBoard object with the given list of players.
     *
     * @param players the list of players to initialize the scoreboard with
     */
    public ScoreBoard(List<Player> players) {
        this.players = new ArrayList<>(players);
        resetPillowPositions();
    }

    /**
     * Moves the pillow of the specified player by the given amount.
     *
     * @param player the player whose pillow to move
     * @param amount the amount to move the pillow by
     */
    public void movePillow(Player player, int amount) {
        player.movePillow(amount);
    }

    /**
     * Returns the number of winks of the specified player.
     *
     * @param player the player to get the number of winks for
     * @return the number of winks of the player
     */
    public int getWinks(Player player) {
        return player.getWinks();
    }

    /**
     * Increases the number of winks of the specified player by the given amount.
     *
     * @param player the player to increase the number of winks for
     * @param amount the amount to increase the number of winks by
     */
    public void gainWinks(Player player, int amount) {
        player.gainWinks(amount);
    }

    /**
     * Returns the position of the pillow of the specified player.
     *
     * @param player the player to get the pillow position for
     * @return the position of the player's pillow
     */
    public int getPillowPosition(Player player) {
        return player.getPillowPosition();
    }

    /**
     * Resets the number of winks of the specified player to zero.
     *
     * @param player the player to reset the number of winks for
     */
    public void wakeUp(Player player) {
        player.resetWinks();
    }

    /**
     * Resets the positions of all players' pillows to their initial positions.
     */
    public void resetPillowPositions() {
        for (Player player : players) {
            player.resetPillowPosition();
        }
    }

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        if (players.size() == 1) {
            Player player = players.get(0);
            return getPillowPosition(player) <= getWinks(player);
        }
        return false; // logic to be added for multiplayer
    }

    /**
     * Performs end-of-turn actions for a single player game.
     * Moves the player's pillow based on the number of winks and resets the winks.
     */
    public void endOfTurn() {
        if (players.size() == 1) {
            Player player = players.get(0);
            int winks = getWinks(player);
            movePillow(player, -winks / 5);
            resetWinks();
            return;
        }
        // logic to be added for multiplayer
    }

    /**
     * Resets the number of winks for all players in the scoreboard.
     */
    private void resetWinks() {
        for (Player player : players) {
            player.resetWinks();
        }
    }

    /**
     * Returns the list of players in the scoreboard.
     *
     * @return the list of players
     */
    public List<Player> getPlayers() {
        return players;
    }
}

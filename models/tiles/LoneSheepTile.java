package models.tiles;

import models.GameBoard;
import models.Player;
import models.Tile;
import views.UserInput;


/**
 * Represents a Lone Sheep Tile in the game.
 * If the player has no zzzs on any other dream tiles within 2 spaces of this one,
 * they gain 3 winks.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class LoneSheepTile extends Tile {

    /**
     * Constructs a new LoneSheepTile object.
     * Initializes the tile with the name "Lone Sheep", the effect description,
     * the availability status, and the tile value.
     */
    public LoneSheepTile() {
        super("Lone Sheep", "If you have no zzzs on any other dream tiles within 2 spaces of this one, gain 3 winks.", true, 1);
    }

    /**
     * Activates the effect of the LoneSheepTile.
     * If the player has no adjacent zzzs on any other dream tiles within 2 spaces of this one,
     * they gain 3 winks.
     *
     * @param player    the player who activated the effect
     * @param board     the game board
     * @param userInput the user input
     */
    @Override
    public void activateEffect(Player player, GameBoard board, UserInput userInput) {
        boolean hasAdjacentZzzs = board.hasAdjacentZzzs(this);
        if (!hasAdjacentZzzs) {
            if (playerInfiniteZZZs > 0 || playerZZZs > 0) {
                player.gainWinks(3);
                if (playerInfiniteZZZs == 0) {
                    playerZZZs--;
                }
            }
        }
    }
}

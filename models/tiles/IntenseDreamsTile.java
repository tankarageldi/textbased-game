package models.tiles;

import models.GameBoard;
import models.Player;
import models.Tile;
import views.UserInput;

/**
 * Represents an Intense Dreams Tile in the game.
 * 
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class IntenseDreamsTile extends Tile {

    /**
     * Constructs a new IntenseDreamsTile object.
     */
    public IntenseDreamsTile() {
        super("Intense Dreams", "Become SCARED to gain 4 winks.", true, 1);
    }

    /**
     * Activates the effect of the IntenseDreamsTile on the player.
     * If the player has infinite ZZZs or ZZZs, the player becomes scared and gains
     * 4 winks.
     * If the player has no infinite ZZZs, the player's ZZZ count is decremented by
     * 1.
     *
     * @param player    The player who activated the tile effect.
     * @param board     The game board.
     * @param userInput The user input.
     */
    @Override
    public void activateEffect(Player player, GameBoard board, UserInput userInput) {
        if (player.isScared()) return;
        if (playerInfiniteZZZs > 0 || playerZZZs > 0) {
            player.becomeScared();
            player.gainWinks(4);
            if (playerInfiniteZZZs == 0) {
                playerZZZs--;
            }
        }
    }
}

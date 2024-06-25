package models.tiles;

import models.GameBoard;
import models.Player;
import models.Tile;
import views.UserInput;

/**
 * Represents an Action Hero tile in the game.
 * When activated, if the player is scared, they gain 3 winks.
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class ActionHeroTile extends Tile {

    /**
     * Constructs a new ActionHeroTile object.
     * Sets the name, effect description, availability, and position of the tile.
     */
    public ActionHeroTile() {
        super("Action Hero", "If you are SCARED, gain 3 winks", true, 1);
    }

    /**
     * Activates the effect of the Action Hero tile.
     * If the player is scared and has infinite ZZZs or ZZZs left, they gain 3 winks.
     * If the player has no infinite ZZZs left, one ZZZ is consumed.
     *
     * @param player    The player who activated the tile.
     * @param board     The game board.
     * @param userInput The user input.
     */
    @Override
    public void activateEffect(Player player, GameBoard board, UserInput userInput) {
        if (player.isScared()) {
            if (playerInfiniteZZZs > 0 || playerZZZs > 0) {
                player.gainWinks(3);
                if (playerInfiniteZZZs == 0) {
                    playerZZZs--;
                }
            }
        }
    }
}
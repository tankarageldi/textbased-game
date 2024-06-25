package models.tiles;
import models.GameBoard;
import models.Player;
import models.Tile;
import views.UserInput;

/**
 * Represents a Cool Kids Club tile in the game.
 * This tile allows the player to move their pillow down by 1.
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class CoolKidsClubTile extends Tile {

    /**
     * Constructs a CoolKidsClubTile object.
     * Initializes the tile with the name "Cool Kids Club", the effect description "Move your pillow down 1.",
     * the availability status as true, and the effect value as 1.
     */
    public CoolKidsClubTile() {
        super("Cool Kids Club", "Move your pillow down 1.", true, 1);
    }

    /**
     * Activates the effect of the Cool Kids Club tile.
     * If the player has infinite ZZZs or ZZZs, it moves the player's pillow down by 1.
     * If the player has no more infinite ZZZs, it decrements the player's ZZZs by 1.
     *
     * @param player    The player who activated the tile.
     * @param board     The game board.
     * @param userInput The user input.
     */
    @Override
    public void activateEffect(Player player, GameBoard board, UserInput userInput) {
        if (playerInfiniteZZZs > 0 || playerZZZs > 0) {
            player.movePillow(-1);
            if (playerInfiniteZZZs == 0) {
                playerZZZs--;
            }
        }
    }
}

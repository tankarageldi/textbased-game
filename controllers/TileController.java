package controllers;

import models.GameBoard;
import models.Player;
import models.Tile;
import views.TileView;
import views.UserInput;

/**
 * The TileController class is responsible for controlling the behavior of a Tile object.
 * It interacts with the TileView to display information about the Tile and activate its effects.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class TileController {
    private Tile tile;
    private TileView view;

    /**
     * Constructs a TileController object with the specified Tile and TileView.
     *
     * @param tile The Tile object to be controlled.
     * @param view The TileView object used to display information about the Tile.
     */
    public TileController(Tile tile, TileView view) {
        this.tile = tile;
        this.view = view;
    }

    /**
     * Displays information about the Tile using the TileView.
     */
    public void displayTileInfo() {
        view.displayInfo(tile.getName(),tile.getAbilityDescription(),tile.getZzzCount(),tile.isInfinite());
    }

    /**
     * Activates the effect of the Tile for the specified player, game board, and user input.
     *
     * @param player    The Player object who activated the Tile effect.
     * @param gameBoard The GameBoard object representing the game board.
     * @param userInput The UserInput object representing the user's input.
     */
    public void activateTileEffect(Player player, GameBoard gameBoard, UserInput userInput) {
        tile.activateEffect(player, gameBoard, userInput);
    }

    /**
     * Checks if the Tile has an infinite placement bonus.
     *
     * @return true if the Tile has an infinite placement bonus, false otherwise.
     */
    public boolean isInfinite() {
        return tile.isInfinite();
    }

    /**
     * Places the specified number of Zzzs on the Tile.
     *
     * @param zzzs       The number of Zzzs to place on the Tile.
     * @param isInfinite true if the Tile has an infinite effect, false otherwise.
     */
    public void placeZzzs(int zzzs, boolean isInfinite) {
        tile.placeZzzs(zzzs, isInfinite);
    }

    /**
     * Gets the name of the Tile.
     *
     * @return The name of the Tile.
     */
    public String getTileName() {
        return tile.getName();
    }

    /**
     * Checks if the Tile has Zzzs placed on it.
     *
     * @return true if the Tile has Zzzs placed on it, false otherwise.
     */
    public boolean hasZzzs() {
        return tile.hasZzzs();
    }

    /**
     * Gets the Tile model.
     *
     * @return The Tile object being controlled.
     */
    public Tile getModel() {
        return tile;
    }
}


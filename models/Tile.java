package models;

import views.UserInput;

/**
 * This abstract class represents a tile in the game.
 * Each tile has a name, ability description, and a count of ZZZs.
 * It also keeps track of the number of ZZZs owned by players.
 * Tiles can have infinite ZZZs or a limited number of ZZZs placed on them.
 * Subclasses of Tile must implement the activateEffect method.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public abstract class Tile {

    protected String name;
    protected String abilityDescription;
    protected boolean isInfinite;
    protected int zzzCount;
    protected int playerZZZs;
    protected int playerInfiniteZZZs;

    /**
     * Constructs a Tile object with the specified name, ability description,
     * infinite flag, and ZZZ count.
     *
     * @param name               the name of the tile
     * @param abilityDescription the description of the tile's ability
     * @param isInfinite         true if the tile has infinite ZZZs, false otherwise
     * @param zzzCount           the number of ZZZs the tile initially has
     */
    public Tile(String name, String abilityDescription, boolean isInfinite, int zzzCount) {
        this.name = name;
        this.abilityDescription = abilityDescription;
        this.isInfinite = isInfinite;
        this.zzzCount = zzzCount;
        this.playerZZZs = 0;
        this.playerInfiniteZZZs = 0;
    }

    /**
     * Returns the number of ZZZs the tile has.
     *
     * @return the number of ZZZs
     */
    public int getZzzCount() {
        return zzzCount;
    }

    /**
     * Returns the name of the tile.
     *
     * @return the name of the tile
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ability description of the tile.
     *
     * @return the ability description of the tile
     */
    public String getAbilityDescription() {
        return abilityDescription;
    }

    /**
     * Returns true if the tile has infinite ZZZs, false otherwise.
     *
     * @return true if the tile has infinite ZZZs, false otherwise
     */
    public boolean isInfinite() {
        return isInfinite;
    }

    /**
     * Places the specified number of ZZZs on the tile.
     * If the tile has infinite ZZZs, the ZZZs are added to the playerInfiniteZZZs count.
     * Otherwise, the ZZZs are added to the playerZZZs count.
     *
     * @param zzzs        the number of ZZZs to place
     * @param isInfinite  true if the ZZZs are infinite, false otherwise
     */
    public void placeZzzs(int zzzs, boolean isInfinite) {
        if (isInfinite) {
            playerInfiniteZZZs += zzzs;
        } else {
            playerZZZs += zzzs;
        }
    }

    /**
     * This method is called when the tile's effect is activated.
     * Subclasses must implement this method to define the specific effect of the tile.
     *
     * @param player    the player who activated the effect
     * @param board     the game board
     * @param userInput the user input for the effect
     */
    public abstract void activateEffect(Player player, GameBoard board, UserInput userInput);

    /**
     * Returns true if the tile has any ZZZs owned by players, false otherwise.
     *
     * @return true if the tile has any ZZZs owned by players, false otherwise
     */
    public boolean hasZzzs() {
        return playerZZZs + playerInfiniteZZZs != 0;
    }
}

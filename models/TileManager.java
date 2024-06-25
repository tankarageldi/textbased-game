package models;

import controllers.TileController;

/**
 * The TileManager class represents a manager for placing and retrieving tiles on a game board.
 * @author Adil Alimzhanov
 */
public class TileManager {
    private TileController[] tiles;
    private final int MAX_TILES = 10;

    /**
     * Constructs a new TileManager object.
     * Initializes the tiles array with a maximum capacity of 10.
     */
    public TileManager() {
        tiles = new TileController[MAX_TILES];
    }

    /**
     * Places a tile at the specified position on the game board.
     *
     * @param position The position where the tile should be placed. Must be between
     *                 1 and 10 (inclusive).
     * @param tile     The tile to be placed on the game board.
     * @throws IllegalArgumentException if the position is invalid or already
     *                                  occupied by a tile.
     */
    public void placeTile(int position, TileController tile) {

        if (!isValidPosition(position)) {
            throw new IllegalArgumentException("Invalid position");
        }
        if (isTilePlaced(position)) {
            throw new IllegalArgumentException("This position is already occupied by a tile.");
        }

        tiles[position - 1] = tile;
    }

    /**
     * Checks if a tile is placed at the specified position on the game board.
     *
     * @param position The position to check. Must be between 1 and 10 (inclusive).
     * @return true if a tile is placed at the specified position, false otherwise.
     * @throws IllegalArgumentException if the position is invalid.
     */
    public boolean isTilePlaced(int position) {
        if (!isValidPosition(position)) {
            throw new IllegalArgumentException("Invalid position");
        }
        return tiles[position - 1] != null;
    }

    /**
     * Retrieves the tile placed at the specified position on the game board.
     *
     * @param position The position of the tile to retrieve. Must be between 1 and 10 (inclusive).
     * @return The tile placed at the specified position, or null if no tile is placed.
     * @throws IllegalArgumentException if the position is invalid.
     */
    public TileController getTile(int position) {
        if (!isValidPosition(position)) {
            throw new IllegalArgumentException("Invalid position");
        }
        return tiles[position - 1];
    }

    /**
     * Retrieves the number of dream tiles currently placed on the game board.
     *
     * @return The number of dream tiles placed on the game board.
     */
    public int getNumOfDreamTiles() {
        int count = 0;
        for (TileController tile : tiles) {
            if (tile != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Places the top tile of the deck on the first available slot.
     * If there are no empty slots, the tile is not placed.
     *
     * @param tile the tile to be placed on the top of the game board
     */
    public void placeTopTile(TileController tile) {
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] == null) {
                tiles[i] = tile;
                return;
            }
        }
    }

    /**
     * Checks if the player has zzzs on any other dream tiles within 2 spaces of
     * this one.
     *
     * @param tile the tile to check for adjacent Zzzs
     * @return true if the tile has adjacent Zzzs, false otherwise
     */
    public boolean hasAdjacentZzzs(Tile tile) {
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] != null && tiles[i].getModel().equals(tile)) {
                if (hasZzzsAdjacent(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Retrieves all the tiles placed on the game board.
     *
     * @return An array of TileController objects representing the tiles placed on the game board.
     */
    public TileController[] getTiles() {
        return tiles;
    }

    // Helper method to check if a tile has Zzzs adjacent to it
    private boolean hasZzzsAdjacent(int index) {
        if (index > 0 && tiles[index - 1] != null && tiles[index - 1].hasZzzs()) {
            return true;
        }
        if (index < tiles.length - 1 && tiles[index + 1] != null && tiles[index + 1].hasZzzs()) {
            return true;
        }
        if (index > 1 && tiles[index - 2] != null && tiles[index - 2].hasZzzs()) {
            return true;
        }
        if (index < tiles.length - 2 && tiles[index + 2] != null && tiles[index + 2].hasZzzs()) {
            return true;
        }
        return false;
    }

    // Helper method to check if a position is valid
    private boolean isValidPosition(int position) {
        return position >= 1 && position <= 10;
    }
}

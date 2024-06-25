package models;

import java.util.Map;

import controllers.TileController;

/**
 * Represents the game board for the SheepyTime game.
 * This class manages the placement and movement of tiles, movables, and the
 * nightmare.
 * It also provides various methods to interact with the game board.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class GameBoard {
    private MovableManager movablesManager;
    private TileManager tilesManager;

    public GameBoard() {
        movablesManager = new MovableManager();
        tilesManager = new TileManager();
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
        tilesManager.placeTile(position, tile);
    }

    /**
     * Checks if a tile is placed at the specified position on the game board.
     *
     * @param position the position of the tile to check
     * @return true if a tile is placed at the specified position, false otherwise
     */
    public boolean isTilePlaced(int position) {
        return tilesManager.isTilePlaced(position);
    }

    /**
     * Represents a controller for a tile in the game board.
     */
    public TileController getTile(int position) {
        return tilesManager.getTile(position);
    }

    /**
     * Returns the number of dream tiles on the game board.
     *
     * @return the number of dream tiles
     */
    public int getNumOfDreamTiles() {
        return tilesManager.getNumOfDreamTiles();
    }

    /**
     * Places the top tile of the deck on the first available slot.
     * If there are no empty slots, the tile is not placed.
     *
     * @param tile the tile to be placed on the top of the game board
     */
    public void placeTopTile(TileController tile) {
        tilesManager.placeTopTile(tile);
    }

    /**
     * Places a movable object on the game board at the specified position.
     *
     * @param movable  The movable object to be placed on the game board.
     * @param position The position on the game board where the movable object
     *                 should be placed.
     */
    public void placeMovable(Movable movable, int position) {
        movablesManager.placeMovable(movable, position);
    }

    /**
     * Returns a map of movables and their corresponding positions.
     *
     * @return a map of movables and their corresponding positions
     */
    public Map<Movable, Integer> getMovables() {
        return movablesManager.getMovables();
    }

    /**
     * Moves the specified Movable object on the game board by the given amount.
     * 
     * @param movable The Movable object to be moved.
     * @param amount  The amount by which the Movable object should be moved.
     * @throws IllegalArgumentException if the Movable object is not on the board,
     *                                  or if the Movable object has called it a
     *                                  night,
     *                                  or if the Movable object has woken up.
     */
    public void moveMovable(Movable movable, int amount) {
        movablesManager.moveMovable(movable, amount);
    }

    /**
     * Scare all movables at the specified position.
     * If a movable is already scared, it will be woken up.
     * If a movable is not scared, it will become scared.
     *
     * @param pos the position to scare the movables at
     */
    public void scareMovablesAtPosition(int pos) {
        movablesManager.scareMovablesAtPosition(pos);
    }

    /**
     * Wakes up the specified movable on the game board.
     * If the movable is present on the game board, it is moved to the wake up
     * position
     * and its `wakeUp` method is called.
     *
     * @param movable the movable to wake up
     */
    public void wakeUpMovable(Movable movable) {
        movablesManager.wakeUpMovable(movable);
    }

    /**
     * Checks if the specified movable object is awake on the game board.
     *
     * @param movable the movable object to check
     * @return true if the movable object is awake, false otherwise
     */
    public boolean isAwake(Movable movable) {
        return movablesManager.isAwake(movable);
    }

    /**
     * Wakes up all the movable objects on the game board and sets their positions
     * to the wake-up position.
     */
    public void wakeEveryone() {
        movablesManager.wakeEveryone();
    }

    /**
     * Moves the specified movable object to the "call it a night" position on the
     * game board.
     *
     * @param movable the movable object to be moved
     */
    public void callItANight(Movable movable) {
        movablesManager.callItANight(movable);
    }

    /**
     * Returns the position of the specified movable on the game board.
     *
     * @param movable the movable object whose position is to be retrieved
     * @return the position of the movable on the game board
     */
    public int getMovablePosition(Movable movable) {
        return movablesManager.getMovablePosition(movable);
    }

    /**
     * Checks if the turn is over by verifying if all movables have either woken up
     * or called it a night.
     *
     * @return true if the turn is over, false otherwise.
     */
    public boolean isTurnOver() {
        return movablesManager.areAllMovablesSettled();
    }

    // Nightmare methods
    /**
     * Adds a nightmare to the game board.
     * 
     * @param nightmare the nightmare to be added
     */
    public void addNightmareToBoard(Nightmare nightmare) {
        movablesManager.addNightmareToBoard(nightmare);
    }

    /**
     * Moves the nightmare on the game board by the specified amount.
     * The nightmare moves one space at a time, scaring any player it lands on.
     * 
     * @param amount the number of spaces to move the nightmare
     * @return true if the nightmare reaches the end of the board and wraps around
     *         to the beginning, false otherwise
     */
    public boolean moveNightmare(int amount) {
        return movablesManager.moveNightmare(amount);
    }

    /**
     * Moves the nightmare position by the specified amount and scares any movables
     * at the new position.
     * If the new position exceeds 10, the nightmare position is reset to the
     * default position and true is returned.
     * Otherwise, false is returned.
     *
     * @param amount the amount to move the nightmare position by
     * @return true if the new position exceeds 10 and the nightmare position is
     *         reset, false otherwise
     */
    public boolean jumpNightmare(int amount) {
        return movablesManager.jumpNightmare(amount);
    }

    /**
     * Returns the position of the nightmare on the game board.
     *
     * @return the position of the nightmare
     */
    public int getNightmarePos() {
        return movablesManager.getNightmarePos();
    }

    /**
     * Returns the Nightmare.
     *
     * @return the Nightmare object
     */
    public Nightmare getNightmare() {
        return movablesManager.getNightmare();
    }

    // Other methods
    /**
     * Resets the positions of all the movable objects on the game board.
     * This method sets the position of each movable object to 1 and makes them
     * become brave.
     * It also resets the position of the nightmare object to the default position.
     */
    public void resetPositions() {
        movablesManager.resetPositions();
    }

    /**
     * Checks if the player has zzzs on any other dream tiles within 2 spaces of
     * this one.
     * 
     * @param tile the tile to check for adjacent Zzzs
     * @return true if the tile has adjacent Zzzs, false otherwise
     */
    public boolean hasAdjacentZzzs(Tile tile) {
        return tilesManager.hasAdjacentZzzs(tile);
    }

    /**
     * Returns an array of TileController objects representing the tiles on the game
     * board.
     *
     * @return an array of TileController objects representing the tiles on the game
     *         board
     */
    public TileController[] getTiles() {
        return tilesManager.getTiles();
    }
}

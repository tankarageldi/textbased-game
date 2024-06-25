package controllers;

import models.GameBoard;
import models.Movable;
import models.Nightmare;
import views.GameBoardView;

/**
 * The GameBoardController class is responsible for controlling the game board
 * and interacting with the view.
 * It provides methods to place tiles, movables, nightmares, and perform various
 * actions on the game board.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class GameBoardController {

    private GameBoard gameBoard;
    private GameBoardView gameBoardView;

    /**
     * Constructs a GameBoardController object with the specified game board and game board view.
     *
     * @param gameBoard     the game board to be controlled
     * @param gameBoardView the game board view to interact with
     */
    public GameBoardController(GameBoard gameBoard, GameBoardView gameBoardView) {
        this.gameBoard = gameBoard;
        this.gameBoardView = gameBoardView;
    }

    /**
     * Returns the game board model.
     *
     * @return the game board model
     */
    public GameBoard getModel() {
        return gameBoard;
    }

    /**
     * Displays the information of the game board.
     */
    public void displayInformation() {
        gameBoardView.displayInformation();
        displayTiles();
        displayMovables();
        displayNightmarePosition();
    }
    
     /**
     * Displays the dream tiles on the game board.
     *
     * 
     */
    private void displayTiles() {
        TileController[] tiles = gameBoard.getTiles();
        if(tiles.length == 0){
            gameBoardView.displayNoNightmare();
        }
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] != null) {
                gameBoardView.displayTiles(tiles[i].getTileName(), (i + 1));
            }
        }
    }

     /**
     * Displays the movables on the game board.
     *
     * 
     */
    private void displayMovables() {
        gameBoard.getMovables().forEach((movable, position) -> {
            gameBoardView.displayMovables(movable.getName(), position);
        });
    }

     /**
     * Displays the nightmare position on the game board.
     *
     * 
     */
    private void displayNightmarePosition() {
        gameBoardView.displayNightmarePosition(gameBoard.getNightmare().getName(), gameBoard.getNightmarePos());
    }

    /**
     * Places a tile at the specified position on the game board.
     *
     * @param position       the position to place the tile
     * @param tileController the tile controller representing the tile to be placed
     */
    public void placeTile(int position, TileController tileController) {
        try {
            gameBoard.placeTile(position, tileController);
        } catch (IllegalArgumentException e) {
            gameBoardView.showError(e.getMessage());
        }
    }

    /**
     * Adds a movable to the game board at the default position.
     *
     * @param movable the movable to be added
     */
    public void addMovableToBoard(Movable movable) {
        gameBoard.placeMovable(movable, 1);
    }

    /**
     * Places a movable at the specified position on the game board.
     *
     * @param movable  the movable to be placed
     * @param position the position to place the movable
     */
    public void placeMovable(Movable movable, int position) {
        gameBoard.placeMovable(movable, position);
    }

    /**
     * Moves a movable by the specified amount on the game board.
     *
     * @param movable the movable to be moved
     * @param amount  the amount to move the movable
     */
    public void moveMovable(Movable movable, int amount) {
        gameBoard.moveMovable(movable, amount);
    }

    /**
     * Calls it a night for the specified movable on the game board.
     *
     * @param movable the movable to call it a night for
     */
    public void callItANight(Movable movable) {
        gameBoard.callItANight(movable);
    }

    /**
     * Moves the nightmare by the specified amount on the game board.
     *
     * @param amount the amount to move the nightmare
     * @return true if the nightmare crossed the fence, false otherwise
     */
    public boolean moveNightmare(int amount) {
        boolean crossedFence = gameBoard.moveNightmare(amount);
        if (crossedFence) {
            gameBoard.wakeEveryone();
        }
        return crossedFence;
    }

    /**
     * Jumps the nightmare by the specified amount on the game board.
     *
     * @param amount the amount to jump the nightmare
     * @return true if the nightmare crossed the fence, false otherwise
     */
    public boolean jumpNightmare(int amount) {
        boolean crossedFence = gameBoard.jumpNightmare(amount);
        if (crossedFence) {
            gameBoard.wakeEveryone();
        }
        return crossedFence;
    }

    /**
     * Adds a nightmare to the game board.
     *
     * @param nightmare the nightmare to be added
     */
    public void addNightmareToBoard(Nightmare nightmare) {
        gameBoard.addNightmareToBoard(nightmare);
    }

    /**
     * Resets the positions of all movables on the game board.
     */
    public void resetPositions() {
        gameBoard.resetPositions();
    }

    /**
     * Checks if a tile is placed at the specified position on the game board.
     *
     * @param position the position to check
     * @return true if a tile is placed at the position, false otherwise
     */
    public boolean isTilePlaced(int position) {
        return gameBoard.isTilePlaced(position);
    }

    /**
     * Returns the tile controller at the specified position on the game board.
     *
     * @param position the position to get the tile controller from
     * @return the tile controller at the position
     */
    public TileController getTile(int position) {
        return gameBoard.getTile(position);
    }

    /**
     * Returns the position of the specified movable on the game board.
     *
     * @param movable the movable to get the position of
     * @return the position of the movable
     */
    public int getMovablePosition(Movable movable) {
        return gameBoard.getMovablePosition(movable);
    }

    /**
     * Returns the number of dream tiles on the game board.
     *
     * @return the number of dream tiles
     */
    public int getNumOfDreamTiles() {
        return gameBoard.getNumOfDreamTiles();
    }

    /**
     * Places the top tile on the game board.
     *
     * @param tileController the tile controller representing the top tile
     */
    public void placeTopTile(TileController tileController) {
        gameBoard.placeTopTile(tileController);
    }

    /**
     * Checks if the turn is over on the game board.
     *
     * @return true if the turn is over, false otherwise
     */
    public boolean isTurnOver() {
        return gameBoard.isTurnOver();
    }

    /**
     * Returns the Nightmare object associated with the game board.
     *
     * @return the Nightmare object
     */
    public Nightmare getNightmare() {
        return gameBoard.getNightmare();
    }
    /**
     * Returns the position of the nightmare on the game board.
     *
     * @return the position of the nightmare
     */
    public int getNightmarePosition() {
        return gameBoard.getNightmarePos();
    }
}

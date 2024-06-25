package views;

/**
 * This class represents the view for the game board.
 * It is responsible for displaying information about the game board state,
 * including the dream tiles, movables, and nightmare position.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class GameBoardView {

    /**
     * Displays the current game board state, including the dream tiles, movables, and nightmare position.
     *
     *
     */
    public void displayInformation() {
        System.out.println("Current Game Board State:");
    }

    /**
     * Displays the dream tiles on the game board.
     *
     * @param name the name of the tile object
     * @param position the position of the tile object
     */
    public void displayTiles(String name, int position) {
        System.out.println("Dream Tile at position " + position + ": " + name);
    }

    /**
     * Displays the movables on the game board.
     *
     * @param name the name of the tile object
     * @param position the position of the tile object
     */
    public void displayMovables(String name, int position) {
        System.out.println(name + " is at position: " + position);
    }

    /**
     * Displays the nightmare position on the game board.
     *
     * @param name the name of the tile object
     * @param position the position of the tile object
     */
    public void displayNightmarePosition(String name, int position) {
        System.out.println(name + " is at position: " + position);
    }

    /*
     * Displays message for empty Dream Tile slot
     */
    public void displayNoNightmare() {
        System.out.println("No active Dream Tiles.");
    }

    /**
     * Displays an error message.
     *
     * @param message the error message
     */
    public void showError(String message) {
        System.err.println("Error: " + message);
    }

}

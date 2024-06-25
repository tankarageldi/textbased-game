package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import controllers.TileController;

/**
 * Represents a deck of Dream Tiles.
 * 
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class TileDeck {

    private Queue<TileController> allTiles;
    private List<TileController> tileMarket;

    /**
     * Constructs a new TileDeck object.
     * Initializes the allTiles queue and the tileMarket list.
     */
    public TileDeck() {
        allTiles = new LinkedList<>();
        tileMarket = new ArrayList<>();
    }

    /**
     * Retrieves a tile from the tileMarket based on the given tile index.
     * 
     * @param tileIndex The index of the tile to retrieve.
     * @return The TileController object at the specified index.
     * @throws IllegalArgumentException if the tile index is invalid.
     */
    public TileController getTile(int tileIndex) {
        tileIndex--;
        if (tileIndex < 0 || tileIndex >= tileMarket.size()) {
            throw new IllegalArgumentException("Invalid tile index.");
        }
        TileController chosenTile = tileMarket.remove(tileIndex);
        if (allTiles.isEmpty()) {
            return chosenTile;
        }
        tileMarket.add(allTiles.poll());
        return chosenTile;
    }

    /**
     * Shuffles the tiles in the allTiles queue.
     */
    public void shuffleTiles() {
        List<TileController> tempList = new ArrayList<>(allTiles);
        Collections.shuffle(tempList);
        allTiles = new LinkedList<>(tempList);
    }

    /**
     * Adds a tile to the allTiles queue.
     * 
     * @param tileController The TileController object to add.
     */
    public void addTile(TileController tileController) {
        allTiles.offer(tileController);
    }

    /**
     * Initializes the tileMarket by adding tiles from the allTiles queue.
     */
    public void initializeMarket() {
        for (int i = 0; i < 4 && !allTiles.isEmpty(); i++) {
            tileMarket.add(allTiles.poll());
        }
    }

    /**
     * Retrieves the top tile from the allTiles queue.
     * 
     * @return The top TileController object, or null if the queue is empty.
     */
    public TileController getTopTile() {
        if (!allTiles.isEmpty()) {
            return allTiles.poll();
        }
        return null;
    }

    /**
     * Retrieves the number of tiles in the tileMarket.
     * 
     * @return The number of tiles in the tileMarket.
     */
    public int getNumOfTiles() {
        return tileMarket.size();
    }

    /**
     * Displays the tile market by printing the tile information.
     * If the tile market is empty, nothing is displayed.
     */
    public void displayTileMarket() {
        if (getNumOfTiles() == 0)
            return;
        System.out.println("Tile Market:");
        for (int i = 0; i < getNumOfTiles(); i++) {
            System.out.print(i + 1 + " ");
            tileMarket.get(i).displayTileInfo();
        }
    }
}

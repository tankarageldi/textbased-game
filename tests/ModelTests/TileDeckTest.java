package ModelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import controllers.TileController;
import models.Tile;
import models.TileDeck;
import models.tiles.ActionHeroTile;
import models.tiles.FinalSprintTile;
import models.tiles.LoneSheepTile;
import models.tiles.RestingSpotTile;
import views.TileView;


public class TileDeckTest {

    private TileDeck tileDeck;
    private Tile tile1;

    @BeforeEach
    public void setUp() {
        tileDeck = new TileDeck();
        tile1 = new ActionHeroTile();
        tileDeck.addTile(new TileController(tile1, new TileView()));
        tileDeck.addTile(new TileController(new RestingSpotTile(), new TileView()));
        tileDeck.addTile(new TileController(new LoneSheepTile(), new TileView()));
        tileDeck.addTile(new TileController(new FinalSprintTile(), new TileView()));
        tileDeck.initializeMarket();
    }

    @Test
    public void testGetTile() {
        TileController tile = tileDeck.getTile(1);
        assertNotNull(tile);
        assertEquals(tile1, tile.getModel());
    }

    @Test
    public void testGetTileInvalidIndex() {
        assertThrows(IllegalArgumentException.class, () -> {
            tileDeck.getTile(5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            tileDeck.getTile(0);
        });
    }


    @Test
    public void testAddTile() {
        tileDeck.getTile(1);    // Remove the first tile
        assertEquals(3, tileDeck.getNumOfTiles(), "The number of tiles should be 3 after removing the first tile");   
        TileController newTile = new TileController(new ActionHeroTile(), null);
        tileDeck.addTile(newTile);
        tileDeck.initializeMarket();
        assertEquals(4, tileDeck.getNumOfTiles(), "The number of tiles should be 4 after adding a new tile");
    }


    @Test
    public void testGetTopTile_EmptyAllTiles_ReturnsNull() {
        assertNull(tileDeck.getTopTile(), "The top tile should be null if allTiles is empty");
        tileDeck.addTile(new TileController(new ActionHeroTile(), null));
        assertNotNull(tileDeck.getTopTile(), "The top tile should not be null after adding a tile");
    }


}
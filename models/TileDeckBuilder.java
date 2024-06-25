package models;

import controllers.TileController;
import models.tiles.*;
import views.TileView;

/**
 * The TileDeckBuilder class is responsible for creating a TileDeck object.
 * It provides a static method to create the TileDeck and initialize it with
 * a set of predefined tiles.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class TileDeckBuilder {

    /**
     * Creates a new TileDeck object and initializes it with a set of predefined tiles.
     *
     * @return The created TileDeck object.
     */
    public static TileDeck createTiles() {
        TileDeck deck = new TileDeck();

        deck.addTile(new TileController(new ActionHeroTile(), new TileView()));
        deck.addTile(new TileController(new CoolKidsClubTile(), new TileView()));
        deck.addTile(new TileController(new DoubleDutchTile(), new TileView()));
        deck.addTile(new TileController(new FinalSprintTile(), new TileView()));
        deck.addTile(new TileController(new IntenseDreamsTile(), new TileView()));
        deck.addTile(new TileController(new LoneSheepTile(), new TileView()));
        deck.addTile(new TileController(new PerfectLandingTile(), new TileView()));
        deck.addTile(new TileController(new RestingSpotTile(), new TileView()));
        deck.addTile(new TileController(new SecondWindTile(), new TileView()));
        deck.addTile(new TileController(new StepBackTile(), new TileView()));
        deck.initializeMarket();
        return deck;
    }
}

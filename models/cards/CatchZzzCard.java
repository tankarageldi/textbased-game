package models.cards;

import controllers.TileController;
import models.Card;
import models.GameBoard;
import models.Player;
import views.UserInput;

/**
 * Represents a card that allows the player to catch a certain number of ZZZs.
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class CatchZzzCard implements Card {
    private int zzzs;

    /**
     * Constructs a new CatchZzzCard with the specified number of ZZZs to catch.
     *
     * @param zzzs the number of ZZZs to catch
     */
    public CatchZzzCard(int zzzs) {
        this.zzzs = zzzs;
    }

    /**
     * Returns the information about the CatchZzzCard.
     *
     * @return the information about the CatchZzzCard
     */
    @Override
    public String getInformation() {
        return "Catch " + zzzs + (zzzs == 1 ? " zzz" : " zzzs");
    }

    /**
     * Executes the action of the CatchZzzCard.
     *
     * @param player     the player who plays the card
     * @param gameBoard  the game board
     * @param input      the user input
     */
    @Override
    public void executeAction(Player player, GameBoard gameBoard, UserInput input) {
        int tileSelection = input.getCatchTileIndex();
        if (!gameBoard.isTilePlaced(tileSelection)) return;
        TileController selectedTile = gameBoard.getTile(tileSelection);
        int catchZZZs = player.catchZZZs(zzzs);
        selectedTile.placeZzzs(catchZZZs, false);
    }

    /**
     * Checks if the CatchZzzCard is a nightmare card.
     *
     * @return true if the CatchZzzCard is a nightmare card, false otherwise
     */
    @Override
    public boolean isNightmare() {
        return false;
    }
}

package models;

import controllers.GameBoardController;
import controllers.PlayerController;
import controllers.TileController;
import views.UserInput;

/**
 * This class represents the logic for the resting phase of the game.
 * It contains methods for playing the resting move and handling the placement
 * of tiles.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class RestingPhaseLogic {

    private static final int MAX_DREAM_TILES = 10;
    private static final int ZZZS_FOR_SINGLE_TILE = 2;
    private static final int ZZZS_FOR_MULTIPLE_TILES = 1;
    private static final int ZZZS_FOR_INFINITE_TILE = 1;
    private static final int ZZZS_FOR_NON_INFINITE_TILE = 3;

    /**
     * Executes the logic for the resting phase of the game. This phase allows
     * players to interact with the tile market, place tiles on the game board, and
     * catch Zzzs on tiles.
     *
     * @param gameBoardController Controls the game board and its state.
     * @param userInput           Handles user input for decisions made during the
     *                            resting phase.
     * @param tileDeck            Represents the deck of tiles available for
     *                            placement on the game board.
     * @param playerController    Manages the player's actions, including tile
     *                            placement and catching Zzzs.
     */
    public void playRestingMove(GameBoardController gameBoardController, UserInput userInput, TileDeck tileDeck,
            PlayerController playerController) {
        if (gameBoardController.getNumOfDreamTiles() < MAX_DREAM_TILES) {
            handleTileMarketInteraction(gameBoardController, userInput, tileDeck, playerController);
        } else {
            handleCatchZzzsDecision(gameBoardController, userInput, playerController, false);
        }
        placeTopTileOnGameBoard(gameBoardController, tileDeck);
    }

    /**
     * Handles interactions with the tile market, allowing the player to select and
     * place tiles, or directly catch Zzzs.
     *
     * @param gameBoardController Controls the game board and its state.
     * @param userInput           Handles user input for decisions made during the
     *                            tile market interaction.
     * @param tileDeck            Represents the deck of tiles available for
     *                            selection.
     * @param playerController    Manages the player's actions during tile market
     *                            interaction.
     */
    private void handleTileMarketInteraction(GameBoardController gameBoardController, UserInput userInput,
            TileDeck tileDeck, PlayerController playerController) {
        tileDeck.displayTileMarket();
        boolean isOption1 = userInput.getRestingMoveDecision();

        if (isOption1) {
            handleTileSelectionAndPlacement(gameBoardController, userInput, tileDeck, playerController);
        } else {
            handleCatchZzzsDecision(gameBoardController, userInput, playerController, true);
        }
    }

    /**
     * Manages the selection and placement of a tile chosen from the tile market.
     *
     * @param gameBoardController Controls the game board and updates it with the
     *                            newly placed tile.
     * @param userInput           Handles user input for tile selection and
     *                            placement.
     * @param tileDeck            Provides access to the selected tile.
     * @param playerController    Manages the player's actions, such as catching
     *                            Zzzs for the placed tile.
     */
    private void handleTileSelectionAndPlacement(GameBoardController gameBoardController, UserInput userInput,
            TileDeck tileDeck, PlayerController playerController) {
        int tileIndex = userInput.getTileSelection(tileDeck.getNumOfTiles());
        TileController selectedTile = tileDeck.getTile(tileIndex);
        int zzzs = playerController
                .catchZZZs(selectedTile.isInfinite() ? ZZZS_FOR_INFINITE_TILE : ZZZS_FOR_NON_INFINITE_TILE);

        selectedTile.placeZzzs(zzzs, !selectedTile.isInfinite());
        placeSelectedTile(gameBoardController, userInput, selectedTile);
    }

    /**
     * Facilitates the placement of a selected tile on the game board at a position
     * chosen by the player.
     *
     * @param gameBoardController Controls the game board and updates it with the
     *                            newly placed tile.
     * @param userInput           Handles user input for the position where the tile
     *                            will be placed.
     * @param selectedTile        The tile selected by the player for placement.
     */
    private void placeSelectedTile(GameBoardController gameBoardController, UserInput userInput,
            TileController selectedTile) {
        boolean isPlaced = false;
        while (!isPlaced) {
            int tilePosition = userInput.getTilePlacementPosition();
            isPlaced = !gameBoardController.isTilePlaced(tilePosition);
            if (isPlaced) {
                gameBoardController.placeTile(tilePosition, selectedTile);
            } else {
                System.out.println("That position is already occupied. Please choose a different position.");
            }
        }
    }

    /**
     * Determines and executes the logic for catching Zzzs on tiles, based on the
     * user's decision.
     *
     * @param gameBoardController Controls the game board and its state.
     * @param userInput           Handles user input related to catching Zzzs
     *                            decisions.
     * @param playerController    Manages the player's actions, such as the number
     *                            of Zzzs to catch.
     * @param onlyOneTileOption   Indicates whether the player has the option to
     *                            catch Zzzs on only one tile.
     */
    private void handleCatchZzzsDecision(GameBoardController gameBoardController, UserInput userInput,
            PlayerController playerController, boolean onlyOneTileOption) {
        if (onlyOneTileOption) {
            int tileIndex = userInput.getCatchTileIndex();
            TileController selectedTile = gameBoardController.getTile(tileIndex);
            if (selectedTile == null) {
                System.out.println("No tile at the chosen position.");
            } else {
                int zzzsCaught = playerController.catchZZZs(ZZZS_FOR_SINGLE_TILE);
                selectedTile.placeZzzs(zzzsCaught, false);
                System.out.println("Zzzs successfully caught on tile: " + selectedTile.getTileName());
            }
        } else {
            System.out.println("Please select the first tile to catch Zzzs on:");
            int firstTileIndex = userInput.getCatchTileIndex();
            catchZzzsOnTile(gameBoardController, playerController, firstTileIndex, ZZZS_FOR_MULTIPLE_TILES);

            System.out.println("Please select the second tile to catch Zzzs on:");
            int secondTileIndex = userInput.getCatchTileIndex();
            catchZzzsOnTile(gameBoardController, playerController, secondTileIndex, ZZZS_FOR_MULTIPLE_TILES);
        }
    }

    /**
     * Catches Zzzs on a specific tile selected by the player.
     *
     * @param gameBoardController Controls the game board and its tiles.
     * @param playerController    Manages the player's actions, such as catching
     *                            Zzzs.
     * @param tileIndex           The index of the tile on which Zzzs are to be
     *                            caught.
     * @param zzzsToCatch         The number of Zzzs the player intends to catch on
     *                            the tile.
     */
    private void catchZzzsOnTile(GameBoardController gameBoardController, PlayerController playerController,
            int tileIndex, int zzzsToCatch) {
        TileController selectedTile = gameBoardController.getTile(tileIndex);
        if (selectedTile == null) {
            System.out.println("No tile at the chosen position. Please select a valid tile.");
        } else {
            int zzzsCaught = playerController.catchZZZs(zzzsToCatch);
            selectedTile.placeZzzs(zzzsCaught, false);
            System.out.println("Zzzs successfully caught on tile: " + selectedTile.getTileName());
        }
    }

    /**
     * Places the top tile from the tile deck onto the game board automatically at
     * the end of the resting phase.
     *
     * @param gameBoardController Controls the game board and updates it with the
     *                            top tile.
     * @param tileDeck            Provides access to the top tile of the deck.
     */
    private void placeTopTileOnGameBoard(GameBoardController gameBoardController, TileDeck tileDeck) {
        TileController topTile = tileDeck.getTopTile();
        gameBoardController.placeTopTile(topTile);
    }
}
package models;

import controllers.DeckController;
import controllers.GameBoardController;
import controllers.PlayerController;
import views.UserInput;

/**
 * This class represents the logic for the racing phase of the game.
 * It contains methods for playing a racing move and checking fences.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class RacingPhaseLogic {

    /**
     * Plays a racing move for the player.
     * This method fills the player's hand with cards, resolves any nightmares,
     * allows the player to play a card, checks fences, and activates tiles if
     * necessary.
     *
     * @param playerController    the player controller
     * @param gameBoardController the game board controller
     * @param deckController      the deck controller
     * @param userInput           the user input
     */
    public void playRacingMove(PlayerController playerController, GameBoardController gameBoardController,
            DeckController deckController, UserInput userInput) {
        if (checkIsTurnOver(gameBoardController)) {
            return;
        }

        fillPlayerHand(playerController, deckController, gameBoardController, userInput);
        playerController.updateView();

        resolveTopCard(playerController, gameBoardController, deckController, userInput);
        gameBoardController.displayInformation();

        playCard(playerController, gameBoardController, userInput);
        checkFence(gameBoardController, playerController.getModel(), userInput);
        activateTileIfPlaced(gameBoardController, playerController, userInput);

        drawNewCard(playerController, deckController, gameBoardController, userInput);
    }

    /**
     * Fills the player's hand with cards until it is full.
     * This method is called by the `playRacingMove` method.
     *
     * @param playerController    the player controller
     * @param deckController      the deck controller
     * @param gameBoardController the game board controller
     * @param userInput           the user input
     */
    private void fillPlayerHand(PlayerController playerController, DeckController deckController,
            GameBoardController gameBoardController, UserInput userInput) {
        if (checkIsTurnOver(gameBoardController)) {
            return;
        }
        while (playerController.needsACard()) {
            drawNewCard(playerController, deckController, gameBoardController, userInput);
            if (gameBoardController.isTurnOver()) {
                return;
            }
        }
    }

    /**
     * Resolves the top card from the deck and handles any nightmares.
     * This method is called by the `playRacingMove` method.
     *
     * @param playerController    the player controller
     * @param gameBoardController the game board controller
     * @param deckController      the deck controller
     * @param userInput           the user input
     */
    private void resolveTopCard(PlayerController playerController, GameBoardController gameBoardController,
            DeckController deckController, UserInput userInput) {
        if (checkIsTurnOver(gameBoardController)) {
            return;
        }
        Card topCard = deckController.drawCard();
        handleNightmare(topCard, playerController, gameBoardController, userInput);
    }

    /**
     * Plays a card from the player's hand and executes its action.
     * This method is called by the `playRacingMove` method.
     *
     * @param playerController    the player controller
     * @param gameBoardController the game board controller
     * @param userInput           the user input
     */
    private void playCard(PlayerController playerController, GameBoardController gameBoardController,
            UserInput userInput) {
        if (checkIsTurnOver(gameBoardController)) {
            return;
        }
        int cardIndex = userInput.getCardSelection();
        Card playedCard = playerController.playCard(cardIndex);
        playedCard.executeAction(playerController.getModel(), gameBoardController.getModel(), userInput);
    }

    /**
     * Checks if the player has crossed a fence and prompts the user to call it a
     * night.
     * If the user chooses to call it a night, the game board is updated
     * accordingly.
     *
     * @param gameBoardController the game board controller
     * @param player              the player
     * @param userInput           the user input
     */
    private void checkFence(GameBoardController gameBoardController, Player player, UserInput userInput) {
        if (player.hasCrossedFence()) {
            boolean callItANight = userInput.getCallItANightDecision();
            if (callItANight) {
                gameBoardController.callItANight(player);
            }
            player.resetFence();
        }
    }

    /**
     * Activates a tile if the player has placed their pawn on it and the user
     * chooses to activate it.
     * This method is called by the `playRacingMove` method.
     *
     * @param gameBoardController the game board controller
     * @param playerController    the player controller
     * @param userInput           the user input
     */
    private void activateTileIfPlaced(GameBoardController gameBoardController, PlayerController playerController,
            UserInput userInput) {
        if (checkIsTurnOver(gameBoardController)) {
            return;
        }
        int playerPosition = gameBoardController.getMovablePosition(playerController.getModel());
        if (gameBoardController.isTilePlaced(playerPosition)) {
            boolean activateTile = userInput.getActivateTileDecision(
                    gameBoardController.getTile(playerPosition).getTileName());
            if (activateTile) {
                gameBoardController.getTile(playerPosition).activateTileEffect(playerController.getModel(),
                        gameBoardController.getModel(), userInput);
            }
        }
    }

    /**
     * Draws a new card from the deck and adds it to the player's hand.
     * This method is called by the `fillPlayerHand` method.
     *
     * @param playerController    the player controller
     * @param deckController      the deck controller
     * @param gameBoardController the game board controller
     * @param userInput           the user input
     */
    private void drawNewCard(PlayerController playerController, DeckController deckController,
            GameBoardController gameBoardController, UserInput userInput) {
        Card newCard = deckController.drawCard();
        while (newCard.isNightmare()) {
            handleNightmare(newCard, playerController, gameBoardController, userInput);
            newCard = deckController.drawCard();
            if (checkIsTurnOver(gameBoardController)) {
                return;
            }
        }
        playerController.gainCard(newCard);
    }

    /**
     * Handles the nightmare card by executing its action.
     * This method is called by the `resolveTopCard` and `drawNewCard` methods.
     *
     * @param card      the nightmare card
     * @param player    the player controller
     * @param gameBoard the game board controller
     * @param input     the user input
     */
    private void handleNightmare(Card card, PlayerController player, GameBoardController gameBoard,
            UserInput input) {
        if (card.isNightmare()) {
            card.executeAction(player.getModel(), gameBoard.getModel(), input);
        }
    }

    /**
     * Checks if the turn is over by checking the game board controller.
     *
     * @param gameBoardController the game board controller
     * @return true if the turn is over, false otherwise
     */
    private boolean checkIsTurnOver(GameBoardController gameBoardController) {
        return gameBoardController.isTurnOver();
    }
}

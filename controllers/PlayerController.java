package controllers;

import models.Player;
import models.Sheep;
import models.Card;
import views.PlayerView;

/**
 * The PlayerController class represents the controller for the Player.
 * It handles the interaction between the Player model and the PlayerView.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class PlayerController {
    private Player model;
    private PlayerView view;

    /**
     * Constructs a PlayerController object with the specified Player model and PlayerView.
     *
     * @param model The Player model.
     * @param view  The PlayerView.
     */
    public PlayerController(Player model, PlayerView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Updates the view with the current state of the Player model.
     */
    public void updateView() {
        view.updateView(model.getName(), model.getSheep().getColor(), model.getSheep().isScared(), model.getNumOfZzzs());
        if (model.getCard(0) != null) {
            view.displayFirstCardInformation(model.getCard(0).getInformation());
        }
        if (model.getCard(1) != null) {
            view.displaySecondCardInformation(model.getCard(1).getInformation());
        }
    }

   
    /**
     * Returns the name of the Player.
     *
     * @return The name of the Player.
     */
    public String getPlayerName() {
        return model.getName();
    }

    /**
     * Returns the Sheep object associated with the Player.
     *
     * @return The Sheep object associated with the Player.
     */
    public Sheep getSheep() {
        return model.getSheep();
    }

    /**
     * Returns the number of Zzzs (sleep points) of the Player.
     *
     * @return The number of Zzzs of the Player.
     */
    public int getNumOfZzzs() {
        return model.getNumOfZzzs();
    }

    /**
     * Adds a Card to the Player's collection of cards.
     *
     * @param card The Card to be gained.
     */
    public void gainCard(Card card) {
        model.gainCard(card);
    }

    /**
     * Checks if the Player needs a Card.
     *
     * @return true if the Player needs a Card, false otherwise.
     */
    public boolean needsACard() {
        return model.needsACard();
    }

    /**
     * Plays a Card from the Player's collection of cards at the specified index.
     *
     * @param index The index of the Card to be played.
     * @return The Card that was played.
     */
    public Card playCard(int index) {
        Card card = model.playCard(index);
        return card;
    }

    /**
     * Increases the number of Zzzs (sleep points) of the Player by the specified amount.
     *
     * @param amount The amount of Zzzs to be caught.
     * @return The total number of Zzzs after catching.
     */
    public int catchZZZs(int amount) {
        int zzzs = model.catchZZZs(amount);
        return zzzs;
    }

    /**
     * Returns the Player model associated with the PlayerController.
     *
     * @return The Player model.
     */
    public Player getModel() {
        return model;
    }
}

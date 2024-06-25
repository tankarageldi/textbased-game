package models;

import controllers.DeckController;
import views.UserInput;

/**
 * The NightmareFactory class is responsible for creating Nightmare objects based on user input.
 * It provides a static method to create a Nightmare object with the specified Nightmare and DeckController.
 * 
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class NightmareFactory {

    /**
     * Creates a Nightmare object based on the user's Nightmare selection.
     * 
     * @param userInput The UserInput object containing the user's input.
     * @param deckController The DeckController object to create Nightmare cards.
     * @return A specified Nightmare object
     */
    public static Nightmare createNightmare(UserInput userInput, DeckController deckController) {
        int choice = userInput.getNightmareSelection();
        Nightmare nightmare;
        switch (choice) {
            case 1:
                nightmare = new Wolf();
                NightmareDeckCreator.createNightmareCards(deckController.getModel(), "Wolf");
                return nightmare;
            case 2:
                nightmare = new BumpInTheNight();
                NightmareDeckCreator.createNightmareCards(deckController.getModel(), "BumpInTheNight");
                return nightmare;
            default:
                nightmare = new Wolf();
                NightmareDeckCreator.createNightmareCards(deckController.getModel(), "Wolf");
                return nightmare;
        }
    }
}

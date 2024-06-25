package models;
import controllers.PlayerController;
import views.PlayerView;
import views.UserInput;

import java.util.ArrayList;
import java.util.List;


/**
 * The PlayerCreator class is responsible for creating and initializing player objects.
 * It provides a method to initialize a list of PlayerController objects based on user input.
 * 
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class PlayerCreator {

    /**
     * Initializes a list of PlayerController objects based on user input.
     * 
     * @param userInput   the user input containing player name and sheep color
     * @param numOfPlayers   the number of players to initialize
     * @return a list of initialized PlayerController objects
     */
    public static List<PlayerController> intialisePlayers(UserInput userInput, int numOfPlayers) {
        List<PlayerController> playerControllers = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++){
            Player newPlayer = new Player(userInput.getPlayerName(), userInput.getSheepColor());
            PlayerView newView = new PlayerView();
            PlayerController controller = new PlayerController(newPlayer, newView);
            playerControllers.add(controller);
        }
        return playerControllers;
    }
}
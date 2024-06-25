package models;

import java.util.List;
import java.util.stream.Collectors;

import controllers.PlayerController;
import controllers.ScoreBoardController;
import views.ScoreBoardView;

/**
 * The ScoreBoardCreator class is responsible for initializing the ScoreBoardController.
 * It provides a method to initialize the ScoreBoardController with a list of PlayerControllers.
 * 
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class ScoreBoardCreator {

    /**
     * Initializes the ScoreBoardController with the given list of PlayerControllers.
     * 
     * @param playerControllers The list of PlayerControllers to initialize the ScoreBoardController with.
     * @return The initialized ScoreBoardController.
     */
    public static ScoreBoardController initializeScoreBoard(List<PlayerController> playerControllers) {
        List<Player> players = playerControllers.stream().map(PlayerController::getModel).collect(Collectors.toList());
        ScoreBoard scoreBoard = new ScoreBoard(players);
        ScoreBoardView scoreBoardView = new ScoreBoardView();
        ScoreBoardController scoreBoardController = new ScoreBoardController(scoreBoard, scoreBoardView);

        return scoreBoardController;
    }
}
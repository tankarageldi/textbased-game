package models;

import controllers.GameBoardController;
import views.GameBoardView;

/**
 * The GameBoardCreator class is responsible for creating and initializing the game board.
 * It provides a static method to initialize the game board and return the game board controller.
 * 
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class GameBoardCreator {
    
    /**
     * Initializes the game board by creating instances of GameBoard, GameBoardView, and GameBoardController.
     * 
     * @return The initialized GameBoardController.
     */
    public static GameBoardController initializeGameBoard(){
        GameBoard gameBoard = new GameBoard();
        GameBoardView gameBoardView = new GameBoardView();
        GameBoardController gameBoardController= new GameBoardController(gameBoard, gameBoardView);
        return gameBoardController;
    }

}
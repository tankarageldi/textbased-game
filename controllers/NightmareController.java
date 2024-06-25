package controllers;

import models.Nightmare;
import views.NightmareView;

/**
 * The NightmareController class is responsible for controlling the Nightmare model and updating the NightmareView.
 * It provides methods to retrieve the name, description, difficulty, and model of the Nightmare.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class NightmareController {

    private Nightmare model;
    private NightmareView view;

    /**
     * Constructs a NightmareController object with the specified Nightmare model and NightmareView.
     *
     * @param model the Nightmare model
     * @param view  the NightmareView
     */
    public NightmareController(Nightmare model, NightmareView view){
        this.model = model;
        this.view = view;
    }

    /**
     * Updates the view by calling the updateView method of the NightmareView.
     */
    public void updateView(){
        view.updateView(model.getName(), model.getDescription(), model.getDifficulty());
    }
    
    /**
     * Returns the name of the Nightmare.
     *
     * @return the name of the Nightmare
     */
    public String getNightmareName(){
        return model.getName();
    }
    
    /**
     * Returns the description of the Nightmare.
     *
     * @return the description of the Nightmare
     */
    public String getNightmareDescription(){
        return model.getDescription();
    }
    
    /**
     * Returns the difficulty of the Nightmare.
     *
     * @return the difficulty of the Nightmare
     */
    public int getNightmareDifficulty(){
        return model.getDifficulty();
    }
    
    /**
     * Returns the Nightmare model.
     *
     * @return the Nightmare model
     */
    public Nightmare getModel() {
        return model;
    }
}

package ControllerTests;

import controllers.NightmareController;
import models.Nightmare;
import models.Wolf;
import views.NightmareView;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NightmareControllerTest {

    private NightmareController nightmareController;
    private Nightmare nightmare;

    @BeforeEach
    void setUp() {
        nightmare = new Wolf(); 
        NightmareView view = new NightmareView(); 
        nightmareController = new NightmareController(nightmare, view);
    }

    @Test
    void testUpdateView() {
        
        assertDoesNotThrow(() -> nightmareController.updateView());
    }

    @Test
    void testGetNighmareName() {
        String expectedName = "Wolf";
        assertEquals(expectedName, nightmareController.getNightmareName(), "The name should be the expected name");
    }

    @Test
    void testGetNightmareDescription() {
        String expectedDescription = "THAT NIGHTMARE WOLF IS NO BIG DEAL. THREE EYES AND IT STILL DOESN'T SEE US SNEAKING PAST! NOPE, DEFINITELY NOT SCARY AT ALL... *HOOOOOOOOOOWL*\r\n" + //
        "THE WOLF IS RIGHT BEHIND ME ISN'T IT?!?!";
        assertEquals(expectedDescription, nightmareController.getNightmareDescription(), "The description should be the expected description");
    }

    @Test
    void testGetNightmareDifficulty() {
        int expectedDifficulty = 1; 
        assertEquals(expectedDifficulty, nightmareController.getNightmareDifficulty(), "The difficulty should be the expected difficulty");
    }

    @Test
    void testGetModel() {
        assertEquals(nightmare, nightmareController.getModel(), "The returned model should be the same as the one set in the constructor");
    }
}
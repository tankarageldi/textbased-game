package models;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the placement, movement, and states of movable objects on the game
 * board including the nightmare.
 * 
 * @author Adil Alimzhanov
 */
public class MovableManager {
    private Map<Movable, Integer> movables;
    private final int WAKE_UP_POSITION = -1;
    private final int CALL_IT_A_NIGHT_POSITION = 11;
    private final int BOARD_START_POSITION = 1;
    private final int BOARD_END_POSITION = 10;
    private final int NIGHTMARE_DEFAULT_POSITION = 0;
    private Nightmare nightmare;
    private int nightmarePos;

    public MovableManager() {
        movables = new HashMap<>();
        nightmarePos = NIGHTMARE_DEFAULT_POSITION;
    }

    /**
     * Places a movable object on the game board at the specified position.
     * 
     * @param movable  The movable object to be placed on the game board.
     * @param position The position on the game board where the movable object
     *                 should be placed.
     */
    public void placeMovable(Movable movable, int position) {
        validatePosition(position);
        movables.put(movable, position);
    }

    /**
     * Moves the specified movable object on the game board by the given amount.
     * 
     * @param movable The movable object to be moved.
     * @param amount  The amount by which the movable object should be moved.
     */
    public void moveMovable(Movable movable, int amount) {
        Integer currentPosition = movables.get(movable);
        if (currentPosition == null) {
            throw new IllegalArgumentException("Movable not on board.");
        }
        if (currentPosition == CALL_IT_A_NIGHT_POSITION) {
            throw new IllegalArgumentException("This element called it a night");
        }
        if (currentPosition == WAKE_UP_POSITION) {
            throw new IllegalArgumentException("This element is awake");
        }

        int newPosition = calculateNewPosition(movable, currentPosition, amount);

        if (checkNightmareLanding(movable, newPosition)) {
            return;
        }

        movables.put(movable, newPosition);
    }

    private boolean checkNightmareLanding(Movable movable, int position) {
        if (position == nightmarePos) {
            if (movable.isScared()) {
                wakeUpMovable(movable);
                return true;
            } else {
                movable.becomeScared();
                return false;
            }
        }
        return false;

    }

    /**
     * Wakes up the specified movable on the game board.
     * 
     * @param movable the movable to wake up.
     */
    public void wakeUpMovable(Movable movable) {
        movables.put(movable, WAKE_UP_POSITION);
        movable.wakeUp();
    }

    /**
     * Moves the specified movable object to the "call it a night" position.
     * 
     * @param movable the movable object to be moved.
     */
    public void callItANight(Movable movable) {
        movables.put(movable, CALL_IT_A_NIGHT_POSITION);
    }

    /**
     * Scare all movables at the specified position.
     * If a movable is already scared, it will be woken up.
     * If a movable is not scared, it will become scared.
     *
     * @param pos the position to scare the movables at
     */
    public void scareMovablesAtPosition(int pos) {
        movables.forEach((movable, movablePos) -> {
            if (movablePos == pos) {
                if (movable.isScared()) {
                    wakeUpMovable(movable);
                } else {
                    movable.becomeScared();
                }
            }
        });
    }

    /**
     * Checks if the specified movable object is awake on the game board.
     *
     * @param movable the movable object to check
     * @return true if the movable object is awake, false otherwise
     */
    public boolean isAwake(Movable movable) {
        return movables.get(movable) == WAKE_UP_POSITION;
    }

    /**
     * Wakes up all the movable objects on the game board and sets their positions
     * to the wake-up position.
     */
    public void wakeEveryone() {
        movables.forEach((movable, pos) -> {
            wakeUpMovable(movable);
        });
    }

    /**
     * Returns the position of the specified movable on the game board.
     *
     * @param movable the movable object whose position is to be retrieved
     * @return the position of the movable on the game board
     */
    public int getMovablePosition(Movable movable) {
        return movables.get(movable);
    }

    /**
     * Checks if all movables have either woken up or called it a night.
     * 
     * @return true if all movables are in end states, false otherwise.
     */
    public boolean areAllMovablesSettled() {
        for (int position : movables.values()) {
            if (position != CALL_IT_A_NIGHT_POSITION && position != WAKE_UP_POSITION) {
                return false;
            }
        }
        return true;
    }

    public Map<Movable, Integer> getMovables() {
        return movables;
    }

    /**
     * Adds a nightmare to the game board.
     * 
     * @param nightmare the nightmare to be added.
     */
    public void addNightmareToBoard(Nightmare nightmare) {
        this.nightmare = nightmare;
    }

    /**
     * Moves the nightmare on the game board by the specified amount, scaring any
     * movable it lands on.
     * 
     * @param amount the number of spaces to move the nightmare.
     * @return true if the nightmare wraps around to the beginning, false otherwise.
     */
    public boolean moveNightmare(int amount) {
        int oldPosition = nightmarePos;
        nightmarePos += amount;
        if (nightmarePos > BOARD_END_POSITION) {
            wakeEveryone();
            nightmarePos = NIGHTMARE_DEFAULT_POSITION;
            return true;
        }
        if (nightmarePos < BOARD_START_POSITION) {
            nightmarePos = BOARD_START_POSITION;
        }
        for (int pos = oldPosition + 1; pos <= nightmarePos; pos++) {
            scareMovablesAtPosition(pos);
        }
        return false;
    }

    /**
     * Moves the nightmare position by the specified amount and scares any movables
     * at the new position.
     * If the new position exceeds 10, the nightmare position is reset to the
     * default position and true is returned.
     * Otherwise, false is returned.
     *
     * @param amount the amount to move the nightmare position by
     * @return true if the new position exceeds 10 and the nightmare position is
     *         reset, false otherwise
     */
    public boolean jumpNightmare(int amount) {
        nightmarePos += amount;
        if (nightmarePos > BOARD_END_POSITION) {
            wakeEveryone();
            nightmarePos = NIGHTMARE_DEFAULT_POSITION;
            return true;
        }
        scareMovablesAtPosition(nightmarePos);
        return false;
    }

    /**
     * Returns the position of the nightmare on the game board.
     *
     * @return the position of the nightmare
     */
    public int getNightmarePos() {
        return nightmarePos;
    }

    /**
     * Returns the Nightmare.
     *
     * @return the Nightmare object
     */
    public Nightmare getNightmare() {
        return nightmare;
    }

    /**
     * Resets the positions of all the movable objects on the game board.
     * This method sets the position of each movable object to 1 and makes them
     * become brave.
     * It also resets the position of the nightmare object to the default position.
     */
    public void resetPositions() {
        movables.forEach((movable, pos) -> {
            movables.put(movable, 1);
            movable.becomeBrave();
        });
        nightmarePos = NIGHTMARE_DEFAULT_POSITION;
    }

    private void validatePosition(int position) {
        if (position < BOARD_START_POSITION || position > BOARD_END_POSITION) {
            throw new IllegalArgumentException("Position out of bounds.");
        }
    }

    private int calculateNewPosition(Movable movable, int currentPosition, int moveAmount) {
        int newPosition = currentPosition + moveAmount;
        if (newPosition > BOARD_END_POSITION) {
            newPosition = newPosition % BOARD_END_POSITION;
            movable.crossFence();
        } else if (newPosition < BOARD_START_POSITION) {
            newPosition = BOARD_START_POSITION;
        }
        return newPosition;
    }

}

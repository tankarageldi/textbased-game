package views;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents the user input functionality for the SheepyTime game.
 * It provides methods to retrieve various types of input from the user.
 * Input validation is performed to ensure that the user provides valid input.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class UserInput {
    private Scanner scanner;

    /**
     * Constructs a UserInput object and initializes the scanner to read user input from the console.
     */
    public UserInput() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prompts the user to enter their player name and returns the trimmed input.
     *
     * @return The player name entered by the user.
     */
    public String getPlayerName() {
        System.out.println("Enter player name: ");
        String playerName = scanner.nextLine().trim();
        return playerName;
    }

    /**
     * Prompts the user to enter the sheep color and returns the trimmed and lowercase input.
     * If the input is not a valid color, it prompts the user again until a valid input is provided.
     *
     * @return The sheep color entered by the user.
     */
    public String getSheepColor() {
        System.out.println("Enter sheep color (yellow/pink/purple/blue): ");
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.matches("yellow|pink|purple|blue")) {
            return input;
        } else {
            System.out.println("Invalid input. Please enter 'yellow', 'pink', 'purple' or 'blue'.");
            return getSheepColor();
        }
    }

    /**
     * Prompts the user to enter a decision to activate a tile (yes/no) and returns a boolean value.
     *
     * @return True if the user wants to activate the tile, false otherwise.
     */
    public boolean activateTilePrompt() {
        System.out.println("Do you want to activate this tile? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();

        if (input.equals("yes")) {
            return true;
        } else if (input.equals("no")) {
            return false;
        } else {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            return activateTilePrompt();
        }
    }

    /**
     * Prompts the user to enter the number of players and returns the input as an integer.
     * If the input is not a valid integer between 1 and 4, it prompts the user again until a valid input is provided.
     *
     * @return The number of players entered by the user.
     */
    public int getNumOfPlayers() {
        System.out.println("Enter number of players: ");
        int input = -1;
        boolean validInput = false;
        
        while (!validInput) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                
                if (input >= 1 || input <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 4: ");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer: ");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        
        return input;
    }

    /**
     * Prompts the user to enter an option selection (1/2) and returns the input as an integer.
     * If the input is not 1 or 2, it prompts the user again until a valid input is provided.
     *
     * @param option1 The first option.
     * @param option2 The second option.
     * @return The option selected by the user.
     */
    public int pickOption(String option1, String option2) {
        System.out.println("Enter 1 for " + option1 + " or 2 for " + option2 + ": ");
        int input = scanner.nextInt();
        if (input == 1 || input == 2) {
            return input;
        } else {
            System.out.println("Invalid input. Please enter 1 or 2.");
            return pickOption(option1, option2);
        }
    }

    /**
     * Prompts the user to enter the index of the card they want to play (1/2) and returns the input as an integer.
     * If the input is not 1 or 2, it prompts the user again until a valid input is provided.
     *
     * @return The index of the card selected by the user.
     */
    public int getCardSelection() {
        System.out.println("Enter the index of the card you want to play (1/2):");
        int index = 0;
        boolean validInput = false;
        while (!validInput) {
            String input = scanner.nextLine().trim();
            if (input.equals("1") || input.equals("2")) {
                index = Integer.parseInt(input) - 1;    // Convert to 0-based index
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter either 1 or 2:");
            }
        }
        return index;
    }

    /**
     * Prompts the user to enter the position they want to place the tile (1-10) and returns the input as an integer.
     * If the input is not a valid integer between 1 and 10, it prompts the user again until a valid input is provided.
     *
     * @return The position where the user wants to place the tile.
     */
    public int getTilePlacementPosition(){
        System.out.println("Enter the position you want to place the tile (1-10):");
        int position = 0;
        boolean validInput = false;
        while (!validInput) {
            String input = scanner.nextLine().trim();
            if (input.matches("[1-9]|10")) {
                position = Integer.parseInt(input);
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 10:");
            }
        }
        return position;
    }

    /**
     * Prompts the user to enter a decision to call it a night (yes/no) and returns a boolean value.
     *
     * @return True if the user wants to call it a night, false otherwise.
     */
    public boolean getCallItANightDecision() {
        System.out.println("Do you want to call it a night? (yes/no):");
        String input = scanner.nextLine().trim().toLowerCase();
        while (!input.equals("yes") && !input.equals("no")) {
            System.out.println("Invalid input. Please enter 'yes' or 'no':");
            input = scanner.nextLine().trim().toLowerCase();
        }
        return input.equals("yes");
    }

    /**
     * Prompts the user to enter the time they went to bed last night in 24-hour format (HH:mm) and returns the input as a string.
     * If the input is not a valid time in 24-hour format, it prompts the user again until a valid input is provided.
     *
     * @return The time the user went to bed last night in 24-hour format.
     */
    public String getSleepTime() {
        System.out.println("Enter the time you went to bed last night in 24-hour format (HH:mm):");
        String input = scanner.nextLine().trim();

        // 24-hour format time
        Pattern pattern = Pattern.compile("^([01]\\d|2[0-3]):([0-5]\\d)$");
        Matcher matcher = pattern.matcher(input);

        while (!matcher.matches()) {
            System.out.println(
                    "Invalid time format. Please enter time in 24-hour format (HH:mm), e.g., '23:45' or '01:20':");
            input = scanner.nextLine().trim();
            matcher = pattern.matcher(input);
        }

        return input;
    }

    /**
     * Prompts the user to select a nightmare (1/2) and returns the input as an integer.
     * If the input is not 1 or 2, it prompts the user again until a valid input is provided.
     *
     * @return The nightmare selected by the user.
     */
    public int getNightmareSelection() {
        System.out.println("Select a nightmare:");
        System.out.println("1. Wolf");
        System.out.println("2. Bump in the Night");
        System.out.print("Enter your choice (1-2): ");

        int selection = 0;
        boolean validSelection = false;

        while (!validSelection) {
            if (scanner.hasNextInt()) {
                selection = scanner.nextInt();
                validSelection = selection >= 1 && selection <= 2;
            } else {
                scanner.next(); // Consume non-integer input
            }
            if (!validSelection) {
                System.out.println("Invalid choice. Please select a number between 1 and 2:");
            }
        }
        scanner.nextLine(); // Consume newline

        return selection;
    }

    /**
     * Prompts the user to enter a decision to activate a tile (yes/no) and returns a boolean value.
     * It also displays the tile information before prompting for the decision.
     *
     * @param tileInformation The information about the tile.
     * @return True if the user wants to activate the tile, false otherwise.
     */
    public boolean getActivateTileDecision(String tileInformation) {
        System.out.println("You landed on the following tile: " + tileInformation);
        System.out.println("Do you want to activate this tile? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();

        while (!input.equals("yes") && !input.equals("no")) {
            System.out.println("Invalid input. Please enter 'yes' or 'no': ");
            input = scanner.nextLine().trim().toLowerCase();
        }

        return input.equals("yes");
    }

    /**
     * Prompts the user to enter a decision to place a new tile or catch zzzs (1/2) and returns a boolean value.
     *
     * @return True if the user wants to place a new tile, false if the user wants to catch zzzs.
     */
    public boolean getRestingMoveDecision() {
        System.out.println("Do you want to place a new tile or catch zzzs? (1/2): ");
        String input = scanner.nextLine().trim().toLowerCase();

        while (!input.equals("1") && !input.equals("2")) {
            System.out.println("Invalid input. Please enter '1' or '2': ");
            input = scanner.nextLine().trim().toLowerCase();
        }

        return input.equals("1");
    }

    /**
     * Prompts the user to enter the index of the tile they want to place (1-numOfTiles) and returns the input as an integer.
     * If the input is not a valid index, it prompts the user again until a valid input is provided.
     *
     * @param numOfTiles The number of tiles available to choose from.
     * @return The index of the tile selected by the user.
     */
    public int getTileSelection(int numOfTiles) {
        System.out.println("Enter the index of the tile you want to place (1-" + numOfTiles + "): ");
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        while (input < 1 || input > numOfTiles) {
            System.out.println("Invalid input. Please enter an index between 1 and " + numOfTiles + ": ");
            input = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
        }

        return input;
    }

    /**
     * Prompts the user to enter a decision to catch ZZZs onto only one tile (yes/no) and returns a boolean value.
     *
     * @return True if the user wants to catch ZZZs onto only one tile, false otherwise.
     */
    public boolean getCatchZzzsDecision() {
        System.out.println("Do you want to catch ZZZs onto only one tile? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();

        while (!input.equals("yes") && !input.equals("no")) {
            System.out.println("Invalid input. Please enter 'yes' or 'no': ");
            input = scanner.nextLine().trim().toLowerCase();
        }

        return input.equals("yes");
    }

    /**
     * Prompts the user to enter the index of the tile they want to catch ZZZs onto and returns the input as an integer.
     *
     * @return The index of the tile selected by the user.
     */
    public int getCatchTileIndex() {
        System.out.println("Enter the index of the tile you want to catch ZZZs onto: ");
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        return input;
    }

    /**
     * Prompts the user to enter a selection for an OR combo (1/2) and returns the input as an integer.
     * If the input is not 1 or 2, it prompts the user again until a valid input is provided.
     *
     * @return The selection made by the user.
     */
    public int getOrComboSelection() {
        System.out.println("Enter 1 for action 1 or 2 for action 2: ");
        int input = -1;
        boolean validInput = false;
        
        while (!validInput) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                
                if (input == 1 || input == 2) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter 1 or 2: ");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer: ");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        
        return input;
    }
}
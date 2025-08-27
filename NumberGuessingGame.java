import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    // ansi escape codes for colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // colors for the guessing game attempts
    public static final String[] ATTEMPT_COLORS = { ANSI_CYAN, ANSI_YELLOW, ANSI_BLUE, ANSI_GREEN, ANSI_PURPLE };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        // ask for the user's name
        System.out.print(ANSI_YELLOW + "Please enter your name: " + ANSI_RESET);
        String playerName = sc.nextLine();

        int totalScore = 0;
        int rounds = 3;
        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 10; // number of attempts is 10

        
        System.out.println(ANSI_PURPLE + "\n=== Welcome to the Number Guessing Game, " + playerName + "! ===" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "Are you ready to play? You will have " + rounds + " rounds." + ANSI_RESET);
        System.out.println("----------------------------------------");

        for (int round = 1; round <= rounds; round++) {
            int numberToGuess = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;
            boolean hasGuessed = false;

            System.out.println(ANSI_CYAN + "\nRound " + round + " - Guess a number between " + lowerBound + " and " + upperBound + ANSI_RESET);
            System.out.println(ANSI_WHITE + "You have " + maxAttempts + " attempts." + ANSI_RESET);

            while (attempts < maxAttempts && !hasGuessed) {
                
                String promptColor = ATTEMPT_COLORS[attempts % ATTEMPT_COLORS.length];
                System.out.print(promptColor + "Enter your guess (" + (attempts + 1) + "/" + maxAttempts + "): " + ANSI_RESET);
                
                int guess;
                try {
                    guess = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(ANSI_RED + "Invalid input. Please enter a number." + ANSI_RESET);
                    continue; // ask for input again
                }
                attempts++;

                if (guess == numberToGuess) {
                    System.out.println(ANSI_GREEN + "🎉 Correct! You guessed it in " + attempts + " attempts!" + ANSI_RESET);
                    int points = (maxAttempts - attempts + 1) * 10;
                    totalScore += points;
                    System.out.println(ANSI_GREEN + "You earned " + points + " points this round." + ANSI_RESET);
                    hasGuessed = true;
                } else if (guess < numberToGuess) {
                    System.out.println("Too low! Try again."); 
                } else {
                    System.out.println("Too high! Try again."); 
                }
            }

            if (!hasGuessed) {
                System.out.println(ANSI_RED + "❌ Out of attempts! The number was: " + numberToGuess + ANSI_RESET);
            }
        }

        System.out.println(ANSI_PURPLE + "\n=== Game Over ===" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "Your total score: " + totalScore + ANSI_RESET);
        System.out.println(ANSI_WHITE + "Thanks for playing, " + playerName + "!" + ANSI_RESET);
        sc.close();
    }
}

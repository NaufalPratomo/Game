import java.util.Scanner;

public class HangmanGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Hangman Game ===");

        // Player 1 enters the word
        System.out.println("Player 1, enter the word to be guessed: ");
        String secretWord = scanner.nextLine().toLowerCase();

        clearScreen(); // Clear the screen

        // Create an array to store the guessed word's characters
        char[] guessedWord = new char[secretWord.length()];
        for (int i = 0; i < secretWord.length(); i++) {
            guessedWord[i] = '_';
        }

        int attempts = 6; // Number of incorrect attempts allowed

        while (attempts > 0) {
            // Display the current state of the guessed word
            System.out.println("Current word: " + String.valueOf(guessedWord));

            // Player 2 guesses a letter
            System.out.println("Player 2, guess a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            boolean correctGuess = false;

            // Check if the guessed letter is in the secret word
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == guess) {
                    guessedWord[i] = guess;
                    correctGuess = true;
                }
            }

            // Check if the word has been completely guessed
            if (String.valueOf(guessedWord).equals(secretWord)) {
                System.out.println("Congratulations! You guessed the word: " + secretWord);
                break;
            }

            // If the guess was incorrect, decrement attempts
            if (!correctGuess) {
                attempts--;
                System.out.println("Incorrect guess. Attempts left: " + attempts);
            }
        }

        // Display the result
        if (attempts == 0) {
            System.out.println("Out of attempts! The word was: " + secretWord);
        }

        scanner.close();
    }

    // Clear screen function
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

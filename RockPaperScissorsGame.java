import java.util.Scanner;

public class RockPaperScissorsGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Rock, Paper, Scissors Game ===");

        while (true) {
            System.out.println("Player 1, enter your choice (Rock, Paper, Scissors): ");
            String player1Choice = scanner.nextLine().toLowerCase();

            System.out.println("Player 2, enter your choice (Rock, Paper, Scissors): ");
            String player2Choice = scanner.nextLine().toLowerCase();

            System.out.println("Player 1 chose: " + player1Choice);
            System.out.println("Player 2 chose: " + player2Choice);

            String result = determineWinner(player1Choice, player2Choice);
            System.out.println(result);

            System.out.println("Do you want to play again? (yes/no): ");
            String playAgain = scanner.nextLine().toLowerCase();

            if (!playAgain.equals("yes")) {
                break;
            }
        }

        scanner.close();
    }

    private static String determineWinner(String choice1, String choice2) {
        if (choice1.equals(choice2)) {
            return "It's a tie!";
        }

        if ((choice1.equals("rock") && choice2.equals("scissors")) ||
            (choice1.equals("scissors") && choice2.equals("paper")) ||
            (choice1.equals("paper") && choice2.equals("rock"))) {
            return "Player 1 wins!";
        } else {
            return "Player 2 wins!";
        }
    }
}

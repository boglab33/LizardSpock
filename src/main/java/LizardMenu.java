import java.util.Scanner;
import java.util.stream.IntStream;

class LizardMenu {
    private final LizardGame lizardGame;
    private final LizardAI lizardAI;
    private final Scanner scanner;

    public LizardMenu(String[] gameClasses) {
        lizardGame = new LizardGame(gameClasses);
        lizardAI = new LizardAI(lizardGame);
        scanner = new Scanner(System.in);
    }

    /*Shows menu in a loop till 0 is entered*/
    public void start() {
        boolean gameOn = true;
        while (gameOn) {
            printMenu();
            gameOn = playGame(validUserInput());
        }
    }

    private boolean playGame(int userInput) {
        if (userInput == 0) {
            System.out.println("Thank you for the game, bye!");
            return false;
        }
        showMoves(userInput);
        int winner = lizardGame.checkWin(lizardAI.getMove(), userInput);
        showResult(userInput, winner);
        return true;
    }

    private void showResult(int userInput, int winner) {
        if (winner == userInput) {
            System.out.println("You win!");
        } else if (winner == 0) {
            System.out.println("It`s a tie!");
        } else {
            System.out.println("You loose!");
        }
        System.out.println("HMAC key: " + lizardAI.getSharedKey());
    }

    private void showMoves(int userInput) {
        System.out.println("Your move: " + lizardGame.getClass(userInput));
        System.out.println("Computer move: " + lizardGame.getClass(lizardAI.getMove()));
    }

    private int validUserInput() {
        System.out.println("Enter your move: ");
        int input;
        try {
            input = getUserInput();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return validUserInput();
        }
        return input;
    }

    private int getUserInput() {
        String input = scanner.nextLine();
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException("Please enter a number!");
        }
        int inputAsInt = Integer.parseInt(input);
        if (inputAsInt < 0 || inputAsInt > lizardGame.getLength()) {
            throw new IllegalArgumentException("You have entered a wrong number!");
        }
        return inputAsInt;
    }

    private void printMenu() {
        System.out.println("\nHMAC: " + lizardAI.getHMAC());
        IntStream.rangeClosed(1, lizardGame.getLength())
                .forEach(a -> System.out.printf("%d - %s\n", a, lizardGame.getClass(a)));
        System.out.println("0 - exit");
    }
}

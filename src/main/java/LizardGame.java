import java.util.Arrays;

class LizardGame {
    private final String[] gameClasses;


    public LizardGame(String[] gameClasses) {
        validArray(gameClasses);
        this.gameClasses = gameClasses;
    }

    private void validArray(String[] gameClasses) {
        checkSize(gameClasses);
        checkIntegrity(gameClasses);
    }

    private void checkIntegrity(String[] gameClasses) {
        if (gameClasses.length != Arrays.stream(gameClasses).distinct().count()) {
            throw new IllegalArgumentException("Arguments are not unique!");
        }
    }

    private void checkSize(String[] gameClasses) {
        if (gameClasses.length % 2 == 0 || gameClasses.length < 3) {
            throw new IllegalArgumentException("Amount of arguments is not valid!");
        }
    }

    /*Returns amount of game classes*/
    public int getLength() {
        return gameClasses.length;
    }

    /*Receive value starting from 1, like printed in main menu*/
    public String getGameClass(int index) {
        return gameClasses[index - 1];
    }

    /*Checks who is the winner from a pair of moves.
     * In the case of a tie returns 0
     * Returns index of the winner*/
    public int checkWin(int computer, int player) {
        if (computer == player) {
            return 0;
        }
        int half = gameClasses.length / 2;
        if (computer < half) {
            return (player > computer && player < computer + half) ? player : computer;
        } else {
            return (player < computer && player > computer - half) ? computer : player;
        }
    }
}

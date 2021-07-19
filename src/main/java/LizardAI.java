import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;

class LizardAI {
    private static final int SHARED_KEY_SIZE = 16;
    private final LizardGame lizardGame;
    private final byte[] privateKey;
    private int move;
    private final SecureRandom random;

    public LizardAI(LizardGame lizardGame) {
        this.lizardGame = lizardGame;
        privateKey = new byte[SHARED_KEY_SIZE];
        random = new SecureRandom();

    }

    /*Returns index of the move in the lizardGame array*/
    public int getMove() {
        return move;
    }

    /*Generates unique privateKey and HMAC out of it and the move string using SHA_384*/
    public String getHMAC() {

        random.nextBytes(privateKey);
        move = random.nextInt(lizardGame.getLength()) + 1;
        String computerChoice = lizardGame.getGameClass(move);
        HmacUtils hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_384, privateKey);
        return hmac.hmacHex(computerChoice);
    }

    /*Returns shared key in the hex format*/
    public String getSharedKey() {
        return DatatypeConverter.printHexBinary(privateKey);
    }
}

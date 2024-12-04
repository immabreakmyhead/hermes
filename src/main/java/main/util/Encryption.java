package main.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

    /**
     * Υπολογίζει το hash του δοθέντος string χρησιμοποιώντας τον αλγόριθμο MD5.
     * Επιστρέφει το hash σε κεφαλαίους χαρακτήρες.
     *
     * @param unhashed το string προς κατακερματισμό
     * @return το hash του δοθέντος string σε κεφαλαίους χαρακτήρες
     */
    public static String getHashMD5(String unhashed) {
        return getHashMD5(unhashed, "");
    }

    /**
     * Υπολογίζει το hash του δοθέντος string με αλάτι. Επιστρέφει το hash σε
     * κεφαλαίους χαρακτήρες.
     *
     * @param unhashed το string προς κατακερματισμό
     * @param salt     το αλάτι που θα χρησιμοποιηθεί
     * @return το hash του δοθέντος string με αλάτι σε κεφαλαίους χαρακτήρες
     */
    public static String getHashMD5(String unhashed, String salt) {
        // Κατακερματίζει τον κωδικό.
        final String toHash = salt + unhashed + salt;
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            return "00000000000000000000000000000000"; // Επιστροφή default τιμής σε περίπτωση που δεν βρεθεί ο
                                                       // αλγόριθμος MD5
        }
        messageDigest.update(toHash.getBytes(), 0, toHash.length());
        String hashed = new BigInteger(1, messageDigest.digest()).toString(16);
        if (hashed.length() < 32) {
            hashed = "0" + hashed;
        }
        return hashed.toUpperCase();
    }

}

package nl.bsoft.batch.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

public class CryptoHash {

    private static final Logger logger = LoggerFactory.getLogger(CryptoHash.class);

    public String getHash(final String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Change this to UTF-16 if needed
        md.update(text.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();

        String hex = String.format("%064x", new BigInteger(1, digest));
        return hex;
    }

    public static void main( String[] args) {
        String hash;
        String input = "My inputstring";
        CryptoHash ch = new CryptoHash();
        try {
            hash = ch.getHash(input);
            logger.info("Hash from input: {} is: {}", input, hash);
        } catch (Exception e) {

        }
    }
}
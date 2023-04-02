package ru.ntdv.proicis.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordManager {
    final static String backpack = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz#$_+=";
    final static SecureRandom random;

    static {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            try {
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            }
        }
        random = secureRandom;
    }

    private static String generateRandomPassword(int len) {
        final StringBuilder password = new StringBuilder(len);
        for (int i = 0; i < len; i++) password.append(backpack.charAt(random.nextInt(backpack.length())));
        return password.toString();
    }

    public static String generateRandomPassword() {
        return generateRandomPassword(random.nextInt(9, 11));
    }
}

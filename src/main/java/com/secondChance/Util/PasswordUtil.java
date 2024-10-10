package com.secondChance.Util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Hash a plain text password
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Verify a password by comparing the input password (hashed) with the stored hash
    public static boolean verifyPassword(String inputPassword, String storedHashedPassword) {
        return BCrypt.checkpw(inputPassword, storedHashedPassword);
    }
}

package controller.password.encryption;

import controller.exception.handler.ExceptionHandler;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encryptor implements Encryptor {

    @Override
    public String getEncryptedPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashPassword = number.toString(16);
            while (hashPassword.length() < 32) {
                hashPassword = "0" + hashPassword;
            }
            return hashPassword;
        }
        catch (NoSuchAlgorithmException ex) {
            ExceptionHandler.instance().handleException(ex, MD5Encryptor.class.getName());
            return null;
        }
    }
    
}

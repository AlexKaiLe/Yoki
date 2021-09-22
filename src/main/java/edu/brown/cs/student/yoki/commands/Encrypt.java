package edu.brown.cs.student.yoki.commands;
import edu.brown.cs.student.yoki.driver.TriggerAction;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

/**
 * This class utilizes encryption and decryption of various inputs.
 */
public class Encrypt implements TriggerAction {
  //create global variables
  private static SecretKeySpec secretKey;
  private static byte[] key;
  private static final int NEW_LEN = 16;
  /**
   * Implements the trigger action class, thus overrides the method action and
   * executes the following code when the encrypt command is inputted.
   * @param args List of strings from the command line
   */
  @Override
  public void action(ArrayList<String> args) throws SQLException, ClassNotFoundException {
    if (args.size() == 3) {
      String encryptionKey = args.get(1);
      String argument = args.get(2);
      setKey(encryptionKey);
      System.out.println("Encryption key: " + encryptionKey);
      System.out.println("Original message: " + argument);
      String encyrptedMessage = encrypt(argument, encryptionKey);
      System.out.println("Encrypted message: " + encyrptedMessage);
      System.out.println("Decrypted message: " + decrypt(encyrptedMessage, encryptionKey));
    } else {
      System.err.println("ERROR: the encrypt command must "
          + "follow the form <[encrypt] [key] [string]>");
    }
  }

  /**
   * This method sets the key of the encryption.
   * @param hiddenKey key
   */
  public static void setKey(String hiddenKey) {
    MessageDigest mssg = null;
    try {
      key = hiddenKey.getBytes("UTF-8");
      mssg = MessageDigest.getInstance("SHA-1");
      key = mssg.digest(key);
      key = Arrays.copyOf(key, NEW_LEN);
      secretKey = new SecretKeySpec(key, "AES");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method encrypts a string.
   * @param strToEncrypt the string to encrypt
   * @param hidden the secret key
   * @return the encrypted string
   */
  public static String encrypt(String strToEncrypt, String hidden) {
    try {
      setKey(hidden);
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
      return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    } catch (Exception e) {
      System.out.println("Error while encrypting: " + e.toString());
    }
    return null;
  }

  /**
   * This method decrypts the inputted string given a correct key.
   * @param strToDecrypt encrypted string
   * @param secret secrete key
   * @return decrypted String
   */
  public static String decrypt(String strToDecrypt, String secret) {
    try {
      setKey(secret);
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, secretKey);
      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    } catch (Exception e) {
      System.out.println("Error while decrypting: " + e.toString());
    }
    return null;
  }
}

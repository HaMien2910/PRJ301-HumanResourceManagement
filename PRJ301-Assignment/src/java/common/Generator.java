/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.Random;

/**
 *
 * @author PhuongNH
 */
public class Generator {

    /**
     *
     *@return a random string [a-zA-Z0-0-9] with length is 8
     */
    public static String generateRandomPassword() {
        Random random = new Random();
        int lengthOfPassword = 8;
        StringBuilder characters = new StringBuilder("");
        StringBuilder password = new StringBuilder("");
        // Loop to append digits at the end of characters 
        for (char i = '0'; i <= '9'; i++) {
            characters.append(i);
        }
        // Loop to append abphabet lowercase at the end of String characters
        for (char i = 'a'; i <= 'z'; i++) {
            characters.append(i);
        }
        // Loop to append abphabet uppercase at the end of String characters 
        for (char i = 'A'; i <= 'Z'; i++) {
            characters.append(i);
        }
        // Loop to get random captcha with the length = 8
        for (int i = 0; i < lengthOfPassword; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    public static String generateEmailPrivacy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

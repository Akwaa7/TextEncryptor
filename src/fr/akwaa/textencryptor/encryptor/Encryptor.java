package fr.akwaa.textencryptor.encryptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Créé le 23/01/2022 à 14h31 par valen
 **/

public class Encryptor {
    public static String encrypt(String input, String key){
        String binaryInput = toBinaryString(input);
        String binaryKey = toBinaryString(key);

        List<String> encryptedBytes = new ArrayList<>();
        StringBuilder currentByteBuilder = new StringBuilder();
        int currentBitIndex = 0;
        int keyIndex = 0;
        for(char c : binaryInput.toCharArray()){
            if(Integer.parseInt(String.valueOf(c)) + Integer.parseInt(String.valueOf(binaryKey.charAt(keyIndex))) == 1){
                currentByteBuilder.append("1");
            }else{
                currentByteBuilder.append("0");
            }
            currentBitIndex++;
            if(currentBitIndex >= 8){
                encryptedBytes.add(currentByteBuilder.toString());
                currentByteBuilder = new StringBuilder();
                currentBitIndex = 0;
            }
            keyIndex++;
            if(keyIndex >= binaryKey.length()) keyIndex = 0;
        }

        StringBuilder output = new StringBuilder();
        for(String encryptedByte : encryptedBytes){
            output.append(Character.valueOf((char) Integer.parseInt(encryptedByte, 2)).toString());
        }

        return output.toString();
    }

    private static String toBinaryString(String string){
        StringBuilder binaryStringBuilder = new StringBuilder();

        for(Byte b : string.getBytes()){
            String binaryString = Integer.toBinaryString(b);
            binaryStringBuilder.append("0".repeat(Math.max(0, 8 - binaryString.length())));
            binaryStringBuilder.append(binaryString);
        }

        return binaryStringBuilder.toString();
    }
}
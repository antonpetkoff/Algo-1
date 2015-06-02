package encryption;

import java.util.HashMap;
import java.util.Map;

public class EncryptedMessage {

    public static Map<Character, Integer> generateAlphabet(String alphabet) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        
        for (int i = 0; i < alphabet.length(); ++i) {
            map.put(alphabet.charAt(i), i);
        }
        
        return map;
    }

    public static String decrypt(String msg) {
        msg = msg.substring(msg.length() / 2) + msg.substring(0, msg.length() / 2);
        int firstTildePos = msg.indexOf('~'), secondTildePos = msg.indexOf('~', firstTildePos + 1);
        int alphabetLength = Integer.valueOf(msg.substring(0, firstTildePos));
        String alphabet = msg.substring(firstTildePos + 1, firstTildePos + alphabetLength + 1);
        Map<Character, Integer> map = generateAlphabet(alphabet);
        int keyLength = Integer.valueOf(msg.substring(1 + secondTildePos));
        String key = msg.substring(secondTildePos - keyLength, secondTildePos);
        String encrypted = msg.substring(firstTildePos + alphabetLength + 1, secondTildePos - keyLength);

        StringBuilder decrypted = new StringBuilder();
        int newIndex = 0;

        for (int msgIndex = 0, keyIndex = 0; msgIndex < encrypted.length(); ++msgIndex, ++keyIndex) {
            if (keyIndex == keyLength) {
                keyIndex = 0;
            }
            
            newIndex = map.get(encrypted.charAt(msgIndex)) - map.get(key.charAt(keyIndex));
            while (newIndex < 0) {
                newIndex += alphabetLength;
            }
            
            decrypted.append(alphabet.charAt(newIndex));
        }

        return decrypted.toString();
    }

    public static void main(String[] args) {
        System.out.println(decrypt("vrItommseIal vihack~416~Ilocveakgrithms he"));
        System.out.println(decrypt("rc hscesi tcrest~410~thisaecr .rcese"));
        System.out.println(decrypt("fl k.ccfsIolskv.~312~ .Ifrckslovelvo"));
        System.out.println(decrypt("o?uin uw?stutnfwat?~413~orwa? thfuisnnrsiu"));
    }

}

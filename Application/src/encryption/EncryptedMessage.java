package encryption;

public class EncryptedMessage {

    public static String decrypt(String msg) {
        msg = msg.substring(msg.length() / 2) + msg.substring(0, msg.length() / 2);
        int firstTildePos = msg.indexOf('~'), secondTildePos = msg.indexOf('~', firstTildePos + 1);
        int alphabetLength = Integer.valueOf(msg.substring(0, firstTildePos));
        String alphabet = msg.substring(firstTildePos + 1, firstTildePos + 1 + alphabetLength);
        int keyLength = Integer.valueOf(msg.substring(1 + secondTildePos));
        String key = msg.substring(secondTildePos - keyLength, secondTildePos);
        String encrypted = msg.substring(firstTildePos + alphabetLength + 1, secondTildePos - keyLength);
        
        System.out.println(msg);
        System.out.println(alphabetLength);
        System.out.println(alphabet);
        System.out.println(keyLength);
        System.out.println(key);
        System.out.println(encrypted);
        
        return "asd";
    }
    
    public static void main(String[] args) {
        System.out.println(decrypt("vrItommseIal vihack~416~Ilocveakgrithms he"));
        
        System.out.println();
        System.out.println(decrypt("rc hscesi tcrest~410~thisaecr .rcese"));
    }
    
}

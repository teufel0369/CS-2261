package Encrypt;

import java.util.Random;
import java.lang.StringBuilder;


public class Message {
    private String plainText; //plain text version of the message.

    public Message() {

    }

    public Message(String pT){ //constructor
        this.plainText = pT;
    }



    private String generateKey(){
        StringBuilder stringBuilder = new StringBuilder();
        Random rand = new Random();
        int alphaMin = 97;
        int alphaMax = 122;
        int charMin = 33;
        int charMax = 47;
        int intMin = 2;
        int intMax = 5;

        int alphaRand1 = rand.nextInt((alphaMax - alphaMin) + 1) + alphaMin; //Generate random int for alpha numeric
        int alphaRand2 = rand.nextInt((alphaMax - alphaMin) + 1) + alphaMin; //Generate random int for alpha numeric
        int alphaRand3 = rand.nextInt((alphaMax - alphaMin) + 1) + alphaMin; //Generate random int for alpha numeric
        int alphaRand4 = rand.nextInt((alphaMax - alphaMin) + 1) + alphaMin; //Generate random int for alpha numeric
        int charRand1 = rand.nextInt((charMax - charMin) + 1) + charMin; //Generate random int for special characters
        int charRand2 = rand.nextInt((charMax - charMin) + 1) + charMin; //Generate random int for special characters
        int intRand = rand.nextInt((intMax - intMin) + 1) + intMin; //Generate random int for integers

        stringBuilder.append((char)alphaRand1); //appends x1
        stringBuilder.append((char)alphaRand2); //appends x2
        stringBuilder.append("-"); //appends the hyphen
        stringBuilder.append((char)charRand1); //appends y1
        stringBuilder.append((char)alphaRand3); //appends x3
        stringBuilder.append((char)alphaRand4); //appends x4
        stringBuilder.append("-"); //appends the hyphen
        stringBuilder.append(intRand); //appends the random integer
        stringBuilder.append((char)charRand2); //appends y2

        String cipher = stringBuilder.toString(); //converts the stringbuilder to a string

        return cipher; //returns the string
    } //works to spec

    private boolean verifyKey(String key, String cipher){

        if(key.equals(cipher)){ //validates the input against the cipher and returns true
            return true;
        }
        else { //otherwise return false
            return false;

        }
    }

    private String encryptText(String pT, String cipher){
        StringBuilder builder2 = new StringBuilder();
        String encryptedSS = "";

        cipher = cipher.replaceAll("-", ""); //replaces all the hyphens

        char[] cipherKey = cipher.toCharArray(); //converts the cipherKey to a character array

        char alphaRand1 = cipherKey[0]; //assigns x1
        char alphaRand2 = cipherKey[1]; //assigns x2
        char alphaRand3 = cipherKey[3]; //assigns x3
        char alphaRand4 = cipherKey[4]; //assigns x4
        char charRand1 = cipherKey[2]; //assigns y1
        char charRand2 = cipherKey[6]; //assigns y2
        int intRand = cipherKey[5]; //assigns z

        pT = pT.toLowerCase(); //converts the string to lower case

        String[] strArr = pT.split(" "); //splits the string into an array


        for(String subStr : strArr){ //for each substring in the string array
            char first = subStr.charAt(0);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(subStr);

            if((first=='a') || (first == 'e') || (first == 'i') || (first == 'o') || (first == 'u')){ //starts with a vowel
                stringBuilder.append(charRand1); //appends y1 to the end of the string
                stringBuilder.append(alphaRand3); //appends x3 to the end of the string
                stringBuilder.append(alphaRand4); //appends x4 to the end of the string
                stringBuilder.append(" ");
                encryptedSS = stringBuilder.toString(); //converts stringbuilder back to string
            }
            else{ //starts with a consonant
                stringBuilder.deleteCharAt(0); //deletes the first character
                stringBuilder.append(first); //appends the first character to the end of the word
                stringBuilder.append(alphaRand1); //append x1 to the end of the word
                stringBuilder.append(alphaRand2); //append x2 to the end of the word*/
                stringBuilder.append(" ");

                encryptedSS = stringBuilder.toString(); //converts string builder back to an array
            }

            builder2.append(encryptedSS); //appends the encrypted substring to the stringbuilder
        }

        int i = intRand; //sets i equal to z

        while(i < builder2.length()){ //while i is less than the string length
            builder2.insert(i, charRand2); //inserts y2 through out the string
            i += i;
        }

        String encrypted = builder2.toString(); //converts entire stringbuilder to string

        return encrypted; //returns the string
    }

    private String decryptText(String encrypted, String cipher){
        StringBuilder stringBuilder = new StringBuilder(); //for manipulating the substrings
        StringBuilder builder2 = new StringBuilder(); //StringBuilder object for returning and accumulating instances of decrypted
        String decryptedSS = "";

        cipher = cipher.replaceAll("-", ""); //replaces all the hyphens
        char[] cipherKey = cipher.toCharArray(); //converts the cipherKey to a character array
        char y1 = cipherKey[2];
        char y2 = cipherKey[6];

        String[] strArr = encrypted.split(" "); //splits the string into an array

        for(String subStr : strArr){ //for each substring in the string array

            stringBuilder.append(subStr); //copies the substring into a String Builder object

            stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length()-1); //deletes the last character
            stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length()-1); //deletes the last character

            char first = stringBuilder.charAt(stringBuilder.length()-1); //copies the last character for prepending to the word if the word started with a consonant


            if(stringBuilder.charAt(stringBuilder.length()-1) == y1){ //if the last character is equal to y1
                stringBuilder.deleteCharAt(stringBuilder.length()-1); //delete it

            }
            else{ //******The problem resides in this else statement
                stringBuilder.deleteCharAt(stringBuilder.length()-1); //delete the last character
                stringBuilder.insert(0, first); //insert the copied character at the beginning of the substring
            }


            stringBuilder.append(" "); //appends a space to each word
            decryptedSS = stringBuilder.toString(); //converts the StringBuilder object to a string
            stringBuilder.setLength(0); //clears the StringBuilder object
            builder2.append(decryptedSS); //appends
        }

        String decrypted = builder2.toString(); //converts the StringBuilder object to a string

        return decrypted; //returns the decrypted string

    }

    public void setPlainText(String pT) {
        this.plainText = pT;
    }

    public String getPlainText() { return plainText; }

    public String getKey() {
        String cipher = generateKey(); //generates the secret key

        return cipher; //returns the key
    }

    public boolean authentication(String key, String cipher) { //helper function for authentication
        boolean confirmation = verifyKey(key, cipher); //passes in the key and the cipher to check against in private function

        return confirmation;
    }

    public String getEncryption(String pT, String cipher){ //helper function to encrypt the string
        String encrypted = encryptText(pT, cipher); //passes in the plain text and cipher for encryption purposes

        return encrypted; //returns the encrypted string
    }

    public String getDecryption(String encrypted, String cipher){ //helper function to assist in decryption
        String decrypted = decryptText(encrypted, cipher); //passes in the encrypted string and the cipher for decryption purposes

        return decrypted; //returns the decrypted string
    }
}

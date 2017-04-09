/******************************************************************************
 * Author       : Christopher Thompson
 * Course       : CS 2261 (Fall 2016)
 * Last Modified: 30 October 2016
 *
 * Assignment   : Project 3 Encryption
 * Summary      : This program will generate a secret key which will be used to
 * encrypt and decrypt a message sent between two clients
 ******************************************************************************/

package Encrypt;

import static java.lang.System.out;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args){
        Message message; //new message object
        Scanner input = new Scanner(System.in);
        String s;
        char choice = 'x';

        out.print("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM8 ...........=MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM, ............... DMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMD ....................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM,..?....................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMD..7......................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM..M .......................MMMMMMMMMMMMMMMMMMMMMMMM.MMMMMMMMMMMM\n" +
                "MMMMMMMMM.MMMMMMMMMMMMMMMMMMMMMMMMMM..M .......................MMMMMMMMMMMMMMMMMMMMMMM~.MMMMMMMMMMMM\n" +
                "MMMMMMMMM .MMMMMMMMMMMMMMMMMMMMMMMMM    .......................MMMMMMMMMMMMMMMMMMMMMM: :MMMMMMMMMMMM\n" +
                "MMMMMMMMM.. MMMMMMMMMMMMMMMMMMMMMMMN.M.....NMM......,MMM.......MMMMMMMMMMMMMMMMMMMMM  .MMMMMMMMMMMMM\n" +
                "MMMMMMMMMM... MMMMMMMMMMMMMMMMMMMMMM.M...MMMMMMM ..MMMMMMM.~..NMMMMMMMMMMMMMMMMMMMM...MMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMM....MMMMMMMMMMMMMMMMMMMMM.I..MMMMMMMM ..MMMMMMM.= .MMMMMMMMMMMMMMMMMMMI ...MMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMO ...+MMMMMMMMMMMMMMMMMMM.M .MMMMMMM .M. MMMMMM.+.MMMMMMMMMMMMMMMMMMM.....MMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMM. ....MMMMMMMMMMMMMMMMMMM+...MMMMM..7MM .MMMM...MMMMMMMMMMMMMMMMMMM ....MMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMM  .... MMMMMMMMMMMMMMMMM..... .....MMMM...  ....MMMMMMMMMMMMMMMM  ....MMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMM=......MMMMMMMMMMMMMMM. .........ZMMMM.......  MMMMMMMMMMMMMMM......MMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMM ..... MMMMMMMMMMMMMM ..MM ....  .OO....ZMMMMMMMMMMMMMMMMM.......MMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMM  ..... MMMMMMMMMMMMMM8.MM,...........MMMMMMMMMMMMMMMMM$...... MMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMM,...... .MMMMMMMMMMMMI. MM=.........MM~ MMMMMMMMMMMMM  .....OMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMM........+MMMMMMMMMMM .MMM M M.M.,MM$ .MMMMMMMMMMM........MMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMM~ ........MMMMMMMMM..,MMMZ M.=.=.M ..MMMMMMMMM .......OMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMM......... MMMMMMM...MM M7M NM:MO...MMMMMMM. . M....MMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMM  ........MMMMMN. .O.7 M I$.7...?MMMMM....M....MMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMM ........ NMMMM  ............MMMMM  . M. .. MMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMM. ....D  . MMMM. ........:MMMM ..8M ... MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM......M,...:MMMMMN~  .MMM ...MM ....MNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM ......N  ...MMMMMMM+... MM ... MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM .... .MM .. .MMM ..MM.. ...MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMM.....MMMMMMMM...... MM,.....MMMM8$$MMMMMMMMMMMMM....OMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMM....MMMMMMMMMM .......MMN . ...+MMMMMMMMMMMMMM... .MMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMM....MMMMMMMD.,.M. ..... .MMM  .... ,$MMMMMMMZ...MMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMM....MM+......MMN~MM. ......,MMM?  .......M....MMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMM.....MMMMMD.......?MMMMI....... .NMMMMMM ...:M ~  . + .. MMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMM...MM............MM......,MMMMMMMMMMMMM   .......M. ...M ......:M ...MMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMM.....M.......M.....MD..MMMMMMMMMMMMMMMMMMMMMMMM= ...M....M ......M....?MMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMM..ZM8+M.....?MMM....MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN...MMMMMMMM IN..MMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMM,....MMMMMMMMMM,...IMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM....MMMMMMMMM.=MMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM........MMMMMMMMMMMMMMMMMMMMM .. .....MMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM....$MMMMMMMMMMMMMMMMMMMMMMM.     MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");

        out.println("\n\n\t\t\t\tWELCOME TO JOLLY ROGER ENCRYPTION SERVICES!");

        out.println("\n\nEnter a message: ");
        s = input.nextLine(); //takes in a string
        message = new Message(s);

        out.println("\n");
        String secretKey = message.getKey(); //gets the secret

        out.print("Authenticating");
        for(int i = 0; i < 5; i++){
            try{
                out.print(". ");
                Thread.sleep(350);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        out.println();

        if(message.authentication(secretKey, secretKey) == true){ //authenticates the cipher
            out.println("Authentication Successful!");
        }
        else { out.println("Wrong Password"); }
        out.println("\n");

        out.print("Encrypting");
        for(int j = 0; j < 5; j++){
            try{
                out.print(". ");
                Thread.sleep(350);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        out.println();
        String encrypted = message.getEncryption(s, secretKey); //Encrypts the string

        out.print("\nSending");
        for(int j = 0; j < 5; j++){
            try{
                out.print(". ");
                Thread.sleep(350);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        out.println("\nSent!");
        out.println();

        out.print("\nReceiving");
        for(int i = 0; i < 5; i++){
            try{
                out.print(". ");
                Thread.sleep(350);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        out.println();

        out.println("Encrypted message received!\n\n");
        out.println("Encrypted Message: ");
        out.println(encrypted);
        out.println("\n");

        out.print("Decrypting");
        for(int i = 0; i < 5; i++){
            try{
                out.print(". ");
                Thread.sleep(350);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        out.println("\n");

        String decrypted = message.getDecryption(encrypted, secretKey); //decrypts the string
        out.println("\nDecrypted Message:");
        out.println(decrypted);

        out.print("\nCipher: ");
        out.print(secretKey); //prints the secret cipher
    }
}




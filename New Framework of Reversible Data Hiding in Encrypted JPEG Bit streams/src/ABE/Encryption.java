package ABE;

 
import java.security.*;
import javax.crypto.*;
import sun.misc.*;

public class Encryption
{
	
	public static String encrypt(String Data) throws Exception
	{
	        Key key = ABEKey.generateKey();
	        Cipher c = Cipher.getInstance("AES");
	        c.init(Cipher.ENCRYPT_MODE, key);
	        byte[] encVal = c.doFinal(Data.getBytes());
	        String encryptedValue = new BASE64Encoder().encode(encVal);
	        return encryptedValue;
	  }
	
	public static String decrypt(String encryptedData) throws Exception 
	{
        Key key = ABEKey.generateKey();
        System.out.println("key...!!!!  "+key);
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        //System.out.println("key is :::"+ABEKey.generateKey());
        return decryptedValue;
	}
	
	/*public static void main(String s[]) throws Exception
	{
		String str="JAVA";
		String encrpt=Encryption.encrypt(str);
		System.out.println("Encrypted Text...!!!!   "+encrpt);

		String decrypt=Encryption.decrypt(encrpt);
		System.out.println(decrypt);
	}*/
 
}

import java.util.Base64;
import java.util.Scanner;
import javax.crypto.*;

class DESp{
	public static SecretKey keygen() throws Exception{
		KeyGenerator key = KeyGenerator.getInstance("DES");
		//key.init(128);
		return key.generateKey();
	}
	public static String encrypt(SecretKey key,String text) throws Exception{
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE,key);
		return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes()));
	}
	public static String decrypt(SecretKey key,String text) throws Exception{
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE,key);
		return new String(cipher.doFinal(Base64.getDecoder().decode(text)));
	}
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the text to be encrypted:");
		String text = sc.nextLine();
		SecretKey key = keygen();
		System.out.println("Key: " + Base64.getEncoder().encodeToString(key.getEncoded()));
		String enc = encrypt(key,text);
		String dec = decrypt(key,enc);
		System.out.println("Encrypted: "+enc);
		System.out.println("Decrypted: "+dec);
	}

}




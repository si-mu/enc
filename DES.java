import javax.crypto.*;
import java.util.*;
class DES{
	static SecretKey keyGen() throws Exception{
		KeyGenerator key = KeyGenerator.getInstance("DES");
		return key.generateKey();
	}
	static String encrypt(String text,SecretKey key) throws Exception{
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE,key);
		return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes()));
	}
	static String decrypt(String text,SecretKey key) throws Exception{
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE,key);
		return new String(cipher.doFinal(Base64.getDecoder().decode(text)));
	}
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a text to encrypt:");
		String text = sc.nextLine();
		SecretKey key = keyGen();
		String enc = encrypt(text,key);
		String dec = decrypt(enc,key);
		System.out.println("The encrypted text is: "+enc+" and the decrypted text is : "+dec);
	}
}

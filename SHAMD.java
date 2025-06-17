import java.security.*;
import java.util.Scanner;

class SHAMD{
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		String text = sc.nextLine();
		try{
			MessageDigest msgdigest = MessageDigest.getInstance("MD5");
			byte[] md = msgdigest.digest(text.getBytes());
			for(byte b:md){
				System.out.printf("%02x",b);
			}
		}catch(NoSuchAlgorithmException e){
			System.out.println(e);
		}
	}
}

import java.util.*;
class caeser{
	static String transform(String text,int shift){
		shift = shift % 26;
		StringBuilder res = new StringBuilder();
		for(char c : text.toCharArray()){
			if(Character.isLetter(c)){
				char base = (Character.isUpperCase(c)) ? 'A' : 'a';
				res.append((char)((c-base+shift)%26 + base));
			}else{
				res.append(c);
			}
		}
		return res.toString();
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the text to encrypt:");
		String text = sc.nextLine();
		System.out.println("Enter the shift value:");
		int shift = sc.nextInt();
		String enc = transform(text,shift);
		String dec = transform(enc,26-(shift%26));
		System.out.println("The encrypted text is: "+enc+" and the decrypted text is: "+dec);
	}
}

import java.util.*;

class caeserp{
	static String transform(String text,int shift){
		shift = shift %26;
		StringBuilder sb = new StringBuilder();
		for(char c : text.toCharArray()){
			if(Character.isLetter(c)){
				char base = Character.isLowerCase(c) ? 'a' : 'A';
				sb.append((char)((c-base+shift)%26+base));
			}else{
				sb.append(c);
			}
		}
		return sb.toString();
	
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the text to be encrypted:");
		String text = sc.nextLine();
		System.out.println("Enter the shift value:");
		int shift = sc.nextInt();
		String encrypted = transform(text,shift);
		String decrypted = transform(encrypted,26-(shift%26));		
		System.out.println("Encrypted text: "+encrypted);
		System.out.println("Decrypted text: "+decrypted);
	}
}

import java.util.*;

class Pf{
	static char[][] key = new char[5][5];
	static String keyword;
	Pf(String word){
		word = word.toUpperCase().replaceAll("[^A-Z]","").replace("J","I");
		keyword = word;
		buildMatrix(keyword);
		printMatrix();
	}
	static void buildMatrix(String word){
		StringBuilder sb = new StringBuilder();
		Set<Character> hs = new HashSet<>();
		int in=0;
		for(char c : word.toCharArray()){
			if(!hs.contains(c)){
				hs.add(c);
				sb.append(c);
			}
		}
		for(char c = 'A';c<='Z';c++){
			if(c=='J') continue;
			if(!hs.contains(c)){
				hs.add(c);
				sb.append(c);
			}
		}
		keyword = sb.toString();
		for(int i = 0 ;i <5;i++)
			for(int j = 0 ;j <5;j++)
				key[i][j] = keyword.charAt(in++);
				
	}
	static void printMatrix(){
		for(int i = 0 ;i <5;i++){
			for(int j = 0 ;j <5;j++){
				System.out.print(key[i][j]+" ");
			}
			System.out.print("\n");
		}	
	}
	static List<String> prepText(String text){
		int in = 0;
		text = text.toUpperCase().replaceAll("[^A-Z]","").replace("J","I");
		List<String> pairs = new ArrayList<String>();
		while(in < text.length()){
			char a = text.charAt(in++);
			char b = (in<text.length()) ? text.charAt(in) : 'X' ;
			if(a==b) 
				b = 'X';
			else{
				in++;
			 };
			pairs.add(""+a+b);
		}
		return pairs;
	}
	static int[] findPos(char c){
		if(c=='J') c='I';
		for(int i = 0 ;i <5;i++)
			for(int j = 0 ;j <5;j++)
				if(c==key[i][j])
					return new int[]{i,j};
		return null;
	} 
	static String encrypt(String text){
		List<String> pairs = prepText(text);
		StringBuilder sb = new StringBuilder();
		for(String pair : pairs){
			int[] pos1 = findPos(pair.charAt(0));
			int[] pos2 = findPos(pair.charAt(1));
			if(pos1[0]==pos2[0]){
				sb.append(key[pos1[0]][(pos1[1]+1)%5]);
				sb.append(key[pos2[0]][(pos2[1]+1)%5]);
			}else if(pos1[1]==pos2[1]){
				sb.append(key[(pos1[0]+1)%5][pos1[1]]);
				sb.append(key[(pos2[0]+1)%5][pos2[1]]);
			}else{
				sb.append(key[pos1[0]][pos2[1]]);
				sb.append(key[pos2[0]][pos1[1]]);
			}
		
		}
		return sb.toString();
	
	} 
	static String decrypt(String text){
		List<String> pairs = prepText(text);
		StringBuilder sb = new StringBuilder();
		for(String pair : pairs){
			int[] pos1 = findPos(pair.charAt(0));
			int[] pos2 = findPos(pair.charAt(1));
			if(pos1[0]==pos2[0]){
				sb.append(key[pos1[0]][(pos1[1]+4)%5]);
				sb.append(key[pos2[0]][(pos2[1]+4)%5]);
			}else if(pos1[1]==pos2[1]){
				sb.append(key[(pos1[0]+4)%5][pos1[1]]);
				sb.append(key[(pos2[0]+4)%5][pos2[1]]);
			}else{
				sb.append(key[pos1[0]][pos2[1]]);
				sb.append(key[pos2[0]][pos1[1]]);
			}
		
		}
		return sb.toString();
	
	} 
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the keyword:");
		String keyword = sc.nextLine();
		Pf cipher = new Pf(keyword);
		System.out.println("Enter the text to be encrypted:");
		String text = sc.nextLine();
		String enc = cipher.encrypt(text);
		String dec = cipher.decrypt(enc);
		System.out.println("Encrypted: "+enc);
		System.out.println("Decrypted: "+dec);
	}
}


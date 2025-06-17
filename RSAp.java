import java.util.Scanner;
import java.math.BigInteger;

class RSAp{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter m:");
		BigInteger msg = sc.nextBigInteger();
		System.out.println("Enter p:");
		BigInteger p = sc.nextBigInteger();
		System.out.println("Enter q:");
		BigInteger q = sc.nextBigInteger();
		
		BigInteger n = p.multiply(q);
		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		System.out.println("phi: "+phi);
		BigInteger e = BigInteger.TWO;
		
		
		while (!gcd(e, phi).equals(BigInteger.ONE)) {
            e = e.add(BigInteger.ONE);
        }
		System.out.println("e: "+e);
		BigInteger d = e.modInverse(phi);
		System.out.println("d: "+d);
		BigInteger c = msg.modPow(e,n);
		System.out.println("Encrypted: "+c);
		BigInteger me = c.modPow(d,n);
		System.out.println("Decrypted: "+me);
	}
	static BigInteger gcd(BigInteger a ,BigInteger b){
		if(a.equals(BigInteger.ZERO))
			return b;
		else{
			return gcd(b.mod(a),a);
		}
	}
}


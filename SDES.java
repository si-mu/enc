//S-DES
import java.util.Scanner;
class SDES {
 // Permutation tables
 private static final int[] P10 = {3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
 private static final int[] P8 = {6, 3, 7, 4, 8, 5, 10, 9};
 private static final int[] IP = {2, 6, 3, 1, 4, 8, 5, 7};
 private static final int[] IP_INV = {4, 1, 3, 5, 7, 2, 8, 6};
 private static final int[] EP = {4, 1, 2, 3, 2, 3, 4, 1};
 private static final int[] P4 = {2, 4, 3, 1};
 private static final int[][] S0 = {
 {1, 0, 3, 2},
 {3, 2, 1, 0},
 {0, 2, 1, 3},
 {3, 1, 0, 2}
 };
 private static final int[][] S1 = {
 {0, 1, 2, 3},
 {2, 0, 1, 3},
 {3, 0, 1, 0},
 {2, 1, 0, 3}
 };
 public static void main(String[] args) {
 Scanner scanner = new Scanner(System.in);
 System.out.print("Enter 10-bit key (e.g., 1010000010): ");
 String key = scanner.next();
 System.out.print("Enter 8-bit plaintext (e.g., 11010111): ");
 String plaintext = scanner.next();
 String[] keys = generateKeys(key);
 String cipher = encrypt(plaintext, keys[0], keys[1]);
 System.out.println("Encrypted ciphertext: " + cipher);
 }
 // Key Generation
 public static String[] generateKeys(String key) {
 String p10 = permute(key, P10);
 String left = p10.substring(0, 5);
 String right = p10.substring(5);
 left = leftShift(left, 1);
 right = leftShift(right, 1);
 String k1 = permute(left + right, P8);
 left = leftShift(left, 2);
 right = leftShift(right, 2);
 String k2 = permute(left + right, P8);
 return new String[]{k1, k2};
 }
 // Encryption
 public static String encrypt(String pt, String k1, String k2) {
 String ip = permute(pt, IP);
 String fk1 = fk(ip, k1);
 String swapped = fk1.substring(4) + fk1.substring(0, 4);
 String fk2 = fk(swapped, k2);
 String cipher = permute(fk2, IP_INV);
 return cipher;
 }
 // fk function
 public static String fk(String bits, String key) {
 String left = bits.substring(0, 4);
 String right = bits.substring(4);
 String ep = permute(right, EP);
 String xor = xor(ep, key);
 String sbox = sBoxOutput(xor.substring(0, 4), S0) + sBoxOutput(xor.substring(4), S1);
 String p4 = permute(sbox, P4);
 String leftXOR = xor(left, p4);
 return leftXOR + right;
 }
 // Utility Functions
 public static String permute(String bits, int[] table) {
 StringBuilder output = new StringBuilder();
 for (int pos : table) {
 output.append(bits.charAt(pos - 1));
 }
 return output.toString();
 }
 public static String leftShift(String bits, int count) {
 return bits.substring(count) + bits.substring(0, count);
 }
 public static String xor(String a, String b) {
 StringBuilder result = new StringBuilder();
 for (int i = 0; i < a.length(); i++) {
result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
 }
 return result.toString();
 }
 public static String sBoxOutput(String input, int[][] sbox) {
 int row = Integer.parseInt("" + input.charAt(0) + input.charAt(3), 2);
 int col = Integer.parseInt("" + input.charAt(1) + input.charAt(2), 2);
 int val = sbox[row][col];
 return String.format("%2s", Integer.toBinaryString(val)).replace(' ', '0');
 }
}

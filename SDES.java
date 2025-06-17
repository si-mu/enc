import java.util.Scanner;

public class SDES {
    
    static int[][] sMatrix0 = {{1, 0, 3, 2}, {3, 2, 1, 0}, {0, 2, 1, 3}, {3, 1, 3, 2}};
    static int[][] sMatrix1 = {{0, 1, 2, 3}, {2, 0, 1, 3}, {3, 0, 1, 0}, {2, 1, 0, 3}};
    static int[] p10 = {3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
    static int[] p8 = {6, 3, 7, 4, 8, 5, 10, 9};
    static int[] ip = {2, 6, 3, 1, 4, 8, 5, 7};
    static int[] ep = {4, 1, 2, 3, 2, 3, 4, 1};
    static int[] p4 = {2, 4, 3, 1};
    static int[] ipInverse = {4, 1, 3, 5, 7, 2, 8, 6};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input
        System.out.println("Enter 10-bit key (space separated): ");
        int[] key = new int[10];
        for (int i = 0; i < 10; i++) {
            key[i] = scanner.nextInt();
        }

        System.out.println("Enter 8-bit plaintext (space separated): ");
        int[] plaintext = new int[8];
        for (int i = 0; i < 8; i++) {
            plaintext[i] = scanner.nextInt();
        }

        System.out.println("\n---------- KEY GENERATION ----------");
        int[] k1 = generateKey(key, 1);
        int[] k2 = generateKey(key, 2);

        System.out.print("\nKey-1: ");
        printArray(k1);
        
        System.out.print("\nKey-2: ");
        printArray(k2);

        System.out.println("\n---------- S-DES ENCRYPTION ----------");
        int[] ciphertext = encrypt(plaintext, k1, k2);

        System.out.print("\nCiphertext: ");
        printArray(ciphertext);
    }

    // Generate Key 1 or Key 2 from the original 10-bit key
    static int[] generateKey(int[] key, int round) {
        int[] permutedKey = new int[10];
        for (int i = 0; i < 10; i++) {
            permutedKey[i] = key[p10[i] - 1];
        }

        // Left shift
        permutedKey = leftShift(permutedKey, round);

        // Apply p8
        int[] k = new int[8];
        for (int i = 0; i < 8; i++) {
            k[i] = permutedKey[p8[i] - 1];
        }

        return k;
    }

    // Left shift based on round (LS-1 or LS-2)
    static int[] leftShift(int[] key, int round) {
        int[] left = new int[5];
        int[] right = new int[5];
        
        System.arraycopy(key, 0, left, 0, 5);
        System.arraycopy(key, 5, right, 0, 5);
        
        if (round == 1) {
            left = leftShiftOnce(left);
            right = leftShiftOnce(right);
        } else {
            left = leftShiftTwice(left);
            right = leftShiftTwice(right);
        }
        
        int[] newKey = new int[10];
        System.arraycopy(left, 0, newKey, 0, 5);
        System.arraycopy(right, 0, newKey, 5, 5);

        return newKey;
    }

    static int[] leftShiftOnce(int[] arr) {
        int temp = arr[0];
        for (int i = 0; i < 4; i++) {
            arr[i] = arr[i + 1];
        }
        arr[4] = temp;
        return arr;
    }

    static int[] leftShiftTwice(int[] arr) {
        arr = leftShiftOnce(arr);
        arr = leftShiftOnce(arr);
        return arr;
    }

    // Encryption process
    static int[] encrypt(int[] plaintext, int[] k1, int[] k2) {
        int[] afterIp = permute(plaintext, ip);
        int[] left = new int[4];
        int[] right = new int[4];
        System.arraycopy(afterIp, 0, left, 0, 4);
        System.arraycopy(afterIp, 4, right, 0, 4);

        int[] epRight = permute(right, ep);
        int[] afterXor = xor(epRight, k1);
        int[] sBoxOut = sBoxes(afterXor);
        int[] p4Out = permute(sBoxOut, p4);
        int[] leftAfterXor = xor(left, p4Out);

        int[] newLeft = leftAfterXor;
        int[] newRight = right;

        // Swap left and right
        int[] temp = newLeft;
        newLeft = newRight;
        newRight = temp;

        epRight = permute(newRight, ep);
        afterXor = xor(epRight, k2);
        sBoxOut = sBoxes(afterXor);
        p4Out = permute(sBoxOut, p4);
        int[] finalLeft = xor(newLeft, p4Out);

        // Combine final result
        int[] finalOutput = new int[8];
        System.arraycopy(finalLeft, 0, finalOutput, 0, 4);
        System.arraycopy(newRight, 0, finalOutput, 4, 4);
        return permute(finalOutput, ipInverse);
    }

    // XOR operation
    static int[] xor(int[] a, int[] b) {
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] ^ b[i];
        }
        return result;
    }

    // S-Boxes operation
    static int[] sBoxes(int[] input) {
        int[] output = new int[4];

        int row = toDigit(input[0], input[3]);
        int col = toDigit(input[1], input[2]);
        int s0 = sMatrix0[row][col];
        toBinary(s0, output, 0);

        row = toDigit(input[4], input[7]);
        col = toDigit(input[5], input[6]);
        int s1 = sMatrix1[row][col];
        toBinary(s1, output, 2);

        return output;
    }

    // Convert 2 bits to a number (0-3)
    static int toDigit(int a, int b) {
        return a * 2 + b;
    }

    // Convert a number to 2-bit binary and store it in the output array
    static void toBinary(int num, int[] output, int start) {
        output[start] = (num >> 1) & 1;
        output[start + 1] = num & 1;
    }

    // Permute the input array using a given permutation array
    static int[] permute(int[] input, int[] perm) {
        int[] output = new int[perm.length];
        for (int i = 0; i < perm.length; i++) {
            output[i] = input[perm[i] - 1];
        }
        return output;
    }

    // Print array
    static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

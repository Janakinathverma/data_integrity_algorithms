import java.util.*;

public class HammingCode {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ---------------------------
        // USER INPUT: 4 data bits
        // ---------------------------
        int[] data = new int[4];
        System.out.println("Enter 4 data bits (d1 d2 d3 d4):");

        for (int i = 0; i < 4; i++) {
            data[i] = sc.nextInt();
        }

        // ---------------------------
        // CREATE HAMMING(7,4) FRAME
        // ---------------------------
        // Positions: 1 2 3 4 5 6 7
        // Bits:     p1 p2 d1 p3 d2 d3 d4
        int[] h = new int[7];

        h[2] = data[0];  // d1 → pos3
        h[4] = data[1];  // d2 → pos5
        h[5] = data[2];  // d3 → pos6
        h[6] = data[3];  // d4 → pos7

        // Calculate parity bits (even parity)
        h[0] = h[2] ^ h[4] ^ h[6];       // p1 covers bits 1,3,5,7 → index 0,2,4,6
        h[1] = h[2] ^ h[5] ^ h[6];       // p2 covers bits 2,3,6,7 → index 1,2,5,6
        h[3] = h[4] ^ h[5] ^ h[6];       // p3 covers bits 4,5,6,7 → index 3,4,5,6

        System.out.println("\nGenerated Hamming Code (7 bits):");
        printArray(h);

        // ---------------------------
        // INTRODUCE ERROR (optional)
        // ---------------------------
        System.out.print("\nDo you want to introduce an error? (y/n): ");
        char opt = sc.next().charAt(0);

        if (opt == 'y' || opt == 'Y') {
            System.out.print("Enter bit position to flip (1–7): ");
            int pos = sc.nextInt();

            h[pos - 1] ^= 1;   // Flip the selected bit

            System.out.println("Corrupted Code:");
            printArray(h);
        }

        // ---------------------------
        // DETECT ERROR
        // ---------------------------
        int p1 = h[0] ^ h[2] ^ h[4] ^ h[6];
        int p2 = h[1] ^ h[2] ^ h[5] ^ h[6];
        int p3 = h[3] ^ h[4] ^ h[5] ^ h[6];

        // Syndrome
        int errorPos = p1 * 1 + p2 * 2 + p3 * 4;

        if (errorPos == 0) {
            System.out.println("\nNo error detected.");
        } else {
            System.out.println("\nError detected at bit position: " + errorPos);

            // Correct it
            h[errorPos - 1] ^= 1;

            System.out.println("Corrected Code:");
            printArray(h);
        }

        sc.close();  // <<< added as you requested
    }

    static void printArray(int[] arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }
}

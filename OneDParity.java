import java.util.*;

public class OneDParity {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ---------------------------
        // USER INPUT
        // ---------------------------
        System.out.print("Enter number of bits: ");
        int n = sc.nextInt();

        int[] data = new int[n];

        System.out.println("Enter the bits (0/1 only):");
        for (int i = 0; i < n; i++) {
            data[i] = sc.nextInt();
        }

        // ---------------------------
        // CALCULATE PARITY BIT
        // ---------------------------
        int parityBit = calculateParity(data);

        System.out.println("\nOriginal Data Bits:");
        printArray(data);

        System.out.println("Calculated Parity Bit (Even Parity): " + parityBit);

        // Create transmitted frame (data + parity)
        int[] transmitted = Arrays.copyOf(data, n + 1);
        transmitted[n] = parityBit;

        System.out.println("\nTransmitted Bits (with parity):");
        printArray(transmitted);

        // ---------------------------
        // INTRODUCE SINGLE-BIT ERROR
        // ---------------------------
        System.out.print("\nDo you want to introduce error? (y/n): ");
        char ch = sc.next().charAt(0);

        int errorPosition = -1;
        if (ch == 'y' || ch == 'Y') {
            System.out.print("Enter error bit position (0 to " + n + "): ");
            errorPosition = sc.nextInt();

            transmitted[errorPosition] ^= 1;   // Flip the bit
        }

        System.out.println("\nReceived Bits:");
        printArray(transmitted);

        // ---------------------------
        // ERROR DETECTION
        // ---------------------------
        int receivedParity = calculateParity(transmitted);

        if (receivedParity == 0) {
            System.out.println("\nNo error detected.");
        } else {
            System.out.println("\nERROR DETECTED! (1-bit corruption occurred)");
            System.out.println("Note: 1D parity can detect errors but cannot correct them.");
        }
        sc.close();
    }

    // Calculate even parity
    public static int calculateParity(int[] arr) {
        int sum = 0;
        for (int bit : arr) sum += bit;
        return sum % 2;
    }

    // Utility function to print array
    public static void printArray(int[] arr) {
        for (int bit : arr) System.out.print(bit + " ");
        System.out.println();
    }
}

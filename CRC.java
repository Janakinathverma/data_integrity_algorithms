import java.util.*;

public class CRC {

    // XOR operation for binary bits
    private static int xor(int a, int b) {
        return (a == b) ? 0 : 1;
    }

    // Perform CRC division (modulo-2)
    private static int[] divide(int[] data, int[] generator) {
        int[] temp = data.clone();
        int n = generator.length;

        for (int i = 0; i <= temp.length - n; i++) {
            if (temp[i] == 1) {  // Only divide if leading bit is 1
                for (int j = 0; j < n; j++) {
                    temp[i + j] = xor(temp[i + j], generator[j]);
                }
            }
        }

        return temp;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ---------------------------
        // USER INPUT
        // ---------------------------
        System.out.print("Enter number of data bits: ");
        int n = sc.nextInt();

        int[] data = new int[n];
        System.out.println("Enter the data bits (0/1):");
        for (int i = 0; i < n; i++) data[i] = sc.nextInt();

        System.out.print("\nEnter number of generator bits: ");
        int m = sc.nextInt();

        int[] generator = new int[m];
        System.out.println("Enter the generator bits (0/1):");
        for (int i = 0; i < m; i++) generator[i] = sc.nextInt();

        // ---------------------------
        // APPEND ZEROES (m-1 zeros)
        // ---------------------------
        int[] appended = new int[n + m - 1];
        System.arraycopy(data, 0, appended, 0, n);

        System.out.println("\nData after appending " + (m - 1) + " zeros:");
        printArray(appended);

        // ---------------------------
        // PERFORM CRC DIVISION
        // ---------------------------
        int[] remainder = divide(appended, generator);

        System.out.println("Remainder:");
        printArray(remainder);

        // Extract last (m-1) bits as CRC
        int[] crc = new int[m - 1];
        System.arraycopy(remainder, remainder.length - (m - 1), crc, 0, m - 1);

        System.out.println("\nCRC:");
        printArray(crc);

        // ---------------------------
        // CREATE TRANSMITTED FRAME
        // ---------------------------
        int[] transmitted = new int[n + m - 1];
        System.arraycopy(data, 0, transmitted, 0, n);
        System.arraycopy(crc, 0, transmitted, n, m - 1);

        System.out.println("\nTransmitted Frame:");
        printArray(transmitted);

        // ---------------------------
        // INTRODUCE ERROR
        // ---------------------------
        System.out.print("\nDo you want to introduce an error? (y/n): ");
        char ch = sc.next().charAt(0);

        if (ch == 'y' || ch == 'Y') {
            System.out.print("Enter position to flip (0 to " + (transmitted.length - 1) + "): ");
            int pos = sc.nextInt();
            transmitted[pos] ^= 1;  // Flip bit
        }

        System.out.println("\nReceived Frame:");
        printArray(transmitted);

        // ---------------------------
        // RECEIVER CRC CHECK
        // ---------------------------
        int[] recvRemainder = divide(transmitted, generator);

        boolean error = false;
        for (int bit : recvRemainder) {
            if (bit != 0) error = true;
        }

        if (!error)
            System.out.println("\nNO ERROR DETECTED (Remainder is all zeros)");
        else
            System.out.println("\nERROR DETECTED! (Remainder not zero)");

        sc.close();
    }

    // Print helper
    public static void printArray(int[] arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }
}

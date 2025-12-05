import java.util.*;

public class TwoDParityUserInput {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ---------------------------
        // USER INPUT MATRIX SIZE
        // ---------------------------
        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();

        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();

        int[][] data = new int[rows][cols];

        // ---------------------------
        // USER INPUT MATRIX DATA
        // ---------------------------
        System.out.println("\nEnter the data matrix (0/1 only):");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                data[r][c] = sc.nextInt();
            }
        }

        System.out.println("\nOriginal Data:");
        printMatrix(data);

        // ---------------------------
        // COMPUTE ROW PARITY
        // ---------------------------
        int[] rowParity = new int[rows];
        for (int r = 0; r < rows; r++) {
            rowParity[r] = parity(data[r]);
        }

        // ---------------------------
        // COMPUTE COLUMN PARITY
        // ---------------------------
        int[] colParity = new int[cols];
        for (int c = 0; c < cols; c++) {
            int[] colArr = new int[rows];
            for (int r = 0; r < rows; r++)
                colArr[r] = data[r][c];
            colParity[c] = parity(colArr);
        }

        System.out.println("\nRow Parity:");
        printArray(rowParity);
        System.out.println("Column Parity:");
        printArray(colParity);

        // ---------------------------
        // INTRODUCE SINGLE-BIT ERROR
        // ---------------------------
        System.out.print("\nDo you want to introduce an error? (y/n): ");
        char errorOpt = sc.next().charAt(0);

        int errorRow = -1, errorCol = -1;
        if (errorOpt == 'y' || errorOpt == 'Y') {
            System.out.print("Enter error row index (0-based): ");
            errorRow = sc.nextInt();

            System.out.print("Enter error column index (0-based): ");
            errorCol = sc.nextInt();

            data[errorRow][errorCol] ^= 1; // Flip bit

            System.out.println("\nData after introducing error:");
            printMatrix(data);
        }

        // ---------------------------
        // DETECT ERROR
        // ---------------------------
        int detectedRow = -1, detectedCol = -1;

        // Check row parity
        for (int r = 0; r < rows; r++) {
            if (parity(getRow(data, r)) != rowParity[r])
                detectedRow = r;
        }

        // Check column parity
        for (int c = 0; c < cols; c++) {
            if (parity(getCol(data, c)) != colParity[c])
                detectedCol = c;
        }

        System.out.println("\nDetected Error Position:");
        System.out.println("Row = " + detectedRow + ", Column = " + detectedCol);

        // ---------------------------
        // CORRECT ERROR
        // ---------------------------
        if (detectedRow != -1 && detectedCol != -1) {
            System.out.println("\nCorrecting the error...");
            data[detectedRow][detectedCol] ^= 1; // Flip back
        } else {
            System.out.println("\nNo single-bit error detected.");
        }

        System.out.println("\nCorrected Data:");
        printMatrix(data);
        sc.close();
    }

    // Compute even parity
    public static int parity(int[] arr) {
        int sum = 0;
        for (int bit : arr) sum += bit;
        return sum % 2;
    }

    // Get row
    public static int[] getRow(int[][] matrix, int row) {
        return matrix[row];
    }

    // Get column array
    public static int[] getCol(int[][] matrix, int col) {
        int[] arr = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++)
            arr[i] = matrix[i][col];
        return arr;
    }

    // Utility print functions
    public static void printArray(int[] arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int x : row) System.out.print(x + " ");
            System.out.println();
        }
    }
}

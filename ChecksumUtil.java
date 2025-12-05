import java.util.Scanner;
public class ChecksumUtil {

    // Calculates simple 8-bit checksum
    public static int calculateChecksum(byte[] data) {
        int sum = 0;

        for (byte b : data) {
            sum = (sum + (b & 0xFF)) & 0xFF; // mod 256
        }

        return sum; // returns checksum (0–255)
    }

    public static void main(String[] args) {
        Scanner ss = new Scanner(System.in);
        System.out.println("Enter a message to compute its checksum");
        String message = ss.nextLine();
        byte[] bytes = message.getBytes();

        int checksum = calculateChecksum(bytes);
        System.out.println("Checksum: " + checksum);
        ss.close();
    }
}

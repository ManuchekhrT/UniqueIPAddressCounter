import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UniqueIPAddressCounter {
    public static void main(String[] args) {
        String filePath = "file/ip4_address.rtf";
        try {
            long uniqueIPCount = countUniqueIPs(filePath);
            System.out.println("Unique IP Addresses: " + uniqueIPCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long countUniqueIPs(String filePath) throws IOException {
        Set<Long> uniqueIPs = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                long ipAsLong = ipToLong(line.trim());
                uniqueIPs.add(ipAsLong);
            }
        }

        return uniqueIPs.size();
    }

    // Convert an IPv4 address to its long representation
    public static long ipToLong(String ipAddress) {
        String[] octets = ipAddress.split("\\.");
        long result = 0;
        for (int i = 0; i < 4; i++) {
            result |= (Long.parseLong(octets[i]) << (8 * (3 - i)));
        }
        return result;
    }
}

package si.samgres.api.helpers;

import java.security.SecureRandom;
import java.util.UUID;

public class TokenGenerator {
    public static String generate() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[128];
        random.nextBytes(bytes);
        return bytes.toString();
    }
}

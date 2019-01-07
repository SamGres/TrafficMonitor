package si.samgres.api.helpers;

import java.util.UUID;

public class UUIDHelper {
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}

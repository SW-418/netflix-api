package samwells.io.netflix_api.util;

import samwells.io.netflix_api.exception.PaginationCursorException;
import samwells.io.netflix_api.model.PaginationCursor;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Base64;
import java.util.Objects;

public class PaginationUtil {
    private static final String DELIMITER = "_";

    public static String encode(Instant timestamp, Long id) {
        Objects.requireNonNull(timestamp, "timestamp must not be null");
        Objects.requireNonNull(id, "id must not be null");

        String cursor = String.format("%s%s%s", timestamp, DELIMITER, id);

        return Base64.getEncoder().encodeToString(cursor.getBytes(StandardCharsets.UTF_8));
    }

    public static PaginationCursor decode(String encodedCursor) {
        if (encodedCursor == null || encodedCursor.isBlank()) throw new PaginationCursorException("Null or empty cursor provided");

        try {
            String decodedCursor = new String(Base64.getDecoder().decode(encodedCursor), StandardCharsets.UTF_8);

            String[] splitCursor = decodedCursor.split(DELIMITER);
            if (splitCursor.length != 2) throw new PaginationCursorException("Invalid cursor format");

            Instant timestamp = Instant.parse(splitCursor[0]);
            Long id = Long.valueOf(splitCursor[1]);

            return new PaginationCursor(timestamp, id);
        } catch (NumberFormatException e) {
            throw new PaginationCursorException("Cursor id was invalid");
        } catch (IllegalArgumentException e) {
            throw new PaginationCursorException("Cursor was not a valid Base64 String");
        } catch (DateTimeParseException e) {
            throw new PaginationCursorException("Cursor timestamp was invalid");
        }
    }
}

package samwells.io.netflix_api.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import samwells.io.netflix_api.exception.PaginationCursorException;
import samwells.io.netflix_api.model.PaginationCursor;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

class PaginationUtilTest {
    private static final String DELIMITER = "_";

    @Test
    void encode_withValidInput_returnsExpectedString() {
        Instant timestamp = Instant.now();
        Long id = 12L;

        String expected = Base64
                .getEncoder()
                .encodeToString(
                        String.format("%s%s%s", timestamp, DELIMITER, id).getBytes(StandardCharsets.UTF_8)
                );
        String actual = PaginationUtil.encode(timestamp, id);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void encode_withNullTimestamp_throwsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> PaginationUtil.encode(null, 1L));
    }

    @Test
    void encode_withNullId_throwsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> PaginationUtil.encode(Instant.now(), null));
    }

    @Test
    void decode_withValidInput_returnsExpectedPaginationCursor() {
        Instant timestamp = Instant.now();
        Long id = 12L;

        String encoded = Base64
                .getEncoder()
                .encodeToString(
                        String.format("%s%s%s", timestamp, DELIMITER, id).getBytes(StandardCharsets.UTF_8)
                );

        PaginationCursor actualCursor = PaginationUtil.decode(encoded);

        Assertions.assertEquals(timestamp, actualCursor.timestamp());
        Assertions.assertEquals(id, actualCursor.id());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",                                     // empty
            " ",                                    // whitespace
            "invalid-timestamp_12",                 // timestamp cannot be parsed
            "2025-13-40T25:61:00Z_12",              // nonsensical timestamp
            "2025-10-16T08:00:00Z_not-a-number",    // ID cannot be parsed to long
            "2025-10-16T08:00:00Z_",                // missing ID
            "_123",                                 // missing timestamp
            "not-a-timestamp_not-a-number"          // both invalid
    })
    void decode_withInvalidInput_throwsPaginationCursorException(String input) {
        String encoded = Base64
                .getEncoder()
                .encodeToString(input.getBytes(StandardCharsets.UTF_8));

        Assertions.assertThrows(PaginationCursorException.class, () -> PaginationUtil.decode(encoded));
    }

    @Test
    void decode_withNullInput_throwsPaginationCursorException() {
        Assertions.assertThrows(PaginationCursorException.class, () -> PaginationUtil.decode(null));
    }

    @Test
    void decode_withInvalidBase64Input_throwsPaginationCursorException() {
        Assertions.assertThrows(PaginationCursorException.class, () -> PaginationUtil.decode("%%%"));
    }
}

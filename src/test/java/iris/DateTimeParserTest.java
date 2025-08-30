package iris;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimeParserTest {

    @Test
    public void parseDateTime_valid_ddMMyyyyHHmm() {
        LocalDateTime dt = DateTimeParser.parseDateTime("2/12/2019 1800");
        assertEquals(LocalDateTime.of(2019, 12, 2, 18, 0), dt);
    }

    @Test
    public void parseDateTime_valid_isoDate() {
        LocalDateTime dt = DateTimeParser.parseDateTime("2019-12-02");
        assertEquals(LocalDateTime.of(2019, 12, 2, 0, 0), dt); // midnight assumed
    }

    @Test
    public void parseDateTime_invalidFormat_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            DateTimeParser.parseDateTime("12-02-2019 6pm");
        });
    }
}

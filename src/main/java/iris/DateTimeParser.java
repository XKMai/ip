package iris;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Parses date-time strings in various formats
public class DateTimeParser {
    private static final DateTimeFormatter[] DATE_TIME_FORMATS = new DateTimeFormatter[] {
        DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),   // 2/12/2019 1800
        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"), // 2019-12-02 1800
        DateTimeFormatter.ISO_LOCAL_DATE_TIME           // 2019-12-02T18:00
    };

    private static final DateTimeFormatter[] DATE_ONLY_FORMATS = new DateTimeFormatter[] {
        DateTimeFormatter.ofPattern("d/M/yyyy"),        // 2/12/2019
        DateTimeFormatter.ISO_LOCAL_DATE                // 2019-12-02
    };

    public static LocalDateTime parseDateTime(String input) {
        assert input != null : "Input to DateTimeParser cannot be null";
        assert !input.trim().isEmpty() : "Input to DateTimeParser cannot be empty";

        String trimmed = input.trim();

        // Try full date-time patterns first
        for (DateTimeFormatter f : DATE_TIME_FORMATS) {
            try {
                LocalDateTime parsed = LocalDateTime.parse(trimmed, f);
                assert parsed != null : "Parsed LocalDateTime should not be null";
                return parsed;
            } catch (DateTimeParseException e) {
                // continue
            }
        }

        // Try date-only patterns → assume start of day (00:00)
        for (DateTimeFormatter f : DATE_ONLY_FORMATS) {
            try {
                LocalDate parsedDate = LocalDate.parse(trimmed, f);
                assert parsedDate != null : "Parsed LocalDate should not be null";
                return parsedDate.atStartOfDay();
            } catch (DateTimeParseException e) {
                // continue
            }
        }

        throw new IllegalArgumentException(
            "Invalid date format: " + input +
            ". Try formats like: d/M/yyyy (2/12/2019) or d/M/yyyy HHmm (2/12/2019 1800)."
        );
    }
}

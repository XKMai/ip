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
        String trimmed = input.trim();

        // Try full date-time patterns first
        for (DateTimeFormatter f : DATE_TIME_FORMATS) {
            try {
                return LocalDateTime.parse(trimmed, f);
            } catch (DateTimeParseException e) {
                // continue
            }
        }

        // Try date-only patterns → assume 0000
        for (DateTimeFormatter f : DATE_ONLY_FORMATS) {
            try {
                return LocalDate.parse(trimmed, f).atStartOfDay();
            } catch (DateTimeParseException e) {
                // continue
            }
        }

        throw new IllegalArgumentException("Invalid date format: " + input +
            ". Try formats like: d/M/yyyy (2/12/2019) or d/M/yyyy HHmm (2/12/2019 1800).");
    }
}

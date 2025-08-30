import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    public static LocalDateTime parseDateTime(String input) {
        DateTimeFormatter[] formatters = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),    // 2/12/2019 1800
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),  // 2019-12-02 1800
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,           // 2019-12-02T18:00
            DateTimeFormatter.ISO_LOCAL_DATE                 // 2019-12-02
        };

        for (DateTimeFormatter f : formatters) {
            try {
                if (f == DateTimeFormatter.ISO_LOCAL_DATE) {
                    return LocalDate.parse(input.trim(), f).atStartOfDay();
                }
                return LocalDateTime.parse(input.trim(), f);
            } catch (DateTimeParseException e) {
                // Try next
            }
        }
        throw new IllegalArgumentException("Invalid date format: " + input);
    }
}

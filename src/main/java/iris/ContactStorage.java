package iris;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactStorage {
    private final String filePath;

    public ContactStorage(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path must not be null or empty";
        this.filePath = filePath;
    }

    // Load contacts from file
    public List<Contact> load() throws IOException {
        assert filePath != null && !filePath.trim().isEmpty() : "File path must not be null or empty when loading";

        List<Contact> contacts = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return contacts; // return empty list if no file
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                assert line != null : "Line read from file should not be null";

                try {
                    Contact c = parseContact(line);
                    assert c != null : "Parsed contact should not be null";
                    contacts.add(c);
                } catch (Exception e) {
                    System.out.println("Skipping corrupted contact line: " + line);
                }
            }
        }

        return contacts;
    }

    // Save contacts to file
    public void save(List<Contact> contacts) throws IOException {
        assert contacts != null : "Contact list must not be null";

        File file = new File(filePath);
        File dir = file.getParentFile();
        if (dir != null && !dir.exists()) {
            boolean created = dir.mkdirs();
            assert created || dir.exists() : "Directory should exist after mkdirs() call";
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Contact c : contacts) {
                assert c != null : "Contact must not be null";
                String saveFormat = toSaveFormat(c);
                assert saveFormat != null && !saveFormat.isEmpty() : "Contact save format must not be empty";
                writer.write(saveFormat + System.lineSeparator());
            }
        }
    }

    // Format a contact into string for saving
    private String toSaveFormat(Contact c) {
        return c.getName() + " | " + c.getPhoneNumber() + " | " + c.getEmail();
    }

    // Parse a line into a Contact object
    private Contact parseContact(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid contact format: " + line);
        }
        return new Contact(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }
}

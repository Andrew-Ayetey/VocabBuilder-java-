

import com.andrew.csvreader.CsvWriter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.calls;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CsvWriterTest {

    private final String TEST_CSV_FILE = "test.csv";

    @BeforeEach
    void setUp() {
        // Any setup if needed
    }

    @AfterEach
    void tearDown() {
        // Clean up the test file after each test
        new File(TEST_CSV_FILE).delete();
    }

    @Test
    void testWriteWordDefinitionsToCSV() throws IOException {
        // Prepare test data
        List<String[]> testData = new ArrayList<>();
        testData.add(new String[]{"word1", "noun", "definition1"});
        testData.add(new String[]{"word2", "verb", "definition2"});

        // Write to CSV
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.writeWordDefinitionsToCSV(testData, TEST_CSV_FILE);

        // Read the file and verify the content
        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_CSV_FILE))) {
            String line1 = reader.readLine();
            String line2 = reader.readLine();

            assertNotNull( line1);
            assertNotNull(line2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

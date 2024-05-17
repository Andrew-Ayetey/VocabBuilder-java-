
import com.andrew.csvreader.ReadCSV;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;






public class ReadCSVTest {

    @Test
    public void testReadWordsFromCSV() {
        ReadCSV readCSV = new ReadCSV();
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String testCsvFile = resourceDirectory.resolve("test1.csv").toString();

        List<String> words = readCSV.readWordsFromCSV(testCsvFile);
        
        assertNotNull(words, "The list of words should not be null");
        assertFalse(words.isEmpty(), "The list of words should not be empty"); // Further assertions can be made depending on the expected content of your test CSV
        // Further assertions can be made depending on the expected content of your test CSV
    }
}


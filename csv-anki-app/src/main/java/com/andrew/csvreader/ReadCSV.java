package com.andrew.csvreader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {
    private final String filePath;

    public ReadCSV() {
        this.filePath = ""; // or some default value
    }

    public ReadCSV(String filePath) {
        this.filePath = filePath;
    }

    public List<String> readWordsFromCSV(String filePath) {
        List<String> words = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CSVFormat csvFormat = CSVFormat.DEFAULT
                    .builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();

            try (CSVParser csvParser = new CSVParser(reader, csvFormat)) {
                for (CSVRecord csvRecord : csvParser) {
                    String word = csvRecord.get("Word"); // Assuming the column name is 'Word'
                    // System.out.println("CSVparser: word: " + word);
                    words.add(word);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
        return words;
    }
}

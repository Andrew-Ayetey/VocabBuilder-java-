package com.andrew.csvreader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.csv.QuoteMode;

public class CsvWriter {

    public void writeWordDefinitionsToCSV(List<String[]> wordDefinitions, String baseFilePath) throws IOException {
        // Remove .csv extension if present in baseFilePath
        String filePathWithoutExtension = baseFilePath.endsWith(".csv")
                ? baseFilePath.substring(0, baseFilePath.length() - 4)
                : baseFilePath;

        // Create a unique file name by appending a timestamp
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filePath = filePathWithoutExtension + "_" + timestamp + ".csv";

        // Write to the uniquely named CSV file
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setQuoteMode(QuoteMode.MINIMAL) // Set quote mode
                .setRecordSeparator(System.lineSeparator()) // Set system-specific line separator
                .build();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                CSVPrinter csvPrinter = new CSVPrinter(writer, format)) {
            // Add the header
            csvPrinter.printRecord("Word", "Definition");

            // Your existing code to write records
            for (String[] wordDefinition : wordDefinitions) {
                // System.out.println("WordDefinition: " + wordDefinition[0] + "\n \tSecond Definition " + wordDefinition[1]);
                // Assuming wordDefinition has exactly two elements: word and concatenated
                // definitions
                if (wordDefinition.length == 2) {
                    String word = wordDefinition[0].replaceAll("[\\n,;:]", ""); // Remove \n, ; and :
                    String definition = wordDefinition[1].replaceAll("[\\n,;:]", ""); // Remove \n, ; and :
                    csvPrinter.printRecord(word, definition); // Write the word and its definitions
                } else {
                    // Handle unexpected array length
                    System.out.println("Unexpected data format for word: " + wordDefinition[0]);
                }
            }
        }
    }
}
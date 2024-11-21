package com.andrew.csvreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CSVController {
    @FXML
    private void importCSV(ActionEvent event) throws IOException {

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        var fileChooser = new FileChooser();
        fileChooser.setTitle("Select CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        var file = fileChooser.showOpenDialog(stage);

        var filePath = file.getAbsolutePath();
        ReadCSV newCsv = new ReadCSV(filePath);
        List<String> words = newCsv.readWordsFromCSV(filePath);

        // Create an HttpClient instance
        HttpClient httpClient = HttpClient.newHttpClient();

        // Initialize ApiIntegration with HttpClient
        ApiIntegration apiIntegration = new ApiIntegration(httpClient);

        try{
        // Fetch data from API
        List<String[]> allWordDefinitions = new ArrayList<>();
        for (String word : words) {
            String jsonResponse = apiIntegration.fetchDefinition(word);
            if (jsonResponse == null) {
            System.out.println("Failed to fetch definition for word: " + word);
            StringBuilder noDefWord = new StringBuilder();
            allWordDefinitions.add(new String[] { word, noDefWord.toString() });
            continue; // Skip this word
            }

            JsonParser jsonParser = new JsonParser();
            List<String[]> wordDefinitions = jsonParser.extractWordAndDefinitions(jsonResponse);

            StringBuilder definitionsConcatenated = new StringBuilder();
            // concatenate all definitions into one string
            for (String[] wordDefinition : wordDefinitions) {
            if (definitionsConcatenated.length() > 0) {
                definitionsConcatenated.append("\n ");
            }
            definitionsConcatenated.append(wordDefinition[1]); // Assuming definition[1] contains the definition text

            }
            allWordDefinitions.add(new String[] { word, definitionsConcatenated.toString() });
        }
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.writeWordDefinitionsToCSV(allWordDefinitions, filePath);

        } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
        }
    }
}

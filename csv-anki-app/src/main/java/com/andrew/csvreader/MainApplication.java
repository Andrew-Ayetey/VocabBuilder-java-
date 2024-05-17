package com.andrew.csvreader;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class MainApplication{

  
  public static void main(String[] args) {
    try {
    //   try {
    //     launch(args);
    // } catch (Exception e) {
    //     e.printStackTrace();
    // }
      // open CSV file
      // prompt for csv
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter the path to the CSV file: ");
      String filePath = scanner.nextLine();
      scanner.close();

      ReadCSV newCsv = new ReadCSV(filePath);
      List<String> words = newCsv.readWordsFromCSV(filePath);

      // Create an HttpClient instance
      HttpClient httpClient = HttpClient.newHttpClient();

      // Initialize ApiIntegration with HttpClient
      ApiIntegration apiIntegration = new ApiIntegration(httpClient);

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
    ;
  }
}

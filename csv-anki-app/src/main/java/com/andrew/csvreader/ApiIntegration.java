package com.andrew.csvreader;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class ApiIntegration {

  private final HttpClient httpClient;

  // Constructor that accepts a HttpClient
  public ApiIntegration(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  public String fetchDefinition(String word) throws IOException, InterruptedException {
            String encodedWord = URLEncoder.encode(word, StandardCharsets.UTF_8.toString());

    String url = "https://api.dictionaryapi.dev/api/v2/entries/en/" + encodedWord;
    //to do: integrate new wikitionary api https://stackoverflow.com/questions/2770547/how-can-i-retrieve-wiktionary-word-content
    
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET() // GET is the default method and doesn't need to be explicitly set.
        .build();

    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() == 200) {
      return response.body(); // The JSON response as a string
    } else {
      // Handle non-200 responses appropriately
      return null;
    }
  }
}

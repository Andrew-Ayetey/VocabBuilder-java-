package com.andrew.csvreader;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public List<String[]> extractWordAndDefinitions(String jsonResponse) {
        List<String[]> wordDefinitions = new ArrayList<>();
        JSONArray entries = new JSONArray(jsonResponse);
        try {
            for (int i = 0; i < entries.length(); i++) {
                JSONObject entry = entries.getJSONObject(i);
                String word = entry.getString("word");
                JSONArray phoneticsArray = entry.getJSONArray("phonetics");

                String phonetics = ""; // Initialize phonetics as an empty string

                // Check if the phoneticsArray is not empty
                if (phoneticsArray.length() > 0) {
                    JSONObject phoneticsObject = phoneticsArray.getJSONObject(0);
                    
                    // Check if the phonetics text is present in the phonetics object
                    if (phoneticsObject.has("text")) {
                        phonetics = phoneticsObject.getString("text");
                    }
                }

                JSONArray meanings = entry.getJSONArray("meanings");

                StringBuilder allDefinitions = new StringBuilder();
                if (!phonetics.isEmpty()) {
                    allDefinitions.append(phonetics).append(" - "); // Prepend phonetics text only if it's not empty
                }
                for (int j = 0; j < meanings.length(); j++) {
                    JSONObject meaning = meanings.getJSONObject(j);
                    JSONArray defArray = meaning.getJSONArray("definitions");

                    for (int k = 0; k < defArray.length(); k++) {
                        if (allDefinitions.length() > 0) {
                            allDefinitions.append("\n");
                        }
                        JSONObject def = defArray.getJSONObject(k);
                        String definition = def.getString("definition").replace("\"", "\"\"");
                        allDefinitions.append(definition);
                    }
                }
                wordDefinitions.add(new String[] { word, allDefinitions.toString() });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wordDefinitions;
    }
}

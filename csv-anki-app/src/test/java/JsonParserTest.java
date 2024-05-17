import com.andrew.csvreader.JsonParser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class JsonParserTest {

    @Test
    void testExtractWordAndDefinitions() {
        // Sample JSON string
        String sampleJsonOne = "[{\"word\":\"test\",\"phonetic\":\"/test/\",\"phonetics\":[{\"text\":\"/test/\",\"audio\":\"https://example.com/test.mp3\"}],\"meanings\":[{\"partOfSpeech\":\"noun\",\"definitions\":[{\"definition\":\"A procedure intended to establish the quality, performance, or reliability of something.\"}]}]}]";

        String sampleJsonTwo = "[{\"word\":\"rapine\",\"phonetic\":\"/ˈɹæpaɪn/\",\"phonetics\":[{\"text\":\"/ˈɹæpaɪn/\",\"audio\":\"https://api.dictionaryapi.dev/media/pronunciations/en/rapine-us.mp3\",\"sourceUrl\":\"https://commons.wikimedia.org/w/index.php?curid=21396772\",\"license\":{\"name\":\"BY-SA 3.0\",\"url\":\"https://creativecommons.org/licenses/by-sa/3.0\"}}],\"meanings\":[{\"partOfSpeech\":\"noun\",\"definitions\":[{\"definition\":\"The seizure of someone's property by force; pillage, plunder.\",\"synonyms\":[],\"antonyms\":[]}],\"synonyms\":[],\"antonyms\":[]},{\"partOfSpeech\":\"verb\",\"definitions\":[{\"definition\":\"To plunder.\",\"synonyms\":[],\"antonyms\":[]}],\"synonyms\":[],\"antonyms\":[]}],\"license\":{\"name\":\"CC BY-SA 3.0\",\"url\":\"https://creativecommons.org/licenses/by-sa/3.0\"},\"sourceUrls\":[\"https://en.wiktionary.org/wiki/rapine\"]}]";

        JsonParser jsonParser = new JsonParser();
        List<String[]> result = jsonParser.extractWordAndDefinitions(sampleJsonOne);
        result.addAll(jsonParser.extractWordAndDefinitions(sampleJsonTwo));

        for (String[] entry : result) {
            System.out.println("Word: " + entry[0] + ", Definition: " + entry[1] + "");
        }

        // Assertions
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "There should be one definition in the list");

        String[] firstEntry = result.get(0);
        assertEquals("test", firstEntry[0], "The word should be 'test'");
        
    }
}

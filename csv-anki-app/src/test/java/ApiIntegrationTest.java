
import com.andrew.csvreader.ApiIntegration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import static org.junit.jupiter.api.Assertions.*;

class ApiIntegrationTest {

    @Test
    void testFetchDefinition_Success() throws Exception {
        // Use the real HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();
        ApiIntegration apiIntegration = new ApiIntegration(httpClient);

        // Call the real API
        String result = apiIntegration.fetchDefinition("example");
        // System.out.println(result);
        // Assertions
        assertNotNull(result);
        // You might want to check if the result is a valid JSON, contains certain keys, etc.
        // The exact assertions will depend on the structure of the API response
    }
}

import com.andrew.csvreader.MainApplication;
import com.andrew.csvreader.CsvWriter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class mainTest {
  // run mainApplication
  @Test
  public void testMainApplication() {
    MainApplication mainApplication = new MainApplication();
    // Add test assertions here
    assertNotNull(mainApplication);
  }
}

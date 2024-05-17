module com.andrew {
  requires javafx.controls;
  requires javafx.fxml;
  requires org.apache.commons.csv;
  requires org.json; // Add this line to require the org.json module
  requires java.net.http; // Add this line to require the java.net.http module

  opens com.andrew.csvreader to javafx.fxml;
  exports com.andrew.csvreader;
}



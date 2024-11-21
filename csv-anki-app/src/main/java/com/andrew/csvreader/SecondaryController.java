package com.andrew.csvreader;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        gui.setRoot("primary");
    }
}

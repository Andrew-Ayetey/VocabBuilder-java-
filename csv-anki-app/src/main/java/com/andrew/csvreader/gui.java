package com.andrew.csvreader;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class gui extends Application {

  private static Scene scene;

  @Override
  public void start(Stage stage) throws Exception {
    scene = new Scene(loadFXML("primary"), 640, 480);
    stage.setScene(scene);
    stage.show();
    
  }

  private static Parent loadFXML(String fxml) throws IOException{
    FXMLLoader fxmlLoader = new FXMLLoader(gui.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static void main(String[] args){
    launch();
  }
  
}

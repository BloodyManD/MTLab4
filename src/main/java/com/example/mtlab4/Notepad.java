package com.example.mtlab4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Notepad extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        TextViewerController.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Notepad.class.getResource("TextViewer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1280, 720);
        stage.setTitle("Text Viewer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiManager extends Application {
    public static BorderPane root;
    private Button login;
    private Button signUp;
    private Button guest;
    private Button admin;
    private Button login;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("..\\..\\Fxmls\\login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getStylesheets().add("Fxmls/Danial.css");
        primaryStage.setTitle("Sitadu");
        primaryStage.setScene(new Scene(root, 850, 600));
        System.out.println("show");
        primaryStage.show();
    }
}

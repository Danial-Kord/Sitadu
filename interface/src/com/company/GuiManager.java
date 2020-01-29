package com.company;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiManager extends Application {
    public static BorderPane root;
    private LoginManager loginManager;
    public static Stage mainStage;
    public static Sitadu sitadu;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        sitadu = new Sitadu();
        root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("..\\..\\Fxmls\\login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getStylesheets().add("Fxmls/Danial.css");
        loginManager = new LoginManager();
        mainStage = primaryStage;

        primaryStage.setTitle("Sitadu");
        primaryStage.setScene(new Scene(root, 800, 600));
        System.out.println("show");
        primaryStage.show();
    }

}

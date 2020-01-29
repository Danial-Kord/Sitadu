package com.company;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginManager {


    private AnchorPane loginPane;
    private Button login;
    private Button signUp;
    private Button guest;
    private Button admin;
    private Button user;
    private Button back;
    private TextField signUpEncourage;

    public LoginManager() {
        BorderPane root = GuiManager.root;
        loginPane = (AnchorPane)root.getCenter();
        login = (Button) loginPane.getChildren().get(2);
        signUp = (Button) loginPane.getChildren().get(4);
        guest = (Button) loginPane.getChildren().get(3);
        admin = (Button) loginPane.getChildren().get(1);
        user = (Button) loginPane.getChildren().get(0);
        signUpEncourage = (TextField)loginPane.getChildren().get(5);
        back = (Button)loginPane.getChildren().get(6);

        signUpEncourage.setVisible(false);
        login.setVisible(false);
        guest.setVisible(false);
        signUp.setVisible(false);
        back.setVisible(false);

        onUserEnter();
        onAdminEnter();
        onBackEnter();
        onLoginEnter();
    }

    private void onAdminEnter(){
        admin.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new LoginPanel(true);
            }
        });
    }
    private void onLoginEnter(){
        login.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new LoginPanel(false);
            }
        });
    }
    private void onUserEnter(){
        user.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                admin.setVisible(false);
                user.setVisible(false);
                signUp.setVisible(true);
                guest.setVisible(true);
                login.setVisible(true);
                back.setVisible(true);
                signUpEncourage.setVisible(true);
            }
        });

    }
    private void onBackEnter(){
        back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                admin.setVisible(true);
                user.setVisible(true);
                signUp.setVisible(false);
                guest.setVisible(false);
                login.setVisible(false);
                back.setVisible(false);
                signUpEncourage.setVisible(false);
            }
        });

    }

}


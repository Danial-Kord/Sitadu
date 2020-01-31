package GUI;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPanel {
    private Button ok;
    private TextField user;
    private TextField pass;
    private boolean admin;
    public LoginPanel(boolean admin){
        StackPane root = null;
        this.admin = admin;
        try {
            root = FXMLLoader.load(getClass().getResource("Fxmls\\loginPane.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        //  root.getStylesheets().add("Fxmls/Danial.css");
        Stage primaryStage = new Stage();
        StackPane stackPane = ((StackPane) (root.getChildren().get(0)));
        VBox vBox = (VBox)stackPane.getChildren().get(0);
        GridPane gridPane = (GridPane)vBox.getChildren().get(0);
        user = (TextField)gridPane.getChildren().get(2);
        pass = (TextField)gridPane.getChildren().get(3);
        ok = (Button) ((StackPane)(((StackPane)vBox.getChildren().get(1)))).getChildren().get(0);
        onOkClick();

        primaryStage.setTitle("Log in");
        primaryStage.setScene(new Scene(root, 400, 140));
        System.out.println("show");
        primaryStage.show();
    }

    private void onOkClick(){

        ok.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String userTest =  user.getText();
                String passTest =  pass.getText();
                if(GuiManager.sitadu.getAccount().logIn(admin,userTest,passTest)){
                    if(!admin)
                        new AccountPanel(GuiManager.sitadu.getAccount());
                    else {
                        new ManagementPanel();
                    }

                }
                else {
                    AttentionPane.warning("Wrong user or passWord!");
                }
            }
        });

    }
}

package GUI;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpPanel {
    private Button ok;
    private TextField user;
    private PasswordField pass;
    private Text wrong_Pass;
    private Stage stage;
    public SignUpPanel(){
        StackPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Fxmls\\SignUp.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        //  root.getStylesheets().add("Fxmls/Danial.css");
        Stage primaryStage = new Stage();
        stage = primaryStage;
        StackPane stackPane = ((StackPane) (root.getChildren().get(0)));
        VBox vBox = (VBox)stackPane.getChildren().get(0);
        GridPane gridPane = (GridPane)vBox.getChildren().get(0);
        user = (TextField)gridPane.getChildren().get(2);
        pass = (PasswordField)gridPane.getChildren().get(3);
        ok = (Button) ((StackPane)(((StackPane)vBox.getChildren().get(1)))).getChildren().get(0);
        wrong_Pass = (Text)gridPane.getChildren().get(4);
        wrong_Pass.setVisible(false);
        onOkClick();

        primaryStage.setTitle("Sign Up");
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

                if(GuiManager.sitadu.getAccount().settingUserPass(userTest,passTest) && !passTest.equals("")){
                    wrong_Pass.setVisible(false);
                    stage.close();
                    new Registration();
                }
                else {
                    wrong_Pass.setVisible(true);
                }
            }
        });

    }
}

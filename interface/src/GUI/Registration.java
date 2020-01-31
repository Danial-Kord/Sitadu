package GUI;

import com.company.Account;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Registration {
    private Button submit;
    private TextField first_name;
    private TextField last_name;
    private TextField melli_code;
    private TextField phone;
    private TextField age;
    public Registration(){
        GridPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Fxmls\\registration_form.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
          root.getStylesheets().add("GUI/Fxmls/Danial.css");
        Stage primaryStage = new Stage();
        first_name = (TextField) root.getChildren().get(2);
        last_name = (TextField) root.getChildren().get(4);
        melli_code = (TextField) root.getChildren().get(10);
        phone = (TextField) root.getChildren().get(8);
        age = (TextField) root.getChildren().get(12);
        submit = (Button) root.getChildren().get(13);
        onOkClick();

        primaryStage.setTitle("Sign Up");
        primaryStage.setScene(new Scene(root, 600, 700));
        System.out.println("show");
        primaryStage.show();
    }

    private void onOkClick(){

        submit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String first_nameS = first_name.getText();
                String last_nameS = last_name.getText();
                String melli_codeS = melli_code.getText();
                String phoneS = phone.getText();
                try {
                    int ageI = Integer.parseInt(age.getText());
                    Integer.parseInt(phoneS);
                    Integer.parseInt(melli_codeS);
                    Account account = GuiManager.sitadu.getAccount();
                    account.setMelli_code(melli_codeS);
                    account.setLast_name(last_nameS);
                    account.setFirst_name(first_nameS);
                    account.setAge(ageI);
                    account.setPhone(phoneS);
                    if(account.signUp()){

                    }
                    else {

                    }
                }
                catch (NumberFormatException e){
                    AttentionPane.Error("wrong input");
                }
            }
        });

    }
}

package GUI;
import com.company.Account;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
public class AccountPanel {


    TabPane tabPane;
    public AccountPanel(Account account){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("ControlManagement");


        BorderPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Fxmls\\account.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getStylesheets().add("GUI/Fxmls/Danial.css");
        tabPane = (TabPane) root.getCenter();
        TabPane tabPane = (TabPane) root.getCenter();

        Tab tab = tabPane.getTabs().get(0);
        FactorAccountTAble factorAccountTAble = new FactorAccountTAble(account);
        factorAccountTAble.isitials(tab);


        Tab tab2 = tabPane.getTabs().get(1);
        AddressAccountTable addressAccountTable = new AddressAccountTable(account);
        addressAccountTable.isitials(tab2);



        primaryStage.setScene(new Scene(root, 800, 600));
        System.out.println("show");
        primaryStage.show();
    }
}

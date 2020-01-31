package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagementPanel {
    TabPane tabPane;
    public ManagementPanel(){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("ControlManagement");


        BorderPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Fxmls\\Table - Copy.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
          root.getStylesheets().add("GUI/Fxmls/Danial.css");
        tabPane = (TabPane) root.getCenter();
        TabPane tabPane = (TabPane) root.getCenter();

        Tab tab = tabPane.getTabs().get(0);
        AccountTable myTAbleView = new AccountTable();
        myTAbleView.isitials(tab);

        Tab tab1 = tabPane.getTabs().get(2);
        PeykTable peykTable = new PeykTable();
        peykTable.isitials(tab1);

        Tab tab2 = tabPane.getTabs().get(1);
        FactorTable factorTable = new FactorTable();
        factorTable.isitials(tab2);


        Tab tab3 = tabPane.getTabs().get(4);
        MenuTable menuTable = new MenuTable();
        menuTable.isitials(tab3);

        Tab tab4 = tabPane.getTabs().get(5);
        RawMaterialTable rawMaterialTable  = new RawMaterialTable();
        rawMaterialTable.isitials(tab4);

        Tab tab5 = tabPane.getTabs().get(6);
        AddressTable addressTable = new AddressTable();
        addressTable.isitials(tab5);

        Tab tab6 = tabPane.getTabs().get(3);
        LogTable logTable = new LogTable();
        logTable.isitials(tab6);

        primaryStage.setScene(new Scene(root, 800, 600));
        System.out.println("show");
        primaryStage.show();
    }
}

package GUI;


import com.company.Account;
import com.company.Log;
import com.company.Log;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;





public class LogTable {
    private TableView<Log> table;
    private ObservableList<Log> data;
    private Text actionStatus;
    private ArrayList<Log> lastAdded = new ArrayList<Log>();
    public LogTable(){

    }

    public void isitials(Tab tab){


        // Logs label
        Label label = new Label("Log");
        label.setTextFill(Color.DARKBLUE);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
        HBox labelHb = new HBox();
        labelHb.setAlignment(Pos.CENTER);
        labelHb.getChildren().add(label);

        // Table view, data, columns and properties

        table = new TableView<>();
        data = getInitialTableData();
        table.setItems(data);
        table.setEditable(true);

        factorActions();

        table.setPrefWidth(450);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getSelectionModel().selectedIndexProperty().addListener(
                new RowSelectChangeListener());


        // Status message text
        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);

        // Vbox
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(25, 25, 25, 25));

        vbox.getChildren().addAll(labelHb, table, actionStatus);



        tab.setContent(vbox);


        // Select the first row
        table.getSelectionModel().select(0);
        Log factor = table.getSelectionModel().getSelectedItem();
        //actionStatus.setText(factor.toString());

    } // start()


    private void factorActions(){


        TableColumn name = new TableColumn("log");
        name.setCellValueFactory(new PropertyValueFactory<Log, String>("text"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());



        table.getColumns().setAll( name);
    }
    private class RowSelectChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number oldVal, Number newVal) {

            int ix = newVal.intValue();

            if ((ix < 0) || (ix >= data.size())) {

                return; // invalid data
            }

            Log account = data.get(ix);
            //  actionStatus.setText(account.toString());
        }
    }


    private ObservableList<Log> getInitialTableData() {

        List<Log> list = new ArrayList<>();
        ArrayList<Log> accounts = GuiManager.sitadu.getLogs();
        for (int i=0;i<accounts.size();i++){
            list.add(accounts.get(i));
        }
        ObservableList<Log> data = FXCollections.observableList(list);

        return data;
    }



}

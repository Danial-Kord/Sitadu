package GUI;

import com.company.Factor;
import com.company.Factor;
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

public class FactorTable {
    private TableView<Factor> table;
    private ObservableList<Factor> data;
    private Text actionStatus;
    private ArrayList<Factor> lastAdded = new ArrayList<Factor>();
    public FactorTable(){

    }

    public void isitials(Tab tab){


        // Factors label
        Label label = new Label("Factor");
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

        // Add and delete buttons
        Button addbtn = new Button("Add");
        addbtn.setOnAction(new AddButtonListener());
        Button delbtn = new Button("Delete");
        delbtn.setOnAction(new DeleteButtonListener());
        Button refbtn = new Button("refresh");
        refbtn.setOnAction(new RefreshHandler());
        Button save = new Button("save");
        save.setOnAction(new SaveButtonListener());
        HBox buttonHb = new HBox(10);
        buttonHb.setAlignment(Pos.CENTER);
        buttonHb.getChildren().addAll(addbtn, delbtn,refbtn,save);

        // Status message text
        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);

        // Vbox
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.getChildren().addAll(labelHb, table, buttonHb, actionStatus);



        tab.setContent(vbox);


        // Select the first row
        table.getSelectionModel().select(0);
        Factor factor = table.getSelectionModel().getSelectedItem();
        //actionStatus.setText(factor.toString());

    } // start()


    private void factorActions(){


        TableColumn name = new TableColumn("name");
        name.setCellValueFactory(new PropertyValueFactory<Factor, String>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Factor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Factor, String> t) {

                ((Factor) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setName(t.getNewValue());
            }
        });



        TableColumn<Factor, String> customer_id =
                new TableColumn<>("customer_id");
        customer_id.setCellValueFactory(
                new PropertyValueFactory<Factor,
                        String>("customer_id"));
        customer_id.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Factor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Factor, String> t) {

                ((Factor) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setCustomer_id(t.getNewValue());
            }
        });


        TableColumn<Factor, Integer> id =
                new TableColumn<>("id");
        id.setCellValueFactory(
                new PropertyValueFactory<Factor,
                        Integer>("id"));
        id.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Factor, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Factor, Integer> t) {

                ((Factor) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setId(t.getNewValue());
            }
        });


        TableColumn<Factor, Timestamp> time =
                new TableColumn<>("time");
        time.setCellValueFactory(
                new PropertyValueFactory<Factor,
                        Timestamp>("price"));
        time.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Factor, Timestamp>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Factor, Timestamp> t) {

                ((Factor) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setTime(t.getNewValue());
            }
        });


        table.getColumns().setAll(id, name,time,customer_id);
    }
    private class RowSelectChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number oldVal, Number newVal) {

            int ix = newVal.intValue();

            if ((ix < 0) || (ix >= data.size())) {

                return; // invalid data
            }

            Factor account = data.get(ix);
            //  actionStatus.setText(account.toString());
        }
    }


    private ObservableList<Factor> getInitialTableData() {

        List<Factor> list = new ArrayList<>();
        ArrayList<Factor> accounts = GuiManager.sitadu.getFactors();
        for (int i=0;i<accounts.size();i++){
            list.add(accounts.get(i));
        }
        ObservableList<Factor> data = FXCollections.observableList(list);

        return data;
    }

    private class AddButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            // Create a new row after last row
            //TODO
        }
    }



    private class SaveButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            for (int i=0;i<lastAdded.size();i++){
                //afa

            }
            lastAdded = new ArrayList<Factor>();
        }
    }


    private class DeleteButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            // Get selected row and delete
            System.out.println("here");
            int ix = table.getSelectionModel().getSelectedIndex();
            Factor account = (Factor) table.getSelectionModel().getSelectedItem();
            if(account.remove())
                data.remove(ix);
            else {
                AttentionPane.Error("some thing went wrong!");
            }
            // actionStatus.setText("Deleted: " + account.toString());

            // Select a row

            if (table.getItems().size() == 0) {

                actionStatus.setText("No data in table !");
                return;
            }

            if (ix != 0) {

                ix = ix -1;
            }

            table.requestFocus();
            table.getSelectionModel().select(ix);
            table.getFocusModel().focus(ix);
        }
    }

    private class RefreshHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            data = getInitialTableData();
            table.setItems(data);
        }
    }

}

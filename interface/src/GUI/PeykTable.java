package GUI;

import com.company.Peyk;
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
import java.util.ArrayList;
import java.util.List;

public class PeykTable {
    private TableView<Peyk> table;
    private ObservableList<Peyk> data;
    private Text actionStatus;
    private ArrayList<Peyk> lastAdded = new ArrayList<Peyk>();
    public PeykTable(){

    }

    public void isitials(Tab tab){


        // Peyks label
        Label label = new Label("Peyk");
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

        peykActions();

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
        Peyk peyk = table.getSelectionModel().getSelectedItem();
        //actionStatus.setText(peyk.toString());

    } // start()


    private void peykActions(){

        TableColumn firstName = new TableColumn("first Name");
        firstName.setCellValueFactory(new PropertyValueFactory<Peyk, String>("first_name"));
        firstName.setCellFactory(TextFieldTableCell.forTableColumn());
        firstName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Peyk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Peyk, String> t) {

                ((Peyk) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setFirst_name(t.getNewValue());
            }
        });
        TableColumn lastNAme = new TableColumn("last Name");
        lastNAme.setCellValueFactory(new PropertyValueFactory<Peyk, String>("last_name"));
        lastNAme.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNAme.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Peyk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Peyk, String> t) {

                ((Peyk) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setLast_name(t.getNewValue());
            }
        });

        TableColumn phone = new TableColumn("phone");
        phone.setCellValueFactory(new PropertyValueFactory<Peyk, String>("phone"));
        phone.setCellFactory(TextFieldTableCell.forTableColumn());
        phone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Peyk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Peyk, String> t) {

                ((Peyk) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setPhone(t.getNewValue());
            }
        });

        TableColumn id1 = new TableColumn("id");
        id1.setCellValueFactory(new PropertyValueFactory<Peyk, String>("id1"));
        id1.setCellFactory(TextFieldTableCell.forTableColumn());
        id1.setEditable(false);


        table.getColumns().setAll(id1, firstName,lastNAme,phone);
    }

    private class RowSelectChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number oldVal, Number newVal) {

            int ix = newVal.intValue();

            if ((ix < 0) || (ix >= data.size())) {

                return; // invalid data
            }

            Peyk peyk = data.get(ix);
              actionStatus.setText(peyk.toString());

        }
    }


    private ObservableList<Peyk> getInitialTableData() {

        List<Peyk> list = new ArrayList<>();
        ArrayList<Peyk> peyks = GuiManager.sitadu.getPeyks();
        for (int i=0;i<peyks.size();i++){
            list.add(peyks.get(i));
        }
        ObservableList<Peyk> data = FXCollections.observableList(list);

        return data;
    }

    private class AddButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            // Create a new row after last row
            Peyk peyk = new Peyk();
            data.add(peyk);
            int row = data.size() - 1;

            // Select the new row
            table.requestFocus();
            table.getSelectionModel().select(row);
            table.getFocusModel().focus(row);
            lastAdded.add(peyk);
            actionStatus.setText("New peyk: Enter title and author. Press <Enter>.");
        }
    }



    private class SaveButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            for (int i=0;i<lastAdded.size();i++){
                lastAdded.get(i).addNewPeyk();
            }
            lastAdded = new ArrayList<Peyk>();
        }
    }


    private class DeleteButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            // Get selected row and delete
            System.out.println("here");
            int ix = table.getSelectionModel().getSelectedIndex();
            Peyk peyk = (Peyk) table.getSelectionModel().getSelectedItem();
            if(peyk.remove())
                data.remove(ix);
            else {
                AttentionPane.Error("some thing went wrong!");
            }
            // actionStatus.setText("Deleted: " + peyk.toString());

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

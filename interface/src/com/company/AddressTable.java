package GUI;

import com.company.Address;
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

public class AddressTable {
    private TableView<Address> table;
    private ObservableList<Address> data;
    private Text actionStatus;
    private ArrayList<Address> lastAdded = new ArrayList<>();
    public AddressTable(){

    }

    public void isitials(Tab tab){


        // Addresss label
        Label label = new Label("Address");
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

        addressActions();

        table.setPrefWidth(450);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getSelectionModel().selectedIndexProperty().addListener(
                new RowSelectChangeListener());

        // Add and delete buttons
        Button delbtn = new Button("Delete");
        delbtn.setOnAction(new DeleteButtonListener());
        Button refbtn = new Button("refresh");
        refbtn.setOnAction(new RefreshHandler());
        Button save = new Button("save");
        save.setOnAction(new SaveButtonListener());
        HBox buttonHb = new HBox(10);
        buttonHb.setAlignment(Pos.CENTER);
        buttonHb.getChildren().addAll(delbtn,refbtn,save);

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
        Address address = table.getSelectionModel().getSelectedItem();
        //actionStatus.setText(address.toString());

    } // start()


    private void addressActions(){



        TableColumn phone = new TableColumn("name");
        phone.setCellValueFactory(new PropertyValueFactory<Address, String>("name"));
        phone.setCellFactory(TextFieldTableCell.forTableColumn());
        phone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Address, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Address, String> t) {

                ((Address) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setName(t.getNewValue());
            }
        });

        TableColumn address = new TableColumn("address");
        address.setCellValueFactory(new PropertyValueFactory<Address, String>("address"));
        address.setCellFactory(TextFieldTableCell.forTableColumn());
        address.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Address, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Address, String> t) {

                ((Address) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setAddress(t.getNewValue());
            }
        });


        TableColumn house_phone_number = new TableColumn("house_phone_number");
        house_phone_number.setCellValueFactory(new PropertyValueFactory<Address, String>("house_phone_number"));
        house_phone_number.setCellFactory(TextFieldTableCell.forTableColumn());
        house_phone_number.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Address, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Address, String> t) {

                ((Address) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setHouse_phone_number(t.getNewValue());
            }
        });



        TableColumn customer_id = new TableColumn("customer_id");
        customer_id.setCellValueFactory(new PropertyValueFactory<Address, String>("customer_id"));
        customer_id.setCellFactory(TextFieldTableCell.forTableColumn());
        customer_id.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Address, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Address, String> t) {

                ((Address) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setCustomer_id(t.getNewValue());
            }
        });

        TableColumn id1 = new TableColumn("id");
        id1.setCellValueFactory(new PropertyValueFactory<Address, String>("id1"));
        id1.setCellFactory(TextFieldTableCell.forTableColumn());
        id1.setEditable(false);




        table.getColumns().setAll(id1,phone,customer_id,house_phone_number,address);
    }
    private class RowSelectChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number oldVal, Number newVal) {

            int ix = newVal.intValue();

            if ((ix < 0) || (ix >= data.size())) {

                return; // invalid data
            }

            Address address = data.get(ix);
            actionStatus.setText(address.toString());
            address.update();
        }
    }


    private ObservableList<Address> getInitialTableData() {

        List<Address> list = new ArrayList<>();
        ArrayList<Address> addresss = GuiManager.sitadu.findAddress();
        for (int i=0;i<addresss.size();i++){
            list.add(addresss.get(i));
        }
        ObservableList<Address> data = FXCollections.observableList(list);

        return data;
    }



    private class SaveButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            for (int i=0;i<lastAdded.size();i++){
                lastAdded.get(i).setnewAddress();
            }
            lastAdded = new ArrayList<Address>();
        }
    }


    private class DeleteButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            // Get selected row and delete
            System.out.println("here");
            int ix = table.getSelectionModel().getSelectedIndex();
            Address address = (Address) table.getSelectionModel().getSelectedItem();
            if(address.removeAddress())
                data.remove(ix);
            else {
                AttentionPane.Error("some thing went wrong!");
            }
            // actionStatus.setText("Deleted: " + address.toString());

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

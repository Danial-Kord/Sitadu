package GUI;

import com.company.Account;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

public class AccountTable {
    private TableView<Account> table;
    private ObservableList<Account> data;
    private Text actionStatus;
    private ArrayList<Account> lastAdded = new ArrayList<Account>();
    public AccountTable(){

    }

    public void isitials(Tab tab){


        // Accounts label
        Label label = new Label("Accounts");
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

        accountActions();

        table.setPrefWidth(450);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getSelectionModel().selectedIndexProperty().addListener(
                new RowSelectChangeListener());

        // Add and delete buttons
        Button addbtn = new Button("addFactor");
        addbtn.setOnAction(new addNewFactor());
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
        Account account = table.getSelectionModel().getSelectedItem();
        //actionStatus.setText(account.toString());

    } // start()


    private void accountActions(){
        TableColumn userId = new TableColumn("user Id");
        userId.setCellValueFactory(new PropertyValueFactory<Account, String>("user"));
        userId.setCellFactory(TextFieldTableCell.forTableColumn());
        userId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Account, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Account, String> t) {

                ((Account) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setUser(t.getNewValue());
            }
        });

        TableColumn pass = new TableColumn("pass");
        pass.setCellValueFactory(new PropertyValueFactory<Account, String>("pass"));
        pass.setCellFactory(TextFieldTableCell.forTableColumn());
        pass.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Account, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Account, String> t) {

                ((Account) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setPass(t.getNewValue());
            }
        });
//
        TableColumn firstName = new TableColumn("first Name");
        firstName.setCellValueFactory(new PropertyValueFactory<Account, String>("first_name"));
        firstName.setCellFactory(TextFieldTableCell.forTableColumn());
        firstName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Account, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Account, String> t) {

                ((Account) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setFirst_name(t.getNewValue());
            }
        });
        TableColumn lastNAme = new TableColumn("last Name");
        lastNAme.setCellValueFactory(new PropertyValueFactory<Account, String>("last_name"));
        lastNAme.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNAme.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Account, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Account, String> t) {

                ((Account) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setLast_name(t.getNewValue());
            }
        });

        TableColumn phone = new TableColumn("phone");
        phone.setCellValueFactory(new PropertyValueFactory<Account, String>("phone"));
        phone.setCellFactory(TextFieldTableCell.forTableColumn());
        phone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Account, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Account, String> t) {

                ((Account) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setPhone(t.getNewValue());
            }
        });

        TableColumn age1 = new TableColumn("age1");
        age1.setCellValueFactory(new PropertyValueFactory<Account, String>("age1"));
        age1.setCellFactory(TextFieldTableCell.forTableColumn());
        age1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Account, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Account, String> t) {

                ((Account) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setAge1(t.getNewValue());
            }
        });

        TableColumn melli_code = new TableColumn("melli_code");
        melli_code.setCellValueFactory(new PropertyValueFactory<Account, String>("melli_code"));
        melli_code.setCellFactory(TextFieldTableCell.forTableColumn());
        melli_code.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Account, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Account, String> t) {

                ((Account) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setMelli_code(t.getNewValue());
            }
        });

        table.getColumns().setAll(userId,pass, firstName,lastNAme,phone,age1,melli_code);
    }
    private class RowSelectChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number oldVal, Number newVal) {

            int ix = newVal.intValue();

            if ((ix < 0) || (ix >= data.size())) {

                return; // invalid data
            }

            Account account = data.get(ix);

          //  actionStatus.setText(account.toString());
        }
    }


    private ObservableList<Account> getInitialTableData() {

        List<Account> list = new ArrayList<>();
        ArrayList<Account> accounts = GuiManager.sitadu.getAllAccounts();
        for (int i=0;i<accounts.size();i++){
            list.add(accounts.get(i));
        }
        ObservableList<Account> data = FXCollections.observableList(list);

        return data;
    }

    private class AddButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            // Create a new row after last row
            Account account = new Account();
            data.add(account);
            int row = data.size() - 1;

            // Select the new row
            table.requestFocus();
            table.getSelectionModel().select(row);
            table.getFocusModel().focus(row);
            lastAdded.add(account);
            actionStatus.setText("New account: Enter title and author. Press <Enter>.");
        }
    }



    private class SaveButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            for (int i=0;i<lastAdded.size();i++){
                lastAdded.get(i).signUp();
            }
            lastAdded = new ArrayList<Account>();
        }
    }


    private class DeleteButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            // Get selected row and delete
            System.out.println("here");
            int ix = table.getSelectionModel().getSelectedIndex();
            Account account = (Account) table.getSelectionModel().getSelectedItem();
            if(account.removeAccount())
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
            Account account = (Account) table.getSelectionModel().getSelectedItem();
            if(account!=null)
            account.update();
            data = getInitialTableData();
            table.setItems(data);
        }
    }

    private class addNewFactor implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            Account account = (Account) table.getSelectionModel().getSelectedItem();

            Factor factor = GuiManager.sitadu.delivary(account);
            new FoodAdderTable(factor);
        }
    }

}

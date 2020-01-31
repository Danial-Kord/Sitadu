package GUI;



import com.company.Factor;
import com.company.Food;
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

public class FoodAdderTable {

    private TableView<Food> table2;
    private ObservableList<Food> data2;
    private TableView<Food> table;
    private ObservableList<Food> data;
    private Text actionStatus;
    private Factor factor;
    private ArrayList<Food> lastAdded = new ArrayList<>();
    public FoodAdderTable(Factor factor){
        System.out.println(factor);
        this.factor = factor;
        Stage primaryStage = new Stage();
        primaryStage.setTitle("ControlManagement");


        BorderPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Fxmls\\foodAdd.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getStylesheets().add("GUI/Fxmls/Danial.css");
        TabPane tabPane = (TabPane) root.getCenter();

        Tab tab = tabPane.getTabs().get(0);
        Tab tab1 = tabPane.getTabs().get(1);
         TableView<Food> table = new TableView<>();
         ObservableList<Food> data=null;
        TableView<Food> table2 = new TableView<>();
        ObservableList<Food> data2=null;
        isitials(tab,table,data);
        isitials1(tab1,table2,data2);
        primaryStage.setScene(new Scene(root, 400, 140));
        System.out.println("show");
        primaryStage.show();
    }



    public void isitials1(Tab tab,TableView<Food> table,ObservableList<Food> data){
        // Foods label
        Label label = new Label("Food");
        label.setTextFill(Color.DARKBLUE);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
        HBox labelHb = new HBox();
        labelHb.setAlignment(Pos.CENTER);
        labelHb.getChildren().add(label);

        // Table view, data, columns and properties

        data = getInitialTableData2();
        table.setItems(data);
        table.setEditable(true);

        foodActions(table);

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
        Food food = table.getSelectionModel().getSelectedItem();
        //actionStatus.setText(food.toString());
        this.table = table;
        this.data = data;
    } // start()








    public void isitials(Tab tab,TableView<Food> table,ObservableList<Food> data){
        // Foods label
        Label label = new Label("Food");
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

        foodActions(table);

        table.setPrefWidth(450);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getSelectionModel().selectedIndexProperty().addListener(
                new RowSelectChangeListener());

        // Add and delete buttons
        Button addbtn = new Button("Add");
        addbtn.setOnAction(new AddButtonListener());

        HBox buttonHb = new HBox(10);
        buttonHb.setAlignment(Pos.CENTER);
        buttonHb.getChildren().addAll(addbtn);

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
        Food food = table.getSelectionModel().getSelectedItem();
        //actionStatus.setText(food.toString());
        this.table2 = table;
        this.data2 = data;
    } // start()


    private void foodActions(TableView<Food> table){



        TableColumn phone = new TableColumn("name");
        phone.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
        phone.setCellFactory(TextFieldTableCell.forTableColumn());
        phone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Food, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Food, String> t) {

                ((Food) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setName(t.getNewValue());
            }
        });

        TableColumn id1 = new TableColumn("id");
        id1.setCellValueFactory(new PropertyValueFactory<Food, String>("id1"));
        id1.setCellFactory(TextFieldTableCell.forTableColumn());
        id1.setEditable(false);

        TableColumn price = new TableColumn("price1");
        price.setCellValueFactory(new PropertyValueFactory<Food, String>("price1"));
        price.setCellFactory(TextFieldTableCell.forTableColumn());
        price.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Food, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Food, String> t) {

                ((Food) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setPrice1(t.getNewValue());
            }
        });


        table.getColumns().setAll(id1,phone,price);
    }
    private class RowSelectChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number oldVal, Number newVal) {

            int ix = newVal.intValue();

            if ((ix < 0) || (ix >= data.size())) {

                return; // invalid data
            }

            Food food = data.get(ix);
         //   actionStatus.setText(food.toString());
          //  food.update();
        }
    }


    private ObservableList<Food> getInitialTableData() {

        List<Food> list = new ArrayList<>();
        ArrayList<Food> foods = GuiManager.sitadu.getMenu();
        for (int i=0;i<foods.size();i++){
            list.add(foods.get(i));
        }
        ObservableList<Food> data = FXCollections.observableList(list);

        return data;
    }

    private ObservableList<Food> getInitialTableData2() {

        List<Food> list = new ArrayList<>();
        factor.findFoodsOfFactor();
        ArrayList<Food> foods = factor.getFoods();
        for (int i=0;i<foods.size();i++){
            list.add(foods.get(i));
        }
        ObservableList<Food> data = FXCollections.observableList(list);

        return data;
    }

    private class AddButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            Food food = (Food) table2.getSelectionModel().getSelectedItem();
            lastAdded.add(food);


            data.add(food);
            int row = data.size() - 1;

            // Select the new row
            table.requestFocus();
            table.getSelectionModel().select(row);
            table.getFocusModel().focus(row);
        }
    }



    private class SaveButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            int max = 0;

            if(factor.getTotal_price() != null)
                max = Integer.parseInt(factor.getTotal_price());
            for (int i=0;i<lastAdded.size();i++){
                max+=lastAdded.get(i).getPrice();
                factor.addFoodToFactor(lastAdded.get(i));
            }
            max+=max/4;
            factor.setTotal_price(""+max);
            factor.update();
            lastAdded = new ArrayList<Food>();
        }
    }


    private class DeleteButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {//TODO


            // Get selected row and delete
            System.out.println("here");
            int ix = table.getSelectionModel().getSelectedIndex();
            Food food = (Food) table.getSelectionModel().getSelectedItem();
            if(food != null) {
                for(int i=0;i<lastAdded.size();i++){
                    if(lastAdded.get(i).getId() == food.getId()){
                        lastAdded.remove(i);
                        break;
                    }
                }
                data.remove(ix);

            }
            else {
                AttentionPane.Error("some thing went wrong!");
            }
            // actionStatus.setText("Deleted: " + food.toString());

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
            data = getInitialTableData2();
            table.setItems(data);
        }
    }

}

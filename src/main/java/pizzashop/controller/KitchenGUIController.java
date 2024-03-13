package pizzashop.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.Calendar;

public class KitchenGUIController {
    @FXML
    private ListView kitchenOrdersList;
    @FXML
    public Button cook;
    @FXML
    public Button ready;

    private Stage stage;
    public static  ObservableList<String> order = FXCollections.observableArrayList();
    private Object selectedOrder;
    private Calendar now = Calendar.getInstance();
    private String extractedTableNumberString = new String();
    private int extractedTableNumberInteger;
    //thread for adding data to kitchenOrderList
    public  Thread addOrders = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        kitchenOrdersList.setItems(order);
                        }
                });
                try {
                    Thread.sleep(100);
                  } catch (InterruptedException ex) {
                    break;
                }
            }
        }
    });

    public void initialize() {
        //starting thread for adding data to kitchenOrderList
        addOrders.setDaemon(true);
        addOrders.start();

        //We added try-catch to verify if it is a selected order
        cook.setOnAction(event -> {
            try {
                selectedOrder = kitchenOrdersList.getSelectionModel().getSelectedItem();
                kitchenOrdersList.getItems().remove(selectedOrder);
                kitchenOrdersList.getItems().add(selectedOrder.toString()
                        .concat(" Cooking started at: ").toUpperCase()
                        .concat(now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE)));
            } catch (NullPointerException ex) {
                new Alert(Alert.AlertType.INFORMATION, "Select an order");
                System.out.println("Not selected order");
            }
        });

        ready.setOnAction(event -> {
            try {
                selectedOrder = kitchenOrdersList.getSelectionModel().getSelectedItem();
                //We verify if it is cooked firstly (before setting it Ready)****
                if ( kitchenOrdersList.getSelectionModel().getSelectedItem().toString().contains((" Cooking started at: ").toUpperCase())) {
                    kitchenOrdersList.getItems().remove(selectedOrder);
                    extractedTableNumberString = selectedOrder.toString().subSequence(5, 6).toString();
                    extractedTableNumberInteger = Integer.valueOf(extractedTableNumberString);
                    System.out.println("--------------------------");
                    System.out.println("Table " + extractedTableNumberInteger + " ready at: " + now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE));
                    System.out.println("--------------------------");
                } else {
                    System.out.println("It is not cooked yet");
                }
            } catch (NullPointerException ex) {
                new Alert(Alert.AlertType.INFORMATION, "Select an order");
                System.out.println("Not selected order");
            }
        });

    }

    public void setStage(Stage stage) {
            this.stage = stage;
    }
}
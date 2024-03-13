package pizzashop.gui;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;
import pizzashop.controller.KitchenGUIController;

import java.io.IOException;
import java.util.Optional;

public class KitchenGUI {
    public void KitchenGUI() {
        VBox vBoxKitchen = null;

        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/kitchenGUIFXML.fxml"));
            vBoxKitchen = loader.load();

            // Get the controller
            KitchenGUIController controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Kitchen");
            stage.setResizable(false);

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to exit Kitchen window?", ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> result = exitAlert.showAndWait();
                    if (result.get() == ButtonType.YES){
                        //Stage stage = (Stage) this.getScene().getWindow();
                        stage.close();
                    }
                    // consume event
                    else if (result.get() == ButtonType.NO){
                        event.consume();
                    }
                    else {
                        event.consume();
                    }
                }

            });
            stage.setAlwaysOnTop(false);
            stage.setScene(new Scene(vBoxKitchen));
            stage.show();
            controller.setStage(stage);
            stage.toBack();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

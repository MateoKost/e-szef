package API.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public abstract class BaseTopController extends Pane {

    public BaseTopController(String nameView) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            URL xmlURL = getClass().getResource("/views/"+ nameView +".fxml");
            loader.setLocation(xmlURL);
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setMinHeight(300);
            stage.setMinWidth(300);
            stage.show();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

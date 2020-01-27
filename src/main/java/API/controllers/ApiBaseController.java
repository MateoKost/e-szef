package API.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

public abstract class ApiBaseController extends Pane {
    @FXML
    protected VBox container;
    public ApiBaseController(String controllerName, VBox motherboard )
    {
        this.container = motherboard;
        FXMLLoader loader = new FXMLLoader();
        URL xmlURL = getClass().getResource("/views/"+ controllerName +".fxml");
        loader.setLocation(xmlURL);
        loader.setController(this);

        Node borderPane = null;
        try {
            borderPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        motherboard.getChildren().add(borderPane);
    }


}

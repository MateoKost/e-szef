package API;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class MainApplication extends Application {

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        URL xmlURL = getClass().getResource("/views/Login.fxml");
        loader.setLocation(xmlURL);
        Parent root = loader.load();
        Scene scene2 = new Scene(root);
        stage.setScene(scene2);
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}

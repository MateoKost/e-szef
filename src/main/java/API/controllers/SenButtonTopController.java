package API.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

class SenButtonTopController {
    @FXML private ImageView imageView;
    @FXML private VBox background;
    @FXML private Pane tab;
    private TopPanelController superController;
    private Image inactiveImage, activeImage;
    private String role;

    SenButtonTopController(TopPanelController superController, String role, String inActiveImagePath, String activeImagePath){

        this.superController=superController;
        this.role=role;

        FXMLLoader loader= new FXMLLoader();
        URL xmlURL = getClass().getResource("/views/senButtonView.fxml");
        loader.setLocation(xmlURL);
        loader.setController(this);

        try {
            VBox background =  loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        inactiveImage=new Image(inActiveImagePath);
        activeImage=new Image(activeImagePath);
        reseImageView();

        background.setOnMouseClicked(this::imageViewClick);
        background.setOnMouseEntered(this::imageMouseEntered);
        background.setOnMouseExited(this::imageMouseExited);

        background.setPrefWidth(55);
        superController.getTopPanel().getChildren().add(background);
    }


    @FXML
    private void imageViewClick(Event evt){

        if(superController.getActiveSenBtn()!=this) {
            superController.handleRoleInvoke(role);
                imageView.setImage(activeImage);
                background.setStyle("-fx-background-color: #330804");
        }
        superController.setActiveSenBtn(this);
    }

    @FXML
    private void imageMouseEntered(Event evt){
        if(superController.getActiveSenBtn()!=this) {
            background.setStyle("-fx-background-color: #330804");
        }
    }

    @FXML
    private void imageMouseExited(Event evt){

        if(superController.getActiveSenBtn()!=this) {
            resetBackground();
        }
    }

    void resetTab() {
        tab.setStyle("-fx-background-color: transparent");
    }
    void resetBackground() {
        background.setStyle("-fx-background-color: transparent");
    }
    void reseImageView() {
        imageView.setImage(inactiveImage);
    }
    
}

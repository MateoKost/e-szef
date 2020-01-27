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

public class SenButtonController {
    @FXML private ImageView imageView;
    @FXML private VBox background;
    @FXML private Pane tab;
    private LeftPanelController superController;
    private Image inactiveImage, activeImage;
    private String role;

    SenButtonController(LeftPanelController superController, String role, String inActiveImagePath, String activeImagePath){

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

        superController.getLeftPanel().getChildren().add(background);
    }

    public String getRole() {
        return role;
    }

    @FXML
    private void imageViewClick(Event evt){
        if(superController.getActiveSenBtn()!=this) {
            imageView.setImage(activeImage);
            tab.setStyle("-fx-background-color: #D9A577");
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

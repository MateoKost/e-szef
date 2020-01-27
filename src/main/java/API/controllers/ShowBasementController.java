package API.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class ShowBasementController extends ApiBaseController {

    @FXML private VBox floorGroup;
    @FXML private Pane paddingPane;

    ShowBasementController(WelcomeViewController welcomeViewController) {
        super("showBasement", welcomeViewController.getFloorGroup());
        this.floorGroup = welcomeViewController.getFloorGroup();
        this.paddingPane = (Pane) floorGroup.getChildren().get(0);


        if (this.floorGroup.getChildren().size() < 3) {
            this.paddingPane.setPrefHeight(this.paddingPane.getHeight() - 30);
        }
    }

    @FXML
    public void showBasement(){

    }
}

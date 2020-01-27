package API.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;


public class RightPanelController {
    private VBox rightPanel, slideVBox;
    private Tab selectedTab;
    private WelcomeViewController superController;
    private RoomPanelController roomCtrl_1, roomCtrl_2;

    public RightPanelController(WelcomeViewController superController){
        this.rightPanel=superController.getRightPanel();
        this.slideVBox=superController.getRightSlideVBox();
        this.superController=superController;
        this.slideVBox.setPrefWidth(0);
    }

    public void handleChoosenBox(RoomPanelController roomCtrl_1, RoomPanelController roomCtrl_2){
        this.roomCtrl_1=roomCtrl_1;
        this.roomCtrl_2=roomCtrl_2;

        TabPane roomTabs = new TabPane();

        if(roomCtrl_1!=null){
            Tab tab1 = new Tab("Pomieszczenie nr:"+roomCtrl_1.getRoomNumber());
            tab1.setOnSelectionChanged(this::chooseTab);
            roomTabs.getTabs().add(tab1);
        }

        if(roomCtrl_2!=null){
            Tab tab2 = new Tab("Pomieszczenie nr:"+roomCtrl_2.getRoomNumber());
            tab2.setOnSelectionChanged(this::chooseTab);
            roomTabs.getTabs().add(tab2);
        }

        roomTabs.setSide(Side.RIGHT);
        roomTabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        roomTabs.setPrefHeight(450);

        rightPanel.getChildren().clear();
        rightPanel.getChildren().add(roomTabs);
    }

    @FXML public void chooseTab(Event event){
        Tab eventTab=(Tab)event.getSource();

        if(eventTab.isSelected() && selectedTab!=eventTab){
            slideVBox.getChildren().clear();
            selectedTab=eventTab;
            int roomNr=Integer.parseInt(selectedTab.getText().split(":")[1]);
            slideVBox.setPrefWidth(300);
            VBox v1=new VBox();
            VBox v2=new VBox();
            if(roomCtrl_1.getRoomNumber()==roomNr){
                roomCtrl_1.fillItems(v1);
                roomCtrl_1.fillSoldiers(v2);
            }
            else if(roomCtrl_2.getRoomNumber()==roomNr){
                roomCtrl_2.fillItems(v1);
                roomCtrl_2.fillSoldiers(v2);
            }
            slideVBox.getChildren().addAll(v1,v2);
        }
    }
}

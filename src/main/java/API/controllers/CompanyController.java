package API.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class CompanyController extends ApiBaseController {

    @FXML private VBox floorGroup;
    @FXML private GridPane Box, PreviousClicked;
    @FXML private Label floorLabel;
    @FXML private WelcomeViewController welcomeViewController;

    CompanyController(WelcomeViewController welcomeViewController){
        super("companyView",welcomeViewController.getFloorGroup());
        this.floorGroup = welcomeViewController.getFloorGroup();
        this.welcomeViewController=welcomeViewController;

    }

    @FXML
    public void handleBoxMouseEntered(Event evt)
    {
        GridPane SourceBox = ((GridPane) evt.getSource());
        if(PreviousClicked!=SourceBox)
            SourceBox.setStyle("-fx-background-color: #F9E3BD;-fx-border-color: #FFC457; -fx-border-width: 5;");
    }

    @FXML
    public void handleBoxMouseExit(Event evt)
    {
        GridPane SourceBox = ((GridPane) evt.getSource());
        if(PreviousClicked!=SourceBox)
            SourceBox.setStyle(" -fx-border-color: #73693B; -fx-border-width: 2;");
    }

    @FXML
    public void handleBoxLeftClicked(Event evt)
    {
        if(PreviousClicked!=null)
            PreviousClicked.setStyle("-fx-border-color:#73693B; -fx-border-width: 2");

        GridPane SourceBox = ((GridPane) evt.getSource());
        int SourceBoxChildrenCount = SourceBox.getChildren().size();
        if(SourceBoxChildrenCount>0)
        {
            if (SourceBoxChildrenCount==2)
            {
                Label BoxRoom_1 = (Label)(SourceBox.getChildren().get(0));
                Label BoxRoom_2 = (Label)(SourceBox.getChildren().get(1));
                String BoxDescriptionRoom_2 = BoxRoom_2.getText();
                String BoxDescriptionRoom_1 = BoxRoom_1.getText();
                int nr1 = Integer.parseInt(BoxDescriptionRoom_1);
                int nr2 = Integer.parseInt(BoxDescriptionRoom_2);

               try {
                    this.welcomeViewController.getRightPanelController().handleChoosenBox(new RoomPanelController(nr1),new RoomPanelController(nr2));
               }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }
        SourceBox.setStyle("-fx-border-color: #F2EAC4; -fx-background-color: #F98040 ;  -fx-border-width: 7;");
        PreviousClicked=SourceBox;
    }

}

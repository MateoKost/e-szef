package API.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class WelcomeViewController {

    @FXML private BorderPane MotherBorderPane;
    @FXML private Label PreviousClickedFont;
    @FXML private VBox floorGroup, BoxPanel, leftPanel, rightPanel,  leftSlideVBox, rightSlideVBox;
    @FXML private HBox topPanel;
    private RightPanelController rightPanelController;


    public void initialize() {

        new LeftPanelController(this);
        new TopPanelController(this);
        rightPanelController=new RightPanelController(this);
        addCMP();
    }

    @FXML
    public void handleFontClick(Event evt) {
        Label SourceLabel = ((Label) evt.getSource());
        if(PreviousClickedFont!=null)
            PreviousClickedFont.setStyle("");
        PreviousClickedFont=SourceLabel;
        String fontSize = "-fx-font-size: "+ SourceLabel.getText();
        MotherBorderPane.setStyle(fontSize);
        SourceLabel.setStyle(" -fx-background-color: #F9E3BD");
    }

    private void addCMP()  {
        try {
            new CompanyController(this);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

     public VBox getFloorGroup(){ return floorGroup; }
     VBox getBoxPanel(){return BoxPanel;}
     VBox getLeftPanel(){
        return leftPanel;
    }
    public VBox getRightPanel() { return rightPanel; }
    public VBox getLeftSlideVBox(){
        return leftSlideVBox;
    }
    public VBox getRightSlideVBox() { return rightSlideVBox; }

    public RightPanelController getRightPanelController() { return rightPanelController; }

     HBox getTopPanel(){
        return topPanel;
    }

}

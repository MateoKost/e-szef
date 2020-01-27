package API.controllers;

import javafx.scene.layout.HBox;

class TopPanelController {
    private HBox topPanel;
    private SenButtonTopController activeSenBtn;
    private WelcomeViewController superController;

    TopPanelController(WelcomeViewController superController){
        this.topPanel=superController.getTopPanel();
        this.superController=superController;
        new SenButtonTopController(this, "com","/images/com.png", "/images/com_active.png");
        new SenButtonTopController(this, "user","/images/user.png", "/images/user_active.png");
        new SenButtonTopController(this, "set","/images/set.png", "/images/set_active.png");
  }
    void handleRoleInvoke(String role){
        switch (role) {
            case "com":
                break;
            case "user":
                superController.getTopPanel().getChildren().add(new UserController());
                break;
            case "set":
                superController.getTopPanel().getChildren().add(new SettingsController());
                break;
        }
    }


    void setActiveSenBtn(SenButtonTopController activeSenBtn){
        if(this.activeSenBtn!=null){
            this.activeSenBtn.resetTab();
            this.activeSenBtn.resetBackground();
            this.activeSenBtn.reseImageView();
        }
        if(this.activeSenBtn==activeSenBtn) {

            this.activeSenBtn=null;
            return;
        }
        this.activeSenBtn=activeSenBtn;

    }


    SenButtonTopController getActiveSenBtn(){ return this.activeSenBtn; }
    HBox getTopPanel(){ return this.topPanel; }
}

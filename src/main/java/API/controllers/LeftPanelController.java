package API.controllers;

import javafx.scene.layout.VBox;

class LeftPanelController {
    private VBox leftPanel, slideVBox;
    private SenButtonController activeSenBtn;
    private WelcomeViewController superController;
    private boolean basementActive=false;

    LeftPanelController(WelcomeViewController superController){
        this.leftPanel=superController.getLeftPanel();
        this.slideVBox=superController.getLeftSlideVBox();
        this.superController=superController;
        this.slideVBox.setPrefWidth(0);
        new SenButtonController(this, "addItem","/images/add.png", "/images/add_active.png");
        new SenButtonController(this, "findSoldier","/images/search.png", "/images/search_active.png");
        new SenButtonController(this, "deleteItem","/images/remove.png", "/images/remove_active.png");
        new SenButtonController(this, "showAllItems","/images/list.png", "/images/list_active.png");
        new SenButtonController(this, "showBasement","/images/add.png", "/images/add_active.png");
    }

    void setActiveSenBtn(SenButtonController activeSenBtn){
        if(this.activeSenBtn!=null){
            this.activeSenBtn.resetTab();
            this.activeSenBtn.resetBackground();
            this.activeSenBtn.reseImageView();
            slideVBox.getChildren().clear();
        }
        if(this.activeSenBtn==activeSenBtn) {
            setSlideVBoxWidth(0);
            this.activeSenBtn=null;
            return;
        }
        this.activeSenBtn=activeSenBtn;
        handleRoleInvoke(activeSenBtn.getRole());
        if(activeSenBtn.getRole()!="showBasement")
            setSlideVBoxWidth(300);
    }

    SenButtonController getActiveSenBtn(){ return this.activeSenBtn; }

    VBox getLeftPanel(){ return this.leftPanel; }

    void handleRoleInvoke(String role){
        if(role.equals("addItem"))
         addItem();
        if(role.equals("findSoldier"))
            findSoldier();
        if(role.equals("deleteItem"))
            deleteItem();
        if(role.equals("showAllItems"))
            showAllItems();
        if(role.equals("showBasement"))
            showBasement();
    }

    private void setSlideVBoxWidth(int width){
        slideVBox.setPrefWidth(width);
    }


    private void addItem(){
        try {
            new AddItemController(slideVBox);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void findSoldier(){
        try {
            new FindSoldierController(slideVBox);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void deleteItem(){
        try {
            new DeleteItemController(slideVBox);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void showAllItems(){
        try {
            new ShowAllItemsController(slideVBox);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void showBasement(){
        try {
            if(!basementActive) {
                basementActive = true;
                new ShowBasementController(superController);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

package API.controllers;

import Entities.Item;
import Entities.Soldier;
import Models.ItemModel;
import Models.SoldierModel;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.net.URL;

public class RoomPanelController{

    private int roomNumber;
    private TableView<Item> inventoryTableView;
    private TableView<Soldier> soldierTableView;

    public RoomPanelController(int nr)throws Exception{
        this.roomNumber=nr;
        try {
            FXMLLoader loader1 = new FXMLLoader();
            URL xmlURL = getClass().getResource("/views/roomPanelView.fxml");
            loader1.setLocation(xmlURL);
            loader1.setController(this);
            loader1.load();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        fillAndCreateItemTable(nr);
        fillAndCreateSoldiersTable(nr);
    }

    public void fillAndCreateItemTable(int nr) {

        TableView<Item> inventoryTableView = new TableView<>();
        TableColumn<Item, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idItem"));
        TableColumn<Item, String> nameColumn = new TableColumn<>("Nazwa");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        TableColumn<Item, Integer> idRoomColumn = new TableColumn<>("Pokój");
        idRoomColumn.setCellValueFactory(new PropertyValueFactory<>("idRoom"));
        inventoryTableView.getColumns().addAll(idColumn, nameColumn, idRoomColumn);
        this.inventoryTableView=inventoryTableView;
        Task<ObservableList> loadDataTask = new Task<ObservableList>() {
            @Override
            protected ObservableList call() throws Exception {
                ItemModel itemModel = new ItemModel();
                return itemModel.getListOfItem(nr);
            }
        };
        loadDataTask.setOnSucceeded(e -> {
            if(loadDataTask.getValue().size()==0) {
                inventoryTableView.getPlaceholder().setVisible(false);
                inventoryTableView.getItems().clear();
            }
            else inventoryTableView.getItems().setAll(loadDataTask.getValue());
        });
        loadDataTask.setOnFailed(e ->{inventoryTableView.getItems().clear();});

        ProgressIndicator progressIndicator = new ProgressIndicator();
        inventoryTableView.setPlaceholder(progressIndicator);

        Thread loadDataThread = new Thread(loadDataTask);
        loadDataThread.start();


    }

    public void fillAndCreateSoldiersTable(int nr) {

        TableView<Soldier> soldierTableView = new TableView<>();
        TableColumn<Soldier, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idSoldier"));
        TableColumn<Soldier, String> firstNameColumn = new TableColumn<>("Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Soldier, String> lastNameColumn = new TableColumn<>("LastName");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        soldierTableView.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn);
        Task<ObservableList> loadDataTask = new Task<ObservableList>() {
            @Override
            protected ObservableList call() throws Exception {
                SoldierModel soldierModel = new SoldierModel();
                return soldierModel.getListOfSoldier(nr);
            }

        };
        ProgressIndicator progressIndicator = new ProgressIndicator();
        soldierTableView.setPlaceholder(progressIndicator);

        loadDataTask.setOnSucceeded(e -> {
            if(loadDataTask.getValue().size()==0)
            {
                soldierTableView.getPlaceholder().setVisible(false);
            }
            else {
                soldierTableView.getItems().setAll(loadDataTask.getValue());
            }
        });
        loadDataTask.setOnFailed(e ->{});

        Thread loadDataThread = new Thread(loadDataTask);
        loadDataThread.start();

        this.soldierTableView=soldierTableView;

    }

    public void fillItems(VBox panel){
        panel.getChildren().clear();
        panel.getChildren().add(inventoryTableView);
    }

    public void fillSoldiers(VBox panel){
        panel.getChildren().clear();
        panel.getChildren().add(soldierTableView);
    }

    public int getRoomNumber() { return roomNumber; }


}

package API.controllers;

import Entities.Item;
import Models.ItemModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class AddItemController extends ApiBaseController {

    @FXML private ComboBox<String> itemCategoryComboBox;
    @FXML private ComboBox<String> roomComboBox;
    private String itemCategoryStartValue,roomStartValue;
    private ObservableList<String> itemList;
    private ObservableList<String> roomList;


    AddItemController(VBox container)
    {
        super("addItem",container);
        itemCategoryStartValue="Wybierz kategorię...";
        itemList = FXCollections.observableArrayList("Szafa", "Komoda","Biurko","Łóżko");
        roomStartValue="Wybierz pokój...";
        List<String> r = new ArrayList<>();
        for(int i=0;i<=43;i++){
            String value = String.valueOf(400+i);
            r.add(value);
        }
        roomList = FXCollections.observableArrayList(r);

        itemCategoryComboBox.setValue(itemCategoryStartValue);
        itemCategoryComboBox.setItems(itemList);

        roomComboBox.setValue(roomStartValue);
        roomComboBox.setItems(roomList);
    }


    @FXML
    public void addItem()
    {
        String name = itemCategoryComboBox.getValue();
        String idRoom = roomComboBox.getValue();

        if(name.equals(itemCategoryStartValue))
        {
            itemCategoryComboBox.setStyle("-fx-border-width: 1; -fx-border-color: red");
            return;
        }
        if (idRoom.equals(roomStartValue))
        {
            roomComboBox.setStyle("-fx-border-width: 1; -fx-border-color: red");
            return;
        }

        Task<Void> addItem = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Item item = new Item();
                int id = Integer.parseInt(idRoom);
                item.setIdRoom(id);
                item.setItemName(name);
                ItemModel itemModel = new ItemModel();
                itemModel.createItem(item);
                return null;
            }
        };
        Thread thread = new Thread(addItem);
        thread.start();
        
    }

    @FXML
    public void resetHandler()
    {
        itemCategoryComboBox.setStyle("-fx-border-width: 1; -fx-border-color: null");
        roomComboBox.setStyle("-fx-border-width: 1; -fx-border-color: null");
        itemCategoryComboBox.setValue(null);
        itemCategoryComboBox.setValue(itemCategoryStartValue);
        roomComboBox.setValue(null);
        roomComboBox.setValue(roomStartValue);
    }

    @FXML
    public void comboBoxCheck(Event evt){
        ComboBox comboBox = ((ComboBox) evt.getSource());
        if(comboBox.getValue()==roomStartValue || comboBox.getValue()==itemCategoryStartValue || comboBox.getValue()=="")
        {
            comboBox.setStyle("-fx-border-width: 1; -fx-border-color: red");
        }
        else comboBox.setStyle("-fx-border-width: 1; -fx-border-color: green");
    }

}

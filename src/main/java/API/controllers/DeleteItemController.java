package API.controllers;

import Entities.Item;
import Models.ItemModel;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class DeleteItemController extends ApiBaseController {

    @FXML
    private ComboBox<Integer> rooms;
    @FXML
    private JFXButton deleteButton;
    private ObservableList<Integer> idRooms;
    TableView<Item> tab;

    public DeleteItemController(VBox container) {
        super("deleteItem",container);
        List<Integer> r = new ArrayList<>();
        for(int i=0;i<=43;i++){
            r.add(400+i);
        }
        idRooms = FXCollections.observableArrayList(r);

        rooms.setValue(400);
        rooms.setItems(idRooms);

    }
    @FXML
    private void showItems(){
        if(container.getChildren().size()>1) container.getChildren().remove(1);

        int number = rooms.getSelectionModel().getSelectedItem();
        ItemModel itemModel = new ItemModel();
        ObservableList items = itemModel.getListOfItem(number);
        tab = new TableView<>();
        TableColumn<Item, Integer> quantityColumn2 = new TableColumn<>("Id ");
        quantityColumn2.setCellValueFactory(new PropertyValueFactory<>("idItem"));
        TableColumn<Item, String> priceColumn = new TableColumn<>("Nazwa");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        TableColumn<Item, Integer> quantityColumn = new TableColumn<>("Numer pokoju");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("idRoom"));
        tab.setItems(items);

        tab.getColumns().addAll(quantityColumn2,priceColumn, quantityColumn);

        container.getChildren().add(tab);
    }
    @FXML
    public void deleteItem()
    {
        Task<Void> deleteItem = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                ItemModel itemModel = new ItemModel();
                Item item = tab.getSelectionModel().getSelectedItem();
                itemModel.deleteItem(item);
            return null;
            }
        };
        Thread thread = new Thread(deleteItem);
        thread.start();

        showItems();
    }
}

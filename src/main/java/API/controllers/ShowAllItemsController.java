package API.controllers;

import Entities.Item;
import Models.ItemModel;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


class ShowAllItemsController extends ApiBaseController {

    ShowAllItemsController(VBox container)
    {
        super("showAllItems",container);
        TableView<Item> tab = new TableView<>();
        TableColumn<Item, Integer> quantityColumn2 = new TableColumn<>("Id ");
        quantityColumn2.setCellValueFactory(new PropertyValueFactory<>("idItem"));
        TableColumn<Item, String> priceColumn = new TableColumn<>("Nazwa");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        TableColumn<Item, Integer> quantityColumn = new TableColumn<>("Numer pokoju");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("idRoom"));
        tab.getColumns().addAll(quantityColumn2,priceColumn, quantityColumn);

        container.getChildren().addAll(tab);

            Task<ObservableList> show = new Task<ObservableList>() {
                @Override
                protected ObservableList call() {
                    ItemModel itemModel = new ItemModel();
                    return itemModel.getListOfAllItem();
                }
            };
            show.setOnSucceeded(e->{
                tab.getItems().setAll(show.getValue());
            });
            Thread t1 = new Thread(show);
            t1.start();
    }

}

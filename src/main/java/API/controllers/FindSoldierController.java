package API.controllers;

import Entities.Soldier;
import Models.SoldierModel;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.util.List;

public class FindSoldierController extends ApiBaseController {

    @FXML private JFXTextField lastName;
    public static List<Soldier> soldier;
    public FindSoldierController(VBox container)
    {
        super("findSoldier",container);
    }

    @FXML
    public void findSoldier() throws Exception
    {
        String lastName = this.lastName.getText();
        if (lastName.equals("")) {
            System.out.println("Wprowadz nazwisko!");

        } else {
                Task<List<Soldier>> find = new Task<List<Soldier>>() {
                    @Override
                    protected List<Soldier> call() throws Exception {
                        SoldierModel soldierModel = new SoldierModel();
                        soldier = soldierModel.getSoldier(lastName);
                        return soldier;
                    }
                };
                find.setOnSucceeded(e->{
                    if(soldier.size()==0) {
                        if(container.getChildren().size()>1) container.getChildren().remove(1);
                        Label label = new Label();
                        label.setText("Brak żołnierza o podanym nazwisku");
                        container.getChildren().add(label);
                    }
                    else {
                        for(int i=0;i<soldier.size();i++){
                            if(container.getChildren().size()>1) container.getChildren().remove(1);
                            Label label = new Label();
                            label.setText(soldier.get(i).getIdRoom() + " " + soldier.get(i).getRank() + " " + soldier.get(i).getName() + " " + soldier.get(i).getLastName());
                            container.getChildren().add(label);
                        }
                    }
                });
               Thread thread = new Thread(find);
               thread.start();

        }
    }
}

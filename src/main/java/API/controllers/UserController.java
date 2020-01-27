package API.controllers;

import Entities.User;
import Models.UserModel;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class UserController extends BaseTopController {
    @FXML
    private Text email,name,lastname,company;

    public UserController()
    {
        super("userView");
        User user = new UserModel().getUser(LoginController.e, LoginController.p);
        this.company.setText(String.valueOf(user.getKompania()));
        this.email.setText(user.getEmail());
        this.name.setText(user.getName());
        this.lastname.setText(user.getLastName());
    }
}

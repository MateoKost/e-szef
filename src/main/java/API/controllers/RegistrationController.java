package API.controllers;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Models.UserModel;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RegistrationController {

    @FXML private JFXTextField email;
    @FXML private JFXPasswordField password1;
    @FXML private JFXPasswordField password2;
    @FXML private JFXTextField name;
    @FXML private JFXTextField lastname;
    @FXML private Label notEqualPasswordsWarning;
    @FXML private Label emailExist;
    @FXML private Label weakPassword;

  @FXML
    public void register(){
        notEqualPasswordsWarning.setVisible(false);
        emailExist.setVisible(false);
        weakPassword.setVisible(false);
        String email = this.email.getText();
        String password1 = this.password1.getText();
        String password2 = this.password2.getText();
        String name = this.name.getText();
        String lastname = this.lastname.getText();
        if(!password1.equals(password2)) notEqualPasswordsWarning.setVisible(true);
        else{
            // cyfra co najmniej raz, mala litera co najmniej raz, duza litera co najmniej raz, co najmniej 8 znakow
            String pattern ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(password1);
            if(m.matches()) {
                UserModel userModel = new UserModel();
                if (userModel.getUser(email) != null) emailExist.setVisible(true);
                else {
                   userModel.createUser(email, password1, name, lastname);
                }
            }
            else{
                weakPassword.setVisible(true);
            }
        }
    }


}

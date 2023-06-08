/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mainpkg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import models.User;

/**
 * FXML Controller class
 *
 * @author sajee
 */
public class NewAccountSceneController implements Initializable {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField newPasswordPasswordField;
    @FXML
    private PasswordField retypePasswordPasswordField;
    
    private Alert msgbox = new Alert(AlertType.ERROR);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginLabelOnClick(MouseEvent event) throws IOException {
        Scene scene = (Scene) ((Label) event.getSource()).getScene();
        
        Stage stage = (Stage) scene.getWindow();
        
        Scene loginScene = new Scene(FXMLLoader.load(getClass().getResource("LoginScene.fxml")));
        
        stage.setScene(loginScene);

    }

    @FXML
    private void createAccountButtonOnClick(ActionEvent event) throws FileNotFoundException, IOException {
        try
        {
            if (User.loadUser(usernameTextField.getText()) == null)
            {
                if (newPasswordPasswordField.getText().equals(retypePasswordPasswordField.getText()))
                {
                    User new_user = new User(usernameTextField.getText(), newPasswordPasswordField.getText());

                    new_user.save();

                    msgbox.setAlertType(AlertType.CONFIRMATION);
                    msgbox.setContentText("Successfully created user");
                }
                else
                {
                    msgbox.setAlertType(AlertType.WARNING);
                    msgbox.setContentText("New password and retype-password do not match");
                }
            }
            else
            {
                msgbox.setAlertType(AlertType.ERROR);
                msgbox.setContentText("User already exists.");
            }
        }
        catch (FileNotFoundException e)
        {
            msgbox.setAlertType(AlertType.ERROR);
            msgbox.setContentText("Text file not found");
        }
        catch (IOException e)
        {
            msgbox.setAlertType(AlertType.ERROR);
            msgbox.setContentText("Error reading file");
        }
        msgbox.showAndWait();
    }
    
}

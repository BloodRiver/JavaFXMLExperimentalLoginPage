/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mainpkg;

import java.io.IOException;
import java.io.FileNotFoundException;
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
 * @author Sajeed Ahmed Galib Arnob
 */
public class LoginSceneController implements Initializable {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    
    private Alert msgbox = new Alert(AlertType.ERROR);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * 
     * @param event this is used to get the source of the event (in this case, click event)
     * @throws IOException if the given FXML file is not found, an IOException is thrown and the error will be shown in the Output window in NetBeans
     */
    @FXML
    private void newAccountLabelOnClick(MouseEvent event) throws IOException {
        Scene currentScene = (Scene) ((Label) event.getSource()).getScene();
        
        Stage currentStage = (Stage) currentScene.getWindow();
        
        Scene newAccountScene = new Scene(FXMLLoader.load(getClass().getResource("NewAccountScene.fxml")));
        
        currentStage.setScene(newAccountScene);
    }

    @FXML
    private void loginButtonOnClick(ActionEvent event) {
        try
        {
            User login_user = User.loadUser(usernameTextField.getText());
            
            if (login_user != null)
            {
                if (login_user.checkPassword(passwordPasswordField.getText()))
                {
                    msgbox.setAlertType(AlertType.CONFIRMATION);
                    msgbox.setContentText("Login Successful");
                }
                else
                {
                    msgbox.setAlertType(AlertType.WARNING);
                    msgbox.setContentText("Login Failed. Incorrect password");
                }
            }
            else
            {
                msgbox.setAlertType(AlertType.WARNING);
                msgbox.setContentText("Login Failed. User does not exist");
            }
        }
        catch (FileNotFoundException e)
        {
            msgbox.setAlertType(AlertType.ERROR);
            msgbox.setContentText("Text File is missing");
        }
        catch (IOException e)
        {
            msgbox.setAlertType(AlertType.ERROR);
            msgbox.setContentText("Error reading file");
        }
        
        msgbox.showAndWait();
    }
    
}

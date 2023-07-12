/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import static Controlador.RegisterController.loadRegisteredUsers;
import static Controlador.RegisterController.registeredUsers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Modelo.CircularNodeList;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author yumip
 */
public class LoguinController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button registarButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      registeredUsers = loadRegisteredUsers();
      System.out.println(registeredUsers);
      registarButton.setOnAction(e -> {
          try {
              vistaRegistro();
          } catch (IOException ex) {
              ex.printStackTrace();
          }
      });
    }
     
    @FXML     
    void Ingresar(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        //App.setRoot("/Vistas/CreadorEmojis");
        login(username,password);
    }
    
    private static void vistaRegistro() throws IOException {
        App.setRoot("/Vistas/Register");
    }
    
    private static void login(String username ,String password) throws IOException {

        if (registeredUsers.containsKey(username) && registeredUsers.get(username).equals(password)) {
            App.setRoot("/Vistas/CreadorEmojis");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Nombre de usuario o contraseña incorrectos.");
            alert.showAndWait();
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }
    }
}

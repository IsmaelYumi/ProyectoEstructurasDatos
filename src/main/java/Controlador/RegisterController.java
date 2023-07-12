/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author Raul Leon
 */
public class RegisterController implements Initializable{
    
    private static final String USERS_FILE = "users.txt";
    public static Map<String, String> registeredUsers;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button registroButton;
    @FXML
    private Button atrasButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       registeredUsers = loadRegisteredUsers();
       System.out.println(registeredUsers);
       registroButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            registerUser(username, password);
        });
       atrasButton.setOnAction(e -> {
          try {
              vistaLogin();
          } catch (IOException ex) {
              ex.printStackTrace();
          }
      });
    }
    
    
    private static void registerUser(String username ,String password) {
        
        if (registeredUsers.containsKey(username)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("El usuario ya está registrado. Por favor, elija otro nombre de usuario.");
            alert.showAndWait();
            System.out.println("El usuario ya está registrado. Por favor, elija otro nombre de usuario.");
            return;
        }
        
        registeredUsers.put(username, password);
        saveRegisteredUsers();

        System.out.println("El usuario ha sido registrado exitosamente.");
        System.out.println(registeredUsers);
    }
    
    public static Map<String, String> loadRegisteredUsers() {
        Map<String, String> users = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al cargar los usuarios registrados: " + e.getMessage());
        }

        return users;
    }
    
    private static void saveRegisteredUsers() {
        try (FileWriter fileWriter = new FileWriter(USERS_FILE)) {
            for (Map.Entry<String, String> entry : registeredUsers.entrySet()) {
                fileWriter.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los usuarios registrados: " + e.getMessage());
        }
    }
    
    public static Map<String, String> getRegisteredUsers() {
        return registeredUsers;
    }

    private static void vistaLogin() throws IOException {
        App.setRoot("/Vistas/Loguin");
    }
}

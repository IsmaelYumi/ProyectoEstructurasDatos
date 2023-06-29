/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Controlador.manejadorArchivos;
import java.io.File;
import javafx.scene.image.Image;
import Modelo.DoubleCircleLinkedList;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author yumip
 */
public class CreadorEmojisController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private HBox ContainerLista;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CargarListas();
        
        // TODO
    }
    public void CargarListas(){
        //Crea las listas de manera Visible,la lista se supone q crea Imageviewers y lso pone en el conainer de la lista que es un Hbox , pero no funciona bien
        //falta implementar 
        File Carpeta=new File("src/main/resources/Imagenes/accessories");
        DoubleCircleLinkedList<Image> ListaAccesorios=manejadorArchivos.cargarArchivos(Carpeta);
        for(int i=0; i<ListaAccesorios.getSize();i++){
            String nombre=ListaAccesorios.getByIndex(i).toString();
            System.out.println(nombre);
             ImageView imv=new ImageView(ListaAccesorios.getByIndex(i));
            ContainerLista.getChildren().add(imv);
            
        }
 
        
        
        
        
        
    }
    
}

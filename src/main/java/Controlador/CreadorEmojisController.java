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
        //Crea las listas de manera Visible,
        File Carpeta=new File("/Imagenes/accesories");
        DoubleCircleLinkedList<Image> ListaAccesorios=manejadorArchivos.cargarArchivos(Carpeta);
        for(int i=0; i<ListaAccesorios.getSize();i++){
             ImageView imv=new ImageView(ListaAccesorios.getByIndex(i));
             ContainerLista.getChildren().add(imv);
            
        }
 
        
        
        
        
        
    }
    
}

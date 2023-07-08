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
    private HBox ContenedorLista;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         CargarListas();
        //CargarListas("eyes");
        //CargarListas("faces");
        //CargarListas("mouth");
        
        // TODO
    }
    public void CargarListas(){
        //Crea las listas de manera Visible,la lista se supone q crea Imageviewers y lso pone en el conainer de la lista que es un Hbox , pero no funciona bien
        //falta implementar 
        String Path="src/main/resources/Imagenes/accesories";
        DoubleCircleLinkedList<Image> ListaAccesorios=manejadorArchivos.cargarArchivos(Path);
        for(int i=0; i<ListaAccesorios.getSize();i++){
            Image imagen=ListaAccesorios.getByIndex(i);
            System.out.println("Bineaqui");
            ImageView imv=new ImageView(imagen);
            imv.setFitWidth(50);
            ContenedorLista.getChildren().add(imv);
        }
 
        
        
        
        
        
    }
    
}

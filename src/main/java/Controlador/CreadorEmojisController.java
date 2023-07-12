/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Controlador.manejadorArchivos;
import Modelo.CircularNodeList;
import java.io.File;
import javafx.scene.image.Image;
import Modelo.DoubleCircleLinkedList;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author yumip
 */
public class CreadorEmojisController<E> implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private HBox ContenedorLista;
     
     final CircularNodeList[] arrDisplay= new CircularNodeList[5];
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         CargarListas("accessories");
         actualizarVista();
        //CargarListas("eyes");
        //CargarListas("faces");
        //CargarListas("mouth");
        // TODO
    }
    public void CargarListas(String archivo){
        //Crea las listas de manera Visible,la lista se supone q crea Imageviewers y lso pone en el conainer de la lista que es un Hbox , pero no funciona bien
        //falta implementar 
        String Path="src/main/resources/Imagenes/"+archivo;
        DoubleCircleLinkedList<Image> ListaAccesorios=manejadorArchivos.cargarArchivos(Path);
        
        for(int i=0; i<5;i++){
            CircularNodeList referenciaNodo=ListaAccesorios.getByIndex(i);
            arrDisplay[i]=referenciaNodo;
        }  
        
}
     private void actualizarVista(){
         ContenedorLista.getChildren().clear();
         for(int i=0;i<arrDisplay.length;i++){
             //Peligroso
             Image img=(Image) arrDisplay[i].getContent();
             ImageView imgv= new ImageView(img);
             ContenedorLista.getChildren().add(imgv);
         }
         
         
         
    }
    @FXML
    void CambiarLista(ActionEvent event) {
        for(int i=0; i<arrDisplay.length;i++){
            arrDisplay[i]=arrDisplay[i].getNext();
        }
        
        actualizarVista();
        
        
  
    }
    }




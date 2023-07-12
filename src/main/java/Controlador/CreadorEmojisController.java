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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
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
    private ImageView ContenedorImagen=new ImageView();
    private Image Imagenfoco;
     
     final CircularNodeList[] arrDisplay= new CircularNodeList[5];
     String Lista="Caras";
      @FXML
    private RadioButton Bocas;
        @FXML
    private RadioButton Accesorios;
     
    @FXML
    private RadioButton Caras;

    @FXML
    private RadioButton Cejas;

    @FXML
    private RadioButton Ojos;
    
    public  ToggleGroup tg = new ToggleGroup();
   
   

     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         CargarListas("faces");
         actualizarVista();
         Bocas.setToggleGroup(tg);
         Caras.setToggleGroup(tg);
         Cejas.setToggleGroup(tg);
         Ojos.setToggleGroup(tg);
         Accesorios.setToggleGroup(tg);
     tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
             @Override
             public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
               RadioButton rb= (RadioButton)tg.getSelectedToggle();
               if(rb!=null){
                   Lista=rb.getText();
                if(Lista.equals("Caras")){
                    CargarListas("faces");
                    actualizarVista();
                }
                if(Lista.equals("Cejas")){
                    CargarListas("eyebrows");
                    actualizarVista();
                }if(Lista.equals("Accesorios")){
                    CargarListas("accessories");
                    actualizarVista();
                }if(Lista.equals("Ojos")){
                    CargarListas("eyes");
                    actualizarVista();
                }if(Lista.equals("Cejas")){
                    CargarListas("eyebrows");
                    actualizarVista();
                }if(Lista.equals("Bocas")){
                    CargarListas("mouth");
                    actualizarVista();
                }
             
               }
         
     }});//Metodo para ponerle eventos a los image view pero no funciona
            //ContenedorImagen.setOnMouseClicked(  new EventHandler<MouseEvent>() {
            // @Override
            // public void handle(MouseEvent event) {
            //     Imagenfoco=ContenedorImagen.getImage();
            //     System.out.println(Imagenfoco.getUrl());
           //  }
        // });  
        
     
             }
    
    public void CargarListas(String archivo){
        //Crea las listas de manera Visible,la lista se supone q crea Imageviewers y lso pone en el conainer de la lista que es un Hbox , pero no funciona bien
        //falta implementar 
        String Path="src/main/resources/Imagenes/"+archivo;
        DoubleCircleLinkedList<ImageView> ListaAccesorios=manejadorArchivos.cargarArchivos(Path);
        for(int i=0; i<5;i++){
            CircularNodeList<ImageView> referenciaNodo=ListaAccesorios.getByIndex(i);
           
            arrDisplay[i]=referenciaNodo;
            
           
        }  
        
}
     private void actualizarVista(){
         ContenedorLista.getChildren().clear();
         for(int i=0;i<arrDisplay.length;i++){
             //Peligroso
             ImageView img=(ImageView) arrDisplay[i].getContent();
              img.setOnMouseClicked(  new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent event) {
                Imagenfoco=img.getImage();
             }
        });
             ContenedorLista.getChildren().add(img);
               
             
              
         }
              
    }
    @FXML
    void CambiarLista(ActionEvent event) {
        for(int i=0; i<arrDisplay.length;i++){
            arrDisplay[i]=arrDisplay[i].getNext();
        }
        
        actualizarVista();
         
    }
     @FXML
    void CambiarListaAtras(ActionEvent event){
        for(int i =arrDisplay.length-1; i>=0;i--){
             arrDisplay[i]=arrDisplay[i].getPrevious();
        }
        actualizarVista();
    }
    
    
   
     
    }




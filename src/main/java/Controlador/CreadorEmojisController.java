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

import java.io.FileInputStream;

import java.util.Iterator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author yumip
 */
public class CreadorEmojisController<E> implements Initializable {

    /**
     * Initializes the controller class.
     *
     */
    private HashMap<String, ImageView> mapa = new HashMap<>();

    @FXML
    private HBox ContenedorLista;

    private ImageView ContenedorImagen = new ImageView();
    private Image imagenFoco;

    final CircularNodeList[] arrDisplay = new CircularNodeList[5];
    String Lista = "Caras";
    @FXML
    private RadioButton Bocas;
    @FXML
    private RadioButton Accesorios;
    private DoubleCircleLinkedList<ImageView> ListaAccesorios;

    @FXML
    private RadioButton Caras;

    @FXML
    private RadioButton Cejas;

    @FXML
    private RadioButton Ojos;

    public ToggleGroup tg = new ToggleGroup();

    @FXML
    private Button btnHacer;

    @FXML
    private Button btnDeshacer;

    @FXML
    private StackPane stackP;

    private int puntero;

    private Stack<HashMap<String, ImageView>> pilaHistorial = new Stack();

    private Stack<HashMap<String, ImageView>> pilaRehacer = new Stack();
    @FXML
    private Button btnExportar;

    @FXML
    private Button btnGuardar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        try {
            if (!manejadorArchivos.carpetaEsVacia(Paths.get("src/main/resources/Proyectos/" + LoguinController.usuarioValido + "_proyecto"))) {
                System.out.println("Hay un proyecto guardado.");
                FileInputStream file = new FileInputStream("src/main/resources/Proyectos/" + LoguinController.usuarioValido + "_proyecto/" + LoguinController.usuarioValido);
                ObjectInputStream objeto = new ObjectInputStream(file);
                
                try {
                    HashMap<String, String> mapaDeserealizado = (HashMap<String, String>) objeto.readObject();
                    System.out.println("Se pudo leer el objeto.");
                    
                    
                    Set<String> llaves= mapaDeserealizado.keySet();
                    
                    HashMap<String, ImageView> mapaSetear = new HashMap<>();
                    
                    Iterator<String> it= llaves.iterator();
                    while(it.hasNext()){
                        String llave= it.next();
                        System.out.println(llave);
                        String rutaImagen = mapaDeserealizado.get(llave);
                        String[] arrRuta = rutaImagen.split("/");
                        String nombreImagen= arrRuta[arrRuta.length-1];
                        String nombreCarpeta= arrRuta[arrRuta.length-2];
                        File file2= new File("/src/main/resources/Imagenes/"+nombreCarpeta+"/"+nombreImagen);
                        String localUrl = file2.toURI().toURL().toString();
                        Image imagencita = new Image(localUrl, 50,0,true,false);
                        mapaSetear.put(llave,new ImageView(imagencita));
                    }
                    
                    mapa= mapaSetear;
                    System.out.println(mapa);
       
                    
                    System.out.println("Se pudo cargar el proyecto del usuario.");
                   
                    
                    
                    
                    
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    System.out.println("No se pudo leer el objeto.");
                }
                
            } else {
                System.out.println("No hay ningun proyecto guardado.");
            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        
        

        CargarListas("faces");
        actualizarVista();
        Bocas.setToggleGroup(tg);
        Caras.setToggleGroup(tg);
        Cejas.setToggleGroup(tg);
        Ojos.setToggleGroup(tg);
        Accesorios.setToggleGroup(tg);
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton rb = (RadioButton) tg.getSelectedToggle();
                if (rb != null) {
                    Lista = rb.getText();
                    if (Lista.equals("Caras")) {
                        CargarListas("faces");
                        actualizarVista();
                    }
                    if (Lista.equals("Cejas")) {
                        CargarListas("eyebrows");
                        actualizarVista();
                    }
                    if (Lista.equals("Accesorios")) {
                        CargarListas("accessories");
                        actualizarVista();
                    }
                    if (Lista.equals("Ojos")) {
                        CargarListas("eyes");
                        actualizarVista();
                    }
                    if (Lista.equals("Cejas")) {
                        CargarListas("eyebrows");
                        actualizarVista();
                    }
                    if (Lista.equals("Bocas")) {
                        CargarListas("mouth");
                        actualizarVista();
                    }

                }

            }
        });//Metodo para ponerle eventos a los image view pero no funciona
        //ContenedorImagen.setOnMouseClicked(  new EventHandler<MouseEvent>() {
        // @Override
        // public void handle(MouseEvent event) {
        //     Imagenfoco=ContenedorImagen.getImage();
        //     System.out.println(Imagenfoco.getUrl());
        //  }
        // });  

//        btnExportar.setOnAction(event -> exportarImagen(stackP));

        
        actualizarConMapa();
        
        
        btnExportar.setOnAction(event -> exportarImagen(stackP));

    }

    public void CargarListas(String archivo) {
        //Crea las listas de manera Visible,la lista se supone q crea Imageviewers y lso pone en el conainer de la lista que es un Hbox , pero no funciona bien
        //falta implementar 
        String Path = "src/main/resources/Imagenes/" + archivo;
        ListaAccesorios = manejadorArchivos.cargarArchivos(Path);
        for (int i = 0; i < 5; i++) {
            CircularNodeList<ImageView> referenciaNodo = ListaAccesorios.getByIndex(i);

            arrDisplay[i] = referenciaNodo;

        }

    }

    private void actualizarVista() {
        ContenedorLista.getChildren().clear();
        for (int i = 0; i < arrDisplay.length; i++) {
            //Peligroso
            int valor = i;
            ImageView img = (ImageView) arrDisplay[i].getContent();
            img.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    imagenFoco = img.getImage();
                    ImageView imgv = new ImageView(imagenFoco);
                    añadirComponente(img, Lista);
                    actualizarConMapa();
                    puntero = valor;
                }
            });
            ContenedorLista.getChildren().add(img);

        }

    }

    @FXML
    void CambiarLista(ActionEvent event) {
        for (int i = 0; i < arrDisplay.length; i++) {
            arrDisplay[i] = arrDisplay[i].getNext();

        }
        ImageView imgvx = (ImageView) arrDisplay[puntero].getContent();
        añadirComponente(imgvx, Lista);
        actualizarConMapa();
        actualizarVista();

    }

    @FXML
    boolean deshacer(ActionEvent event) {
        if (!pilaHistorial.isEmpty() && pilaHistorial.size() >= 2) {
            System.out.println(pilaHistorial);
            HashMap mapaPopeado = pilaHistorial.pop();
            pilaRehacer.add(mapaPopeado);
            mapa = pilaHistorial.peek();
            actualizarConMapa();
            System.out.println("Se deshizo un cambio con éxito.");
            return true;
        } else if (pilaHistorial.size() == 1) {
            pilaRehacer.add(pilaHistorial.pop());
            stackP.getChildren().clear();
            System.out.println("Se deshizo un cambio con éxito.");

            return true;

        } else {
            System.out.println("No hay historial para deshacer cambios!");
            return false;

        }

    }

    @FXML
    boolean reHacer(ActionEvent event) {
        System.out.println(pilaRehacer);
        if (!pilaRehacer.isEmpty()) {
            HashMap mapaPopeado = pilaRehacer.pop();
            pilaHistorial.add(mapaPopeado);
            mapa = pilaHistorial.peek();
            System.out.println("Se rehizo un cambio con éxito.");
            actualizarConMapa();
        }
        return false;
    }

    @FXML
    void CambiarListaAtras(ActionEvent event) {
        for (int i = arrDisplay.length - 1; i >= 0; i--) {
            arrDisplay[i] = arrDisplay[i].getPrevious();
        }
        ImageView imgvx = (ImageView) arrDisplay[puntero].getContent();
        añadirComponente(imgvx, Lista);
        actualizarConMapa();
        actualizarVista();
    }

    void añadirComponente(ImageView img, String tipo) {

        ImageView actual = img;
        ImageView copiedImageView = new ImageView();
        copiedImageView.setImage(actual.getImage());
        copiedImageView.setFitWidth(actual.getFitWidth());
        copiedImageView.setFitHeight(actual.getFitHeight());
        copiedImageView.setPreserveRatio(actual.isPreserveRatio());

        HashMap<String, ImageView> mapaTemporal = new HashMap<>();

        if (tipo.equals("Bocas")) {
            mapa.put("Boca", copiedImageView);
        }
        if ("Ojos".equals(tipo)) {
            mapa.put("Ojos", copiedImageView);
        }
        if ("Caras".equals(tipo)) {
            copiedImageView.setFitHeight(75);
            copiedImageView.setFitWidth(75);
            mapa.put("Cara", copiedImageView);
        }
        if (tipo.equals("Cejas")) {
            mapa.put("Cejas", copiedImageView);
        }
        if (tipo.equals("Accesorios")) {
            mapa.put("Accesorio", copiedImageView);
        }

        //Quiero copiar el mapa en el temporal que voy a almacenar en la pilaHistorial
        Set llavesMapa = mapa.keySet();
        Iterator<String> it = llavesMapa.iterator();
        while (it.hasNext()) {
            String llave = it.next();
            mapaTemporal.put(llave, mapa.get(llave));
        }

        pilaHistorial.push(mapaTemporal);

    }

    void actualizarConMapa() {

        System.out.println(mapa);
        
        
        if (!stackP.getChildren().isEmpty()) {
            stackP.getChildren().clear();
        }
        stackP.setAlignment(Pos.CENTER);

        if (mapa.containsKey("Cara")) {
            ImageView actual = mapa.get("Cara");

            stackP.getChildren().add(actual);
        }

        Pane pane = new Pane();

        ///Aquí hay que corregir a que salga a la altura de la boca, y si no está vacío no se ponga mal.
        //Probablemnte sea mejor usar un anchorPane y definir los píxeles.
        if (mapa.containsKey("Ojos")) {
            ImageView actual = mapa.get("Ojos");
            actual.relocate(100, 80);
            pane.getChildren().add(actual);
            
        }

        if (mapa.containsKey("Boca")) {
            ImageView actual = mapa.get("Boca");
            actual.relocate(100, 110);
            pane.getChildren().add(actual);

        }

        if (mapa.containsKey("Accesorio")) {
            ImageView actual = mapa.get("Accesorio");
            actual.relocate(100, 80);
            pane.getChildren().add(actual);
        }

        if (mapa.containsKey("Cejas")) {
            ImageView actual = mapa.get("Cejas");
            actual.relocate(100, 65);
            pane.getChildren().add(actual);
        }

        stackP.getChildren().add(pane);
        System.out.println("Se actualizó el emoji exitosamente.");
        System.out.println("----------------------------------------------------------------------------------------------------------------");

    }
    

     //   private void exportarImagen(StackPane stackPane) {
     //   SnapshotParameters parameters = new SnapshotParameters();
     //   parameters.setDepthBuffer(true);
     //   Image snapshot = stackPane.snapshot(parameters, null);

     //   FileChooser fileChooser = new FileChooser();
    //    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
     //   File file = fileChooser.showSaveDialog(null);

     //   if (file != null) {             
     //       BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);
     //       ImageIO.write(bufferedImage, "png", file);
     //       System.out.println("La imagen ha sido exportada correctamente.");
     //   }
        
   // }
        @FXML
     void AgregarElemento(ActionEvent event){
         Stage buscador= new Stage();
         FileChooser selectorArchivos = new FileChooser();
         selectorArchivos.getExtensionFilters().addAll(
     new FileChooser.ExtensionFilter("Images Png Files", "*.png")
    ,new FileChooser.ExtensionFilter("Image JPG Files", "*.jpg"),
                 new FileChooser.ExtensionFilter("Image JPEG Files", "*.jpeg")
);
         File nuevaImagen=selectorArchivos.showOpenDialog(buscador);
         try{
         FileInputStream ruta= new FileInputStream(selectorArchivos.showOpenDialog(buscador));
         Image imagen=new Image(ruta);
         ImageView imv= new ImageView( imagen);
         if (Lista.equals("Caras")) {
             
                        CargarListas("faces");
                        ListaAccesorios.addLast(imv);
                        actualizarVista();
                    }
                    if (Lista.equals("Cejas")) {
                        CargarListas("eyebrows");
                         ListaAccesorios.addLast(imv);
                        actualizarVista();
                    }
                    if (Lista.equals("Accesorios")) {
                        CargarListas("accessories");
                         ListaAccesorios.addLast(imv);
                        actualizarVista();
                    }
                    if (Lista.equals("Ojos")) {
                        CargarListas("eyes");
                         ListaAccesorios.addLast(imv);
                        actualizarVista();
                    }
                    if (Lista.equals("Cejas")) {
                        CargarListas("eyebrows");
                         ListaAccesorios.addLast(imv);
                        actualizarVista();
                    }
                    if (Lista.equals("Bocas")) {
                        CargarListas("mouth");
                         ListaAccesorios.addLast(imv);
                        actualizarVista();
                    }
                    System.out.println("exito al cargar");

   
         
         }catch(Exception ex){
             System.out.println("fallo");
             
         }
         
     }
     @FXML
     void EliminarElemento(ActionEvent evento){
         ImageView ImagenEliminar=new ImageView(imagenFoco);
         String Path = "src/main/resources/Imagenes/";
         if (Lista.equals("Caras")) {
                        Path+="faces";
                    }
                    if (Lista.equals("Cejas")) {
                        CargarListas("eyebrows");
                        Path+="eyebrows";
                        
                    }
                    if (Lista.equals("Accesorios")) {
                        CargarListas("accessories");
                        Path+="accessories";
                        
                    }
                    if (Lista.equals("Ojos")) {
                        Path+="eyes";
                        
                    }
                    
                    if (Lista.equals("Bocas")) {
                        CargarListas("mouth");
                        Path+="mouth";
                        
                    }
                    
         ListaAccesorios=manejadorArchivos.cargarArchivos(Path);
         ListaAccesorios.delete(ImagenEliminar);
         for (int i = 0; i < 5; i++) {
            CircularNodeList<ImageView> referenciaNodo = ListaAccesorios.getByIndex(i);

            arrDisplay[i] = referenciaNodo;
        }
         actualizarVista();
         System.out.println("Exito al eliminar");
     }

     @FXML
    void guardarProyecto(ActionEvent event) {
        try {
            System.out.println("Se guardo como usuario ingresado a:");
            System.out.println(LoguinController.usuarioValido);
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/Proyectos/" + LoguinController.usuarioValido + "_proyecto/" + LoguinController.usuarioValido);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            HashMap<String, String> mapaSerializable = new HashMap<>();

            //Copio al mapa: 
            Set llavesMapa = mapa.keySet();
            Iterator<String> it = llavesMapa.iterator();
            while (it.hasNext()) {
                String llave = it.next();
                String url=
                mapaSerializable.put(llave, mapa.get(llave).getImage().getUrl());
            }

            objectOut.writeObject(mapaSerializable);
            System.out.println(mapaSerializable);
            objectOut.close();
            System.out.println("Proyecto guardado correctamente en su perfil, la proxima vez que inicie sesión se mostrará esta disposición de emoji");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
        private void exportarImagen(StackPane stackPane) {
//        SnapshotParameters parameters = new SnapshotParameters();
//        parameters.setDepthBuffer(true);
//        Image snapshot = stackPane.snapshot(parameters, null);
//
//   
//    
//    
//
//
//
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
//        File file = fileChooser.showSaveDialog(null);
//
//        if (file != null) {             
//            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);
//            ImageIO.write(bufferedImage, "png", file);
//            System.out.println("La imagen ha sido exportada correctamente.");
//        }
    }

}   

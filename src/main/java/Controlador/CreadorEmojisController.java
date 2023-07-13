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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    }

    public void CargarListas(String archivo) {
        //Crea las listas de manera Visible,la lista se supone q crea Imageviewers y lso pone en el conainer de la lista que es un Hbox , pero no funciona bien
        //falta implementar 
        String Path = "src/main/resources/Imagenes/" + archivo;
        DoubleCircleLinkedList<ImageView> ListaAccesorios = manejadorArchivos.cargarArchivos(Path);
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
        if(!pilaRehacer.isEmpty()){
            HashMap mapaPopeado = pilaRehacer.pop();
            pilaHistorial.add(mapaPopeado);
            mapa=pilaHistorial.peek();
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

        System.out.println(mapaTemporal);
        pilaHistorial.push(mapaTemporal);
        System.out.println(pilaHistorial);

    }

    void actualizarConMapa() {

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

}

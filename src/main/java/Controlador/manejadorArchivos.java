/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DoubleCircleLinkedList;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Administrador
 */
public class manejadorArchivos {

    
     //Podría de una vez retornar una lista de imagenes según el directorio, puede ser el de sonrisas entre otros.
    public static DoubleCircleLinkedList<Image> cargarArchivos(String carpeta) {
        File carpetaPrincipal= new File(carpeta);
        File[] archivos = carpetaPrincipal.listFiles();
        System.out.println(carpetaPrincipal);
        System.out.println(Arrays.toString(archivos));
        if (archivos == null || archivos.length == 0) {
            System.out.println("La carpeta que quiere leer está vacía.");
        }
        String [] ruta=carpeta.split("/");
        
        DoubleCircleLinkedList<Image> listaReturn= new DoubleCircleLinkedList();
        
        //System.out.println(listaReturn.toString());
        //Asumo que las imagenes que me dan están en png
        try{
        for (File archivo:archivos){
           Image im= new Image("Imagenes/"+ruta[-1]+"/"+archivo.getName(),50,0,true,false);

           listaReturn.addLast(im);  
        }
        }catch(Exception i){
            i.printStackTrace();
        }

        
        return listaReturn;
        
    }
    
    
    
    
}

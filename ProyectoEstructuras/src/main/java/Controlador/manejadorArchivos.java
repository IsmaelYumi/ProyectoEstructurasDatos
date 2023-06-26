/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DoubleCircleLinkedList;
import java.io.File;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class manejadorArchivos {

    
     //Podría de una vez retornar una lista de imagenes según el directorio, puede ser el de sonrisas entre otros.
    public static DoubleCircleLinkedList<String> cargarArchivos(String carpetaPrincipal) {
        File CarpetaPrincipal=new File(carpetaPrincipal);
        File[] archivos = CarpetaPrincipal.listFiles();
        if (archivos == null || archivos.length == 0) {
            System.out.println("La carpeta que quiere leer está vacía.");
        }
        
        DoubleCircleLinkedList<String> listaReturn= new DoubleCircleLinkedList();
        
        //Asumo que las imagenes que me dan están en png
        for (File archivo:archivos){
            listaReturn.addLast(archivo.getName());
        }
        
        return listaReturn;
        
    }
    
    
    
    
}

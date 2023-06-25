/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DoubleCircleLinkedList;
import java.io.File;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class manejadorArchivos {

    
     //Podría de una vez retornar una lista de imagenes según el directorio, puede ser el de sonrisas entre otros.
    public static DoubleCircleLinkedList<File> cargarArchivos(File carpetaPrincipal) {
        File[] archivos = carpetaPrincipal.listFiles();
        if (archivos == null || archivos.length == 0) {
            System.out.println("La carpeta que quiere leer está vacía.");
        }
        
        DoubleCircleLinkedList listaReturn= new DoubleCircleLinkedList();
        
        //Asumo que las imagenes que me dan están en png
        for (File archivo:archivos){
            listaReturn.addLast(archivo);
        }
        
        return listaReturn;
        
    }
    
    
    
    
}

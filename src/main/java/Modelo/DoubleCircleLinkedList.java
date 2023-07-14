/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author yumip
 * @param <E>
 */
public class DoubleCircleLinkedList<E> implements List<E> {

    private CircularNodeList<E> last;
    private int size;
    
    public int getSize(){
        return size;
    }

    @Override
    public CircularNodeList<E> getByIndex(int index) {
        int contador=0;
        for(CircularNodeList<E> nodo=last.getNext();nodo!=null ;nodo=nodo.getNext()){
            if(contador==index){
                return nodo;
            }
            contador++;
        }
        throw new IndexOutOfBoundsException("Indice fuera de rango");      
    }

  
    
    @Override
    public boolean add(E element, int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addFirst(E element) {
        if (!isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
            //Si está vacía la lista
        } else if (this.isEmpty()) {
            CircularNodeList nuevo = new CircularNodeList(element);
            last = nuevo;
            nuevo.setNext(nuevo);
            nuevo.setPrevious(nuevo);
            size++;
            return true;
        } else if (!this.isEmpty()) {
            CircularNodeList nuevo = new CircularNodeList(element);
            last.getNext().setPrevious(nuevo);
            nuevo.setNext(last.getNext());
            last.setNext(nuevo);
            nuevo.setPrevious(last);
            last = nuevo;
            size++;
            return true;
        }
        return false;
    }

    @Override
    public CircularNodeList<E> getPrevious(CircularNodeList<E> nodo) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if (this.isEmpty() || nodo == null) {
            return null;
        }
        CircularNodeList<E> p;
        p = this.last;
        do {
            if (p.getNext() == nodo) {
                return p;

            }
            p = p.getNext();

        } while (p != this.last);
        return null;

    }

    public boolean isEmpty() {
        return this.last == null;
    }

    @Override
    public CircularNodeList<E> getNext(CircularNodeList<E> nodo) {
        if (this.isEmpty() || nodo == null) {
            return null;
        }
        CircularNodeList<E> p;
        p = this.last;
        do {
            if (p.getPrevious() == nodo) {
                return p;
            }
            p = p.getNext();

        } while (p != this.last);
        return null;

    }

    @Override
    public boolean delete(E contentComp) {
        //Si el contador sobrepasa el size de la lista, significa que ya iteró por todos los elementos y no encontro el que 
        //quiere eliminar
        int contador = 0;
        CircularNodeList<E> nodoViajero = last.getNext();
        while (!nodoViajero.getContent().equals(this) && contador < size) {
            nodoViajero = nodoViajero.getNext();
            contador++;
        }

        if (contador < size) {
            CircularNodeList<E> nodoEliminar = nodoViajero;

            CircularNodeList<E> nodoAnterior = nodoViajero.getPrevious();
            CircularNodeList<E> nodoPosterior = nodoViajero.getNext();

           
            nodoAnterior.setNext(nodoPosterior);
            nodoPosterior.setPrevious(nodoAnterior);
            
            //El nodo a eliminar no tendrá nada apuntandolo por lo que se lo llevará el garbage collector;
            
            if (nodoEliminar.equals(last)) {
                last = nodoAnterior;
            }
            size--;
            return true;
        }
        return false;
    }
}

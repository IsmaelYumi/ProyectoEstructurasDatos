/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;


/**
 *
 * @author yumip
 * @param <E>
 */



public interface List <E>{
    public boolean add(E element, int index);
    public  CircularNodeList<E> getByIndex(int index);
    public boolean addFirst(E element); 
     public boolean addLast(E element);
    public CircularNodeList<E> getPrevious(CircularNodeList<E> nodo);
    public CircularNodeList<E> getNext(CircularNodeList<E> nodo);
    public boolean delete(E content);
    
    
    
}

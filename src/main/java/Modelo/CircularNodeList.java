/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author yumip
 * @param <E>
 */
public class CircularNodeList<E> {
    private E content;
    private CircularNodeList<E> next;
    private CircularNodeList<E> previous;

    
    public CircularNodeList(E elemento){
        this.content= elemento;
        
    }
    
public CircularNodeList(){
        
    }
    
    public E getContent() {
        return content;
        
            
        }

    public CircularNodeList<E> getNext() {
        return next;
    }

    public CircularNodeList<E> getPrevious() {
        return previous;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public void setNext(CircularNodeList<E> next) {
        this.next = next;
    }

    public void setPrevious(CircularNodeList<E> previous) {
        this.previous = previous;
    }
    
    
}

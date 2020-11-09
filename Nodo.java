public class Nodo {
    public int dato; //Dato que puede tener más
    public Lista l;
    public Nodo siguiente; //Puntero enlace
    //Constructor que nos ayuda a insertar al final
    public Nodo(int d){ 
        this.dato = d;
        this.siguiente = null;
    }
    public Nodo(Lista l){
        this.l = l;
        this.siguiente = null;
    }
    //Constructor que nos ayuda a insertar al inicio
    public Nodo(int d, Nodo n){ 
        this.dato = d;
        this.siguiente = n;
    }
    public Nodo(Lista l, Nodo n){ 
        this.l = l;
        this.siguiente = n;
    }
    
}
 
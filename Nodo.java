public class Nodo {
    public int dato; //Dato que puede tener más
    public Lista l;
    public Nodo siguiente; //Puntero enlace
    //Constructor que nos ayuda a insertar al final

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
 
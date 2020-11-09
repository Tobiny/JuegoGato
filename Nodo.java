public class Nodo {
    public int dato; //Dato que puede tener más
    public Nodo siguiente; //Puntero enlace
    //Constructor que nos ayuda a insertar al final
    public Nodo(int d){ 
        this.dato = d;
        this.siguiente = null;
    }
    //Constructor que nos ayuda a insertar al inicio
    public Nodo(int d, Nodo n){ 
        this.dato = d;
        this.siguiente = n;
    }
    public int getValor() {
        return dato;
    }

    public void setValor(int valor) {
        this.valor = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
 
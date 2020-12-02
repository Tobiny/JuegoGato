public class NodoChavezMoreno {
    public int dato; //Dato que puede tener más
    public ListaChavezMoreno l;
    public NodoChavezMoreno siguiente; //Puntero enlace
    
    //Constructor que nos ayuda a insertar al final.
    public NodoChavezMoreno(int d){ 
        this.dato = d;
        this.siguiente = null;
    }
    //Constructor que nos ayuda a insertar al inicio.
    public NodoChavezMoreno(int d, NodoChavezMoreno n){ 
        this.dato = d;
        this.siguiente = n;
    }
    //Constructor que nos ayuda a hacer una lista de listas.
    public NodoChavezMoreno(ListaChavezMoreno l, NodoChavezMoreno n){ 
        this.l = l;
        this.siguiente = n;
    }
    public int getValor() {
        return dato;
    }

    public void setValor(int valor) {
        this.dato = valor;
    }

    public NodoChavezMoreno getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoChavezMoreno siguiente) {
        this.siguiente = siguiente;
    }
}
 
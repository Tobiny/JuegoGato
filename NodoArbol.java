public class NodoArbol {
    int dato;
    NodoArbol derecho;
    NodoArbol izquierdo;
    String nombre;
    public NodoArbol(int d, String nom){
        this.dato = d;
        this.derecho = null;
        this.izquierdo = null;
        this.nombre = nom;
    }
    public String toString(){
        return nombre+", con "+dato+" puntos";
    }
}
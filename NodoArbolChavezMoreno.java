public class NodoArbolChavezMoreno {
    int dato;
    NodoArbolChavezMoreno derecho;
    NodoArbolChavezMoreno izquierdo;
    String nombre;
    public NodoArbolChavezMoreno(int d, String nom){
        this.dato = d;
        this.derecho = null;
        this.izquierdo = null;
        this.nombre = nom;
    }
    public String toString(){
        return nombre+", con "+dato+" puntos";
    }
}
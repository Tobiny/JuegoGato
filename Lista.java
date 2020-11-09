import javax.swing.JOptionPane;


public class Lista {
    protected Nodo inicio, fin;//Punteros para saber donde esta el inicio y fin
    public Lista(){ //Constructor de lista para inicializar punteros
        this.inicio = null;
        this.fin = null;
    }
    //Método para saber si la lista se encuentra vacía
    public boolean estaVacia(){
        if(inicio == null)
            return true;
        else
            return false;
    }

    //Método para agregar un nodo con un elemento de tipo entero
    public void agregar(int elemento){
        inicio = new Nodo(elemento, inicio);
        if(fin == null) 
            fin = inicio;
    }
    //Método para agregar un nodo con un elemento de tipo lista
    public void agregar(Lista elemento){
        inicio = new Nodo(elemento, inicio);
        if(fin == null) 
            fin = inicio;
    }

    
    public int recorrer(int posicionesJugador[], int jugadorCont, int jugador){
        int ganador = 0;
        boolean ganado = false;
        int ganarCont = 0;
        Nodo temporal, anterior, temporalL, anteriorL;
        Nodo temp = inicio;
        Nodo temp2 = inicio.l.inicio;
         
        while (temp != null && !ganado){
            while (temp2 != null){
                for (int k = 0; k < jugadorCont; k++) {
                    if(posicionesJugador[k] == temp2.dato){
                        ganarCont++;              
                    }  
                    if(ganarCont == 3){
                       ganado = true;
                       ganador = jugador;
                    }
                    
                }

                temp2 = temp2.siguiente;
            }
            ganarCont = 0;
            temp = temp.siguiente;
            if(temp!=null)
               temp2 = temp.l.inicio;
               
         }
    
        return ganador;
    }

    
}

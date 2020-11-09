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

    //Método para insertar al final de la lista
    public void agregarAlFinal(int elemento){
        if(!estaVacia()){
            fin.siguiente = new Nodo(elemento);
            fin = fin.siguiente;
        } else
            inicio = fin = new Nodo(elemento);
    }
    //Método para agregar un nodo al inicio de la lista
    public void agregar(int elemento){
        inicio = new Nodo(elemento, inicio);
        if(fin == null) 
            fin = inicio;
    }
    public void agregar(Lista elemento){
        inicio = new Nodo(elemento, inicio);
        if(fin == null) 
            fin = inicio;
    }
    public void agregarAlInicio(int elemento){
        //Crear el nodo
        inicio = new Nodo(elemento, inicio);
        if(fin == null) 
            fin = inicio;
    }
    //Método para mostrar los datos
    public String mostrarLista(){
        String impreLista = "";
        Nodo recorrer = inicio;
        while(recorrer != null){
            impreLista += "[" + recorrer.dato + "]-->";
            recorrer = recorrer.siguiente;
        }
        impreLista += "\n";
        return impreLista;
    }
    //Método para borrar al inicio
    public int borrarDelInicio(){
        int elemento = inicio.dato;
        if(inicio == fin)
            inicio = fin = null;
        else {
            inicio = inicio.siguiente;
        }
        return elemento;
    }
    //Método para eliminar un nodo del final
    public int borrarDelFinal(){
        int elemento = fin.dato;
        if(inicio == fin)
            inicio = fin = null;
        else {
            Nodo temp = inicio;
            while(temp.siguiente != fin){
                temp = temp.siguiente;
            }
            fin = temp;
            fin.siguiente = null;
        }
        return elemento;
    }
    //Método para eliminar un nodo en específico
    public void eliminar(int elemento){
        if(inicio == fin && elemento == inicio.dato)
            inicio = fin = null;
        else if(elemento == inicio.dato)
            inicio = inicio.siguiente;
        else {
            Nodo anterior, temporal;
            anterior = inicio;
            temporal = inicio.siguiente;
            while(temporal != null && temporal.dato != elemento){
                anterior = anterior.siguiente;
                temporal = temporal.siguiente;
            }
            if(temporal != null){
                anterior.siguiente = temporal.siguiente;
                if(temporal == fin){
                    fin = anterior;
                }
            }
        }

    }
    //Método para buscar en la lista
    //Ojo, se mejoro un poco el algoritmo dado, pero la lógica es la misma
    public boolean buscar(int elemento){
        boolean b = false;
        if(inicio == fin && elemento == inicio.dato) b = true;
        else if(elemento == inicio.dato) b = true;
        else {
            Nodo temporal, anterior;
            anterior = inicio;
            temporal = inicio.siguiente;
            while(temporal != null && temporal.dato != elemento){
                anterior = anterior.siguiente;
                temporal = temporal.siguiente;
            }
            if(temporal != null) b = true;
        }
        return b;
    }
    public String accede(){
       String impreLista = "";

        Nodo recorrer = inicio.l.inicio;
        while(recorrer != null){
            impreLista += "[" + recorrer.dato + "]-->";
            recorrer = recorrer.siguiente;
        }
         return impreLista;
    }
    
    
    public void recorre(){
         Nodo temp = inicio;
         Nodo temp2 = inicio.l.inicio;
         
         while (temp != null){
            while (temp2 != null){
               System.out.print(temp2.dato);
               temp2 = temp2.siguiente;
            }
            
            System.out.println();
            temp = temp.siguiente;
            if(temp!=null)
               temp2 = temp.l.inicio;
         }
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

    
    

    //Método para insertar entre dos nodos
    public void insertarEntreNodos(int datoTemp, int elemento){
        if(inicio == fin && datoTemp == inicio.dato){
            inicio = new Nodo(elemento, inicio);
        }
        else {if(datoTemp == inicio.dato){
            inicio = new Nodo(elemento, inicio);
        }

        else {
            Nodo anterior, temporal;
            anterior = inicio;
            temporal = inicio.siguiente;
            while(temporal != null && temporal.dato != datoTemp){
                anterior = anterior.siguiente;
                temporal = temporal.siguiente;
            }
            if(temporal != null){
                anterior.siguiente = new Nodo(elemento, temporal);
            }
        }}
        
    }

}

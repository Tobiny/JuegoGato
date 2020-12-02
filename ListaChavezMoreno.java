import javax.swing.JOptionPane;


public class ListaChavezMoreno {
    int longitud = 0;
    protected NodoChavezMoreno inicio, fin;//Punteros para saber donde esta el inicio y fin
    public ListaChavezMoreno(){ //Constructor de lista para inicializar punteros
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
    public void agregar(ListaChavezMoreno elemento){
        inicio = new NodoChavezMoreno(elemento, inicio);
        if(fin == null) 
            fin = inicio;
    }
    //Método para insertar al final de la lista
    public void agregarAlFinal(int elemento){
        if(!estaVacia()){
            fin.siguiente = new NodoChavezMoreno(elemento);
            fin = fin.siguiente;
        } else
            inicio = fin = new NodoChavezMoreno(elemento);
        longitud++;
    }
    //Método para agregar un nodo al inicio de la lista
    public void agregarAlInicio(int elemento){
        //Crear el nodo
        inicio = new NodoChavezMoreno(elemento, inicio);
        if(fin == null) 
            fin = inicio;
        longitud++;
    }
    //Método para mostrar los datos
    public String mostrarLista(){
        String impreLista = "";
        NodoChavezMoreno recorrer = inicio;
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
        longitud--;
        return elemento;
    }
    //Método para eliminar un nodo del final
    public int borrarDelFinal(){
        int elemento = fin.dato;
        if(inicio == fin)
            inicio = fin = null;
        else {
            NodoChavezMoreno temp = inicio;
            while(temp.siguiente != fin){
                temp = temp.siguiente;
            }
            fin = temp;
            fin.siguiente = null;
        }
        longitud--;
        return elemento;
    }
    //Método para eliminar un nodo en específico
    public void eliminar(int elemento){
        if(inicio == fin && elemento == inicio.dato)
            inicio = fin = null;
        else if(elemento == inicio.dato)
            inicio = inicio.siguiente;
        else {
            NodoChavezMoreno anterior, temporal;
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
    public boolean buscar(int referencia){
        NodoChavezMoreno aux = inicio;
        boolean encontrado = false;
        while(aux != null && encontrado != true){
            if (referencia == aux.getValor()){
                encontrado = true;
            }
            else{
                aux = aux.getSiguiente();
            }
        }
        return encontrado;
    }

    //Método para insertar entre dos nodos
    public void insertarEntreNodos(int datoTemp, int elemento){
        if(inicio == fin && datoTemp == inicio.dato){
            inicio = new NodoChavezMoreno(elemento, inicio);
        }
        else {
            if(datoTemp == inicio.dato){
            inicio = new NodoChavezMoreno(elemento, inicio);
            } else {
                NodoChavezMoreno anterior, temporal;
                anterior = inicio;
                temporal = inicio.siguiente;
                while(temporal != null && temporal.dato != datoTemp){
                anterior = anterior.siguiente;
                temporal = temporal.siguiente;
                }
                if(temporal != null){
                    anterior.siguiente = new NodoChavezMoreno(elemento, temporal);
                }
            }
        }
    }
    public int getPosicion(int referencia){
        if (buscar(referencia)) {
            NodoChavezMoreno aux = inicio;
            int cont = 0;
            while(referencia != aux.getValor()){
                cont ++;
                aux = aux.getSiguiente();
            }
            return cont;
        }
        return -1;
    }
    public int longitud(){
        return longitud;
    }
    public int getReferencia(int posicion) {
        NodoChavezMoreno aux = inicio;
        int referencia = 0;
        int cont = 0;
        if(estaVacia()) return 0;
        else if(posicion == 0){
            referencia = aux.getValor();
            return referencia;
        }else{
            while(cont < posicion){
                aux = aux.getSiguiente();
                referencia = aux.dato;
                cont++;
            }
            return referencia;
        }
    }
    public void llenarGanador(int a, int b, int c){
        this.agregarAlFinal(a);
        this.agregarAlFinal(b);
        this.agregarAlFinal(c);
    }
    public int recorrer(ListaChavezMoreno posicionesJugador, int jugadorCont, int jugador){
        int ganador = 0;
        boolean ganado = false;
        int ganarCont = 0;
        NodoChavezMoreno temp = inicio;
        NodoChavezMoreno temp2 = inicio.l.inicio;
        while (temp != null && !ganado){
            while (temp2 != null){
                for (int k = 0; k < posicionesJugador.longitud(); k++) {
                    if(posicionesJugador.getReferencia(k) == temp2.getValor()){
                        ganarCont++;              
                    }  
                    if(ganarCont == 18){
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

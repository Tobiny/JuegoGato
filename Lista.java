import javax.swing.JOptionPane;


public class Lista {
    int longitud = 0;
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
        longitud++;
    }
    //Método para agregar un nodo al inicio de la lista
    public void agregarAlInicio(int elemento){
        //Crear el nodo
        inicio = new Nodo(elemento, inicio);
        if(fin == null) 
            fin = inicio;
        longitud++;
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
        longitud--;
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
    public boolean buscar(int referencia){
        // Crea una copia de la lista.
        Nodo aux = inicio;
        // Bandera para indicar si el valor existe.
        boolean encontrado = false;
        // Recorre la lista hasta encontrar el elemento o hasta 
        // llegar al final de la lista.
        while(aux != null && encontrado != true){
            // Consulta si el valor del nodo es igual al de referencia.
            if (referencia == aux.getValor()){
                // Canbia el valor de la bandera.
                encontrado = true;
            }
            else{
                // Avansa al siguiente. nodo.
                aux = aux.getSiguiente();
            }
        }
        // Retorna el resultado de la bandera.
        return encontrado;
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
    public int getPosicion(int referencia){
        // Consulta si el valor existe en la lista.
        if (buscar(referencia)) {
            // Crea una copia de la lista.
            Nodo aux = inicio;
            // Contador para almacenar la posición del nodo.
            int cont = 0;
            // Recorre la lista hasta llegar al nodo de referencia.
            while(referencia != aux.getValor()){
                // Incrementa el contador.
                cont ++;
                // Avanza al siguiente nodo.
                aux = aux.getSiguiente();
            }
            // Retorna el valor del contador.
            return cont;
        // Crea una excepción de Valor inexistente en la lista.
        }
        return -1;
    }
    public int longitud(){
        return longitud;
    }
    public int getReferencia(int posicion) {
        Nodo aux = inicio;
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
}

public class Arbol {
    NodoArbol raiz;
    public Arbol(){
        raiz = null;
    }
    //Insertar nodo en el arbol
    public void insertar(int d, String n){
        NodoArbol nuevo = new NodoArbol(d,n);
        if(raiz == null){
            raiz = nuevo;
        }else{
            NodoArbol aux = raiz;
            NodoArbol padre;
            while(true){
                padre = aux;
                if(d < aux.dato){
                    aux = aux.izquierdo;
                    if(aux == null){
                        padre.izquierdo = nuevo;
                        return;
                    }
                }else{
                    aux = aux.derecho;
                    if(aux == null){
                        padre.derecho = nuevo;
                        return;
                    }
                }
            }
        }
    }
    //Saber si el arbol está vacio
    public boolean estaVacio(){
        return raiz == null;
    }
    //Recorrido inOrder
    public void inOrder(NodoArbol raiz){
        if(raiz != null){
            inOrder(raiz.izquierdo);
            System.out.println("Nombre: "+raiz.nombre+", Puntuación: "+raiz.dato);
            inOrder(raiz.derecho);
        }
    }
    //Recorrido preOrder
    public void preOrder(NodoArbol raiz){
        if(raiz!=null){
            System.out.println("Nombre: "+raiz.nombre+", Puntuación: "+raiz.dato);;
            preOrder(raiz.izquierdo);
            preOrder(raiz.derecho);
        }
    }
    //Recorrido postOrden
    public void postOrder(NodoArbol raiz){
        if(raiz!=null){
            postOrder(raiz.izquierdo);
            postOrder(raiz.derecho);
            System.out.println("Nombre: "+raiz.nombre+", Puntuación: "+raiz.dato);
        }
    }
    //Buscar un nodo
    public NodoArbol consultarNodo(int d){
        NodoArbol aux = raiz;
        while(aux.dato != d){
            if(d < aux.dato){
                aux = aux.izquierdo;
            }else{
                aux = aux.derecho;
            }
            if(aux == null){
                return null;
            }
        }
        return aux;
    }
    //Eliminar un NodoArbol
    public boolean eliminarNodo(int d){
        NodoArbol aux = raiz;
        NodoArbol padre = raiz;
        boolean izq = true;
        while(aux.dato != d){
            padre = aux;
            if(d<aux.dato){
                izq = true;
                aux = aux.izquierdo;
            }else{
                izq = false;
                aux = aux.derecho;
            }
            if(aux == null){
                return false;
            }
        }
        if(aux.izquierdo == null && aux.derecho == null){
            if(aux == raiz){
                raiz = null;
            }else if(izq){
                padre.izquierdo = null;
            }else{
                padre.derecho = null;
            }
        }else if(aux.derecho == null){
            if(aux == raiz){
                raiz = aux.izquierdo;
            }else if(izq){
                padre.izquierdo = aux.izquierdo;
            }else{
                padre.derecho = aux.izquierdo;
            }
        }else if(aux.izquierdo == null){
            if(aux == raiz){
                raiz = aux.derecho;
            }else if(izq){
                padre.izquierdo = aux.derecho;
            }else{
                padre.derecho = aux.izquierdo;
            }
        }else{
            NodoArbol reemplazo = obtenerReemplazo(aux);
            if(aux == raiz){
                raiz = reemplazo;
            }else if(izq){
                padre.izquierdo = reemplazo;
            }else{
                padre.derecho = reemplazo;
            }
            reemplazo.izquierdo = aux.izquierdo;
        }
        return true;
    }
    //Obtener nodo reemplazo
    public NodoArbol obtenerReemplazo(NodoArbol nodo){
        NodoArbol reemplazarPadre = nodo;
        NodoArbol reemplazo = nodo;
        NodoArbol aux = nodo.derecho;
        while(aux != null){
            reemplazarPadre = reemplazo;
            reemplazo = aux;
            aux = aux.izquierdo;
        }
        if(reemplazo != nodo.derecho){
            reemplazarPadre.izquierdo = reemplazo.derecho;
            reemplazo.derecho = nodo.derecho;
        }
        return reemplazo;
    }
}
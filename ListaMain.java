import javax.swing.JOptionPane;

public class ListaMain {
    public static void main(String[] args) {
        Lista lista = new Lista();
        int opcion = 0, dato;
        do{
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "1. Insertar por el final\n" +
                        "2. Insertar entre dos nodos (se ingresa antes)\n"+
                        "3. Consultar\n"+
                        "4. Buscar\n"+
                        "5. Eliminar nodo\n"+
                        "6. (Extra) Eliminar nodo al final\n"+
                        "7. (Extra) Eliminar nodo al inicio\n"+
                        "8. (Extra) Insertar al principio\n"+
                        "9. Salir", "Menú de Listas Enlazadas", 3));
                switch (opcion){
                    case 1: //Insertando por el final
                        try {
                            dato = leeEntero("Ingresa el elemento que desea agregar", "Insertando al final", 3);
                            lista.agregarAlFinal(dato);
                        } catch (NumberFormatException n) {
                            JOptionPane.showMessageDialog(null, "Error al ingresar el dato:\n"+n.getMessage(),"Error", 2);
                        }
                        break;
                    case 2: //Insertando entre dos nodos
                        try {
                            int datoTemp = leeEntero("Ingresa el nodo que quiere que se inserte el dato", "Insertando datos entre nodos.", 3);
                            if(lista.buscar(datoTemp)){
                                dato = leeEntero("Ingresa el elemento que desea agregar", "Insertando datos entre nodos.", 3);
                                lista.insertarEntreNodos(datoTemp, dato);
                                JOptionPane.showMessageDialog(null, "El elemento "+ dato +" fue agregado correctamente.","Inserción completada.", JOptionPane.INFORMATION_MESSAGE);
                            } else JOptionPane.showMessageDialog(null, "No se encontró el nodo con el dato ingresado.","Error, no se encontró el nodo.", 2);
                        } catch (NumberFormatException n) {
                            JOptionPane.showMessageDialog(null, "Error al ingresar el dato:\n"+n.getMessage(),"Error", 2);
                        }
                        break;
                    case 3: //Consultando
                        if(!lista.estaVacia()){
                            JOptionPane.showMessageDialog(null, "La lista es la siguiente\n"+lista.mostrarLista(),"Impresión completada", 1);
                        } else JOptionPane.showMessageDialog(null, "La lista se encuentra vacía","Error, lista vacía.", 3);
                        break;
                    case 4: //Buscando
                        if(!lista.estaVacia()){
                            dato = leeEntero("Ingresa el elemento que desea buscar.", "Buscando nodo.", 3);
                            if(!lista.buscar(dato)) JOptionPane.showMessageDialog(null, "No se encontró el dato.","Error, no se encontró el dato.", 2);
                            else JOptionPane.showMessageDialog(null, "El dato fue encontrado con éxito.","Dato encontrado.", 1);
                        } else JOptionPane.showMessageDialog(null, "La lista se encuentra vacía","Error, lista vacía.", 2);
                        break;
                    case 5: //Eliminando nodos donde sea
                        if(!lista.estaVacia()){
                            try {
                                dato = leeEntero("Ingresa el elemento que deseas eliminar", "Eliminando nodos.", 3);
                                if(lista.buscar(dato)) lista.eliminar(dato);
                                else JOptionPane.showMessageDialog(null, "No se encontró el dato.","Error, no se encontró el dato.", 2);
                            } catch (NumberFormatException n) {
                                JOptionPane.showMessageDialog(null, "Error al ingresar el dato:\n"+n.getMessage(),"Error", 2);
                            }
                        } else JOptionPane.showMessageDialog(null, "La lista se encuentra vacía","Error, lista vacía.", 2);
                        break;
                    case 6:  //Elimando al final
                        if(!lista.estaVacia()){
                            dato = lista.borrarDelFinal();
                            JOptionPane.showMessageDialog(null, "El elemento "+ dato +" fue eliminado correctamente.","Eliminación completada.", JOptionPane.INFORMATION_MESSAGE);
                        } else JOptionPane.showMessageDialog(null, "La lista se encuentra vacía","Error, lista vacía.", 2);
                        
                        break;
    
                    case 7: //Eliminado al inicio
                        if(!lista.estaVacia()){
                            dato = lista.borrarDelInicio();
                            JOptionPane.showMessageDialog(null, "El elemento "+ dato +" fue eliminado correctamente.","Eliminación completada.", JOptionPane.INFORMATION_MESSAGE);
                        } else JOptionPane.showMessageDialog(null, "La lista se encuentra vacía","Error, lista vacía.", 2);
                        
                        
                        break;
                    case 8://Agregando al inicio de la lista
                        try {
                            dato = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el elemento que desea agregar", "Insertando al inicio",3));
                            lista.agregarAlInicio(dato);
                        } catch (NumberFormatException n) {
                            JOptionPane.showMessageDialog(null, "Error al ingresar el dato:\n"+n.getMessage(),"Error", 2);
                        }
                        break;
                    case 9:
                        JOptionPane.showMessageDialog(null, "Programa finalizado.\n Hecho por:\n"+
                        "Luis Fernando Chávez Jiménez\n Guillermo Moreno Rivera", "Listas enlazadas", 1);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción incorrecta.","Error", 2);
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tipo de dato ingresado no válido:\n"+e.getMessage(),"Error", 2);
            }
        } while(opcion!=9);
    }
    //Utilización de recursividad en validación de datos
    static int leeEntero(String a, String b, int c){
        int n = 0;
        try {
            n = Integer.parseInt(JOptionPane.showInputDialog(null, a, b, c));
            if(n < 1 || n > 100){
                JOptionPane.showMessageDialog(null, "El número ingresado está fuera de rango","Error", 2);
                n = leeEntero(a, b, c);
            }
        } catch (NumberFormatException t) {
            JOptionPane.showMessageDialog(null, "Error al ingresar el dato:\n"+t.getMessage(),"Error", 2);
            n = leeEntero(a, b, c);
        }
        return n;
    }

}

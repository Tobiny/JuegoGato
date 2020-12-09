/*
* Nombre del proyecto: Juego de Gato
* Integrantes: Luis Fernando Chávez Jiménez y Guillermo Moreno Rivera
* ¿De qué trata?: Realizamos el juego conocido como "Gato" utilizando arreglos de enteros,
* de caracteres y métodos para evaluar y realizar todo el programa.
*  OJO: Actualizamos toda la estructura respecto al programa anterior porque sentimos que estaba muy desordenado
*/

import java.util.*;
import javax.swing.*;

public class GatoChavezMoreno{
    //Listas que usamos para comprobar al ganador
    static ListaChavezMoreno posicionesJugador1;
    static ListaChavezMoreno posicionesCpu1;
    //Listas que almacenan las combinaciones ganadoras
    static ListaChavezMoreno filaTop1 = new ListaChavezMoreno();
    static ListaChavezMoreno filaMid1 = new ListaChavezMoreno();
    static ListaChavezMoreno filaBot1 = new ListaChavezMoreno();
    static ListaChavezMoreno colIzq1 = new ListaChavezMoreno();
    static ListaChavezMoreno colMid1 = new ListaChavezMoreno();
    static ListaChavezMoreno colDer1 = new ListaChavezMoreno();
    static ListaChavezMoreno diagonal1A = new ListaChavezMoreno();
    static ListaChavezMoreno diagonal2A = new ListaChavezMoreno();
    static ListaChavezMoreno posiblesG = new ListaChavezMoreno();
    //Variables de puntuaciones
    static int puntosCantidad[] = new int[50];
    static String puntosNombre[] = new String[50];
    static int contPuntos = 0;
    static int puntosJ = 0;
    static int puntosC = 0;
    static String nom = "";
    //Variables para controlar
    static int variableDeBusqueda = 0;
    static int cpuCont = 0;
    static int jugadorCont = 0;
    static boolean corriendo = true;
    static boolean aTemp = false;
    static int finalizar = 0;
    static int tableroCont = 0;
    static int ganador = 0;
    static int menu = 0;
    static String nombreDeBusqueda = "";
    static ArbolChavezMoreno arbol = new ArbolChavezMoreno();
    //Variables útiles
    static JFrame frame = new JFrame("Juego de Gato");
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        nom = JOptionPane.showInputDialog(null, "Ingrese su nombre: ","Gato - Inicio", JOptionPane.INFORMATION_MESSAGE);
        menu();
        sc.close();
    }

    public static void menu(){
        
        String opc = "";
        String[] opciones = {"Jugar partida", "Ver puntuaciones","Buscar jugador por puntuación","Buscar jugador por nombre","Nuevo usuario", "Salir" };
        while(!opc.equals(opciones[5])){
            limpiar();
            opc = (String) JOptionPane.showInputDialog(frame, "¿Qué desea hacer?", "Juego de Gato", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            switch (opc) {
                case "Jugar partida":
                    JOptionPane.showMessageDialog(null, "Tablero de juego impreso en consola.", "Gato - Juego", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("----------------------------------------------");
                    juego();
                    break;
                case "Ver puntuaciones":
                    imprimePuntuaciones();
                    break;
                case "Buscar jugador por puntuación":
                    variableDeBusqueda = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese los puntos a buscar: ","Gato - Busqueda", JOptionPane.INFORMATION_MESSAGE));
                    busquedaBinaria();
                    break;
                case "Buscar jugador por nombre":
                    nombreDeBusqueda = JOptionPane.showInputDialog(null, "Ingrese los puntos a buscar: ","Gato - Busqueda", JOptionPane.INFORMATION_MESSAGE);
                    busquedaSecuencial();
                break;
                case "Nuevo usuario":
                    puntosJ = 0;
                    nom = "";
                    nom = JOptionPane.showInputDialog(null, "Ingrese su nombre: ","Gato - Inicio", JOptionPane.INFORMATION_MESSAGE);
                    contPuntos++;
                    break;
                case "Salir":
                    JOptionPane.showMessageDialog(null, "Programa finalizado.\n"+
                                                        "Creado por: Luis Fernando Chávez Jiménez\n"+
                                                        "Guillermo Moreno Rivera","Gato - Salir", JOptionPane.INFORMATION_MESSAGE);

                    break;
            }
        }
    }

    public static void juego(){
        char[][] tableroJuego = {{' ', '|', ' ', '|', ' '},
                                 {'-', '+', '-', '+', '-'},
                                 {' ', '|', ' ', '|', ' '},
                                 {'-', '+', '-', '+', '-'},
                                 {' ', '|', ' ', '|', ' '}};
        imprimirTablero(tableroJuego);
        while (finalizar != 1) {
            int posJugador = ingresarJugador();

            while(posicionesJugador1.buscar(posJugador)|| posicionesCpu1.buscar(posJugador)){
                System.out.println("Ya hay una ficha en esa posición, ingresa otra vacía: ");
                posJugador = ingresarJugador();
            }
            asignarPieza(tableroJuego, posJugador, "jugador");
            
            if(revisarGanador()){
                break;
            }
            Random aleatorio = new Random();
            int posCpu = aleatorio.nextInt(9) + 1;
            while(posicionesJugador1.buscar(posCpu)|| posicionesCpu1.buscar(posCpu)){
                posCpu = aleatorio.nextInt(9) + 1;
            }
            asignarPieza(tableroJuego, posCpu, "cpu");
            imprimirTablero(tableroJuego);
            if(revisarGanador()){
                break;
            }
        }
    }
    public static int ingresarJugador(){
        int numT = 0;
        try {
            do {
                System.out.print("Ingrese una posicion (1-9): ");
                numT = sc.nextInt();
            } while (numT < 1 || numT > 9);
        } catch (Exception e) {
            System.out.println("Ingreso un valor inválido.");
        }
        return numT;
    }

    //Método para imprimir el tablero de juego
    public static void imprimirTablero(char[][] tableroJuego){
        for (char[] fila : tableroJuego) {
            for (char i : fila) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
    //Método para asignar la pieza a la posición correspondiente
    public static void asignarPieza(char[][] tableroJuego, int pos, String usuario){
        char simbolo = ' ';
        if(usuario.equals("jugador")){
            simbolo = 'X';
            posicionesJugador1.agregarAlFinal(pos);
            jugadorCont++;
            tableroCont++;
        }  
        else if(usuario.equals("cpu")){
            simbolo = 'O';
            posicionesCpu1.agregarAlFinal(pos);
            cpuCont++;
            tableroCont++;
        }
            
        switch(pos){
            case 1:
                tableroJuego[0][0] = simbolo;
                break;
            case 2:
                tableroJuego[0][2] = simbolo;
                break;
            case 3:
                tableroJuego[0][4] = simbolo;
                break;
            case 4:
                tableroJuego[2][0] = simbolo;
                break;
            case 5:
                tableroJuego[2][2] = simbolo;
                break;
            case 6:
                tableroJuego[2][4] = simbolo;
                break;
            case 7:
                tableroJuego[4][0] = simbolo;
                break;
            case 8:
                tableroJuego[4][2] = simbolo;
                break;
            case 9:
                tableroJuego[4][4] = simbolo;
                break;
            default:
                break;
        }
    }
    public static boolean revisarGanador(){
        //Creamos lista que almacena listas de los posibles casos de gane
        
        filaTop1.llenarGanador(1, 2, 3);
        filaMid1.llenarGanador(4, 5, 6);
        filaBot1.llenarGanador(7, 8, 9);
        colIzq1.llenarGanador(1, 4, 7);
        colMid1.llenarGanador(2, 5, 8);
        colDer1.llenarGanador(3, 6, 9);
        diagonal1A.llenarGanador(1, 5, 9);
        diagonal2A.llenarGanador(7, 5, 3);
        posiblesG.agregar(filaTop1);
        posiblesG.agregar(filaMid1);
        posiblesG.agregar(filaBot1);
        posiblesG.agregar(colIzq1);
        posiblesG.agregar(colMid1);
        posiblesG.agregar(colDer1);
        posiblesG.agregar(diagonal1A);
        posiblesG.agregar(diagonal2A);
        
        if((posiblesG.recorrer(posicionesJugador1, jugadorCont, 1)) == 1){

            JOptionPane.showMessageDialog(null, "El jugador ha ganado la ronda","Gato - Jugador ganó.", JOptionPane.INFORMATION_MESSAGE);
            puntosJ++;
            aTemp = true;
            finalizar = 1;
        } else if((posiblesG.recorrer(posicionesCpu1, cpuCont, 2)) == 2){
            JOptionPane.showMessageDialog(null, "El cpu ha ganado la ronda","Gato - Cpu ganó.", JOptionPane.INFORMATION_MESSAGE);
            puntosC++;
            aTemp = true;
            finalizar = 1;
        } else if(tableroCont == 9){
            System.out.println("Se empato la ronda");
            aTemp = true;
            finalizar = 1;
        }
        //Guarda los puntos
        arbol.insertar(puntosJ, nom);
        addPuntosBubble();
        return aTemp;
    }
    public static void limpiar(){
        jugadorCont = 0;
        cpuCont = 0;
        puntosC = 0;
        jugadorCont = 0;
        finalizar = 0;
        tableroCont = 0;
        ganador = 0;
        menu = 0;
        aTemp = false;
        posicionesCpu1 = null;
        posicionesJugador1 = null;
        posiblesG = null;
        posiblesG = null;
        filaMid1 = null;
        filaBot1 = null;
        colIzq1 = null;
        colMid1 = null;
        colDer1 = null;
        diagonal1A = null;
        diagonal2A = null;
        filaTop1 = null;
        posicionesJugador1 = new ListaChavezMoreno();
        posicionesCpu1 = new ListaChavezMoreno();
        posiblesG = new ListaChavezMoreno();
        filaMid1 = new ListaChavezMoreno();
        filaBot1 = new ListaChavezMoreno();
        colIzq1 = new ListaChavezMoreno();
        colMid1 = new ListaChavezMoreno();
        colDer1 = new ListaChavezMoreno();
        diagonal1A = new ListaChavezMoreno();
        diagonal2A = new ListaChavezMoreno();
        filaTop1 = new ListaChavezMoreno();
    }
    //Añade puntuaciones a un arreglo y los ordena por el método de bubble
    public static void addPuntosBubble(){
        if(contPuntos == 50){
            contPuntos = 0;
            System.out.println("Se ha alcanzado el limite de puntuaciones guardadas; se comenzaran a borrar las más bajas");
        }
        

        puntosCantidad[contPuntos] = puntosJ;
        puntosNombre[contPuntos] = nom;
        //Ordenamiento bubble
        String auxString = new String();
        int aux = 0;
        for (int i = 0; i < contPuntos; i++) {
            for (int j = 0; j < contPuntos-1; j++) {
                if (puntosCantidad[j] > puntosCantidad[j+1]) {
                    auxString = puntosNombre[j];
                    aux = puntosCantidad[j];
                    puntosCantidad[j] = puntosCantidad[j+1];
                    puntosNombre[j] = puntosNombre[j+1];
                    puntosCantidad[j+1] = aux;
                    puntosNombre[j+1] = auxString;
                }
            }
        }
    }
    public static void imprimePuntuaciones(){
        JOptionPane.showMessageDialog(null, "Puntuaciones impresas en consola","Gato - Puntuaciones.", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Las puntuaciones son las siguientes:");
        System.out.printf("%-25s %-10s \n", "Nombre del jugador", "Puntos");
        int i = 0;
        while(i != contPuntos+1){
            System.out.printf("%-25s %-10s \n", puntosNombre[i], puntosCantidad[i]);
            i++;
        }
    }
    public static void busquedaBinaria(){
        boolean bandera = false;
        int i = 0, izq = 0, der = contPuntos-1, centro = 0;
        String puntos = "";
        if(contPuntos < 1){
            if(puntosCantidad[i] == variableDeBusqueda) bandera = true;
        }else if(contPuntos == 1){
            while(i < puntosCantidad.length-1 && !bandera){
                if(puntosCantidad[i] == variableDeBusqueda) bandera = true;
                else i++;
            }
        }else {
            while(izq <= der && !bandera){
                centro =(int) ((izq+der)/2);
                if(variableDeBusqueda == puntosCantidad[centro]){
                    bandera = true;
                }else if( variableDeBusqueda > puntosCantidad[centro]){
                    izq = centro + 1;
                    i++;
                } else{
                    der = centro - 1;
                    i++;
                }
            }
        }
        if(bandera){
            puntos = "Se encontró un registro: "+ puntosNombre[i]+", con "+puntosCantidad[i]+" puntos.";
        }else if(!bandera){
            puntos = "No se encontró ningún registro";
        }
        JOptionPane.showMessageDialog(null, puntos,"Gato - Puntuaciones.", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void busquedaSecuencial(){
        boolean bandera = false;
        int i = 0;
        String puntos = "";
        while(i < puntosNombre.length-1 && !bandera){
            if(nombreDeBusqueda.equals(puntosNombre[i])){
                bandera = true;
            }else{
                i++;
            }
            if(bandera){
                puntos = "Se encontró un registro: "+ puntosNombre[i]+", con "+puntosCantidad[i]+" puntos.";
            }else if(!bandera){
                puntos = "No se encontró ningún registro";
            }
        }
        JOptionPane.showMessageDialog(null, puntos,"Gato - Puntuaciones.", JOptionPane.INFORMATION_MESSAGE);
    }
}
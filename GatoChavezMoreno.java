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
    static int jugadorCont = 0;
    static int cpuCont = 0;
    static int puntosJ = 0;
    static int puntosC = 0;
    static int finalizar = 0;
    static int tableroCont = 0;
    static int ganador = 0;
    static String nom = "";
    static int menu = 0;
    static ArbolChavezMoreno arbol = new ArbolChavezMoreno();
    static boolean corriendo = true;
    static boolean aTemp = false;
    static JFrame frame = new JFrame("Juego de Gato");
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        nom = JOptionPane.showInputDialog(null, "Ingrese si nombre: ","Gato - Inicio", JOptionPane.INFORMATION_MESSAGE);
        menu();
        sc.close();
    }

    public static void menu(){
        
        String opc = "";
        String[] opciones = {"Jugar partida", "Ver puntuaciones / Guardar puntuaciones", "Nuevo usuario", "Salir" };
        while(!opc.equals(opciones[3])){
            limpiar();
            opc = (String) JOptionPane.showInputDialog(frame, "¿Qué desea hacer?", "Juego de Gato", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            switch (opc) {
            
                case "Jugar partida":
                    juego();
                    break;
                case "Ver puntuaciones / Guardar puntuaciones":
                    JOptionPane.showMessageDialog(null, "Puntuaciones impresas en consola","Gato - Puntuaciones.", JOptionPane.INFORMATION_MESSAGE);
                    arbol.insertar(puntosJ, nom);
                    System.out.println("Puntuaciones: ");
                    arbol.inOrder(arbol.raiz);
                    
                    break;
                case "Nuevo usuario":
                    puntosJ = 0;
                    nom = "";
                    nom = JOptionPane.showInputDialog(null, "Ingrese si nombre: ","Gato - Inicio", JOptionPane.INFORMATION_MESSAGE);
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
            Scanner sc = new Scanner(System.in);

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
    public static void validaA(){
    
    }
}
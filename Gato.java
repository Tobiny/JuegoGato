/*
* Nombre del proyecto: Juego de Gato
* Integrantes: Luis Fernando Chávez Jiménez y Guillermo Moreno Rivera
* ¿De qué trata?: Realizamos el juego conocido como "Gato" utilizando arreglos de enteros,
* de caracteres y métodos para evaluar y realizar todo el programa.
*
*/

import java.util.*;

public class Gato{

    static int[] posicionesJugador = new int[9];
    static int[] posicionesCpu = new int[9];
    static int jugadorCont = 0;
    static int cpuCont = 0;
    static int finalizar = 0;
    static int tableroCont = 0;
    public static void main(String[] args) {
        char[][] tableroJuego = {{' ', '|', ' ', '|', ' '},
                                 {'-', '+', '-', '+', '-'},
                                 {' ', '|', ' ', '|', ' '},
                                 {'-', '+', '-', '+', '-'},
                                 {' ', '|', ' ', '|', ' '}};

        imprimirTablero(tableroJuego);

        while (finalizar !=1) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingrese la posición a seleccionar (1 al 9): ");
            int posJugador = sc.nextInt();
            while(validar(posJugador)){
                System.out.print("Posición tomada, ingrese otra posición: ");
                posJugador = sc.nextInt();
            }

            asignarPieza(tableroJuego, posJugador, "jugador");
            
            Random aleatorio = new Random();
            int posCpu = aleatorio.nextInt(9) + 1;
            while(validar(posCpu)){
                posCpu = aleatorio.nextInt(9) + 1;
            }
            asignarPieza(tableroJuego, posCpu, "cpu");
            imprimirTablero(tableroJuego);
            System.out.println(revisarGanador());
            
        }
 

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
        //Dependiendo quién sea asignara O o X
        if(usuario.equals("jugador")){
            simbolo = 'X';
            posicionesJugador[jugadorCont] = pos;
            jugadorCont++;
            tableroCont++;
        } else if(usuario.equals("cpu")){
            simbolo = 'O';
            posicionesCpu[cpuCont] = pos;
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
    public static Lista llenaLista(int[][] p, int p2){
        Lista lista1 = new Lista();
        for (int i = 0; i < 3; i++) {
            lista1.agregarAlInicio(p[p2][i]);
        }
        
        return lista1;
    }
    //Analiza en todas las posibles combinaciones si es que hay un ganador
    public static String revisarGanador(){
        Lista posiblesG = new Lista();
        int[][] posibleGanar = {{1, 4, 7},
                                {2, 5, 8},
                                {3, 6, 9},
                                {1, 2, 3},
                                {4, 5, 6},
                                {7, 8, 9},
                                {1, 5, 9},
                                {2, 5, 7}};

        Lista filaTop = new Lista();
        filaTop = llenaLista(posibleGanar, 3);
        Lista filaMid = new Lista();
        filaMid = llenaLista(posibleGanar, 4);
        Lista filaBot = new Lista();
        filaBot = llenaLista(posibleGanar, 5);
        Lista colIzq = new Lista();
        colIzq = llenaLista(posibleGanar, 0);
        Lista colMid = new Lista();
        colMid = llenaLista(posibleGanar, 1);
        Lista colDer = new Lista();
        colDer = llenaLista(posibleGanar, 2);
        Lista diago1 = new Lista();
        diago1 = llenaLista(posibleGanar, 6);
        Lista diago2 = new Lista();
        diago2 = llenaLista(posibleGanar, 7);
        int ganarCont = 0;
        
        posiblesG.agregar(filaTop);
        posiblesG.agregar(filaMid);
        posiblesG.agregar(filaBot);
        posiblesG.agregar(colIzq);
        posiblesG.agregar(colMid);
        posiblesG.agregar(colDer);
        posiblesG.agregar(diago1);
        posiblesG.agregar(diago2);
        
        
        int ganador = posiblesG.recorrer(posicionesJugador, jugadorCont, 1);
        if((posiblesG.recorrer(posicionesJugador, jugadorCont, 1) == 1){
            System.out.println(ganador);
        } else if(posiblesG.recorrer(posicionesCpu, cpuCont, 2) == 2){
            System.out.println(ganador);
        } else if(tablero == 9){

        }
        
        
        // for (int i = 0; i < posibleGanar.length; i++) {
        //     for (int j = 0; j < posibleGanar[0].length; j++) {
        //         for (int k = 0; k < jugadorCont; k++) {
        //             if(posicionesJugador[k] == posibleGanar[i][j]){
        //                 ganarCont++;
        //             } 
        //             if(ganarCont==3){
        //                 finalizar = 1;
        //                 return "El jugador ha ganado.";
        //             }
        //         } 
        //     } 
        //     ganarCont = 0;
        // }
        // for (int i = 0; i < posibleGanar.length; i++) {
        //     for (int j = 0; j < posibleGanar[0].length; j++) {
        //         for (int k = 0; k < cpuCont; k++) {
        //             if(posicionesCpu[k]==posibleGanar[i][j]){
        //                 ganarCont++;
        //             } 
        //             if(ganarCont==3){
        //                 finalizar = 1;
        //                 return "El cpu ha ganado.";
        //             }
        //         }
        //     }
        //     ganarCont = 0;
        // }
        
        return "";
    }

    //Valida si es que ya se tomó la posición
    public static boolean validar(int pos){
        for (int i = 0; i < posicionesJugador.length; i++) {
            if(posicionesJugador[i] == pos || posicionesCpu[i] == pos){
                return true;
            }
        }
        return false;
    }
}
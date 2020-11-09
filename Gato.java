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
        } else if(usuario.equals("cpu")){
            simbolo = 'O';
            posicionesCpu[cpuCont] = pos;
            cpuCont++;
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
    //Analiza en todas las posibles combinaciones si es que hay un ganador
    public static String revisarGanador(){
        Lista lista[] = new Lista();

        
        int[][] posibleGanar = {{1, 4, 7},
                                {2, 5, 8},
                                {3, 6, 9},
                                {1, 2, 3},
                                {4, 5, 6},
                                {7, 8, 9},
                                {1, 5, 9},
                                {2, 5, 7}};
        int ganarCont = 0;
        
        for (int i = 0; i < posibleGanar.length; i++) {
            for (int j = 0; j < posibleGanar[0].length; j++) {
                for (int k = 0; k < jugadorCont; k++) {
                    if(posicionesJugador[k] == posibleGanar[i][j]){
                        ganarCont++;
                    } 
                    if(ganarCont==3){
                        finalizar = 1;
                        return "El jugador ha ganado.";
                    }
                } 
            } 
            ganarCont = 0;
        }
        for (int i = 0; i < posibleGanar.length; i++) {
            for (int j = 0; j < posibleGanar[0].length; j++) {
                for (int k = 0; k < cpuCont; k++) {
                    if(posicionesCpu[k]==posibleGanar[i][j]){
                        ganarCont++;
                    } 
                    if(ganarCont==3){
                        finalizar = 1;
                        return "El cpu ha ganado.";
                    }
                }
            }
            ganarCont = 0;
        }
        
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
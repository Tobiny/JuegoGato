import java.util.*;

public class Gato{
    //Listas que usamos para comprobar al ganador
    static Lista posicionesJugador1 = new Lista();
    static Lista posicionesCpu1 = new Lista();
    //Listas que almacenan las combinaciones ganadoras
    static Lista filaTop1 = new Lista();
    static Lista filaMid1 = new Lista();
    static Lista filaBot1 = new Lista();
    static Lista colIzq1 = new Lista();
    static Lista colMid1 = new Lista();
    static Lista colDer1 = new Lista();
    static Lista diagonal1A = new Lista();
    static Lista diagonal2A = new Lista();
    static int jugadorCont = 0;
    static int cpuCont = 0;
    static int puntosJ = 0;
    static int puntosC = 0;

    static int finalizar = 0;
    static int tableroCont = 0;
    static int ganador = 0;
    public static void main(String[] args) {
        char[][] tableroJuego = {{' ', '|', ' ', '|', ' '},
                                 {'-', '+', '-', '+', '-'},
                                 {' ', '|', ' ', '|', ' '},
                                 {'-', '+', '-', '+', '-'},
                                 {' ', '|', ' ', '|', ' '}};
        imprimirTablero(tableroJuego);
        while (finalizar != 1) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingrese la posición a seleccionar (1 al 9): ");
            int posJugador = sc.nextInt();
            while(posicionesJugador1.buscar(posJugador)|| posicionesCpu1.buscar(posJugador)){
                System.out.println("Ya hay una ficha en esa posición, ingresa otra vacía: ");
                posJugador = sc.nextInt();
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
        boolean aTemp = false;
        //Creamos lista que almacena listas de los posibles casos de gane
        Lista posiblesG = new Lista();
        filaTop1.llenarGanador(1, 2, 3);
        filaMid1.llenarGanador(4, 5, 6);
        filaBot1.llenarGanador(7, 8, 9);
        colIzq1.llenarGanador(1, 4, 7);
        colMid1.llenarGanador(2, 5, 8);
        colDer1.llenarGanador(3, 6, 9);
        diagonal1A.llenarGanador(1, 5, 9);
        diagonal2A.llenarGanador(7, 5, 3);
        int ganarCont = 0;
        posiblesG.agregar(filaTop1);
        posiblesG.agregar(filaMid1);
        posiblesG.agregar(filaBot1);
        posiblesG.agregar(colIzq1);
        posiblesG.agregar(colMid1);
        posiblesG.agregar(colDer1);
        posiblesG.agregar(diagonal1A);
        posiblesG.agregar(diagonal2A);
        
        if((posiblesG.recorrer(posicionesJugador1, jugadorCont, 1)) == 1){
            System.out.println("El jugador ha ganado esta ronda");
            aTemp = true;
            puntosJ++;
            finalizar = 1;
        } else if((posiblesG.recorrer(posicionesCpu1, cpuCont, 2)) == 2){
            System.out.println("El cpu ha ganado esta ronda");
            aTemp = true;
            puntosC++;
            finalizar = 1;
        } else if(tableroCont == 9){
            System.out.println("Se empato la ronda");
            aTemp = true;
            finalizar = 1;
        }
        return aTemp;
    }
}

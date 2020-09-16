import java.util.*;

public class Gato{
    public static void main(String[] args) {
        char[][] tableroJuego = {{' ', '|', ' ', '|', ' '},
                                 {'-', '+', '-', '+', '-'},
                                 {' ', '|', ' ', '|', ' '},
                                 {'-', '+', '-', '+', '-'},
                                 {' ', '|', ' ', '|', ' '}};

        imprimirTablero(tableroJuego);

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la posición a seleccionar (1 al 9): ");
        int posJugador = sc.nextInt();

        asignarPieza(tableroJuego, posJugador, "jugador");

        Random aleatorio = new Random();
        int posCpu = aleatorio.nextInt(9) + 1;
        asignarPieza(tableroJuego, posCpu, "cpu");

        imprimirTablero(tableroJuego);

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
        if(usuario.equals("jugador"))
            simbolo = 'X';
        else if(usuario.equals("cpu"))
            simbolo = 'O';
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
}
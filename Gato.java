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
        int pos = sc.nextInt();

        switch(pos){
            case 1:
                tableroJuego[0][0] = 'X';
                break;
            case 2:
                tableroJuego[0][2] = 'X';
                break;
            case 3:
                tableroJuego[0][4] = 'X';
                break;
            case 4:
                tableroJuego[2][0] = 'X';
                break;
            case 5:
                tableroJuego[2][2] = 'X';
                break;
            case 6:
                tableroJuego[2][4] = 'X';
                break;
            case 7:
                tableroJuego[4][0] = 'X';
                break;
            case 8:
                tableroJuego[4][2] = 'X';
                break;
            case 9:
                tableroJuego[4][4] = 'X';
                break;
            
        }

    }
    public static void imprimirTablero(char[][] tableroJuego){
        for (char[] fila : tableroJuego) {
            for (char i : fila) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
}
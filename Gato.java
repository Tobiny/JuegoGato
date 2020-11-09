import java.util.*;

public class Gato{
    // Utilizamos una lista de arreglos para facilitar la evaluación de la victoria
    // o derrota de cada jugador o de la computadora, así cómo también para facilitar
    // la manipulación de cada posición dada.
    static Lista posicionesJugador1 = new Lista();
    static Lista posicionesCpu1 = new Lista();
    public static void main(String[] args) {
        char[][] tableroJuego = {{' ', '|', ' ', '|', ' '},
                                 {'-', '+', '-', '+', '-'},
                                 {' ', '|', ' ', '|', ' '},
                                 {'-', '+', '-', '+', '-'},
                                 {' ', '|', ' ', '|', ' '}};

        imprimirTablero(tableroJuego);

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingrese la posición a seleccionar (1 al 9): ");
            int posJugador = sc.nextInt();
            while(posicionesJugador1.buscar(posJugador)|| posicionesCpu1.buscar(posJugador)){
                System.out.println("Ya hay una ficha en esa posición, ingresa otra vacía: ");
                posJugador = sc.nextInt();
            }

            asignarPieza(tableroJuego, posJugador, "jugador");
            String resultado  = revisarGanador();
            if(resultado.length() > 0){
                
                System.out.println(resultado);
                break;
            }
    
            Random aleatorio = new Random();
            int posCpu = aleatorio.nextInt(9) + 1;
            while(posicionesJugador1.buscar(posCpu)|| posicionesCpu1.buscar(posCpu)){
                posCpu = aleatorio.nextInt(9) + 1;
            }
            asignarPieza(tableroJuego, posCpu, "cpu");
    
            imprimirTablero(tableroJuego);
            
            resultado  = revisarGanador();
            if(resultado.length() > 0){
                imprimirTablero(tableroJuego);
                System.out.println(resultado);
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
        }  
        else if(usuario.equals("cpu")){
            simbolo = 'O';
            posicionesCpu1.agregarAlFinal(pos);
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

    static Lista filaTop1 = new Lista();
    static Lista filaMid1 = new Lista();
    static Lista filaBot1 = new Lista();
    static Lista colIzq1 = new Lista();
    static Lista colMid1 = new Lista();
    static Lista colDer1 = new Lista();
    static Lista diagonal1A = new Lista();
    static Lista diagonal2A = new Lista();
    public static String revisarGanador(){
        // En este caso estamos utilizando listas para facilitarnos 
        // la evaluación de cada caso

        
        filaTop1.agregarAlFinal(1);
        filaTop1.agregarAlFinal(2);
        filaTop1.agregarAlFinal(3);

        
        filaMid1.agregarAlFinal(4);
        filaMid1.agregarAlFinal(5);
        filaMid1.agregarAlFinal(6);

        
        filaBot1.agregarAlFinal(7);
        filaBot1.agregarAlFinal(8);
        filaBot1.agregarAlFinal(9);

        
        colIzq1.agregarAlFinal(1);
        colIzq1.agregarAlFinal(4);
        colIzq1.agregarAlFinal(7);

        
        colMid1.agregarAlFinal(2);
        colMid1.agregarAlFinal(5);
        colMid1.agregarAlFinal(8);

        
        colDer1.agregarAlFinal(3);
        colDer1.agregarAlFinal(6);
        colDer1.agregarAlFinal(9);

        
        diagonal1A.agregarAlFinal(1);
        diagonal1A.agregarAlFinal(5);
        diagonal1A.agregarAlFinal(9);

        
        diagonal2A.agregarAlFinal(7);
        diagonal2A.agregarAlFinal(5);
        diagonal2A.agregarAlFinal(3);

        
        //Evalua al ganador del juego
        if(compruebaF1J()||compruebaF2J()||compruebaF3J()||compruebaC1J()||compruebaC2J()||compruebaC3J()||compruebaD1J()||compruebaD2J()) return"Ganaste!";
        else if(compruebaF1C()||compruebaF2C()||compruebaF3C()||compruebaC1C()||compruebaC2C()||compruebaC3C()||compruebaD1C()||compruebaD2C()) return"Gano el CPU";
        else if(posicionesCpu1.longitud()+ posicionesJugador1.longitud() == 9) return "Empate";
        else return "";
    }
    public static boolean compruebaF1J(){
        int contiene = 0;
        for(int x = 0; x < filaTop1.longitud(); x++){
            for(int z = 0; z < posicionesJugador1.longitud(); z++){
                if(filaTop1.getReferencia(x) == posicionesJugador1.getReferencia(z)){
                     contiene++;
                }    
            }
        }
        if (contiene == 18) return true;
        else return false;
    }
    public static boolean compruebaF2J(){
        int contiene = 0;
        for(int x = 0; x < filaMid1.longitud(); x++){
            for(int z = 0; z < posicionesJugador1.longitud(); z++){
                if(filaMid1.getReferencia(x) == posicionesJugador1.getReferencia(z)){
                     contiene++;
                }    
            }
        }
        if (contiene == 18) return true;
        else return false;
    }
    public static boolean compruebaF3J(){
        int contiene = 0;
        for(int x = 0; x < filaBot1.longitud(); x++){
            for(int z = 0; z < posicionesJugador1.longitud(); z++){
                if(filaBot1.getReferencia(x) == posicionesJugador1.getReferencia(z)){
                     contiene++;
                }    
            }
        }
        if (contiene == 18) return true;
        else return false;
    }
    public static boolean compruebaC1J(){
        int contiene = 0;
        for(int x = 0; x < colIzq1.longitud(); x++){
            for(int z = 0; z < posicionesJugador1.longitud(); z++){
                if(colIzq1.getReferencia(x) == posicionesJugador1.getReferencia(z)){
                     contiene++;
                }    
            }
        }
        if (contiene == 18) return true;
        else return false;
    }
    public static boolean compruebaC2J(){
        int contiene = 0;
        for(int x = 0; x < colMid1.longitud(); x++){
            for(int z = 0; z < posicionesJugador1.longitud(); z++){
                if(colMid1.getReferencia(x) == posicionesJugador1.getReferencia(z)){
                     contiene++;
                }    
            }
        }
        if (contiene == 18) return true;
        else return false;
    }
    public static boolean compruebaC3J(){
        int contiene = 0;
        for(int x = 0; x < colDer1.longitud(); x++){
            for(int z = 0; z < posicionesJugador1.longitud(); z++){
                if(colDer1.getReferencia(x) == posicionesJugador1.getReferencia(z)){
                     contiene++;
                }    
            }
        }
        if (contiene == 18) return true;
        else return false;
    }
    public static boolean compruebaD1J(){
        int contiene = 0;
        for(int x = 0; x < diagonal1A.longitud(); x++){
            for(int z = 0; z < posicionesJugador1.longitud(); z++){
                if(diagonal1A.getReferencia(x) == posicionesJugador1.getReferencia(z)){
                     contiene++;
                }    
            }
        }
        if (contiene == 18) return true;
        else return false;
    }
    public static boolean compruebaD2J(){
        int contiene = 0;
        for(int x = 0; x < diagonal2A.longitud(); x++){
            for(int z = 0; z < posicionesJugador1.longitud(); z++){
                if(diagonal2A.getReferencia(x) == posicionesJugador1.getReferencia(z)){
                     contiene++;
                }    
            }
        }
        if (contiene == 18) return true;
        else return false;
    }
    public static boolean compruebaF1C(){
        int contiene = 0;
        for(int x = 0; x < filaTop1.longitud(); x++){
            for(int z = 0; z < posicionesCpu1.longitud(); z++){
                if(filaTop1.getReferencia(x) == posicionesCpu1.getReferencia(z)){
                     contiene++;
                }    
            }
        }
        if (contiene == 18) return true;
        else return false;
    }
    public static boolean compruebaF2C(){
        int contiene = 0;
        for(int x = 0; x < filaMid1.longitud(); x++){
            for(int z = 0; z < posicionesCpu1.longitud(); z++){
                if(filaMid1.getReferencia(x) == posicionesCpu1.getReferencia(z)){
                     contiene++;
                }    
            }
        }
        if (contiene == 18) return true;
        else return false;
    }
    public static boolean compruebaF3C(){
        int contiene = 0;
        for(int x = 0; x < filaBot1.longitud(); x++){
            for(int z = 0; z < posicionesCpu1.longitud(); z++){
                if(filaBot1.getReferencia(x) == posicionesCpu1.getReferencia(z)){
                     contiene++;
                }    
            }
        }
        if (contiene == 18) return true;
        else return false;
    }
    public static boolean compruebaC1C(){
        int contiene = 0;
        for(int x = 0; x < colIzq1.longitud(); x++){
            for(int z = 0; z < posicionesCpu1.longitud(); z++){
                if(colIzq1.getReferencia(x) == posicionesCpu1.getReferencia(z)){
                     contiene++;
                }    
            }
        }
        if (contiene == 18) return true;
        else return false;
    }
    public static boolean compruebaC2C(){
        int contiene = 0;
        for(int x = 0; x < colMid1.longitud(); x++){
            for(int z = 0; z < posicionesCpu1.longitud(); z++){
                if(colMid1.getReferencia(x) == posicionesCpu1.getReferencia(z)){
                     contiene++;
                }    
            }
        }
        if (contiene == 18) return true;
        else return false;
    }
    public static boolean compruebaC3C(){
        int contiene = 0;
        for(int x = 0; x < colDer1.longitud(); x++){
            for(int z = 0; z < posicionesCpu1.longitud(); z++){
                if(colDer1.getReferencia(x) == posicionesCpu1.getReferencia(z)){
                     contiene++;
                }    
            }
        }
        if (contiene == 18) return true;
        else return false;
    }
    public static boolean compruebaD1C(){
        int contiene = 0;
        for(int x = 0; x < diagonal1A.longitud(); x++){
            for(int z = 0; z < posicionesCpu1.longitud(); z++){
                if(diagonal1A.getReferencia(x) == posicionesCpu1.getReferencia(z)){
                     contiene++;
                }    
            }
        }
        if (contiene == 18) return true;
        else return false;
    }
    public static boolean compruebaD2C(){
        int contiene = 0;
        for(int x = 0; x < diagonal2A.longitud(); x++){
            for(int z = 0; z < posicionesCpu1.longitud(); z++){
                if(diagonal2A.getReferencia(x) == posicionesCpu1.getReferencia(z)){
                     contiene++;
                }    
            }
        }
        if (contiene == 18) return true;
        else return false;
    }
}

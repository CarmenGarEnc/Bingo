/*

 */
package bingo;

import java.util.Scanner;

/**
 *
 * @author Carmen
 */
public class Bingo {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner menu = new Scanner( System.in ); //Creamos el objeto menu
        //int[][] jugador1 = new int [3][9];
        int numJugadores=0;
        int [][][] bingo;
        int[] sorteo = new int[90];
        int bola;
        int cont=0;
        boolean linea=false;
        boolean ganar=false;
        int opcion=0;
        
        System.out.println("Bienvenido al SUPER BINGO.");
        do{
            try{
                System.out.println("¿Cuantos jugadores sois?");
                numJugadores=menu.nextInt();

            }catch(java.util.InputMismatchException e){//Esta excepción es en caso de meter un valor incorrecto en los Int, vuelve al menú
            System.out.println("ERROR: Valor no válido.");
            menu.nextLine();
            }
        }while(numJugadores<=0);
            
        bingo =new int[numJugadores][3][9];

        for (int i = 0; i < numJugadores; i++) {
            Metodos.crearCarton(bingo[i]);
        }  
           
        do{
           try{
            
                System.out.println( "1.Jugar." );
                System.out.println( "2.Crear Cartón nuevo." );
                System.out.println( "3.Sacar número." );
                System.out.println( "4.Mostrar Cartón." );
                System.out.println( "5.Salir." );
                System.out.println("");

                opcion = menu.nextInt();
                switch (opcion) {
                    case 1://Jugar
                        System.out.println("Bienvenido al BINGO, este es tu cartón: ");
                        for (int i = 0; i < numJugadores; i++) {
                            System.out.println("Jugador"+(i+1));
                            Metodos.mostrarTablero(bingo[i]);
                        }
                        System.out.println("Si no te gusta puedes coger otro pulsando el 2 del menú.\n SUERTE");
                        break;
                    case 2://Crear Carton nuevo
                        if (cont==0) {
                            System.out.println("¿Que cartón desea modificar?");
                            int carNew=menu.nextInt();
                            if (carNew>0 && carNew<=numJugadores) {
                                bingo[carNew-1] = new int [3][9];
                                Metodos.crearCarton(bingo[carNew-1]);
                                System.out.println("Su cartón nuevo es: ");
                                System.out.println("Jugador"+(carNew));
                                Metodos.mostrarTablero(bingo[carNew-1]);
                            }else{
                                System.out.println("Lo sentimos pero no existe ese cartón");
                            }

                        }else{
                            System.out.println("Lo sentimos el Bingo ya ha comenzado y no se puede cambiar el Cartón.");
                        }

                        break;
                    case 3://Sacar Numero

                        do{
                            bola=(int)(Math.random()*89+1);
                        }while(Metodos.comprobarBombo(bola, sorteo)==false);
                            sorteo[cont]=bola;
                            cont++;
                        System.out.println("EL "+bola);
                        int i=0;
                        while(!ganar && i<numJugadores){

                            Metodos.comprobar(bola,bingo[i]);
                            System.out.println("Jugador"+(i+1));
                            Metodos.mostrarTablero(bingo[i]);
                            System.out.println("___________________________");
                            if (linea==false) {
                                if (Metodos.linea(bingo[i])) {
                                    System.out.println("El Jugador "+(i+1)+" ha cantado ¡¡¡LINEA!!!");
                                    linea=true;
                                 }  
                            }
                            if (Metodos.bingo(bingo[i])) {
                                System.out.println("El Jugador "+(i+1)+" ha cantado ¡¡¡BINGOOOOO!!!!");
                                ganar=true;
                                System.out.println("Desea volver a jugar? 1:SI, 2:NO");
                                int jugar=menu.nextInt();
                                switch (jugar) {
                                    case 1:
                                        linea=false;
                                        sorteo = new int[90];
                                        bingo = new int [numJugadores][3][9];
                                        for (int j = 0; j < numJugadores; j++) {
                                                Metodos.crearCarton(bingo[j]);
                                        }
                                        System.out.println("Este es tu nuevo cartón:");
                                        for (int k = 0; k < numJugadores; k++) {
                                            System.out.println("Jugador"+(k+1));
                                            Metodos.mostrarTablero(bingo[k]);
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Muchas gracias por jugar.\nHasta pronto.");
                                        opcion=5;
                                        break;
                                    default:
                                        System.out.println("No es una opción válida. Hasta pronto.");
                                        opcion=5;
                                        break;
                                    }
                            }
                            i++;
                        }
                        if (opcion!=5) {
                            ganar=false;
                        }
                        break;
                    case 4://Mostrar carton
                         System.out.println("¿Que cartón desea mostrar?");
                            int car=menu.nextInt();
                            if (car>0 && car<=numJugadores) {
                                System.out.println("Jugador"+(car));
                                Metodos.mostrarTablero(bingo[car-1]);
                            }else{
                                System.out.println("Lo sentimos pero no existe ese cartón");
                            }
                        break;
                    case 5:
                        System.out.println("Gracias por jugar.");
                        break;


                    default:
                        System.out.println("No es una opción válida del menú.");
                        break;

                    }

                   
            }catch(java.util.InputMismatchException e){//Esta excepción es en caso de meter un valor incorrecto en los Int, vuelve al menú
                System.out.println("ERROR: Valor no válido.");
                menu.nextLine();
            } 
        }while(opcion!=5);

        
    }
    
}

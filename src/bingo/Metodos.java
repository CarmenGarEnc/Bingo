/*

 */
package bingo;

/**
 *
 * @author Carmen
 */
public class Metodos {
    
    /**
     * Metodo que valida que no hay tres números en una columna
     * @param Tablero
     * @return booleano valido 
     */
    public static boolean validarCarton(int Tablero[][]){
        boolean valido=true;
        int cont;
        for (int i = 0; i < Tablero[0].length; i++){
            cont=0;
            for (int j = 0; j < Tablero.length; j++){ 
                if (Tablero[j][i]!=99) {
                    cont++;
                }//Fin del if
            }//Fin del for
            if(cont==3){
		valido=false;
            }
        }//Fin del for
        return valido;
    }
    
    /**
     * Método para crear huecos de forma aleatoria en el tablero, tiene que haber 4 huecos por fila
     * @param Tablero 
     */
    public static void crearHuecos(int Tablero[][]){
       int n;
       do{
            for (int i = 0; i < Tablero.length; i++) {
               for (int j = 0; j < Tablero[i].length; j++) {
                   Tablero[i][j]=0;
               }
            }
            for (int i = 0; i < Tablero.length; i++){
             int cont=0;
             while (cont < 4) {
                n=(int)(Math.random()*9);
                    if (i!=Tablero.length-1 || Tablero[0][n]!=99|| Tablero[1][n]!=99) {
                        if (Tablero[i][n]!=99){ 
                            Tablero[i][n]=99;
                            cont++;
                        
                        }//Fin del if 
                    }//Fin del if
            }//Fin del While
        }//Fin del for

       }while(!validarCarton(Tablero));
    }//Fin del método
    
    /**
     * Metodo para comprobar si existe ese valor
     * @param n
     * @param Tablero
     * @return Valido booleando que indica si el valor es válido o ya existe
     */
    public static boolean comparar(int n, int Tablero[][]){
        boolean valido=true;
        for (int i = 0; i < Tablero.length; i++){
            for (int j = 0; j < Tablero[i].length; j++){ 
                if (Tablero[i][j]==n) {
                    valido=false;
                }//Fin del if
            }//Fin del for
        }//Fin del for
        return valido;
    }
    
    
    /**
     * Método que coloca los números del carton de forma ascendente
     * @param Carton
     * @return Carton
     */
    public static int[][] colocarCarton(int[][] Carton){
        int mayor;
        int menor;
        
        for (int i = 0; i < Carton[0].length; i++){
            if (Carton[0][i]!=99 && Carton[1][i]!=99 ){
                if (Carton[0][i]>Carton[1][i]) {
                    mayor= Carton[0][i]; 
                    menor=Carton[1][i];
                    Carton[0][i]=menor; 
                    Carton[1][i]=mayor;
                }
            }else if(Carton[0][i]!=99 && Carton[2][i]!=99){
                if (Carton[0][i]>Carton[2][i]) {
                    mayor= Carton[0][i]; 
                    menor=Carton[2][i];
                    Carton[0][i]=menor; 
                    Carton[2][i]=mayor;
                }
            }else if(Carton[1][i]!=99 && Carton[2][i]!=99){
                if (Carton[1][i]>Carton[2][i]) {
                    mayor= Carton[1][i]; 
                    menor=Carton[2][i];
                    Carton[1][i]=menor; 
                    Carton[2][i]=mayor;
                }
            }
        }
        return Carton;
    }
    
    
    /**
     * Método que crea un Metodos con números aleatorios
     * @param Carton
     * @return Metodos array relleno
     */
    public static int[][] crearCarton(int[][] Carton){
        crearHuecos(Carton);
        int num;
        for (int i = 0; i < Carton.length; i++){
            for (int j = 0; j < Carton[i].length; j++) {
                if (Carton[i][j]!=99){
                    do{
                        num=(int)((Math.random()*9)+1+(10*j));
                    }while(comparar(num, Carton)==false);
                    Carton[i][j]=num;     
                } //Fin if      
            } //Fin for    
        }//Fin for
        colocarCarton(Carton);
        return Carton;
    }//Fin metodo
    
    
    /**
     * Método que comprueba si hay linea
     * @param Tablero
     * @return 
     */ 
    public static boolean linea(int Tablero[][]){
        boolean linea=false;
        int cont=0;
        for (int i = 0; i < Tablero.length; i++) {
            for (int j = 0; j < Tablero[i].length; j++) {
                if (Tablero[i][j]==95) {
                    cont++;
                }
            }
            if (cont==5) {
                linea=true;
                break;
            }
            cont=0;
        }
        return linea;
    } 
        
    /**
     * Médoto que comprueba si el carton tiene bingo
     * @param Tablero
     * @return 
     */
    public static boolean bingo(int Tablero[][]){
        boolean bingo=false;
        int cont=0;
        for (int i = 0; i < Tablero.length; i++) {
            for (int j = 0; j < Tablero[i].length; j++) {
                if (Tablero[i][j]==95) {
                    cont++;
                }
            }
        }
        if (cont==15) {
            bingo=true;
        }
        return bingo;
    }  
    /**
     * Método que compara si un número está en el carton cambiandolo de valor
     * @param n
     * @param Carton
     * @return 
     */
    public static int[][] comprobar(int n, int Carton[][]){
        for (int i = 0; i < Carton.length; i++) {
            for (int j = 0; j < Carton[i].length; j++) {
                if (Carton[i][j]==n) {
                    Carton[i][j]=95;
                }
            }
        }
        return Carton;  
    }
    
    /**
     * Método que imprime el carton
     * @param Tablero 
     */
    public static void mostrarTablero(int[][] Tablero) {
        String numero;
        for (int[] Tablero1 : Tablero) {
            for (int j = 0; j < Tablero1.length; j++) {
                if (Tablero1[j] == 99){
                    System.out.print("[] ");
                } else if(Tablero1[j] == 95){
                    System.out.print("XX "); 
                }else{
                    numero = String.format("%02d", Tablero1[j]);
                    System.out.print(numero + " ");  
                }
            }
            System.out.println();
        }
    }
        /**
     * Método que comprueba si el número ya ha salido anteriormente
     * @param n
     * @param Sorteo
     * @return valido booleano que dice si el numero ya ha salido
     */
    public static boolean comprobarBombo(int n, int Sorteo[]){
        boolean valido= true;
        for (int i = 0; i < Sorteo.length; i++) {
                if (Sorteo[i]==n) {
                    valido=false;
                }
        }
        return valido;  
    }  
}

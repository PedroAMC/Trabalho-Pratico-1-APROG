import java.util.Scanner;

public class Trabalho {
    static Scanner ler = new Scanner(System.in);
    public static void main(String[] args) {

        String nomeDoTerreno = ler.nextLine();
        int[][] matrizTerreno = lerValoresDoTerreno(); // alinea a)
        escreverValoresDoTerreno(matrizTerreno); // alinea b)
        int[][] matrizTerrenoSubidaPor1 = subirNivelDaAguaDoTerreno(matrizTerreno);
        escreverValoresDoTerreno(matrizTerrenoSubidaPor1);



    }

    public static int[][] lerValoresDoTerreno(){
        int numeroDeLinhas, numeroDeColunas;
        do {
            numeroDeLinhas = ler.nextInt();
            numeroDeColunas = ler.nextInt();

        } while (numeroDeColunas <= 0 && numeroDeLinhas <= 0); // verificar que o numero de linhas e colunas são positivos

        int[][] matrizTerreno = new int[numeroDeLinhas][numeroDeColunas];
        for (int i = 0; i < matrizTerreno.length; i++){
            for (int j = 0; j < matrizTerreno[i].length; j++){
                matrizTerreno[i][j] = ler.nextInt(); // lê os valores de cada linha da matriz
            }
        }

        return matrizTerreno;
    }

    public static void escreverValoresDoTerreno(int[][] matrizTerreno){
        String linhaDeValores = "";
        for (int i = 0; i < matrizTerreno.length; i++) {
            linhaDeValores = "";
            for (int j = 0; j < matrizTerreno[i].length; j++) {
                linhaDeValores = linhaDeValores + " " + matrizTerreno[i][j];

            }
            linhaDeValores = linhaDeValores.trim();
            System.out.println(linhaDeValores);
        }
    }

    public static int[][] subirNivelDaAguaDoTerreno(int[][] matrizTerreno){
        for (int i = 0; i < matrizTerreno.length; i++){
            for (int j = 0; j < matrizTerreno[i].length; j++){
                matrizTerreno[i][j] = matrizTerreno[i][j] + 1; // acrescenta 1 a todos os valores da matriz do terreno
            }
        }

        return matrizTerreno;
    }
}

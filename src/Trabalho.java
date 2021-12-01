import java.util.Scanner;

public class Trabalho {
    static Scanner ler = new Scanner(System.in);
    public static void main(String[] args) {

        String nomeDoTerreno = ler.nextLine(); // alinea a)
        int[][] matrizTerreno = lerValoresDoTerreno(); // alinea a)
        escreverValoresDoTerreno(matrizTerreno); // alinea b)
        int[][] matrizTerrenoSubidaPor1 = subirNivelDaAguaDoTerreno(matrizTerreno); //alinea c)
        escreverValoresDoTerreno(matrizTerrenoSubidaPor1); // alinea c)
        double percentagemSubmersaDoTerreno = percentagemSubmersaDoTerreno(matrizTerreno); // alinea d)
        System.out.printf("area submersa: %.2f%%\n", percentagemSubmersaDoTerreno); // alinea d)
        int variacaoDaAreaInundada = variacaoDaAguaInundada(matrizTerreno, matrizTerrenoSubidaPor1); // alinea e)
        System.out.println("variacao da arena inundada: " + variacaoDaAreaInundada + " m2"); // alinea e)





    }

    public static int[][] lerValoresDoTerreno(){ // alinea a)
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

    public static void escreverValoresDoTerreno(int[][] matrizTerreno){ // alinea b)
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

    public static int[][] subirNivelDaAguaDoTerreno(int[][] matrizTerreno){ // alinea c)
        final int X = 1;
        for (int i = 0; i < matrizTerreno.length; i++){
            for (int j = 0; j < matrizTerreno[i].length; j++){
                matrizTerreno[i][j] = matrizTerreno[i][j] + X; // acrescenta 1 a todos os valores da matriz do terreno
            }
        }

        return matrizTerreno;
    }

    public static double percentagemSubmersaDoTerreno(int[][] matrizTerreno){ // alinea d)
        double percentagemSubmersa;
        int quantidadeDeValores = 0, quantidadeDeValoresSubmersos = 0;
        for (int i = 0; i < matrizTerreno.length; i++){
            for (int j = 0; j < matrizTerreno[i].length; j++){
                quantidadeDeValores++;
               if (matrizTerreno[i][j] < 0){ // se estiver submerso
                    quantidadeDeValoresSubmersos++;
               }
            }
        }

        percentagemSubmersa = (double) quantidadeDeValoresSubmersos / quantidadeDeValores * 100;
        return percentagemSubmersa;
    }

    public static int variacaoDaAguaInundada(int[][] matrizTerreno, int[][] matrizTerrenoDepoisAlteracao){ // alinea e)
        int variacao, qtDeAreaInundadaOriginal = 0, qtDeArenaInundadaResultante = 0;
        for (int i = 0; i < matrizTerreno.length; i++){
            for (int j = 0; j < matrizTerreno[i].length; j++){
                if (matrizTerreno[i][j] < 0){ // se estiver submerso
                    qtDeAreaInundadaOriginal++;
                }
            }
        }
        for (int i = 0; i < matrizTerrenoDepoisAlteracao.length; i++){
            for (int j = 0; j < matrizTerreno[i].length; j++){
                if (matrizTerrenoDepoisAlteracao[i][j] < 0){ // se estiver submerso
                    qtDeArenaInundadaResultante++;
                }
            }
        }

        variacao = (qtDeAreaInundadaOriginal - qtDeArenaInundadaResultante) * -1;

        return variacao;
    }
}

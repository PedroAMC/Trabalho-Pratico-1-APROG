import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Trabalho {
    static final int ladoCubo = 3;
    static final int nivelDeAguaParaSubir = 1;
    static Scanner ler = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {


        int[][] matrizTerreno = armazenar(); // alinea a)

        System.out.println("b)"); // alinea b)
        escreverValoresDoTerreno(matrizTerreno); // alinea b)

        System.out.println("c)"); // alinea c)
        subirNivelDaAguaDoTerreno(matrizTerreno, nivelDeAguaParaSubir); // alinea c)
        escreverValoresDoTerreno(matrizTerreno); // alinea c)

        System.out.println("d)"); // alinea d)
        double percentagemSubmersaDoTerreno = percentagemSubmersaDoTerreno(matrizTerreno); // alinea d)
        System.out.printf("area submersa: %.2f%%\n", percentagemSubmersaDoTerreno); // alinea d)

        System.out.println("e)"); // alinea e)
        int variacaoDaAreaInundada = variacaoDaAguaInundada(matrizTerreno, nivelDeAguaParaSubir); // alinea e)
        System.out.println("variacao da arena inundada: " + variacaoDaAreaInundada + " m2"); // alinea e)

        System.out.println("f)"); // alinea f)
        int volumeAguaTerreno = calcularVolumeAguaTerreno(matrizTerreno); // alinea f)
        System.out.println("volume de agua: " + volumeAguaTerreno + " m3"); // alinea f)

        System.out.println("g)"); // alinea g)
        int aguaNecessariaParaInundacaoTotal = calcularAguaNecessariaParaInundacaoTotal(matrizTerreno); // alinea g)
        System.out.println("para inundacao total, subir :" + aguaNecessariaParaInundacaoTotal + " m"); // alinea g)

        System.out.println("h)"); // alinea h)
        visualizarSubidaDeAgua(matrizTerreno); // alinea h)

        System.out.println("i)"); // alinea i)
        calcularCoordenadasIdeaisParaCubo(matrizTerreno , ladoCubo); // alinea i)

        System.out.println("j)"); // alinea j)
        encontrarCaminhoVerticalSeco(matrizTerreno); // alinea j)


    }

    public static int[][] armazenar() throws FileNotFoundException { // alinea a)
        int numLinhas, numColunas;
        File file = new File("ficheiro1");
        Scanner sc = new Scanner(file);
        String nomeDoTerreno = sc.nextLine();
        numLinhas = sc.nextInt();
        numColunas = sc.nextInt();
        int[][] matriz = new int[numLinhas][numColunas];
        for (int i = 0; i < numLinhas; i++) {
            for (int j = 0; j < numColunas; j++) {
                matriz[i][j] = sc.nextInt();
            }
        }
        sc.close();

        return matriz;
    }


    public static void escreverValoresDoTerreno(int[][] matrizTerreno){ // alinea b)
        for (int i = 0; i < matrizTerreno.length; i++){
            for (int j = 0; j < matrizTerreno[i].length; j++){
                System.out.printf("%3d" , matrizTerreno[i][j]);
            }
            System.out.println();
        }
    }


    public static void subirNivelDaAguaDoTerreno(int[][] matrizTerreno, int valorParaSubir){ // alinea c)
        for (int i = 0; i < matrizTerreno.length; i++){
            for (int j = 0; j < matrizTerreno[i].length; j++){
                matrizTerreno[i][j] = matrizTerreno[i][j] + valorParaSubir; // acrescenta o valor a todos os valores da matriz do terreno
            }
        }
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

    public static int variacaoDaAguaInundada(int[][] matrizTerreno, int nivelDaAguaSubido){ // alinea e)
        int variacao = 0;

        for (int i = 0; i < matrizTerreno.length; i++){
            for (int j = 0; j < matrizTerreno[i].length; j++){
                if (matrizTerreno[i][j] >= 0 && matrizTerreno[i][j] - nivelDaAguaSubido < 0){ // verificar os valores não submersos, se estavam submersos antes da subida da água
                    variacao--;
                }
            }
        }

        return variacao;
    }

    public static int calcularVolumeAguaTerreno(int[][] matrizTerreno){ // alinea f)
        int volumeAgua = 0;
        for (int i = 0; i < matrizTerreno.length; i++){
            for (int j = 0; j < matrizTerreno[i].length; j++){
                if (matrizTerreno[i][j] < 0){ // se estiver submerso
                    volumeAgua = volumeAgua + (matrizTerreno[i][j] * -1);
                }
            }
        }
        return volumeAgua;
    }

    public static int calcularAguaNecessariaParaInundacaoTotal(int[][] matrizTerreno){ // alinea g)
        int terrenoMaisElevado = 0, aguaNecessariaParaInundacaoTotal;
        for (int i = 0; i < matrizTerreno.length; i++){
            for (int j = 0; j < matrizTerreno[i].length; j++){
                if (matrizTerreno[i][j] > terrenoMaisElevado){ // descobrir o valor do terreno com maior elevação
                    terrenoMaisElevado = matrizTerreno[i][j];
                }
            }
        }
        aguaNecessariaParaInundacaoTotal = terrenoMaisElevado + 1; // agua necessaria para inundação total vai ser o valor do terreno mais elevado + 1

        return  aguaNecessariaParaInundacaoTotal;
    }

    public static void visualizarSubidaDeAgua(int[][] matrizTerreno){ // alinea h)
        int terrenoMaisElevado = 0, aguaNecessariaParaInundacaoTotal, qtTerrenosInundados = 0;
        int[][] matrizTerrenoPosterior = new int[matrizTerreno.length][matrizTerreno[0].length]; // inicializar nova matrix com o mesmo tamanho que a matriz original
        for (int i = 0; i < matrizTerreno.length; i++){
            for (int j = 0; j < matrizTerreno[i].length; j++){
                matrizTerrenoPosterior[i][j] = matrizTerreno[i][j]; // copiar os valores da matriz original para a nova matriz
            }
        }
        System.out.println("subida da agua (m) | area inundada (m2)");
        System.out.println("------------------ | ------------------");
        for (int i = 0; i < matrizTerrenoPosterior.length; i++){
            for (int j = 0; j < matrizTerrenoPosterior[i].length; j++){
                if (matrizTerrenoPosterior[i][j] > terrenoMaisElevado){ // descobrir o valor do terreno com maior elevação
                    terrenoMaisElevado = matrizTerrenoPosterior[i][j];
                }
            }
        }
        aguaNecessariaParaInundacaoTotal = terrenoMaisElevado + 1; // agua necessaria para inundação total vai ser o valor do terreno mais elevado + 1

        for (int k = 1; k <= aguaNecessariaParaInundacaoTotal; k++){
            for (int i = 0; i < matrizTerrenoPosterior.length; i++){
                for (int j = 0; j < matrizTerrenoPosterior[i].length; j++){
                    if (matrizTerrenoPosterior[i][j] == 0){ // descobrir quantidade de terrenos que vão ser inundados
                        qtTerrenosInundados++;
                    }
                    matrizTerrenoPosterior[i][j] = matrizTerrenoPosterior[i][j] - 1; // subir o nivel da agua por 1 metro
                }
            }
            System.out.println("                 " + k + " |                  " + qtTerrenosInundados);
            qtTerrenosInundados = 0;
        }
    }

    public static void calcularCoordenadasIdeaisParaCubo(int[][] matrizTerreno, int ladoCubo){ // alinea i)
            int menorTerraMobilizada = 500, terraMobilizada = 0, terraMobilizadaSingular, iIdeal = 0, jIdeal = 0;
            for (int i = 0; i < matrizTerreno.length; i++){
                for (int j = 0; j < matrizTerreno[i].length; j++){
                    if (i+ladoCubo <= matrizTerreno.length && j+ladoCubo <= matrizTerreno[i].length){
                        for (int k = 0; k < ladoCubo; k++) {
                            for (int l = 0; l < ladoCubo; l++) {
                                terraMobilizadaSingular = matrizTerreno[i+k][j+l] + ladoCubo;
                                if (terraMobilizadaSingular < 0){
                                    terraMobilizadaSingular = terraMobilizadaSingular * -1;
                                }
                                terraMobilizada = terraMobilizada + terraMobilizadaSingular;
                            }
                        }
                    }
                    if (terraMobilizada < menorTerraMobilizada && terraMobilizada != 0){
                        menorTerraMobilizada = terraMobilizada;
                        iIdeal = i;
                        jIdeal = j;
                    }
                    terraMobilizada = 0;
                }

            }
        System.out.println("coordenadas do cubo: (" + iIdeal + "," + jIdeal + "),    terra a mobilizar: " + menorTerraMobilizada + " m2");

    }

    public static void encontrarCaminhoVerticalSeco(int[][] matrizTerreno){ // alinea j)
        int qtTerrenosSecos = 0, colunaComCaminhoSeco = -1;
        for (int i = 0; i < matrizTerreno[0].length; i++){
            for (int j = 0; j < matrizTerreno.length; j++){
                if (matrizTerreno[j][i] >= 0){ // analisar se o terreno é seco
                    qtTerrenosSecos++;
                }
            }
            if (qtTerrenosSecos == matrizTerreno.length){
                colunaComCaminhoSeco = i;
            }
            qtTerrenosSecos = 0;
        }
        if (colunaComCaminhoSeco == -1){
            System.out.println("não há caminho seco na vertical");
        } else {
            System.out.println("caminho seco na vertical na coluna (" + colunaComCaminhoSeco + ")");
        }
    }

}

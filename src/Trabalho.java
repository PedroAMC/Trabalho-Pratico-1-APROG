import java.util.Scanner;

public class Trabalho {
    static Scanner ler = new Scanner(System.in);
    public static void main(String[] args) {


        String nomeDoTerreno = ler.nextLine(); // alinea a)
        int[][] matrizTerreno = lerValoresDoTerreno(); // alinea a)

        System.out.println("b)"); // alinea b)
        escreverValoresDoTerreno(matrizTerreno); // alinea b)

        System.out.println("c)"); // alinea c)
        int[][] matrizTerrenoSubidaPorX = subirNivelDaAguaDoTerreno(matrizTerreno); //alinea c)
        escreverValoresDoTerreno(matrizTerrenoSubidaPorX); // alinea c)

        System.out.println("d)"); // alinea d)
        double percentagemSubmersaDoTerreno = percentagemSubmersaDoTerreno(matrizTerrenoSubidaPorX); // alinea d)
        System.out.printf("area submersa: %.2f%%\n", percentagemSubmersaDoTerreno); // alinea d)

        System.out.println("e)"); // alinea e)
        int variacaoDaAreaInundada = variacaoDaAguaInundada(matrizTerreno, matrizTerrenoSubidaPorX); // alinea e)
        System.out.println("variacao da arena inundada: " + variacaoDaAreaInundada + " m2"); // alinea e)

        System.out.println("f)"); // alinea f)
        int volumeAguaTerreno = calcularVolumeAguaTerreno(matrizTerrenoSubidaPorX); // alinea f)
        System.out.println("volume de agua: " + volumeAguaTerreno + " m3"); // alinea f)

        System.out.println("g)"); // alinea g)
        int aguaNecessariaParaInundacaoTotal = calcularAguaNecessariaParaInundacaoTotal(matrizTerrenoSubidaPorX); // alinea g)
        System.out.println("para inundacao total, subir :" + aguaNecessariaParaInundacaoTotal + " m"); // alinea g)

        System.out.println("h)"); // alinea h)
        visualizarSubidaDeAgua(matrizTerrenoSubidaPorX); // alinea h)

        System.out.println("i)"); // alinea i)


        System.out.println("j)"); // alinea j)
        encontrarCaminhoVerticalSeco(matrizTerrenoSubidaPorX); // alinea j)






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
        int[][] matrizTerrenoPosterior = new int[matrizTerreno.length][matrizTerreno[0].length]; // inicializar nova matrix com o mesmo tamanho que a matriz original
        for (int i = 0; i < matrizTerreno.length; i++){
            for (int j = 0; j < matrizTerreno[i].length; j++){
                matrizTerrenoPosterior[i][j] = matrizTerreno[i][j]; // copiar os valores da matriz original para a nova matriz
            }
        }
        for (int i = 0; i < matrizTerrenoPosterior.length; i++){
            for (int j = 0; j < matrizTerrenoPosterior[i].length; j++){
                matrizTerrenoPosterior[i][j] = matrizTerrenoPosterior[i][j] + X; // acrescenta X a todos os valores da matriz do terreno
            }
        }

        return matrizTerrenoPosterior;
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
            for (int j = 0; j < matrizTerrenoDepoisAlteracao[i].length; j++){
                if (matrizTerrenoDepoisAlteracao[i][j] < 0){ // se estiver submerso
                    qtDeArenaInundadaResultante++;
                }
            }
        }

        variacao = (qtDeAreaInundadaOriginal - qtDeArenaInundadaResultante) * -1;

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

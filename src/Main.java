import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) throws IOException {
        //Criação do arquivo de resultados
        File arquivo = new File("resultado.txt");

        if (!arquivo.exists()){
            arquivo.createNewFile();
        }

        FileWriter resultado = new FileWriter(arquivo);

        LerArquivo lerArquivo = new LerArquivo();
        String[] linhas = lerArquivo.ler();

        Consumo[] consumos = new Consumo[linhas.length-1];

        //lê arquivo e armazena os valores
        for (int i = 0; i < linhas.length-1; i++) {
            String[] campos = linhas[i].split(",");
            consumos[i] = new Consumo(
                    campos[0],
                    campos[1],
                    Integer.parseInt(campos[2])
            );
        }

        //consumo total por subestacao
        ConsumoTotal consumoTotal = new ConsumoTotal();
        resultado.write("O total geral de consumo foi:\n" + String.format(consumoTotal.calcularConsumoTotal(consumos) + "\n"));
        resultado.write("\n");

        //lista de consumo por mês
        ConsumosDoMes consumosMes = new ConsumosDoMes();
        int[] consumosDoMes = consumosMes.calcularConsumosPorMes(consumos);

        String[] meses = {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        //matriz de consumos
        String[] subestacoes = {"Planalto", "Litoral", "Progresso", "Aurora", "Horizonte"};
        MatrizDeConsumos matrizDeConsumos = new MatrizDeConsumos();
        int[][] consumoSubMes = matrizDeConsumos.criarMatriz(consumos);

        resultado.write("Matriz de consumo das subestacoes \n");
        resultado.write(String.format("%-10s", "Mês"));

        for (int i = 0; i < meses.length; i++) {
            resultado.write(String.format("%-10s", meses[i]));
        }

        resultado.write("\n");
        for (int i = 0; i < consumoSubMes.length; i++) {
            resultado.write(String.format("%-10s", subestacoes[i]));
            for (int j = 0; j < consumoSubMes[i].length; j++) {
                resultado.write(String.format("%-10s", consumoSubMes[i][j]));
            }
            resultado.write("\n");
        }

        //media por subestacao
        Media mediaSubestacao = new Media();
        double[] media = mediaSubestacao.calcularMedia(matrizDeConsumos, consumos);

        DecimalFormat df = new DecimalFormat("0.00");
        resultado.write("\n");
        resultado.write("Média de consumo por subestação \n");
        for (int i = 0; i < media.length; i++) {
            resultado.write(subestacoes[i] + " " + df.format(media[i]) + "\n");
        }

        //subestacao com maior consumo mensal
        MaiorConsumo maiorConsumo = new MaiorConsumo();
        int[] indexMaior = maiorConsumo.calcularMaiorConsumo(matrizDeConsumos, consumos);

        resultado.write("\n");
        resultado.write("Subestação com maior consumo mensal \n");
        resultado.write(subestacoes[indexMaior[0]] + " " + consumoSubMes[indexMaior[0]][indexMaior[1]] + "\n");

        //subestacao com menor consumo mensal
        MenorConsumo menorConsumo = new MenorConsumo();
        int[] indexMenor = menorConsumo.calcularMenorConsumo(matrizDeConsumos, consumos, maiorConsumo, indexMaior);
        resultado.write("\n");
        resultado.write("Subestação com menor consumo mensal \n");
        resultado.write(subestacoes[indexMenor[0]] + " " + consumoSubMes[indexMenor[0]][indexMenor[1]] + "\n");


        //lista do consumo mensal ordenada
        ListaOrdenada listaOrdenada = new ListaOrdenada();
        int[] consumoOrdenado = listaOrdenada.ordenarLista(consumosMes, consumos);

        MesesOrdenados listaMeses = new MesesOrdenados();
        String[] mesesOrdenados = listaMeses.ordenarMeses(consumosMes,consumos);

        resultado.write("\n");
        resultado.write("Lista do consumo total mensal ordenada \n");
        for (int i = 0; i < consumosDoMes.length; i++) {
            resultado.write(mesesOrdenados[i] + " " + consumoOrdenado[i] + "\n");
        }

        resultado.close();

    }

}
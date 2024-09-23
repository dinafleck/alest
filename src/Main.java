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


        String[] meses = {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        int[] consumosDoMes = new int[12];
        for (Consumo consumo : consumos){
            for (int i = 0; i < meses.length; i++){
                if (consumo.getMes().equals(meses[i])){
                    consumosDoMes[i] += consumo.getConsumo();
                }
            }
        }


        //matriz de consumos
        String[] subestacoes = {"Planalto", "Litoral", "Progresso", "Aurora", "Horizonte"};
        int[][] consumosubmes = new int[5][12];

        for (Consumo consumo : consumos){
            for (int i = 0; i < consumosubmes.length; i++){
                for (int j = 0; j < consumosubmes[i].length; j++){
                    if (consumo.getMes().equals(meses[j]) && consumo.getSubestacao().equals(subestacoes[i])){
                        consumosubmes[i][j] += consumo.getConsumo();
                    }
                }
            }
        }

        //media por subestacao
        double[] media = new double[5];
        for(int i = 0; i < consumosubmes.length; i++){
            double soma = 0;
            for(int j = 0; j < consumosubmes[i].length; j++){
                soma += consumosubmes[i][j];
            }
            media[i] = soma / 12;
        }

        //subestacao com maior consumo mensal
        int maior_mes = 0;
        int maior_subestacao = 0;
        int menor_mes = 0;
        int menor_subestacao = 0;
        int aux_consumo = 0;

        for (int i = 0; i < consumosubmes.length; i++) {
            for (int j = 0; j < consumosubmes[i].length; j++) {
                if (consumosubmes[i][j] > aux_consumo){
                    aux_consumo = consumosubmes[i][j];
                    maior_mes = i;
                    maior_subestacao = j;
                }
            }
        }

        //subestacao com menor consumo mensal
        for (int i = 0; i < consumosubmes.length; i++) {
            for (int j = 0; j < consumosubmes[i].length; j++) {
                if (consumosubmes[i][j] < aux_consumo && consumosubmes[i][j] > 0){
                    aux_consumo = consumosubmes[i][j];
                    menor_mes = i;
                    menor_subestacao = j;
                }
            }
        }

        //lista do consumo mensal ordenada
        int aux;
        String[] mesesOrdenados = meses.clone();
        String aux_meses;
        for (int i = 0; i < consumosDoMes.length; i++) {
            for (int j = 0; j < consumosDoMes.length-i-1; j++) {
                if (consumosDoMes[j] < consumosDoMes[j+1]){
                    aux = consumosDoMes[j];
                    consumosDoMes[j] = consumosDoMes[j+1];
                    consumosDoMes[j+1] = aux;
                    aux_meses = mesesOrdenados[j];
                    mesesOrdenados[j] = mesesOrdenados[j+1];
                    mesesOrdenados[j+1] = aux_meses;
                }
            }
        }



        //Impressão dos resultados no arquivo


        //Total geral de consumo

        resultado.write("Matriz de consumo das subestacoes \n");
        resultado.write(String.format("%-10s", "Mês"));

        for (int i = 0; i < meses.length; i++) {
            resultado.write(String.format("%-10s", meses[i]));
        }

        resultado.write("\n");
        for (int i = 0; i < consumosubmes.length; i++) {
            resultado.write(String.format("%-10s", subestacoes[i]));
            for (int j = 0; j < consumosubmes[i].length; j++) {
                resultado.write(String.format("%-10s", consumosubmes[i][j]));
            }
            resultado.write("\n");
        }

        DecimalFormat df = new DecimalFormat("0.00");
        resultado.write("\n");
        resultado.write("Média de consumo por subestação \n");
        for (int i = 0; i < media.length; i++) {
            resultado.write(subestacoes[i] + " " + df.format(media[i]) + "\n");
        }

        resultado.write("\n");
        resultado.write("Subestação com maior consumo mensal \n");
        resultado.write(subestacoes[maior_mes] + " " + consumosubmes[maior_mes][maior_subestacao] + "\n");

        resultado.write("\n");
        resultado.write("Subestação com menor consumo mensal \n");
        resultado.write(subestacoes[menor_mes] + " " + consumosubmes[menor_mes][menor_subestacao] + "\n");

        resultado.write("\n");
        resultado.write("Lista do consumo total mensal ordenada \n");
        for (int i = 0; i < consumosDoMes.length; i++) {
            resultado.write(mesesOrdenados[i] + " " + consumosDoMes[i] + "\n");
        }

        resultado.close();

    }

}
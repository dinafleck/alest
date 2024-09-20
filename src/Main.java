import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
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
        int ConsumoTotal = 0;
        for (Consumo consumo : consumos) {
            ConsumoTotal += consumo.getConsumo();
        }

        System.out.println(" ");
        System.out.println("O total geral de consumo foi:");
        System.out.println(ConsumoTotal);
        System.out.println(" ");


        //lista de consumos por mês
        String[] meses = {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        int[] consumosdomes = new int[12];
        for (Consumo consumo : consumos){
            for (int i = 0; i < meses.length; i++){
                if (consumo.getMes().equals(meses[i])){
                    consumosdomes[i] += consumo.getConsumo();
                }
            }
        }

        System.out.println("Lista de consumo por mês");
        for (int i = 0; i < 12; i++){
            System.out.println(meses[i] + " " + consumosdomes[i]);
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

        System.out.println(" ");
        System.out.println("Matriz de consumo das subestacoes");
        System.out.printf("%-" + 15 + "s", "Mês");
        for (int i = 0; i < meses.length; i++){
            System.out.printf("%-" + 10 + "s", meses[i]);
        }

        for (int i = 0; i < consumosubmes.length; i++) {
            System.out.println(" ");
            System.out.printf("%-" + 15 + "s", subestacoes[i]);
            for (int j = 0; j < consumosubmes[i].length; j++) {
                System.out.printf("%-" + 10 + "d", consumosubmes[i][j]);
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

        DecimalFormat df = new DecimalFormat("#.00");

        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Média de consumo por subestação");
        for (int i = 0; i < media.length; i++) {
            System.out.println(subestacoes[i] + " " + df.format(media[i]));
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

        System.out.println(" ");
        System.out.println("Subestação com maior consumo mensal");
        System.out.println(subestacoes[maior_mes] + " " + consumosubmes[maior_mes][maior_subestacao]);

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

        System.out.println(" ");
        System.out.println("Subestação com menor consumo mensal");
        System.out.println(subestacoes[menor_mes] + " " + consumosubmes[menor_mes][menor_subestacao]);


        //lista do consumo mensal ordenada
        int aux = 0;
        String[] meses_ordenados = meses;
        String aux_meses = "";
        for (int i = 0; i < consumosdomes.length; i++) {
            for (int j = 0; j < consumosdomes.length-i-1; j++) {
                if (consumosdomes[j] < consumosdomes[j+1]){
                    aux = consumosdomes[j];
                    consumosdomes[j] = consumosdomes[j+1];
                    consumosdomes[j+1] = aux;
                    aux_meses = meses_ordenados[j];
                    meses_ordenados[j] = meses_ordenados[j+1];
                    meses_ordenados[j+1] = aux_meses;
                }
            }
        }
        System.out.println(" ");
        for (int i = 0; i < consumosdomes.length; i++) {
            System.out.println(meses_ordenados[i] + " " + consumosdomes[i]);
        }
    }

    public class ExportarResultados{
        public static void main(String[] args){
            try {
                FileWriter resultados = new FileWriter("Resultados.txt");
                resultados.write("Teste");
                resultados.close();

            } catch (IOException e) {
                throw new RuntimeException(e){};
            }
        }
    }
}
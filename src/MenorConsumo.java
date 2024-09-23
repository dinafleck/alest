public class MenorConsumo {
    public int[] calcularMenorConsumo(MatrizDeConsumos matriz, Consumo[] consumos, MaiorConsumo maior, int[] index) {
        int menor_mes = 0;
        int menor_subestacao = 0;

        MaiorConsumo maior_consumo = new MaiorConsumo();

        MatrizDeConsumos matriz_consumo = new MatrizDeConsumos();
        int[][] consumoSubMes = matriz_consumo.criarMatriz(consumos);
        int aux_consumo = consumoSubMes[index[0]][index[1]];


        for (int i = 0; i < consumoSubMes.length; i++) {
            for (int j = 0; j < consumoSubMes[i].length; j++) {
                if (consumoSubMes[i][j] < aux_consumo && consumoSubMes[i][j] > 0){
                    aux_consumo = consumoSubMes[i][j];
                    menor_mes = i;
                    menor_subestacao = j;
                }
            }
        }
        return new int[]{menor_mes, menor_subestacao};
    }
}

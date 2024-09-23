public class MaiorConsumo {
    public int[] calcularMaiorConsumo(MatrizDeConsumos matriz, Consumo[] consumos) {
        int maior_mes = 0;
        int maior_subestacao = 0;
        int aux_consumo = 0;

        MatrizDeConsumos matriz_consumo = new MatrizDeConsumos();
        int[][] consumoSubMes = matriz_consumo.criarMatriz(consumos);

        for (int i = 0; i < consumoSubMes.length; i++) {
            for (int j = 0; j < consumoSubMes[i].length; j++) {
                if (consumoSubMes[i][j] > aux_consumo){
                    aux_consumo = consumoSubMes[i][j];
                    maior_mes = i;
                    maior_subestacao = j;
                }
            }
        }
        return new int[]{maior_mes, maior_subestacao};
    }
}

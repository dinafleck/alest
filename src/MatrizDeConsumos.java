public class MatrizDeConsumos {
    public int[][] criarMatriz(Consumo[] consumos) {
        int[][] consumoSubMes = new int[5][12];
        String[] subestacoes = {"Planalto", "Litoral", "Progresso", "Aurora", "Horizonte"};
        String[] meses = {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        for (Consumo consumo : consumos){
            for (int i = 0; i < consumoSubMes.length; i++){
                for (int j = 0; j < consumoSubMes[i].length; j++){
                    if (consumo.getMes().equals(meses[j]) && consumo.getSubestacao().equals(subestacoes[i])){
                        consumoSubMes[i][j] += consumo.getConsumo();
                    }
                }
            }
        }
        return consumoSubMes;
    }

}

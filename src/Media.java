public class Media {
    public double[] calcularMedia(MatrizDeConsumos matriz, Consumo[] consumos) {
        double[] media = new double[5];
        for(int i = 0; i < matriz.criarMatriz(consumos).length; i++){
            double soma = 0;
            for(int j = 0; j < matriz.criarMatriz(consumos)[i].length; j++){
                soma += matriz.criarMatriz(consumos)[i][j];
            }
            media[i] = soma / 12;
        }
        return media;
    }
}

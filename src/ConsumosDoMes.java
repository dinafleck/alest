public class ConsumosDoMes {
    public int[] calcularConsumosPorMes(Consumo[] consumos) {
        String[] meses = {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        int[] consumosDoMes = new int[12];
        for (Consumo consumo : consumos){
            for (int i = 0; i < meses.length; i++){
                if (consumo.getMes().equals(meses[i])){
                    consumosDoMes[i] += consumo.getConsumo();
                }
            }
        }
        return consumosDoMes;
    }
}

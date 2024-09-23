public class MesesOrdenados {
    public String[] ordenarMeses(ConsumosDoMes lista, Consumo[] consumos){
        int aux;
        String[] mesesOrdenados = {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String aux_meses;

        ConsumosDoMes listaOriginal = new ConsumosDoMes();
        int[] consumosDoMes = listaOriginal.calcularConsumosPorMes(consumos);

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
        return mesesOrdenados;
    }
}

public class ListaOrdenada {
    public int[] ordenarLista(ConsumosDoMes lista, Consumo[] consumos){
        int aux;
        ConsumosDoMes listaOriginal = new ConsumosDoMes();
        int[] consumosDoMes = listaOriginal.calcularConsumosPorMes(consumos);

        for (int i = 0; i < consumosDoMes.length; i++) {
            for (int j = 0; j < consumosDoMes.length-i-1; j++) {
                if (consumosDoMes[j] < consumosDoMes[j+1]){
                    aux = consumosDoMes[j];
                    consumosDoMes[j] = consumosDoMes[j+1];
                    consumosDoMes[j+1] = aux;
                }
            }
        }
        return consumosDoMes;
    }
}

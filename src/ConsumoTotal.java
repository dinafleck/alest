public class ConsumoTotal {
    public int calcularConsumoTotal(Consumo[] consumos) {
        int ConsumoTotal = 0;
        for (Consumo consumo : consumos) {
            ConsumoTotal += consumo.getConsumo();
        }
        return ConsumoTotal;
    }
}

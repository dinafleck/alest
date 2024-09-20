public class Consumo{
    private String mes;
    private String subestacao;
    private int consumo;

    public Consumo(
            String mes,
            String subestacao,
            int consumo
    ){
        this.mes = mes;
        this.subestacao = subestacao;
        this.consumo = consumo;
    }

    public String getMes() {
        return mes;
    }

    public String getSubestacao(){
        return subestacao;
    }

    public int getConsumo(){
        return consumo;
    }

}
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscreverResultado {
    public static void main(String[] args) throws IOException {
        File arquivo = new File("C:\\Users\\Dinara\\Desktop\\t1 - alest1\\resultado.txt");

        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }

        FileWriter escrever = new FileWriter(arquivo);
        escrever.write("Resultado\n");
        escrever.close();

    }
}
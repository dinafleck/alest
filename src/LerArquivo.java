import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LerArquivo {
    public String[] ler() {
        String arquivoCSV = "C:\\Users\\Dinara\\Desktop\\t1 - alest1\\Casos de teste\\consumos_20.csv";
        String linha = "";

        int coluna = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            while ((linha = br.readLine()) != null){
                coluna++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[] linhas = new String[coluna];

        try(BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))){
            br.readLine();
            int i = 0;
            while ((linha = br.readLine()) != null){
                linhas[i] = linha;
                i++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return linhas;
    }
}

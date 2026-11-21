
import java.io.IOException;

public class ConfiguracaoJogo {

    private static ConfiguracaoJogo instancia;

    private ConfiguracaoJogo() {
        // A leitura sera adicionada depois.
    }

    public static synchronized ConfiguracaoJogo getInstancia()
            throws IOException {

        if (instancia == null) {
            instancia = new ConfiguracaoJogo();
        }

        return instancia;
    }
}

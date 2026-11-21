import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfiguracaoJogo {

    private static ConfiguracaoJogo instancia;

    private ConfiguracaoJogo() throws IOException {
        propriedades = new Properties();

        Path caminho = Path.of("config", "jogo.properties");

        try (var leitor = Files.newBufferedReader(
                caminho, StandardCharsets.UTF_8)) {

            propriedades.load(leitor);
        }
    }
    public static synchronized ConfiguracaoJogo getInstancia()
            throws IOException {

        if (instancia == null) {
            instancia = new ConfiguracaoJogo();
        }

        return instancia;
    }
}


public class Main {

    public static void main(String[] args) throws Exception {

        ConfiguracaoJogo config1 =
                ConfiguracaoJogo.getInstancia();

        ConfiguracaoJogo config2 =
                ConfiguracaoJogo.getInstancia();

        System.out.println("Mesma instancia? "
                + (config1 == config2));

        System.out.println("Idioma: "
                + config1.getLanguage());

        config1.imprimirConfiguracoes();
    }
}

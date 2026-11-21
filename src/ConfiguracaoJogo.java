import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfiguracaoJogo {

    private static ConfiguracaoJogo instancia;

    private String appId;
    private String userName;
    private String language;
    private String offline;
    private String autoDLC;
    private String buildId;

    private String dlcName;
    private String updateDB;
    private String signature;
    private String windowInfo;
    private String lvwindowInfo;
    private String applicationPath;
    private String workingDirectory;
    private String waitForExit;
    private String noOperation;
    private final Properties propriedades;

    private ConfiguracaoJogo() throws IOException {
        propriedades = new Properties();

        Path caminho = Path.of("config", "jogo.properties");

        try (var leitor = Files.newBufferedReader(
                caminho, StandardCharsets.UTF_8)) {

            propriedades.load(leitor);

            appId = propriedades.getProperty("AppId");
            userName = propriedades.getProperty("UserName");
            language = propriedades.getProperty("Language");
            offline = propriedades.getProperty("Offline");
            autoDLC = propriedades.getProperty("AutoDLC");
            buildId = propriedades.getProperty("BuildId");
            dlcName = propriedades.getProperty("DLCName");
            updateDB = propriedades.getProperty("UpdateB");
            signature = propriedades.getProperty("Signature");
            windowInfo = propriedades.getProperty("WindowInfo");
            lvwindowInfo = propriedades.getProperty("LVWindowInfo");
            applicationPath = propriedades.getProperty("ApplicationPath");
            workingDirectory = propriedades.getProperty("WorkingDirectory");
            waitForExit = propriedades.getProperty("WaitForExit");
            noOperation = propriedades.getProperty("NoOperation");
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getLanguage() {
        return language;
    }

    public String getAppId() {
        return appId;
    }


    public String getOffline() {
        return offline;
    }

    public String getAutoDLC() {
        return autoDLC;
    }

    public String getBuildId() {
        return buildId;
    }

    public String getDlcName() {
        return dlcName;
    }

    public String getUpdateDB() {
        return updateDB;
    }

    public String getWindowInfo() {
        return windowInfo;
    }


    public String getSignature() {
        return signature;
    }

    public String getLvwindowInfo() {
        return lvwindowInfo;
    }

    public String getApplicationPath() {
        return applicationPath;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public String getWaitForExit() {
        return waitForExit;
    }

    public String getNoOperation() {
        return noOperation;
    }

    public static synchronized ConfiguracaoJogo getInstancia()
            throws IOException {

        if (instancia == null) {
            instancia = new ConfiguracaoJogo();
        }

        return instancia;
    }
    public void imprimirConfiguracoes() {
        System.out.println("AppId=" + appId);
        System.out.println("UserName=" + userName);
        System.out.println("Language=" + language);
        // Continue imprimindo as demais propriedades.

        System.out.println("Offline=" + offline);
        System.out.println("AutoDLC=" + autoDLC);
        System.out.println("BuildId=" + buildId);

        System.out.println("DLCName=" + dlcName);
        System.out.println("UpdateB=" + updateDB);
        System.out.println("Signature=" + signature);

        System.out.println("WindowInfo=" + windowInfo);
        System.out.println("LVWindowInfo=" + lvwindowInfo);
        System.out.println("ApplicationPath=" + applicationPath);

        System.out.println("WorkingDirectory=" + workingDirectory);
        System.out.println("WaitForExit=" + waitForExit);
        System.out.println("NoOperation=" + noOperation);
    }
}

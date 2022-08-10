package sed_keywords_maker;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Config {
    private  String host;
    private  String port;
    private  String database;
    private  String userName;
    private  String password;
    private  String tableName;
    private  boolean configLoaded = false;
    public  final String CONFIG_FILE_PATH = "app/src/main/resources/config.json";

    /**
     * This method load configuration from File
     */
     public void loadConfigFromFile(){
        Gson gson = new Gson();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(CONFIG_FILE_PATH));
            Config config = gson.fromJson(reader,Config.class);
            host = config.getHost();
            port = config.getPort();
            database = config.getDatabase();
            userName = config.getUserName();
            password = config.getPassword();
            tableName = config.getTableName();
            reader.close();
            setConfigLoaded(true);
        }catch(IOException e){
            e.printStackTrace();
            setConfigLoaded(false);
        }
    }

    /**
     * This method save configuration to File.
     */
    public void saveConfigToFile(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Config config = new Config();
            String json = gson.toJson(config);
            FileWriter fileWriter = new FileWriter(CONFIG_FILE_PATH);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showConfig(){
        System.out.println("[ Config ]");
        System.out.println(">Host: " + getHost());
        System.out.println(">Port: " + getPort());
        System.out.println(">Database: " + getDatabase());
        System.out.println(">UserName: " + getUserName());
        System.out.println(">Password: " + getPassword());
    }

    public String getHost() {
        return host;
    }

    private void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    private void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    private void setDatabase(String database) {
        this.database = database;
    }

    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public boolean isConfigLoaded() {
        return configLoaded;
    }

    private void setConfigLoaded(boolean configLoaded) {
        this.configLoaded = configLoaded;
    }

    public String getTableName() {
        return tableName;
    }
    private void setTableName(String tableName) {
        this.tableName = tableName;
    }
}

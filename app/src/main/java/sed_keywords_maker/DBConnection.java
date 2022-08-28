package sed_keywords_maker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is tool to create connection with database.
 */
public class DBConnection {
    private String DBURL; //   "jdbc:mysql://127.0.0.1:3306/szukamekipydo.pl?useUnicode=yes&characterEncoding=UTF8"
    private String DBUSER;
    private String DBPASS;
    public Connection connection;
    public Statement statement;
    public boolean DBConnected = false;

    /**
     * This method created connection with database if config is loaded.
     * @param isConfigLoaded Put in Config.isConfigLoaded(). If config is loaded it returns TRUE.
     */
    public void createConnectionWithDB(boolean isConfigLoaded) {
        Config config = new Config();
        config.loadConfigFromFile();

        if (isConfigLoaded) {
            DBURL = "jdbc:mysql://" + config.getHost() + "/" + config.getDatabase() + "?allowMultiQueries=true";
            DBUSER = config.getUserName();
            DBPASS = config.getPassword();
            try {
                connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
                setDBConnected(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method release resources needed to create connection with database.
     */
    public void releaseResource (){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Getters and Setters
     * @param DBConnected Fields of this class
     */
    private void setDBConnected(boolean DBConnected) {
        this.DBConnected = DBConnected;
    }
    public boolean isDBConnected() {
        return DBConnected;
    }
    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) { this.connection = connection; }
}

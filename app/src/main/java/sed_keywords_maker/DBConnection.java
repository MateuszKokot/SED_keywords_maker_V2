package sed_keywords_maker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private String DBURL; //   "jdbc:mysql://127.0.0.1:3306/szukamekipydo.pl?useUnicode=yes&characterEncoding=UTF8"
    private String DBUSER;
    private String DBPASS;
    public Connection connection;
    public Statement statement;
    public boolean DBConnected = false;

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

    private void setDBConnected(boolean DBConnected) {
        this.DBConnected = DBConnected;
    }
    public boolean isDBConnected() {
        return DBConnected;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


}

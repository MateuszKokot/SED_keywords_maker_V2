package sed_keywords_maker;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Kernel {
    public void doYourJob (Config config, DBConnection dbConnection, AllWordsContainer allWordsContainer){

        int ordinalNumberOfWord = 1;
        boolean hasLineToRead;

        String updateString = "INSERT INTO " + config.getTableName() + " VALUES (NULL, ?, ?, ?); ";

        if (dbConnection.isDBConnected() && allWordsContainer.isFileLoaded()){
            try {
                do {
                    hasLineToRead = allWordsContainer.hasLineToRead();

                    if (hasLineToRead){
                        for (Word w : allWordsContainer.getNextKeyWord()) {

                            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(updateString);
                            preparedStatement.setString(1,w.getWord());
                            preparedStatement.setString(2,w.getKeyWord());
                            preparedStatement.setString(3,"0");
                            preparedStatement.executeUpdate();
                            preparedStatement.close();
                            System.out.println(ordinalNumberOfWord + " - " + w.getWord() + " - " + w.getKeyWord());
                            ordinalNumberOfWord++;
                        }
                    }
                } while (hasLineToRead);
            } catch (SQLException e)   {
                e.printStackTrace();
                System.exit(0);
            }
        }

        dbConnection.releaseResource();
        allWordsContainer.releaseResource();

    }

}

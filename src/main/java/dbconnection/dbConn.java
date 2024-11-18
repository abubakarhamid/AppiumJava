package dbconnection;

import java.sql.*;

import static Config.configProperties.dbPassword;
import static Config.configProperties.dbUrl;
import static Config.configProperties.dbUserName;

public class dbConn {

    //DB Credentials
    String url = dbUrl;
    String username = dbUserName;
    String password = dbPassword;

    Connection connection = null;
    Statement statement = null;

    public void connectDb(String url, String username, String password) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver"); // load sql driver
        connection = DriverManager.getConnection(url, username, password); //DB connection
    }

    public void getVerificationCode() throws SQLException {

        String query =
                "select * from VJ_EXTERNAL_APP_CONFIG " +
                        "where key = 'DUMMY_VERIFICATION_CODE' ";

      try {

          connectDb(url, username, password);
          statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(query);

          while (resultSet.next()) {
              String Verif_Code = resultSet.getString("VALUE");
              System.out.println(Verif_Code);
          }

          resultSet.close();

      } catch (SQLException e) {
          System.out.println("Connection Failed!");
          e.printStackTrace();
      } catch (ClassNotFoundException e) {
          System.out.println("Connection Failed!");

          e.printStackTrace();
      } finally {
          statement.close();
          connection.close();
      }

  }
}

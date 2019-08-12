package pl.marekkalkowski;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private Connection connection;
    public static final Logger LOG = LogManager.getLogger(DatabaseConnection.class);

    public Connection getConnection() {
        return connection;
    }

    public void gettingConnectToDatabase(Properties properties) {

        try {
            String hostName = properties.getProperty("hostName").trim();
            String dbName = properties.getProperty("dbName").trim();
            String dbUser = properties.getProperty("dbUser").trim();
            String password = properties.getProperty("password").trim();

            LOG.info(" Przytotowywanie url dla połączenia z bazą.");
            String url = String.format("jdbc:sqlserver://%s:52313;database=%s;user=%s;password=%s;integratedSecurity=true;"
                    , hostName, dbName, dbUser, password);
            LOG.info("Url gotowy");
            LOG.debug("URl:{}", url);
            LOG.info("Łączenie za bazą danych  .... ");
            connection = DriverManager.getConnection(url);
            LOG.info(connection.getClientInfo());
            String schema = connection.getSchema();
            LOG.info("Połączenie z bazą nawiązane");
            LOG.info("Schema: {}", schema);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



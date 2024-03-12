package se.distansakademin.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RepositoryBase {

    private Connection conn; // Notera PRIVATE

    // Lägg till konsruktor och anslutning till databasen här...
    private static final String url = "jdbc:mysql://localhost:3308/furniture_store";
    private static final String user = "root";
    private static final String pass = "root";


    public RepositoryBase() throws SQLException {
        conn = DriverManager.getConnection(url, user, pass);
        createTables();
    }

    private void createTables() throws SQLException {
        var sql = "CREATE TABLE IF NOT EXISTS furnitures(" +
                "furniture_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "furniture_name VARCHAR(255) NOT NULL, " +
                "furniture_type VARCHAR(255) NOT NULL);"; // FULING: Ska vara egen tabell

        getConnection().createStatement().execute(sql);
    }

    // Denna metod används för att hämta anslutningen och kommer att mockas i testerna
    protected Connection getConnection(){
        return conn;
    }

}

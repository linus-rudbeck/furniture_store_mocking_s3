package se.distansakademin.repositories;

import se.distansakademin.models.Furniture;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FurnitureRepository extends RepositoryBase{
    public FurnitureRepository() throws SQLException {
        super();
    }

    public void insert(Furniture furniture) throws SQLException{

        // FULING: dela upp på fler rader
        var pstmt = getConnection().prepareStatement("INSERT INTO furnitures (furniture_name, furniture_type)" +
                " VALUES (?,?);");

        pstmt.setString(1, furniture.getName());
        pstmt.setString(2, furniture.getType());

        // FULING: Använd returvärdet och returnera något
        pstmt.executeUpdate();
    }

    public List<Furniture> getAll() throws SQLException {
        var furnitures = new ArrayList<Furniture>();
        var stmt = getConnection().createStatement();
        var resultSet = stmt.executeQuery("SELECT furniture_id, furniture_name, furniture_type FROM furnitures;");
        while (resultSet.next()){
            var furniture = new Furniture();

            furniture.setId(resultSet.getInt("furniture_id"));
            furniture.setName(resultSet.getString("furniture_name"));
            furniture.setType(resultSet.getString("furniture_type"));

            furnitures.add(furniture);
        }
        return furnitures;
    }

}

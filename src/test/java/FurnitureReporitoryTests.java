import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.distansakademin.models.Furniture;
import se.distansakademin.repositories.FurnitureRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class FurnitureReporitoryTests {

    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;

    private FurnitureRepository furnitureRepository;


    @BeforeEach
    public void setUp() throws SQLException {

        // Skapar mock-objekt
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // När getConnection anropas ska mock-anslutningen returneras
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        // När executeQuery anropas ska mock-resultatet returneras
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true, false); // Simulate one result in the ResultSet
        when(mockResultSet.getInt("furniture_id")).thenReturn(1);
        when(mockResultSet.getString("furniture_name")).thenReturn("Test Furniture");
        when(mockResultSet.getString("furniture_type")).thenReturn("Test Type");

        // Skapar en instans av DrinkRepository med mock-anslutningen
        furnitureRepository = new FurnitureRepository() {
            @Override
            protected Connection getConnection() {
                return mockConnection;
            }
        };
    }

    @Test
    public void testInsert() throws SQLException {
        Furniture furniture = new Furniture("Test Furniture", "Test Type");

        furnitureRepository.insert(furniture);
        verify(mockConnection).prepareStatement(anyString());

        verify(mockStatement).setString(1, furniture.getName());
        verify(mockStatement).setString(2, furniture.getType());

        verify(mockStatement).executeUpdate();
    }

}

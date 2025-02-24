package DB_Base;
import org.testng.annotations.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseClass {
    public static Connection con;

    @BeforeMethod
    public void setUp() throws SQLException {
        try {
            String dburl = "jdbc:mysql://localhost:3306/CompanyDB";
            String UserName = "root";
            String Password = "duttaryan";

            con = DriverManager.getConnection(dburl, UserName, Password);
            System.out.println("Connected to database: " + dburl);
        } catch (SQLException sqe) {
            System.out.println("Error Code: " + sqe.getErrorCode());
            System.out.println("SQL State: " + sqe.getSQLState());
            System.out.println("Message: " + sqe.getMessage());
            sqe.printStackTrace();
        }
    }

    @Test
    public void testDatabaseConnection()
    {
        assert con != null : "Database connection should not be null";
    }

    @AfterMethod
    public void tearDown() throws SQLException
    {
        if (con != null)
        {
            con.close();
            System.out.println("Database connection closed.");
        }
    }
}

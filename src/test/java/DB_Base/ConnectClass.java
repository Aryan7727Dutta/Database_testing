package DB_Base;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.*;
import java.sql.*;
public class ConnectClass {
    public static Connection cont;
    @BeforeMethod
    public void setup1() throws SQLException
    {
        try{
            String dburl = "jdbc:mysql://localhost:3306/CompanyDB";
            String UserName = "root";
            String Password = "duttaryan";

            cont = DriverManager.getConnection(dburl, UserName, Password);
        }
        catch (SQLException sqe)
        {
            System.out.println(sqe.getErrorCode());
            System.out.println(sqe.getSQLState());
            System.out.println(sqe.getMessage());
            sqe.printStackTrace();
        }
    }
    @Test
    public void check_connection(){
        assert cont != null : "database not connected";
    }
    @AfterMethod
    public void close () throws SQLException
    {
        if (cont != null)
        {
            cont.close();
            System.out.println("connection closed");
        }
    }

}

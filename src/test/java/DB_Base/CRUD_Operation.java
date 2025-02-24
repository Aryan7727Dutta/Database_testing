package DB_Base;
import java.sql.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CRUD_Operation extends BaseClass {

    @Before
    public void setUpTest() throws SQLException {
        setUp();
    }

    @After
    public void tearDownTest() throws SQLException {
        tearDown();
    }

    @Test
    public void testInsertData() throws SQLException {
        String insertQuery = "INSERT INTO Employees (employee_id, first_name, last_name, department_id, salary, joining_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setInt(1, 11);
            ps.setString(2, "David");
            ps.setString(3, "Clark");
            ps.setInt(4, 3);
            ps.setInt(5, 62000);
            ps.setDate(6, Date.valueOf("2024-02-17"));
            int rows1 = ps.executeUpdate();

            ps.setInt(1, 12);
            ps.setString(2, "Sophia");
            ps.setString(3, "Evans");
            ps.setInt(4, 4);
            ps.setInt(5, 59000);
            ps.setDate(6, Date.valueOf("2024-02-18"));
            int rows2 = ps.executeUpdate();

            System.out.println("✅ Employees 11 & 12 added successfully.");
            assertEquals(1, rows1);
            assertEquals(1, rows2);
        }
    }
    @Test
    public void Create_Table()
    {
        String Table = "CREATE TABLE IF NOT EXISTS Employees11 ("
                + "employee_id INT PRIMARY KEY AUTO_INCREMENT, "
                + "first_name VARCHAR(30) NOT NULL, "
                + "last_name VARCHAR(30) NOT NULL, "
                + "salary INT"
                + ")";


        try (PreparedStatement ps = con.prepareStatement(Table)) {
            ps.executeUpdate(Table);
            System.out.println("Table Created Successfully ");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateData() throws SQLException {
        String updateQuery = "UPDATE Employees SET salary = 60000 WHERE employee_id = 6";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            int rowsAffected = ps.executeUpdate();
            System.out.println("✅ Employee 6 salary updated.");
            assertTrue(rowsAffected > 0);
        }
    }

    @Test
    public void testDeleteData() throws SQLException {
        String deleteQuery = "DELETE FROM Employees WHERE employee_id = 12";
        try (PreparedStatement ps = con.prepareStatement(deleteQuery)) {
            int rowsAffected = ps.executeUpdate();
            System.out.println("✅ Employee 12 deleted.");
            assertTrue(rowsAffected > 0);
        }}
        @Test
        public void retreive() throws SQLException{
            String query = "SELECT * FROM Employees";
            String url = "jdbc:mysql://localhost:3306/CompanyDB";
            String user = "root";
            String password = "duttaryan";

            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = con.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {

                System.out.println("Employee Details:");
                System.out.println("-----------------------------------------------------");
                System.out.printf("%-5s %-10s %-10s %-5s %-10s %-12s\n",
                        "ID", "First Name", "Last Name", "Dept", "Salary", "Joining Date");
                System.out.println("-----------------------------------------------------");

                while (rs.next()) {
                    int empId = rs.getInt("employee_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    int deptId = rs.getInt("department_id");
                    int salary = rs.getInt("salary");
                    String joiningDate = rs.getString("joining_date");

                    System.out.printf("%-5d %-10s %-10s %-5d %-10d %-12s\n",
                            empId, firstName, lastName, deptId, salary, joiningDate);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }


import java.sql.*;
import java.util.Scanner;

public class EmployeeApp {
    private static final String URL = "jdbc:mysql://localhost:3306/company_db";
    private static final String USER = "root"; 
    private static final String PASSWORD = "12345678"; 

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n=== Employee Database Menu ===");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1: addEmployee(conn, sc); break;
                    case 2: viewEmployees(conn); break;
                    case 3: updateEmployee(conn, sc); break;
                    case 4: deleteEmployee(conn, sc); break;
                    case 5: System.exit(0);
                    default: System.out.println("Invalid choice!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter position: ");
        String position = sc.nextLine();
        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        String sql = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, position);
            pst.setDouble(3, salary);
            pst.executeUpdate();
            System.out.println("Employee added successfully!");
        }
    }

    private static void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employees";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nID | Name | Position | Salary");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                                   rs.getString("name") + " | " +
                                   rs.getString("position") + " | " +
                                   rs.getDouble("salary"));
            }
        }
    }

    private static void updateEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new name: ");
        String name = sc.nextLine();
        System.out.print("Enter new position: ");
        String position = sc.nextLine();
        System.out.print("Enter new salary: ");
        double salary = sc.nextDouble();

        String sql = "UPDATE employees SET name=?, position=?, salary=? WHERE id=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, position);
            pst.setDouble(3, salary);
            pst.setInt(4, id);
            int rows = pst.executeUpdate();
            if (rows > 0) System.out.println("Employee updated!");
            else System.out.println("No employee found with ID: " + id);
        }
    }

    private static void deleteEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter employee ID to delete: ");
        int id = sc.nextInt();
        String sql = "DELETE FROM employees WHERE id=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            int rows = pst.executeUpdate();
            if (rows > 0) System.out.println("Employee deleted!");
            else System.out.println("No employee found with ID: " + id);
        }
    }
}

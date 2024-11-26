package Project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Backend1 extends HttpServlet {

    // Database connection details (consider using environment variables)
    private static final String URL = "jdbc:mysql://localhost:3306/management";
    private static final String USER = "root";
    private static final String PASSWORD = "akash";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phone");
        String dob = request.getParameter("dob");

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // Load and register the JDBC driver (optional for newer versions of JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            // SQL query to insert user data
            String sql = "INSERT INTO users (name, email, password, phone_number, dob) VALUES (?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password); // Hash the password before storing it
            pstmt.setString(4, phoneNumber);
            pstmt.setString(5, dob);

            // Execute the query
            pstmt.executeUpdate();
            out.println("Registration successful");

        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Use a logging framework in production
            out.println("Database driver not found");
        } catch (SQLException e) {
            e.printStackTrace(); // Use a logging framework in production
            out.println("Database error");
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Use a logging framework in production
            }
        }
    }
}


package student.dao;
/**
 *
 * @author Sasinindu Tharaka
 */
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import student.model.Student;
import student.servlet.StudentServlet;

public class StudentDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcStudentname = "root";
    private String jdbcPassword = "1234";

    private static final String INSERT_STUDENTS_SQL = "INSERT INTO students" + "  (bcno, name, dob, gender) VALUES " +
        " (?, ?, ?, ?);";

    private static final String SELECT_STUDENT_BY_ID = "select id, bcno, name, dob, gender from students where id = ?";
    private static final String SELECT_ALL_STUDENTS = "select * from students";
    private static final String DELETE_STUDENTS_SQL = "delete from students where id = ?;";
    private static final String UPDATE_STUDENTS_SQL = "UPDATE students SET bcno= ?,name = ?,dob = ?, gender = ? WHERE id = ?";

    public StudentDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcStudentname, jdbcPassword);
        } catch (SQLException e) {
            
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            
            e.printStackTrace();
        }
        return connection;
    }

    public void insertStudent(Student student) throws SQLException {
        
        
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)) {
            preparedStatement.setInt(1, student.getBcno());
            
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getDob().toString());
            preparedStatement.setString(4, student.getGender());
            
            System.out.println(preparedStatement);
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Student selectStudent(int id) throws ParseException {
        Student student = null;
        
        try (Connection connection = getConnection();
            
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            
            ResultSet rs = preparedStatement.executeQuery();

            
            while (rs.next()) {
                int bcno = rs.getInt("bcno");
                String name = rs.getString("name");
                Date dob = Date.valueOf(rs.getString("dob").substring(0,10));
                String gender = rs.getString("gender");
                student = new Student(id, bcno, name, dob, gender);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return student;
    }

    public List < Student > selectAllStudents() throws ParseException {

        
        List < Student > students = new ArrayList < > ();
        
        try (Connection connection = getConnection();

            
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);) {
            System.out.println(preparedStatement);
            
            ResultSet rs = preparedStatement.executeQuery();

            
            while (rs.next()) {
                
                int id  =rs.getInt("id");
                int bcno = rs.getInt("bcno");
                String name = rs.getString("name");
                Date dob = Date.valueOf(rs.getString("dob").substring(0,10));
                String gender = rs.getString("gender");
                students.add(new Student(id, bcno, name, dob, gender));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return students;
    }

    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_STUDENTS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENTS_SQL);) {
            
            statement.setInt(1, student.getBcno());
            statement.setString(2, student.getName());
            statement.setString(3, student.getDob().toString());
            statement.setString(4, student.getGender());
            statement.setInt(5, student.getId());
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
  

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
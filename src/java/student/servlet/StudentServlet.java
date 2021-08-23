package student.servlet;

/**
 *
 * @author Sasinindu Tharaka
 */
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.dao.StudentDAO;
import student.model.Student;

@WebServlet("/")
public class StudentServlet extends HttpServlet {

    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert": {
                    try {
                        insertStudent(request, response);
                    } catch (ParseException ex) {
                        Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "/delete":
                    deleteStudent(request, response);
                    break;
                case "/edit": {
                    try {
                        showEditForm(request, response);
                    } catch (ParseException ex) {
                        Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "/update": {
                    try {
                        updateStudent(request, response);
                    } catch (ParseException ex) {
                        Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                default: {
                    try {
                        listStudent(request, response);
                    } catch (ParseException ex) {
                        Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException, ParseException {
        List< Student> listStudent = studentDAO.selectAllStudents();
        request.setAttribute("listStudent", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException, ParseException {

        int id = Integer.parseInt(request.getParameter("id"));

        Student existingStudent = studentDAO.selectStudent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        request.setAttribute("student", existingStudent);
        dispatcher.forward(request, response);

    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        int bcno = Integer.parseInt(request.getParameter("bcno"));
        String name = request.getParameter("name");
        Date dob = Date.valueOf(request.getParameter("dob").substring(0, 10));
        String gender = request.getParameter("gender");
        Student newStudent = new Student(bcno, name, dob, gender);
        studentDAO.insertStudent(newStudent);
        response.sendRedirect("list");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));

        int bcno = Integer.parseInt(request.getParameter("bcno"));
        String name = request.getParameter("name");

        Date dob = Date.valueOf(request.getParameter("dob"));
        String gender = request.getParameter("gender");

        Student student = new Student(id, bcno, name, dob, gender);
        studentDAO.updateStudent(student);
        response.sendRedirect("list");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect("list");

    }
}

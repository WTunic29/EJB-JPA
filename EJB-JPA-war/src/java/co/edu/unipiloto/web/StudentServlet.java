package co.edu.unipiloto.web;

import co.edu.unipiloto.entity.Student;
import co.edu.unipiloto.entity.StudentFacadeLocal;

import jakarta.ejb.EJB;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 *
 * @author poeta
 */
@WebServlet(name = "StudentServlet", urlPatterns = {"/StudentServlet"})
public class StudentServlet extends HttpServlet {

    @EJB
    private StudentFacadeLocal studentFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("allStudents", studentFacade.findAll());

        request.getRequestDispatcher("/studentInfo.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            int studentId = Integer.parseInt(request.getParameter("studentId"));

            switch (action) {

                case "Add": {

                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");

                    int yearLevel = Integer.parseInt(request.getParameter("yearLevel"));

                    Student student = new Student();
                    
                    student.setStudentId(studentId);
                    student.setFirstName(firstName);
                    student.setLastName(lastName);
                    student.setYearLevel(yearLevel);

                    studentFacade.create(student);

                    break;
                }

                case "Edit": {

                    Student student = studentFacade.find(studentId);

                    if (student != null) {

                        String firstName = request.getParameter("firstName");
                        String lastName = request.getParameter("lastName");
                        String yearLevelParam = request.getParameter("yearLevel");

                        if (firstName != null && !firstName.trim().isEmpty()) {
                            student.setFirstName(firstName);
                        }

                        if (lastName != null && !lastName.trim().isEmpty()) {
                            student.setLastName(lastName);
                        }

                        if (yearLevelParam != null && !yearLevelParam.trim().isEmpty()) {
                            student.setYearLevel(Integer.parseInt(yearLevelParam));
                        }

                        studentFacade.edit(student);
                    }

                    break;
                }

                case "Delete": {

                    Student student = studentFacade.find(studentId);

                    if (student != null) {
                        studentFacade.remove(student);
                    }

                    break;
                }

                case "Search": {

                    Student foundStudent = studentFacade.find(studentId);

                    if (foundStudent != null) {
                        request.setAttribute("student", foundStudent);
                    }

                    break;
                }

                default:
                    break;
            }

        } catch (NumberFormatException e) {

            request.setAttribute("error", "Student ID y Year Level deben ser números.");
        }

        request.setAttribute("allStudents", studentFacade.findAll());
        request.getRequestDispatcher("/studentInfo.jsp").forward(request, response);
    
    }

    @Override
    public String getServletInfo() {
        return "Student management servlet";
    }

}

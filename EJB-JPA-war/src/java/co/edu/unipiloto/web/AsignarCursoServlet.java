package co.edu.unipiloto.web;

import co.edu.unipiloto.entity.Curso;
import co.edu.unipiloto.entity.CursoFacadeLocal;
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
@WebServlet(name = "AsignarCursoServlet", urlPatterns = {"/AsignarCursoServlet"})
public class AsignarCursoServlet extends HttpServlet {

    @EJB
    private StudentFacadeLocal studentFacade;

    @EJB
    private CursoFacadeLocal cursoFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("allStudents", studentFacade.findAll());
        request.setAttribute("allCursos", cursoFacade.findAll());

        request.getRequestDispatcher("/asignarCurso.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            int studentId = Integer.parseInt(
                    request.getParameter("studentId")
            );

            Student student = studentFacade.find(studentId);

            if (student == null) {

                request.setAttribute(
                        "error",
                        "No se encontró el estudiante."
                );

            } else {

                switch (action) {

                    case "Asignar": {

                        String codigoCurso = request.getParameter("codigoCurso");

                        Curso curso = cursoFacade.find(codigoCurso);

                        if (curso != null) {

                            student.getCursoCollection().add(curso);

                            studentFacade.edit(student);

                            request.setAttribute(
                                    "mensaje",
                                    "Curso asignado correctamente."
                            );

                        } else {

                            request.setAttribute("error", "No se encontró el curso.");
                        }

                        request.setAttribute("studentSeleccionado", student);
                        break;
                    }

                    case "Ver Cursos": {
                        request.setAttribute("studentSeleccionado", student);
                        break;
                    }

                    case "Quitar": {

                        String codigoCurso = request.getParameter("codigoCurso");
                        Curso curso = cursoFacade.find(codigoCurso);

                        if (curso != null && student.getCursoCollection().contains(curso)) {

                            student.getCursoCollection().remove(curso);
                            studentFacade.edit(student);

                            request.setAttribute("mensaje", "Curso retirado correctamente.");

                        } else {

                            request.setAttribute("error", "El estudiante no tiene asignado ese curso.");
                        }

                        request.setAttribute( "studentSeleccionado", student);
                        break;
                    }

                    default:
                        break;
                }
            }

        } catch (NumberFormatException e) {

            request.setAttribute("error", "El Student ID debe ser un número.");
        }

        request.setAttribute("allStudents", studentFacade.findAll());
        request.setAttribute("allCursos", cursoFacade.findAll());
        request.getRequestDispatcher("/asignarCurso.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Servlet para asignar cursos a estudiantes";
    }
}

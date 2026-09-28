package co.edu.unipiloto.web;

import co.edu.unipiloto.entity.Curso;
import co.edu.unipiloto.entity.CursoFacadeLocal;

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
@WebServlet(name = "CursoServlet", urlPatterns = {"/CursoServlet"})
public class CursoServlet extends HttpServlet {

    @EJB
    private CursoFacadeLocal cursoFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("allCursos", cursoFacade.findAll());

        request.getRequestDispatcher("/cursoInfo.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            String codigoCurso = request.getParameter("codigoCurso");

            switch (action) {

                case "Add": {

                    String nombreCurso = request.getParameter("nombreCurso");
                    int creditos = Integer.parseInt(
                            request.getParameter("creditos")
                    );
                    int semestre = Integer.parseInt(
                            request.getParameter("semestre")
                    );
                    int estudiantesAdmitidos = Integer.parseInt(
                            request.getParameter("estudiantesAdmitidos")
                    );

                    Curso curso = new Curso();
                    curso.setCodigoCurso(codigoCurso);
                    curso.setNombreCurso(nombreCurso);
                    curso.setCreditos(creditos);
                    curso.setSemestre(semestre);
                    curso.setEstudiantesAdmitidos(estudiantesAdmitidos);

                    cursoFacade.create(curso);

                    break;
                }

                case "Edit": {

                    Curso curso = cursoFacade.find(codigoCurso);

                    if (curso != null) {

                        String nombreCurso = request.getParameter("nombreCurso");
                        String creditosParam = request.getParameter("creditos");
                        String semestreParam = request.getParameter("semestre");
                        String estudiantesParam =
                                request.getParameter("estudiantesAdmitidos");

                        if (nombreCurso != null && !nombreCurso.trim().isEmpty()) {
                            curso.setNombreCurso(nombreCurso);
                        }

                        if (creditosParam != null && !creditosParam.trim().isEmpty()) {
                            curso.setCreditos(Integer.parseInt(creditosParam));
                        }

                        if (semestreParam != null && !semestreParam.trim().isEmpty()) {
                            curso.setSemestre(Integer.parseInt(semestreParam));
                        }

                        if (estudiantesParam != null && !estudiantesParam.trim().isEmpty()) {
                            curso.setEstudiantesAdmitidos(
                                    Integer.parseInt(estudiantesParam)
                            );
                        }

                        cursoFacade.edit(curso);
                    }

                    break;
                }

                case "Delete": {

                    Curso curso = cursoFacade.find(codigoCurso);

                    if (curso != null) {
                        cursoFacade.remove(curso);
                    }

                    break;
                }

                case "Search": {

                    Curso foundCurso = cursoFacade.find(codigoCurso);

                    if (foundCurso != null) {
                        request.setAttribute("curso", foundCurso);
                    }

                    break;
                }

                default:
                    break;
            }

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Los campos numéricos deben contener números."
            );
        }

        request.setAttribute(
                "allCursos",
                cursoFacade.findAll()
        );

        request.getRequestDispatcher("/cursoInfo.jsp")
                .forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Curso management servlet";
    }
}
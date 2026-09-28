<%-- 
    Document   : asignarCurso
    Created on : 28 sep 2026, 5:37:16 a.m.
    Author     : poeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Asignar Curso</title>
    </head>
    <body>

        <h1>Asignar Curso a Estudiante</h1>

    <c:if test="${not empty mensaje}">
        <p style="color: green;">
            ${mensaje}
        </p>
    </c:if>

    <c:if test="${not empty error}">
        <p style="color: red;">
            ${error}
        </p>
    </c:if>

    <form action="./AsignarCursoServlet" method="POST">

        <table>

            <tr>
                <td>Estudiante:</td>
                <td>
                    <select name="studentId">

                        <c:forEach items="${allStudents}" var="student">

                            <option value="${student.studentId}">
                                ${student.studentId} -
                                ${student.firstName}
                                ${student.lastName}
                            </option>

                        </c:forEach>

                    </select>
                </td>
            </tr>

            <tr>
                <td>Curso:</td>
                <td>
                    <select name="codigoCurso">

                        <c:forEach items="${allCursos}" var="curso">

                            <option value="${curso.codigoCurso}">
                                ${curso.codigoCurso} -
                                ${curso.nombreCurso}
                            </option>

                        </c:forEach>

                    </select>
                </td>
            </tr>

            <tr>

                <td colspan="2">

                    <input
                        
                        type="submit"
                        name="action"
                        value="Asignar" 
                           
                           />

                    <input 
                        
                        type="submit"
                        name="action"
                        value="Ver Cursos" 
                        
                        />

                    <input 
                        
                        type="submit"
                        name="action"
                        value="Quitar" 
                        
                        />

                </td>

            </tr>

        </table>

    </form>

    <c:if test="${not empty studentSeleccionado}">

        <h2>Cursos del estudiante</h2>

        <table border="1">

            <tr>
                
                <th>Código</th>
                <th>Nombre</th>
                <th>Créditos</th>
                <th>Semestre</th>
                
            </tr>

            <c:forEach
                items="${studentSeleccionado.cursoCollection}"
                var="curso">

                <tr>
                    
                    <td>${curso.codigoCurso}</td>
                    <td>${curso.nombreCurso}</td>
                    <td>${curso.creditos}</td>
                    <td>${curso.semestre}</td>
                    
                </tr>

            </c:forEach>

        </table>

    </c:if>

</body>
</html>
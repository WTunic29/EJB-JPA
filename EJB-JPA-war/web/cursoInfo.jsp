<%-- 
    Document   : cursoInfo
    Created on : 28 sep 2026, 5:26:22 a.m.
    Author     : poeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>

<html>
    
<head>
    
    <meta charset="UTF-8">
    
    <title>Curso Information</title>
    
</head>

<body>

    <h1>Curso Information</h1>

    <form action="./CursoServlet" method="POST">

        <table>

            <tr>
                
                <td>Código del Curso</td>
                
                <td>
                    
                    <input
                        
                        type="text"
                        name="codigoCurso"
                        value="${curso.codigoCurso}" 
                        
                        />
                    
                </td>
                
            </tr>

            <tr>
                
                <td>Nombre del Curso</td>
                
                <td>
                    
                    <input 
                        
                        type="text"
                        name="nombreCurso"
                        value="${curso.nombreCurso}" 
                        
                        />
                    
                </td>
                
            </tr>

            <tr>
                
                <td>Créditos</td>
                
                <td>
                    
                    <input 
                        
                        type="text"
                        name="creditos"
                        value="${curso.creditos}"
                        
                        />
                    
                </td>
                
            </tr>

            <tr>
                
                <td>Semestre</td>
                
                <td>
                    
                    <input
                        
                        type="text"
                        name="semestre"
                        value="${curso.semestre}" />
                    
                </td>
                
            </tr>

            <tr>
                
                <td>Estudiantes Admitidos</td>
                
                <td>
                    
                    <input 
                        
                        type="text"
                        name="estudiantesAdmitidos"
                        value="${curso.estudiantesAdmitidos}" 
                        
                        />
                
                </td>
                
            </tr>

            <tr>
                
                <td colspan="2">

                    <input 
                        
                        type="submit"
                        name="action"
                        value="Add"
                        
                        />

                    <input 
                        
                        type="submit"
                        name="action"
                        value="Edit" 
                        
                        />

                    <input 
                        
                        type="submit"
                        name="action"
                        value="Delete" 
                        
                        />

                    <input 

                        type="submit"
                        name="action"
                        value="Search" 

                        />

                </td>
            </tr>

        </table>

    </form>

    <br>

    <table border="1">

        <tr>
            
            <th>Código</th>
            <th>Nombre</th>
            <th>Créditos</th>
            <th>Semestre</th>
            <th>Estudiantes Admitidos</th>
            
        </tr>

        <c:forEach items="${allCursos}" var="cursoItem">

            <tr>
                
                <td>${cursoItem.codigoCurso}</td>
                <td>${cursoItem.nombreCurso}</td>
                <td>${cursoItem.creditos}</td>
                <td>${cursoItem.semestre}</td>
                <td>${cursoItem.estudiantesAdmitidos}</td>
            
            </tr>

        </c:forEach>

    </table>

</body>

</html>
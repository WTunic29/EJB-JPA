<%-- 
    Document   : studentInfo
    Created on : 28 sep 2026, 4:21:53 a.m.
    Author     : poeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
    
    <head>
        
        <meta charset="UTF-8">
        <title>Student Information</title>
        
    </head>

    <body>

        <h1>Student Information</h1>

        <form action="./StudentServlet" method="POST">

            <table>

                <tr>
                    
                    <td>Student Id</td>
                    
                    <td>
                        
                        <input 
                            
                            type="text"
                            name="studentId"
                            value="${student.studentId}"
                            
                            />
                        
                    </td>
                    
                </tr>

                <tr>
                    
                    <td>First Name</td>
                    
                    <td>
                        
                        <input 
                            
                            type="text"
                            name="firstName"
                            value="${student.firstName}" 
                            
                            />
                        
                    </td>
                    
                </tr>

                <tr>
                    
                    <td>Last Name</td>
                    
                    <td>
                        
                        <input 
                            
                            type="text"
                            name="lastName"
                            value="${student.lastName}"
                            
                            />
                        
                    </td>
                    
                </tr>

                <tr>
                    <td>Year Level</td>
                    
                    <td>
                        
                        <input 
                            type="text"
                            name="yearLevel"
                            value="${student.yearLevel}"
                            
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
                
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Year Level</th>
                
            </tr>

            <c:forEach items="${allStudents}" var="stud">

                <tr>
                    
                    <td>${stud.studentId}</td>
                    <td>${stud.firstName}</td>
                    <td>${stud.lastName}</td>
                    <td>${stud.yearLevel}</td>
                    
                </tr>

            </c:forEach>

        </table>

    </body>
    
</html>
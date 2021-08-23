<%-- 
    Document   : student-list
    Created on : Aug 15, 2021, 10:53:13 AM
    Author     : Sasinindu Tharaka
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="Error.jsp"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Student Management Application
            
            </title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <h2  class="navbar-brand"> Student Management App </h2>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Students</a></li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                

                <div class="container">
                    <h3 class="text-center">List of Students</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
     New Student</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                
                                <th>Birth Certificate NO</th>
                                <th>Name</th>
                                <th>Date Of Birth</th>
                                <th>Gender</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            
                            <c:forEach var="student" items="${listStudent}">

                                <tr>
                                    
                                    <td>
                                        <c:out value="${student.bcno}" />
                                    </td>
                                    <td>
                                        <c:out value="${student.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${student.dob}" />
                                    </td>
                                    <td>
                                        <c:out value="${student.gender}" />
                                    </td>
                                    <td><a href="edit?id=<c:out value='${student.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${student.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>


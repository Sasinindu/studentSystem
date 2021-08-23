<%-- 
    Document   : student-form
    Created on : Aug 15, 2021, 11:44:30 AM
    Author     : Sasinindu Tharaka
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="Error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Student Management Application</title>
        <style>
            .radioButton{
                margin: 10px !important;
            }
        </style>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>

    <body onload="onload()">

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
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${student != null}">
                        <form action="update" method="post">
                        </c:if>
                        <c:if test="${student == null}">
                            <form action="insert" method="post">
                            </c:if>

                            <caption>
                                <h2>
                                    <c:if test="${student != null}">
                                        Edit Student
                                    </c:if>
                                    <c:if test="${student == null}">
                                        Add New Student
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${student != null}">
                                <input type="hidden" id="id" name="id" value="" />
                            </c:if>

                            <fieldset class="form-group">
                                <label>Birth Certificate No</label> <input type="text" value="" class="form-control" id="bcno" name="bcno" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Student Name</label> <input type="text" value="" class="form-control" id="name" name="name" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Date Of Birth</label> <input type="date" class="form-control" value="" name="dob" id="dob" required="">
                            </fieldset>

                            <fieldset class="form-group">
                                <c:if test="${student eq null}">
                                    <input type="radio" class="radioButton" value="Male" name="gender" id="gender" required=""> Male
                                    <input type="radio" class="radioButton" value="Female" name="gender" id="gender" required=""> Female
                                </c:if>
                                <c:if test="${student.gender eq 'Male'}">
                                    <input type="radio" class="radioButton" value="Male" name="gender" id="gender" required="" checked=""> Male
                                    <input type="radio" class="radioButton" value="Female" name="gender" id="gender" required=""> Female
                                </c:if>
                                <c:if test="${student.gender eq 'Female'}">
                                    <input type="radio" class="radioButton" value="Male" name="gender" id="gender" required="" > Male
                                    <input type="radio" class="radioButton" value="Female" name="gender" id="gender" required="" checked> Female
                                </c:if>
                            </fieldset>

                            <button type="submit" class="btn btn-success">Save</button>
                        </form>
                </div>
            </div>
        </div>
    </body>
    <script>
        function onload() {
        <c:if test="${student != null}">
            document.getElementById("id").value = '${student.id}';
            document.getElementById("bcno").value = '${student.bcno}';
            document.getElementById("name").value = '${student.name}';
            document.getElementById("dob").value = '${student.dob}';

        </c:if>
        }

    </script>

</html>
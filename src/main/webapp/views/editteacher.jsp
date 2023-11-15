<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Преподаватели</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Teachers</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <!-- jQuery -->
    <script defer src="js/jquery-3.6.4.js"></script>
    <!-- Bootstrap JS + Popper JS -->
    <script defer src="js/bootstrap.min.js"></script>
</head>

<body>
<div class="container-fluid back-container">
    <jsp:include page="/views/header.jsp" />
    <div class="container-fluid back-container">
        <div class="row justify-content-start ">
            <div class="col-8 border bg-light px-4">
                <h3>Список преподавателей:</h3>
                <br>
                <table class="table">
                    <thead>
                    <th scope="col">Код</th>
                    <th scope="col">Кафедра</th>
                    <th scope="col">Должность</th>
                    <th scope="col">Фамилия</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Отчество</th>
                    <th scope="col">Номер телефона</th>
                    <th scope="col">Почта</th>
                    </thead>
                    <tbody>
                    <c:forEach var="teacher" items="${teachers}">
                        <tr>
                            <td>${teacher.getId()}</td>
                            <td>${teacher.chairs.getFullName()}</td>
                            <td>${teacher.posts.getNamePost()}</td>
                            <td>${teacher.getSecondName()}</td>
                            <td>${teacher.getFirstName()}</td>
                            <td>${teacher.getLastName()}</td>
                            <td>${teacher.getPhone()}</td>
                            <td>${teacher.getEmail()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4 a-color">
                <form method="POST" action="">
                    <h3 class="a-color">Редактировать преподавателя</h3>
                    <div class="mb-3 row">
                        <label for="idteacher"
                               class="col-sm-3 col-form-label a-color">Код преподавателя</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control"
                                   readonly id="idteacher" value="${teacherEdit.getId()}" />
                        </div>
                        <label for="chairs"
                               class="col-sm-3 col-form-label a-color">Кафедра</label>
                        <div class="col-sm-7">
                            <select name="chairField" class="form-control">
                                <option value="${teacherEdit.chairs}">${teacherEdit.chairs.getFullName()}</option>
                                <c:forEach var="chair" items="${chairs}">
                                    <option value="${chair}">
                                        <c:out value="${chair.getFullName()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="posts"
                               class="col-sm-3 col-form-label a-color">Должность</label>
                        <div class="col-sm-7">
                            <select name="postField" class="form-control">
                                <option value="${teacherEdit.posts}">${teacherEdit.posts.getNamePost()}</option>
                                <c:forEach var="post" items="${posts}">
                                    <option value="${post}">
                                        <c:out value="${post.getNamePost()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="inputSecondName"
                               class="col-sm-3 col-form-label a-color">Фамилия</label>
                        <div class="col-sm-6">
                            <input type="text" name="secondName"
                                   class="form-control" id="inputSecondName" value="${teacherEdit.getSecondName()}"/>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="inputfirstName"
                               class="col-sm-3 col-form-label a-color">Имя</label>
                        <div class="col-sm-6">
                            <input type="text" name="firstName"
                                   class="form-control" id="inputfirstName" value="${teacherEdit.getFirstName()}"/>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="inputLastName"
                               class="col-sm-3 col-form-label a-color">Отчество</label>
                        <div class="col-sm-6">
                            <input type="text" name="lastName"
                                   class="form-control" id="inputLastName" value="${teacherEdit.getLastName()}"/>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="inputPhone"
                               class="col-sm-3 col-form-label a-color">Номер телефона</label>
                        <div class="col-sm-6">
                            <input type="text" name="phone"
                                   class="form-control" id="inputPhone" value="${teacherEdit.getPhone()}"/>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="inputEmail"
                               class="col-sm-3 col-form-label a-color">Почта</label>
                        <div class="col-sm-6">
                            <input type="text" name="email"
                                   class="form-control" id="inputEmail" value="${teacherEdit.getEmail()}"/>
                        </div>
                    </div>
                    <p>
                        <br> <br> <br>
                        <button type="submit"
                                class="btn btn-primary">Редактировать</button>
                        <a href='<c:url value="/teachers" />' role="button" class="btn btn-secondary">Отменить</a>
                        <br>
                    </p>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Кафедры</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Chairs</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <!-- jQuery -->
    <script defer src="js/jquery.min.js"></script>
    <!-- Bootstrap JS + Popper JS -->
    <script defer src="js/bootstrap.min.js"></script>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.mi
n.js"></script>
</head>
<body>
<div class="container-fluid back-container">
    <jsp:include page="/views/header.jsp" />
    <div class="container-fluid back-container">
        <div class="row justify-content-start ">
            <div class="col-8 border bg-light px-4">
                <h3>Список кафедр:</h3>
                <table class="table">
                    <thead>
                    <th scope="col">Код</th>
                    <th scope="col">Факультет</th>
                    <th scope="col">Полное название</th>
                    <th scope="col">Аббревиатура</th>
                    </thead>
                    <tbody>
                    <c:forEach var="chair" items="${chairs}">
                        <tr>
                            <td>${chair.getId()}</td>
                            <td>${chair.faculties.getFullName()}</td>
                            <td>${chair.getFullName()}</td>
                            <td>${chair.getShortName()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4 a-color">
                <form method="POST" action="">
                    <h3>Редактировать кафедру:</h3>
                    <br>
                    <div class="mb-3 row">
                        <label for="idchair"
                               class="col-sm-3 col-form-label a-color">Код кафедры</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control"
                                   readonly id="idchair" value="${chairEdit.getId()}" />
                        </div>
                        <label for="inputFaculty"
                               class="col-sm-3 col-form-label a-color">Факультет</label>
                        <div class="col-sm-7">
                            <select name="facultyField" class="form-control">
                                <option value="${chairEdit.faculties}">${chairEdit.faculties.getFullName()}</option>
                                <c:forEach var="faculty" items="${faculties}">
                                    <option value="${faculty}">
                                        <a><c:out value="${faculty.getFullName()}"/></a>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <label for="inputFull"
                               class="col-sm-3 col-form-label a-color">Кафедра</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control"
                                   id="inputFull" name="fullName" value="${chairEdit.getFullName()}"/>
                        </div>
                        <label for="inputShort"
                               class="col-sm-3 col-form-label a-color">Аббревиатура</label>
                        <div class="col-sm-7">
                            <input type="text"
                                   class="form-control" id="inputShort"
                                   name="shortName" value="${chairEdit.getShortName()}"/>
                        </div>
                    </div>
                    <p> <br>
                        <button type="submit"
                                class="btn btn-primary">Редактировать</button>
                        <a href='<c:url value="/chairs" />' role="button" class="btn btn-secondary">Отменить</a>
                    </p>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Факультеты</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Faculties</title>
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
                <h3>Список факультетов:</h3>
                <table class="table">
                    <thead>
                    <th scope="col">Код</th>
                    <th scope="col">Факультет</th>
                    <th scope="col">Аббревиатура</th>
                    </thead>
                    <tbody>
                    <c:forEach var="faculty" items="${faculties}">
                        <tr>
                            <td>${faculty.getId()}</td>
                            <td>${faculty.getFullName()}</td>
                            <td>${faculty.getShortName()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4 a-color">
                <form method="POST" action="">
                    <h3>Редактировать факультет</h3>
                    <div class="mb-3">
                        <br>
                        <label for="idfaculty"
                                    class="col-sm-3 col-form-label a-color">Код факультета</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" readonly id="idfaculty" value="${facultyEdit.getId()}"/>
                        </div>
                        <label for="inputFull"
                                    class="col-sm-3 col-form-label a-color">Полное название</label>
                        <div class="col-sm-6">
                            <input type="text" name="fullName"
                                   class="form-control" id="inputFull" value='${facultyEdit.getFullName()}' />
                        </div>
                        <label for="inputShort"
                               class="col-sm-3 col-form-label a-color">Аббревиатура</label>
                        <div class="col-sm-6">
                            <input type="text" name="shortName"
                                   class="form-control" id="inputShort" value='${facultyEdit.getShortName()}'/>
                        </div>
                    </div>
                    <p>
                        <br> <br> <br>
                        <button type="submit"
                                class="btn btn-primary">Редактировать</button>
                        <a href='<c:url value="/faculty" />' role="button" class="btn btn-secondary">Отменить</a>
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
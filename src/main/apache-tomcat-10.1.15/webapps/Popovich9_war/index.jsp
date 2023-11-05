<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <!-- Настройка viewport -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/style.css">
  <title>Учет операций с ценными бумагами</title>
</head>
<body>
<div class="container-fluid back-container">
  <jsp:include page="/views/header.jsp" />
  <div class="container">
    <br><br><br>
    <div class="list-group text-center py-3 px-3 ">
      <h2 class="colour-white">Функции системы:</h2>
      <ul class="list-group list-group-flush">
        <li class="list-group-item list-group-iteminfo li-background li_theme"><a class="a-color" href="#">Операции</a>
          <br>
        <li class="list-group-item list-group-itemprimary li-background li_theme"><a class="a-color" href="#">Сделки</a>
          <br>
        <li class="list-group-item list-group-iteminfo li-background li_theme"><a class="a-color" href="#">Суб.счета</a>
          <br>
        <li class="list-group-item list-group-iteminfo li-background li_theme"><a class="a-color" href="#">План счетов</a>
      </ul>
    </div>
    <br><br>
  </div>
  <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>
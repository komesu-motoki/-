<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <table>
    <caption>検索結果</caption>
    <thead>
      <tr>
        <th>ID</th>
        <th>名前</th>
        <th>TEL</th>
        <th>権限</th>
      </tr>
    </thead>
    <c:forEach var="user_info" items="${list}">
    <tbody>
      <tr>
        <td>${user_info.login_id}</td>
        <td>${user_info.user_name}</td>
        <td>${user_info.telephone}</td>
        <td>${user_info.role_name}</td>
      </tr>
    </tbody>
    </c:forEach>
  </table>
  <div>
    <a href="menu.jsp">メニューに戻る</a>
  </div>
</body>
</html>

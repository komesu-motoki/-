<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${empty result}">
	<c:redirect url="/index.jsp" />
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>


	<p>${sessionScope.user_name}さん、こんにちは</p>

	<p>
		<a href="select.jsp">検索</a>
	</p>
	<c:if test="${sessionScope.role_id == '1'}">
		<p>
			<a href="insert.jsp">登録</a>
		</p>
	</c:if>

    <c:if test="${sessionScope.role_id == '1'}">
    <p>
      <a href="update.jsp">更新</a>
    </p>
    </c:if>
    <c:if test="${sessionScope.role_id == '1'}">
    <p>
      <a href="delete.jsp">削除</a>
    </p>
    </c:if>

	<form action="logout.jsp" method="post">
		<button type="submit">ログアウト</button>
	</form>
</body>
</html>

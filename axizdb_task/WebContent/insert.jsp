<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>
		登録情報を入力してください<br> <span class="required"></span>は必須です
	</p>

	<c:if test="${not empty msg5}">
		<p class="error">${requestScope.msg5}</p>
	</c:if>

	<form action="insert" method="post">
		<fieldset class="label-60">
			<div>
				<label class="required">ID</label> <input type="text"
					name="loginId" value="${sessionScope.loginId}">
				<c:if test="${not empty msg1}">
					<span class="error">${requestScope.msg1}</span>
				</c:if>
			</div>
			<div>
				<label class="required">名前</label> <input type="text"
					name="userName" value="${sessionScope.userName}">
				<c:if test="${not empty msg2}">
					<span class="error">${requestScope.msg2}</span>
				</c:if>
			</div>
			<div>
				<label class="required">TEL</label> <input type="text"
					name="telephone" value="${sessionScope.telephone}">
				<c:if test="${not empty msg3}">
					<span class="error">${requestScope.msg3}</span>
				</c:if>
			</div>
			<div>
				<label class="required">権限</label> <select name="roleId">
					<option value="2" <c:if test="${sessionScope.roleId == '2' || sessionScope.roleId == null}">selected</c:if>>${role[1].role_name}</option>
					<option value="1"<c:if test="${sessionScope.roleId == '1' }">selected</c:if>>${role[0].role_name}</option>
				</select>
			</div>
			<div>
				<label class="required">PASS</label> <input type="password"
					name="pass" value="${sessionScope.pass}">
				<c:if test="${not empty msg4}">
					<span class="error">${requestScope.msg4}</span>
				</c:if>
			</div>
		</fieldset>
		<button type="submit">確認</button>
	</form>
	<div>
		<a href="menu.jsp">メニューに戻る</a>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新内容入力画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>１項目以上の項目を変更してください</p>

	<c:if test="${not empty msg1}">
		<p class="error">${requestScope.msg1}</p>
	</c:if>
	<c:if test="${not empty msg6}">
		<p class="error">${requestScope.msg6}</p>
	</c:if>

	<form action="updateInput" method="post">
		<fieldset>

			<div>
				<label>ID</label> <input type="text" name="loginId"
					value="${listu[0].login_id}">
				<c:if test="${not empty msg2}">
					<span class="error">${requestScope.msg2}</span>
				</c:if>
			</div>
			<div>
				<label>名前</label> <input type="text" name="userName"
					value="${listu[0].user_name}">
				<c:if test="${not empty msg3}">
					<span class="error">${requestScope.msg3}</span>
				</c:if>
			</div>
			<div>
				<label>TEL</label> <input type="text" name="tel"
					value="${listu[0].telephone}">
				<c:if test="${not empty msg4}">
					<span class="error">${requestScope.msg4}</span>
				</c:if>
			</div>
			<div>
				<label>権限</label> <select name="roleId">
					<option value="1" <c:if test="${listu[0].role_id == 1}">selected</c:if>>${role[0].role_name}</option>
					<option value="2" <c:if test="${listu[0].role_id == 2 }">selected</c:if>>${role[1].role_name}</option>
				</select>
			</div>
			<div>
				<label>PASS</label> <input type="password" name="pass"
					value="${listu[0].password}">
				<c:if test="${not empty msg5}">
					<span class="error">${requestScope.msg5}</span>
				</c:if>
			</div>
		</fieldset>
		<div>
			<button type="submit">確認</button>
			<button type="submit"
				onclick="location.href='update.jsp'; return false;">戻る</button>
		</div>
	</form>
	<div>
		<a href="menu.jsp">メニューに戻る</a>
	</div>
</body>
</html>

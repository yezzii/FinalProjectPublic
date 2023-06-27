<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="65%" color="blue">
		<h3>MEMBER10 회원 가입 폼 페이지</h3>
		<hr width="65%" color="blue">
		<br>


		<form method="post"
			action="<%=request.getContextPath()%>/test_insert_ok.do">
			<table border="1" cellspacing="10" width="300">
				<tr>
					<th>테스트 이름</th>
					<td><input name="testname"></td>
				</tr>
				<tr>
					<th>테스트 아이디</th>
					<td><input name="testid"></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="회원등록"> &nbsp;&nbsp;&nbsp; <input type="reset"
						value="다시작성"></td>
				</tr>
			</table>

		</form>

	</div>


</body>
</html>
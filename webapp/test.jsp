<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
  <center>
<h1>用户登录</h1>
<hr>
	<form action="/ssh/User/SendMessages" method="post">
		<table border="0">
			
			<tr>
				<td colspan="0">
					
				</td>
			</tr>
			
			<tr>
			<td>&nbsp</td>
			</tr>
		<!-- 	
			<tr>
				<td>登 陆 账 户：</td>
				<td> <input type="text" name="KeyWord"></td>
			</tr> -->
			
			<tr>
				<td>详细数据：</td>
				<td> <input type="text" name="phoneNumber"></td>
			</tr>
			
			
		
			
			<tr>
				<th colspan="2">
					<input type="submit" value="登陆">
					<input type="reset" value="重置">
					
				</th>
			</tr>
			
		</table>
	</form>
</center>
</body>
</html>
<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="com.real.bank.model.TX"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/mystyle.css">
<script src="<%=request.getContextPath()%>/resources/js/myscript.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>

<body>
	<div>
		<h1 align="center" class="heading">Verify OTP</h1>
		<hr>
		<span class="heading">${requestScope.otpVerified}</span>
		<table class="table">
			<form method="post" action="${requestScope.url }">

				<tr>
					<td>Enter OTP:</td>
					<td><input type="number" name="otp" required></td>

				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Verify" class="btn"></td>

				</tr>

			</form>
		</table>
	</div>

</body>
</html>
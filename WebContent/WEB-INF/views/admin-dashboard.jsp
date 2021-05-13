<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="com.real.bank.model.Account"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MY BANK</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/mystyle.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">



</head>
<body>
<div class "row">

<div class="col-sm-12">
	<div>
		<div class="header">
			<h1 align="center" class="heading">MY BANK</h1>
			<hr>

		</div>

	</div>
	
	
	

	
<% List<Account> accounts = (List) request.getAttribute("accounts"); %>

<table class="table">
	<thead>
	<tr>
		<th>Account ID</th>
		<th>Account Type</th>
		<th>Account Number</th>
		<th>User ID</th>
		<th>Description</th>
		<th>Balance</th>
		<th>Activated </th>
		<th>Action</th>
		<th>Action</th>
	</tr>
	</thead>
	<tbody>
		<%for(Account account: accounts){ %>
		
			<tr>
				<td><%=account.getAccountId()%></td>
				<td><%=account.getAccountType()%></td>
				<td><%=account.getAccountNumber()%></td>
				<td><%=account.getUserId()%></td>
				<td><%=account.getDescription()%></td>
				<td><%=account.getBalance()%></td>
				<td><%=account.isActive()%></td>
				<% if(!account.isActive()) {%>
				<td><a href="./activate-account?accountNumber=<%=account.getAccountNumber()%>">Activate</a></td>
				<%}else{ %>
					<td><a href="./deactivate-account?accountNumber=<%=account.getAccountNumber()%>">Deactivate</a></td>
				<%} %>
				<td><a href="./delete-account?accountNumber=<%=account.getAccountNumber()%>">Delete</a></td>
			</tr>
		
		<%} %>
		
		<tr>
		<td><a href="./logout" class="btn">Logout</a></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		</tr>
	</tbody>

</table>
		
	</div>

	</div>
	<script>
		function openCity(evt, cityName) {
			var i, tabcontent, tablinks;
			tabcontent = document.getElementsByClassName("tabcontent");
			for (i = 0; i < tabcontent.length; i++) {
				tabcontent[i].style.display = "none";
			}
			tablinks = document.getElementsByClassName("tablinks");
			for (i = 0; i < tablinks.length; i++) {
				tablinks[i].className = tablinks[i].className.replace(
						" active", "");
			}
			document.getElementById(cityName).style.display = "block";
			evt.currentTarget.className += " active";
		}
		
		
		
	</script>
</body>
</html>
<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="com.real.bank.model.TX"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>

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


<div class="row">

	<div class="col-sm-12">
		
		
		<div class="header">
			<h1 align="center" class="heading">MY BANK</h1>
			<hr>

		</div>

	
	
	</div>
	
	</div>
	
	<div class="row">
		
		
			
	<div class="col-sm-4">
		<table class="table">
		
			<tr>
				<th>Name:</th>
				<td>${requestScope.customer.firstName} ${requestScope.customer.lastName}</td>
				
			</tr>
			<tr>
				<th>Address:</th>
				<td>${requestScope.customer.street}, 
				${requestScope.customer.city}, 
				${requestScope.customer.state}, 
				${requestScope.customer.pin}</td>
			</tr>
			<tr>
				<th>Contact:</th>
				<td>${requestScope.customer.contact}</td>
			</tr>
			<tr>
				<th>Email:</th>
				<td>${requestScope.customer.email}</td>
				
			</tr>	
			
			<tr>
				<th>Account Number:</th>
				<td>${requestScope.customer.account.accountNumber}</td>
			</tr>
			<tr>
				<th>Account Type:</th>
				<td>${requestScope.customer.account.accountType}</td>
				
			</tr>	
			<tr>
				<th>Account Verified:</th>
				<td>${requestScope.customer.account.active}</td>
				
			</tr>
			<tr>
				<th>Account Balance:</th>
				<td>${requestScope.customer.account.balance}</td>
				
			</tr>
			
			
		
		</table>
		
		<a href="./logout">Logout</a>
	</div>
	<div class="col-sm-4">
	
		<div class="tab">
			<button class="tablinks" onclick="openTab(event, 'deposite')">Deposit</button>
			<button class="tablinks" onclick="openTab(event, 'withdraw')">Withdraw</button>
			<button class="tablinks" onclick="openTab(event, 'resetUserId')">Reset User Id</button>
			<button class="tablinks" onclick="openTab(event, 'resetPin')">Reset Pin</button>
		</div>
		
		<div id="deposite" class="tabcontent">

			<jsp:include page="deposit-amount.jsp"></jsp:include>
		</div>

		<div id="withdraw" class="tabcontent">
			<jsp:include page="withdraw-amount.jsp"></jsp:include>
		</div>
		
		<div id="resetUserId" class="tabcontent">
			<jsp:include page="reset-user-id.jsp"></jsp:include>
		</div>
		<div id="resetPin" class="tabcontent">
			<jsp:include page="reset-pin.jsp"></jsp:include>
		</div>
	
	</div>
	<div class="col-sm-4">
		<table class="table">

	<tr>
		<th>Transaction ID</th>
		<th>Description</th>
		<th>Time</th>
		<th>Amount</th>
		<th>Balance</th>
	</tr>
	<%for(TX tr: (List<TX>)request.getAttribute("transactions")) {%>
		<tr>
			<td><%=tr.getTransactionId()%></td>
			<td><%=tr.getDescription()%></td>
			<td><%=tr.getTimeStamp()%></td>
			<td><%=tr.getAmount()%></td>
			<td><%=tr.getBalance()%></td>
		
		</tr>
	<%} %>

</table>
	</div>
	
</div>





</body>
</html>
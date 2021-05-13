<!DOCTYPE html>
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

	<div>
		<div class="header">
			<h1 align="center" class="heading">MY BANK</h1>
			<hr>

		</div>

	</div>
	
	
	<div class="row">

	<div class="col-sm-4"></div>

	<div class="col-sm-4">
		<div class="tab">
			<button class="tablinks" onclick="openTab(event, 'login')">Login</button>
			<button class="tablinks" onclick="openTab(event, 'register')">Register</button>

		</div>

		<div id="login" class="tabcontent">

			<jsp:include page="login.jsp"></jsp:include>
		</div>

		<div id="register" class="tabcontent">
			<jsp:include page="register.jsp"></jsp:include>
		</div>
		
		
		</div>
		
		
		<div class="col-sm-4"></div>
		
	</div>

	
	<script>
	
	
	
		function openTab(evt, tabName) {
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
			document.getElementById(tabName).style.display = "block";
			evt.currentTarget.className += " active";
		}
		
		
		
	</script>
</body>
</html>


	<div >
	<h1 align="center" class="heading">Login</h1><hr>
		<span class="heading">${requestScope.userNotFound }</span>
		<span class="heading">${requestScope.loginStatus }</span>
	</div>
	
	<div align="center">
	<table class="table">
		<form method="post" action="./login">
			<tr>
				<td>User ID:</td>
				<td><input type="text" name="userId" required></td>

			</tr>

			<tr>
				<td>Password:</td>

				<td><input type="password" name="pin" required></td>
			</tr>

		
			<tr>
				<td>Admin Login</td>
				<td> Yes: <input type="radio" value="1" name="adminLogin">
				No: <input type="radio" value="0" name="adminLogin" checked></td>
			</tr>
			
			<tr>
				<td></td>
				<td><input type="submit" value="Login" class="btn"></td>
			</tr>
		</form>
	</table>


</div>




<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


	<div>
	<h1 align="center" class="heading">Register</h1><hr>
	
		<span class="heading">${requestScope.customerAdded}</span>
	</div>
	
	<div align="center">
	<table class="table">
		<form method="post" action="./register">
			
			<tr>
				<td>Account Type:</td>
				<td><select name="accountType">
					<option>..Select Account Type..</option>
					<option>Saving</option>
					<option>Current</option>
				</select><br><span class="error" id="fName" >${requestScope.accountTypeRequired}</span>
				</td>

			</tr>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="fName"value="${requestScope.fName}" required><br><span class="error" id="fName" value="${requestScope.fName}">${requestScope.fNameRequired}</span></td>

			</tr>

			<tr>
				<td>Last Name:</td>

				<td><input type="text" name="lName"  value="${requestScope.lName}" required><br><span class="error" id="lName">${requestScope.lNameRequired}</span></td>
			</tr>
			
			<tr>
				<td>Street:</td>
				<td><input type="text" name="street"  value="${requestScope.street}" required><br><span class="error" id="street">${requestScope.streetRequired}</span></td>

			</tr>

			<tr>
				<td>City:</td>

				<td><input type="text" name="city" value="${requestScope.city}" required> <br><span class="error" id="city">${requestScope.cityRequired}</span></td>
			</tr>

			<tr>
				<td>State:</td>
				<td><input type="text" name="state" value="${requestScope.state}" required><br><span class="error" id="state">${requestScope.stateRequired}</span></td>

			</tr>

			<tr>
				<td>Pin:</td>

				<td><input type="number" name="pinCode" value="${requestScope.pinCode}" required><br><span class="error" id="pinCode">${requestScope.pinCodeRequired}</span></td>
			</tr>
			
			<tr>
				<td>Contact:</td>
				<td><input type="number" name="contact" value="${requestScope.contact}" required><br><span class="error" id="contact">${requestScope.contactRequired}</span></td>

			</tr>

			<tr>
				<td>Email:</td>

				<td><input type="email" name="email" value="${requestScope.email}" required><br><span class="error" id="email">${requestScope.emailRequired}</span></td>
			</tr>
			
			<tr>
				<td>Begin Balance:</td>

				<td><input type="number" name="beginBalance" value="${requestScope.beginBalance}" required><br><span class="error" id="balance">${requestScope.beginBalanceRequired}${requestScope.lessBalance}</span></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" onSubmit="valid()" value="Login" class="btn" required></td>
			</tr>
		</form>
	</table>


</div>




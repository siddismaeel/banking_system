

<h1 align="center" class="heading">Reset User ID</h1><hr>

	<span class="heading"> <%= (request.getAttribute("updated") != null) ? request.getAttribute("updated"): "" %></span>
	<span class="heading"> <%= (request.getAttribute("pinStatus") != null) ? request.getAttribute("pinStatus"): "" %></span>

<table class="table">



<form method="post" action="./reset-user-id">
	<tr>
		<td>New User Id: </td>
		<td><input type="text" name="userId"></td>
	</tr>
	
	<tr>
		<td>Pin: </td>
		<td><input type="password" name="pin"></td>
	</tr>
	<tr>
		<td></td>	
		<td><input type="submit" value="Reset" class="btn"></td>
	</tr>
</form>
</table>
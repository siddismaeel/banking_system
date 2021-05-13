

<h1 align="center" class="heading">Reset Pin</h1><hr>

<span class="heading">${requestAttribute.pinUpdated}</span>
<table class="table">



	<form method="post" action="./reset-pin">
		<tr>
			<td>Old Pin:</td>
			<td><input type="password" name="oldPin"><br>
			<span>${requestScope.incorrectOldPin }</span> <span>${requestScope.oldPinRequired }</span></td>
		</tr>

		<tr>
			<td>New Pin:</td>
			<td><input type="password" name="newPin"><br>
			<span>${requestScope.newPinRequired}</span></td>
		</tr>

		<tr>
			<td>Re-enter Pin:</td>
			<td><input type="password" name="matchedPin"><br>
			<span>${requestScope.reEnterPinNotMatch}</span></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Reset" class="btn"></td>
		</tr>
	</form>
</table>
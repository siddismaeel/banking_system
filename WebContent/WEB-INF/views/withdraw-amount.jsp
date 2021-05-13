

<h1 align="center" class="heading">Withdraw Amount</h1><hr>
	
	<span class="heading">${requestScope.withdraw }</span>
	<span class="heading">${requestScope.NegativeBalanceException }</span>
	<span class="heading">${requestScope.OutOfBalanceException }</span>

<table class="table">



<form method="post" action="./withdraw-amount">
	<tr>
		<td>Amount: </td>
		<td><input type="number" name="amount" required></td>
	</tr>
	<tr>
		<td></td>	
		<td><input type="submit" value="Withdraw" class="btn"></td>
	</tr>
</form>
</table>
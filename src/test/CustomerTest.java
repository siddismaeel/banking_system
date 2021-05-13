package test;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.real.bank.dao.TransactionDAO;
import com.real.bank.dao.TransactionDAOImpl;
import com.real.bank.exceptions.InvalidEmailException;
import com.real.bank.exceptions.InvalidMobileNumberException;
import com.real.bank.exceptions.NegativeBalanceException;
import com.real.bank.exceptions.OutOfBalanceException;
import com.real.bank.exceptions.invalidUserIdException;
import com.real.bank.model.Account;
import com.real.bank.model.Admin;
import com.real.bank.model.Customer;
import com.real.bank.model.TX;
import com.real.bank.service.AccountService;
import com.real.bank.service.AccountServiceImpl;
import com.real.bank.service.AdminService;
import com.real.bank.service.AdminServiceImpl;
import com.real.bank.service.CustomerService;
import com.real.bank.service.CustomerServiceImpl;
import com.real.bank.service.TransactionService;
import com.real.bank.service.TransactionServiceImpl;
import com.real.bank.util.IDGenerator;
import com.real.bank.util.Validator;

@TestMethodOrder(OrderAnnotation.class)
class CustomerTest {
	CustomerService customerService = new CustomerServiceImpl();
	AccountService accountService = new AccountServiceImpl();
	static long accountNumber = 1107l;
	String userId = "rehan123";
	private int customerId = 101;
	private int accountId = 105;
	 
	 
	
	@Test
	@Order(2)
	public void testValues()
	{
		System.out.println("Account number " + accountNumber);
		System.out.println("userId " + userId);
		System.out.println("customerId " + customerId);
		System.out.println("accountId " + accountId);
	}
	@Test
	@Order(1)
	public void registerNewAccountTestSuccessful()
	{
		
		Customer customer = new Customer();
		customer.setCutomerId(customerId);
		customer.setFirstName("Rehan");
		customer.setLastName("Ansari");
		customer.setStreet("Gama1");
		customer.setCity("Noida");
		customer.setState("UP");
		customer.setPin(209739);
		
		try {
			customer.setContact("9876543212");
			customer.setEmail("sfhkgml@gmail.com");
		} catch (InvalidMobileNumberException e) {
			e.printStackTrace();
		} catch (InvalidEmailException e) {
			e.printStackTrace();
		}
		
		Account account = new Account();
		account.setAccountId(accountId);
		account.setAccountNumber(accountNumber);
		account.setAccountType("Saving");
		account.setActive(false);
		account.setBeginBalance(7800);
		account.setBalance(account.getBeginBalance());
		account.setPin(IDGenerator.generatePin());
		account.setTimeStamp(Calendar.getInstance().getTime());
		
		if(account.getAccountType().equalsIgnoreCase("saving"))
		{ 
			account.setDescription("Saving Account");
			account.setTransactionAmountLimit(10000);
			account.setTransactionLimit(10);
		}
		else
		{
			account.setDescription("Current Account");
			account.setTransactionAmountLimit(5000);
			account.setTransactionLimit(25);
		}
		  
		account.setUserId(userId);
		
		customer.setAccount(account);
		
		accountNumber = customer.getAccount().getAccountNumber();
		userId = customer.getAccount().getUserId();
		customerId = customer.getCutomerId();
		accountId = customer.getAccount().getAccountId();
		
		int customerAdded = customerService.addCustomer(customer);
		System.out.println(customerAdded);
		assertEquals(1, customerAdded);
		
	}
	

	//Test for customer info by id
	@Test
	@Order(3)
	public void getCustomerInfoByCustomerIdTest()
	{
		Customer customer = customerService.getCustomerInfo(customerId );
		System.out.println("Customer object " + customer);
		assertNotNull("Customer object should not be null", customer);
		if(customer != null)
			assertNotNull("Account object should not be null", customer.getAccount());

	} 
	
	//Test for customer info by id
		@Test
		@Order(4) 
		public void getCustomerInfoByAccountNumberTest()
		{
			Customer customer = customerService.getCustomerInfo(accountNumber);
			
			assertNotNull("Customer object should not be null", customer);
			if(customer != null)
				assertNotNull("Account object should not be null", customer.getAccount());
		}
		 
		@Test 
		@Order(5)
		public void getCustomerInfoByUserIdTest()
		{
			Customer customer = null;
			try {
				customer = customerService.getCustomerInfo(userId);
			} catch (invalidUserIdException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			assertNotNull("Customer object should not be null", customer);
			if(customer != null)
				assertNotNull("Account object should not be null", customer.getAccount());
	}
		
	@Test
	@Order(6)
	public void resetUserIdTest()
	{
		Customer customer = new Customer();
		Account account = new Account();
		account.setAccountId(accountId);
		customer.setAccount(account);
		
		int resetUserId = customerService.resetUserId(customer, userId);
		assertEquals(1, resetUserId);
	}
	
	@Test
	@Order(7)
	public void resetPinTest()
	{
		Account account = new Account();
		
		
		account.setAccountId(accountId);
		account.setPin(12345);
		int resetPin = accountService.resetPin(account);
		assertEquals(1, resetPin);
	}
	
	@Test
	@Order(8)
	public void getAllAccountTest()
	{
		List<Account> accounts = accountService.getAllAccounts();
		 
		assertNotEquals(0, accounts.size());
	}
	
	/*
	 Testin admin functionalities
	 
	*/
	
	
	
	@Test
	@Order(9)
	void activateAccountSuccessful() {

		AccountService accountService = new AccountServiceImpl();
		int accountActivated = accountService.activateAccount(accountNumber);
		
		assertEquals(1, accountActivated);

	}
	
	@Test
	@Order(10)
	void activateAccountNotSuccessful() {

		AccountService accountService = new AccountServiceImpl();
		int accountActivated = accountService.activateAccount(0);
		
		assertEquals(0, accountActivated);

	}
	
	@Test
	@Order(11)
	void deactivateAccountSuccessful() {

		AccountService accountService = new AccountServiceImpl();
		int accountActivated = accountService.deactivateAccount(accountNumber);
		 
		assertEquals(1, accountActivated);

	}
	
	@Test
	@Order(12)
	void deactivateAccountNotSuccessful() {

		AccountService accountService = new AccountServiceImpl();
		int accountActivated = accountService.deactivateAccount(0);
		
		assertEquals(0, accountActivated);

	}
	
	@Test 
	@AfterAll
	 static void deleteAcccountSuccessful() {
	  
	  AccountService accountService = new AccountServiceImpl(); 
	  int deletedAccount = accountService.deleteAccount(accountNumber);
	   
	  assertEquals(1, deletedAccount);
	    
	  }

	@Test 
	@Order(14)
	void deleteAcccountNotSuccessful() {

		AccountService accountService = new AccountServiceImpl();
		int deletedAccount = accountService.deleteAccount(10000);
		
		assertEquals(0, deletedAccount);

	}
	
	@Test
	public void getAdminByIdTestSucessful()
	{
		AdminService admintService = new AdminServiceImpl();
		
		Admin admin = admintService.getAdmin(101);
		assertNotEquals(null, admin);
	}
	
	@Test
	public void getAdminTestNotSucessful()
	{
		AdminService admintService = new AdminServiceImpl();
		
		Admin admin = admintService.getAdmin(100);
		assertEquals(null, admin);
	}
	@Test
	public void getAdminByUserIdTestSucessful()
	{
		AdminService admintService = new AdminServiceImpl();
		
		Admin admin = admintService.getAdmin("kashan123");
		assertNotEquals(null, admin);
	}
	
	@Test
	public void getAdminByUserIdTestNotSucessful()
	{
		AdminService admintService = new AdminServiceImpl();
		
		Admin admin = admintService.getAdmin("kashan1234");
		assertEquals(null, admin);
	}
	/*
		Utility test
	*/
	
	@Test
	public void generateUserIdTest()
	{
		String userId = IDGenerator.generateUserId();
		boolean isValid = Validator.isValidUserId(userId);
		
		assertEquals(true, isValid);
	}
	
	@Test
	public void mobileNumberTestSuccess()
	{
		String contact = "9876543214";
		boolean valid = Validator.isValidMobile(contact);
		
		assertEquals(true, valid);
	}
	
	@Test
	public void mobileNumberTestNotSuccess()
	{
		String contact = "1876543214";
		boolean valid = Validator.isValidMobile(contact);
		
		assertEquals(false, valid);
	}
	
	@Test
	public void accountNumberTestSuccess()
	{
		long accountNumber = IDGenerator.getAccountNumber();
		 
		assertNotEquals(0, accountNumber);
	}
	
	@Test
	public void customerIdTestSuccess()
	{
		int customerId = IDGenerator.getCustomerId();
		
		assertNotEquals(0, customerId);
	}
	
	@Test
	public void accountIdTestSuccess()
	{
		int customerId = IDGenerator.getAccountId();
		
		assertNotEquals(0, customerId);
	}
	@Test
	public void TransactionIdTestSuccess()
	{
		long customerId = IDGenerator.generateTransactionId();
		
		assertNotEquals(0, customerId);
	}
	
	/*
	 * transaction test
	*/
	
	@Test
	public void deposite()
	{
		Customer customer = customerService.getCustomerInfo(customerId);
		TransactionService ts = new TransactionServiceImpl();
		
		TX tx = new TX();
		tx.setAccount(customer.getAccount());
		tx.setAmount(1990);
		tx.setBalance(customer.getAccount().getBalance());
		tx.setTransactionId(IDGenerator.generateTransactionId());
		tx.setDescription("Deposit");
		tx.setTimeStamp(Calendar.getInstance().getTime());
		try {
			ts.deposite(tx);
		} catch (NegativeBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void withdraw()
	{
		Customer customer = customerService.getCustomerInfo(customerId);
		TransactionService ts = new TransactionServiceImpl();
		
		TX tx = new TX();
		tx.setAccount(customer.getAccount());
		tx.setAmount(1990);
		tx.setBalance(customer.getAccount().getBalance());
		tx.setTransactionId(IDGenerator.generateTransactionId());
		tx.setDescription("Withdraw Amount");
		tx.setTimeStamp(Calendar.getInstance().getTime());
		
		try {
			ts.withdraw(tx);
		} catch (NegativeBalanceException | OutOfBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getTransactionsTest()
	{
		TransactionService service = new TransactionServiceImpl();
		List<TX> transactions = service.getTransactions(5);
		
		assertNotEquals(0, transactions.size());
	}
	
	@Test
	public void getTransactionsOfLast6MonthsTest()
	{
		TransactionDAO dao = new TransactionDAOImpl();
		List<TX> transactions = dao.getTransactionsOfLast6Months(3);
		
		assertNotEquals(0, transactions.size());
	}
	
	@Test
	public void getTransactionDetailsTest()
	{
		TransactionService service = new TransactionServiceImpl();
		TX transactionDetails = service.getTransactionDetails(64740421400l);
		
		assertNotNull(null, transactionDetails);
	}
	
	@Test
	public void getTransactionDetailsByCustomerTest()
	{
		TransactionService service = new TransactionServiceImpl();
		Customer customer = customerService.getCustomerInfo(3);
		TX transactionDetails = service.getTransactionDetails(customer,64740421400l);
		 
		assertNotNull(null, transactionDetails);
	}
	
}

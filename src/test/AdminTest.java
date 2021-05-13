package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.real.bank.service.AccountService;
import com.real.bank.service.AccountServiceImpl;

class AdminTest {

	 
	  @Test 
	  void deleteAcccountSuccessful() {
	  
	  AccountService accountService = new AccountServiceImpl(); 
	  int deletedAccount = accountService.deleteAccount(1000);
	   
	  assertEquals(1, deletedAccount);
	    
	  }
	 
	
	@Test
	void deleteAcccountNotSuccessful() {

		AccountService accountService = new AccountServiceImpl();
		int deletedAccount = accountService.deleteAccount(1000);
		
		assertEquals(0, deletedAccount);
 
	}
	
	@Test
	void activateAccountSuccessful() {

		AccountService accountService = new AccountServiceImpl();
		int accountActivated = accountService.activateAccount(1006);
		
		assertEquals(1, accountActivated);

	}
	
	@Test
	void activateAccountNotSuccessful() {

		AccountService accountService = new AccountServiceImpl();
		int accountActivated = accountService.activateAccount(1006);
		
		assertEquals(0, accountActivated);

	}
	
	@Test
	void deactivateAccountSuccessful() {

		AccountService accountService = new AccountServiceImpl();
		int accountActivated = accountService.deactivateAccount(1006);
		
		assertEquals(1, accountActivated);

	}
	
	@Test
	void deactivateAccountNotSuccessful() {

		AccountService accountService = new AccountServiceImpl();
		int accountActivated = accountService.deactivateAccount(1006);
		
		assertEquals(0, accountActivated);

	}
	
	

}
